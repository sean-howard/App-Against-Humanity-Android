package com.appsagainst.humanity.Fragments;

/**
 * Created by User on 09/05/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.appsagainst.humanity.Events.GotInitialWhiteCardIDs;
import com.appsagainst.humanity.Events.SelectBlackCardPlayer;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.Managers.DatabaseManager;
import com.appsagainst.humanity.POJO.BlackCard;
import com.appsagainst.humanity.POJO.WhiteCard;
import com.appsagainst.humanity.Protocol.Game;
import com.appsagainst.humanity.R;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GameFragment extends Fragment {

    private final String TAG = "GameFragment";

    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.blackText)
    TextView blackText;

    private ArrayAdapter<WhiteCard> whiteCardAdapter;

    ArrayList<String> al;
    Game game;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Global.getInstance().bus.register(this);

        Bundle b = getArguments();
        game = (Game)b.getSerializable("game");

        if(game.isHost){
            game.gameClient.selectBlackCardPlayer(game.players.get(game.currentPlayerNumber).uniqueID);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Subscribe
    public void blackCardPlayerSelected(SelectBlackCardPlayer selectBlackCardPlayer){

        Log.d(TAG, "BLACK CARD PLAYER SELECTED");
        Log.d(TAG, selectBlackCardPlayer.uniqueID + " ::: " + Global.getInstance().uniqueID);

        try {
            if(selectBlackCardPlayer.uniqueID.equals(Global.getInstance().uniqueID)){
                Log.d(TAG, "IS BLACK PLAYER");

                game.isBlackCardPlayer = true;

                distributeInitialWhiteCards();
                getBlackCard();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Subscribe
    public void gotInitialWhiteCards(GotInitialWhiteCardIDs gotInitialWhiteCardIDs){
        Log.d(TAG, "GOT INITIAL WHITE CARDS");

        try{
            for(int i = 0; i<gotInitialWhiteCardIDs.cardIDs.size(); i++){
                Log.d(TAG,gotInitialWhiteCardIDs.cardIDs+"");

            }

            for(int i = 0; i<gotInitialWhiteCardIDs.cardIDs.size(); i++){
                game.currentWhiteCards.add(DatabaseManager.getWhiteCardByID(getActivity(), gotInitialWhiteCardIDs.cardIDs.get(i)));
            }

            if(!game.isBlackCardPlayer){
                displayWhiteCards();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getBlackCard(){
        Log.d(TAG, "GETTING BLACK CARD");

        DatabaseManager.getRandomBlackCard(getActivity());
        displayBlackCard(DatabaseManager.getRandomBlackCard(getActivity()));
    }

    public void distributeInitialWhiteCards(){
        try {
            int numberOfPlayers = game.players.size();

            HashMap<String, ArrayList<Integer>> cardIDs = new HashMap<>();

            for (int i = 0; i < numberOfPlayers; i++) {
                cardIDs.put(game.players.get(i).uniqueID, DatabaseManager.getSetOfRandomWhiteCardsIDs(getActivity(), 5));
            }

            game.gameClient.distributeWhiteCards(cardIDs);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void displayBlackCard(BlackCard blackCard){
        blackText.setText(blackCard.getText());
    }

    public void displayWhiteCards(){
        whiteCardAdapter = new ArrayAdapter<WhiteCard>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, game.currentWhiteCards);
        listView.setAdapter(whiteCardAdapter);
    }
}