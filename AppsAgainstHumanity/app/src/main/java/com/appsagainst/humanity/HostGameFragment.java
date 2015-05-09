package com.appsagainst.humanity;

/**
 * Created by User on 09/05/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.appsagainst.humanity.Events.ClientAdded;
import com.appsagainst.humanity.Events.ServerSetupEvent;
import com.appsagainst.humanity.LocalMultiplayer.GameServer;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HostGameFragment extends Fragment {

    @InjectView(R.id.listView)
    ListView listView;

    NsdHelper mNsdHelper;
    GameServer gameServer;

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
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        mNsdHelper.tearDown();
        super.onDestroyView();

    }

    @Subscribe
    public void clientAdded(ClientAdded ca){
        Toast.makeText(getActivity(), ca.clientName, Toast.LENGTH_SHORT);


    }
}