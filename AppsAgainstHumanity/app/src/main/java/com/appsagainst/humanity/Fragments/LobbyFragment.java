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

import com.appsagainst.humanity.Events.PlayerJoinedLobby;
import com.appsagainst.humanity.Events.StartGameSession;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.LocalMultiplayer.GameClient;
import com.appsagainst.humanity.LocalMultiplayer.GameServer;
import com.appsagainst.humanity.LocalMultiplayer.NsdHelper;
import com.appsagainst.humanity.Protocol.Game;
import com.appsagainst.humanity.Protocol.Player;
import com.appsagainst.humanity.R;
import com.squareup.otto.Subscribe;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LobbyFragment extends Fragment {

    String TAG = "LobbyFragment";

    @InjectView(R.id.listView)
    ListView listView;

    NsdHelper mNsdHelper;
    ArrayAdapter<Player> adapter;

    Game game = new Game();

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

        Bundle b = getArguments();
        boolean isHost = b.getBoolean("isHost", false);

        if(isHost){
            game.gameServer = new GameServer();

            mNsdHelper = new NsdHelper(getActivity());
            mNsdHelper.initializeNsd();
            mNsdHelper.registerService(game.gameServer.getLocalPort());

            game.gameClient = new GameClient("http://127.0.0.1", game.gameServer.getLocalPort());
            game.isHost = true;
        } else {
            game.gameClient = new GameClient((InetAddress)b.getSerializable("host"), b.getInt("port"));
        }

        adapter = new ArrayAdapter<Player>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, game.players);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);

        if(mNsdHelper != null){
            mNsdHelper.tearDown();
        }

        super.onDestroyView();

    }

    @OnClick(R.id.startGame)
    public void startGame() {
        game.gameClient.startSession();
    }

    @Subscribe
    public void clientAdded(PlayerJoinedLobby ca){
        boolean alreadyAdded = false;

        for(Player p: game.players){
            if(p.uniqueID.equals(ca.uniqueID)){
                alreadyAdded = true;
            }
        }

        if(!alreadyAdded){
            Player p = new Player(ca.playerName, ca.uniqueID);
            game.players.add(p);
            adapter.notifyDataSetChanged();

            game.gameClient.informPlayersOfName();
        }
    }

    @Subscribe
    public void startGameSession(StartGameSession ca){
        game.players = sortGamePlayersByName(game.players);

        GameFragment gameFragment =  new GameFragment();
        gameFragment.setData(game);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.holder, gameFragment).addToBackStack("1").commit();
    }

    public ArrayList<Player> sortGamePlayersByName(ArrayList<Player> players){
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player object1, Player object2) {
                return object1.name.compareTo(object2.name);
            }
        } );

        return players;
    }


}