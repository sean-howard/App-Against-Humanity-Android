package com.appsagainst.humanity.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.appsagainst.humanity.Events.AllCardsSubmitted;
import com.appsagainst.humanity.Events.GotInitialWhiteCardIDs;
import com.appsagainst.humanity.Events.SelectBlackCardPlayer;
import com.appsagainst.humanity.Events.SubmitWhiteCardToServer;
import com.appsagainst.humanity.Events.WinnerChosen;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.Helpers.DialogHelper;
import com.appsagainst.humanity.Managers.DatabaseManager;
import com.appsagainst.humanity.POJO.BlackCard;
import com.appsagainst.humanity.POJO.WhiteCard;
import com.appsagainst.humanity.Protocol.Game;
import com.appsagainst.humanity.Protocol.Player;
import com.appsagainst.humanity.R;
import com.squareup.otto.Subscribe;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GameFragment extends Fragment {

    private final String TAG = "GameFragment";

    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.blackText)
    TextView blackText;

    private ArrayAdapter<WhiteCard> whiteCardAdapter;

    Game game;

    boolean busIsRegistered = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.inject(this, view);
        return view;

    }

    public void setData(Game game){
        this.game = game;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!busIsRegistered){
            Global.getInstance().bus.register(this);
            busIsRegistered = true;
        }

        if(game.isHost){
            game.gameClient.selectBlackCardPlayer(game.players.get(game.currentPlayerNumber).uniqueID, DatabaseManager.getRandomBlackCard(getActivity()).getId());
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

        if(selectBlackCardPlayer.uniqueID.equals(Global.getInstance().uniqueID)){
            Log.d(TAG, "IS BLACK PLAYER");

            game.isBlackCardPlayer = true;

            if(game.currentWhiteCardHand.size() == 0){
                distributeInitialWhiteCards();
            }

            if(whiteCardAdapter != null){
                whiteCardAdapter = new ArrayAdapter<WhiteCard>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<WhiteCard>());
                listView.setAdapter(whiteCardAdapter);
            }
        } else {
            Log.d(TAG, "IS NOT BLACK PLAYER");

            game.isBlackCardPlayer = false;
            blackText.setText("");

            topUpCard();
            displayWhiteCards();
        }

        displayBlackCard(getBlackCard(selectBlackCardPlayer.blackCardID));
    }

    @Subscribe
    public void whiteCardSubmitted(SubmitWhiteCardToServer submitWhiteCardToServer){
        if(game.isBlackCardPlayer){
            game.submittedWhiteCards.put(submitWhiteCardToServer.uniqueID, DatabaseManager.getWhiteCardByID(getActivity(), submitWhiteCardToServer.whitecardID));

            if(game.submittedWhiteCards.size() == game.players.size()-1){
                game.gameClient.allCardsSubmitted();
            }
        }
    }

    @Subscribe
    public void allCardsSubmitted(AllCardsSubmitted allCardsSubmitted){
        DialogHelper.getInstance().hideProgressDialog();

        if(game.isBlackCardPlayer){
            //TODO Fix this so that it works for arraylists and not just single cards

            List<WhiteCard> cards = new ArrayList<>(game.submittedWhiteCards.values());

            whiteCardAdapter = new ArrayAdapter<WhiteCard>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, cards);
            listView.setAdapter(whiteCardAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    DialogHelper.displayConfirmWinnerDialog(getActivity(), new DialogHelper.DialogCallback() {
                        @Override
                        public void positiveClick() {
                            game.gameClient.chooseWinner(getKeyByValue(game.submittedWhiteCards, whiteCardAdapter.getItem(position)), whiteCardAdapter.getItem(position).getId());
                        }

                        @Override
                        public void negativeClick() {}
                    });
                }
            });
        } else {
            DialogHelper.getInstance().displayProgressDialog(getActivity(), "Waiting for Winner", "Waiting for winning cards to be chosen, bitch");
        }
    }

    public <String, WhiteCard> String getKeyByValue(Map<String, WhiteCard> map, WhiteCard value) {
        for (Map.Entry<String, WhiteCard> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void topUpCard(){
        if(game.currentWhiteCardHand.size() != 0 && game.currentWhiteCardHand.size() < Global.MAX_CARDS){
            game.currentWhiteCardHand.add(DatabaseManager.getRandomWhiteCard(getActivity()));
            topUpCard();
        }
    }

    @Subscribe
    public void winnerChosen(WinnerChosen winnerChosen){
        DialogHelper.getInstance().hideProgressDialog();

        game.currentPlayerNumber++;

        if(game.currentPlayerNumber >= game.players.size()){
            game.currentPlayerNumber = 0;
        }

        if(game.isBlackCardPlayer){
            game.gameClient.selectBlackCardPlayer(game.players.get(game.currentPlayerNumber).uniqueID, DatabaseManager.getRandomBlackCard(getActivity()).getId());
        } else {
            Player winningPlayer = null;
            for(Player player: game.players){
                if(winnerChosen.uniqueID.equals(player.uniqueID)){
                    winningPlayer = player;
                }
            }

            if(winnerChosen.uniqueID.equals(Global.getInstance().uniqueID)){
                DialogHelper.displayWinnerDialog(getActivity(), true, winningPlayer.name, null);
            } else {
                DialogHelper.displayWinnerDialog(getActivity(), false, winningPlayer.name, null);
            }
        }
    }

    @Subscribe
    public void gotInitialWhiteCards(GotInitialWhiteCardIDs gotInitialWhiteCardIDs){
        game.distributedWhiteCards = gotInitialWhiteCardIDs.cardIDs;
        game.myDitributedWhiteCards = game.distributedWhiteCards.get(Global.getInstance().uniqueID);
        Collections.shuffle(game.myDitributedWhiteCards, new Random(System.nanoTime()));

        Log.d(TAG, "GOT INITIAL WHITE CARDS");
        for(int i = 0; i<Global.MAX_CARDS; i++){
            game.currentWhiteCardHand.add(DatabaseManager.getWhiteCardByID(getActivity(), game.myDitributedWhiteCards.get(i)));
        }

        if(!game.isBlackCardPlayer){
            displayWhiteCards();
        }
    }

    public BlackCard getBlackCard(int blackCardID){
        Log.d(TAG, "GETTING BLACK CARD");
        return DatabaseManager.getBlackCardByID(getActivity(), blackCardID);
    }

    public void distributeInitialWhiteCards(){
        try {
            int numberOfPlayers = game.players.size();
            int numberOfIDs = DatabaseManager.getNumberOfWhiteCards(getActivity());
            int numberOfCardsPerPlayer = numberOfIDs/numberOfPlayers;

            ArrayList<Integer> allCardIDs = DatabaseManager.getAllWhiteCardIDs(getActivity());

            HashMap<String, ArrayList<Integer>> cardIDs = new HashMap<>();

            for(int i = 0; i<numberOfPlayers; i++){
                cardIDs.put(game.players.get(i).uniqueID, (ArrayList<Integer>) allCardIDs.subList(i * numberOfCardsPerPlayer, (i + 1) * numberOfCardsPerPlayer));
            }

            game.gameClient.distributeWhiteCards(cardIDs);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void displayBlackCard(BlackCard blackCard){
        blackText.setText(Jsoup.parse(blackCard.getText()).text());
    }

    public void displayWhiteCards(){
        whiteCardAdapter = new ArrayAdapter<WhiteCard>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, game.currentWhiteCardHand);
        listView.setAdapter(whiteCardAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                game.gameClient.selectCard(whiteCardAdapter.getItem(position).getId());

                game.currentWhiteCardHand.remove(position);
                whiteCardAdapter.notifyDataSetChanged();

                DialogHelper.getInstance().displayProgressDialog(getActivity(), "Card Submitted", "Please wait for everybody else...");
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(busIsRegistered){
            Global.getInstance().bus.unregister(this);
            busIsRegistered = false;
        }
    }

}