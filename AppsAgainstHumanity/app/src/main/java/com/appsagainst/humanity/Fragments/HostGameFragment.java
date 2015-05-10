package com.appsagainst.humanity.Fragments;

/**
 * Created by User on 09/05/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appsagainst.humanity.Events.ClientAdded;
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

public class HostGameFragment extends Fragment {

    @InjectView(R.id.listView)
    ListView listView;

    NsdHelper mNsdHelper;
    GameServer gameServer;
    GameClient gameClient;

    List<String> clients = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_host_game, container, false);

        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Global.getInstance().bus.register(this);

        gameServer = new GameServer();

        mNsdHelper = new NsdHelper(getActivity());
        mNsdHelper.initializeNsd();
        mNsdHelper.registerService(gameServer.getLocalPort());

        gameClient = new GameClient("http://127.0.0.1", gameServer.getLocalPort());

        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, clients);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        mNsdHelper.tearDown();
        super.onDestroyView();

    }

    @Subscribe
    public void clientAdded(ClientAdded ca){
        clients.add(ca.clientName);
        adapter.notifyDataSetChanged();
    }

}