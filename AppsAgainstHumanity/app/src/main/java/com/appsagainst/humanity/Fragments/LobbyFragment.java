package com.appsagainst.humanity.Fragments;

/**
 * Created by User on 09/05/2015.
 */

import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appsagainst.humanity.Events.JoiningLobby;
import com.appsagainst.humanity.Events.ServerFoundEvent;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.LocalMultiplayer.GameClient;
import com.appsagainst.humanity.LocalMultiplayer.GameServer;
import com.appsagainst.humanity.LocalMultiplayer.NsdHelper;
import com.appsagainst.humanity.R;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LobbyFragment extends Fragment {

    String TAG = "LobbyFragment";

    @InjectView(R.id.listView)
    ListView listView;

    NsdHelper mNsdHelper;
    GameServer gameServer;
    GameClient gameClient;

    List<String> clients = new ArrayList<>();
    List<String> hosts = new ArrayList<>();
    List<NsdServiceInfo> hostsInfo = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lobby, container, false);

        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Global.getInstance().bus.register(this);

        Bundle b = getArguments();
        boolean isHost = b.getBoolean("isHost", false);

        if(isHost){
            gameServer = new GameServer();

            mNsdHelper = new NsdHelper(getActivity());
            mNsdHelper.initializeNsd();
            mNsdHelper.registerService(gameServer.getLocalPort());

            gameClient = new GameClient("http://127.0.0.1", gameServer.getLocalPort());
            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, clients);
            listView.setAdapter(adapter);
        } else {
            mNsdHelper = new NsdHelper(getActivity());
            mNsdHelper.initializeNsd();

            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, hosts);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    clickConnect(hostsInfo.get(position));
                }
            });

            mNsdHelper.discoverServices();
        }
    }

    public void clickConnect(NsdServiceInfo service) {
        if (service != null) {
            Log.d(TAG, "Connecting.");
            gameClient = new GameClient(service.getHost(), service.getPort());
        } else {
            Log.d(TAG, "No service to connect to!");
        }
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        mNsdHelper.tearDown();
        super.onDestroyView();

    }

    @Subscribe
    public void clientAdded(JoiningLobby ca){
        clients.add(ca.playerName);
        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void serverFound(ServerFoundEvent sfe){
        hosts.add(sfe.serverName);
        hostsInfo.add(sfe.serviceInfo);
        adapter.notifyDataSetChanged();
    }

}