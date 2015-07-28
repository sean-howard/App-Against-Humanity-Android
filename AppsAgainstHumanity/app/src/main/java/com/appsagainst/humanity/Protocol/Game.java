package com.appsagainst.humanity.Protocol;

import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.LocalMultiplayer.GameClient;
import com.appsagainst.humanity.LocalMultiplayer.GameServer;
import com.appsagainst.humanity.POJO.Submission;
import com.appsagainst.humanity.POJO.WhiteCard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public class Game{

    private GameServer gameServer;
    private GameClient gameClient;

    private boolean isHost = false;
    private boolean isBlackCardPlayer = false;

    private int currentBlackPlayerNumber = 0;
    private int currentWhiteCard = Global.MAX_CARDS;

    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<WhiteCard> currentWhiteCardHand = new ArrayList<>();

    public HashMap<String, Submission> submittedWhiteCards = new HashMap<>();

    public HashMap<String, ArrayList<Integer>> distributedWhiteCards = new HashMap<>();
    public ArrayList<Integer> myDitributedWhiteCards = new ArrayList<>();

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public GameServer getGameServer() {
        return gameServer;
    }

    public void setGameServer(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }

    public boolean isBlackCardPlayer() {
        return isBlackCardPlayer;
    }

    public void setIsBlackCardPlayer(boolean isBlackCardPlayer) {
        this.isBlackCardPlayer = isBlackCardPlayer;
    }

    public int getCurrentBlackPlayerNumber() {
        return currentBlackPlayerNumber;
    }

    public void setCurrentBlackPlayerNumber(int currentBlackPlayerNumber) {
        this.currentBlackPlayerNumber = currentBlackPlayerNumber;
    }

    public void incrementCurrentPlayerNumber() {
        this.currentBlackPlayerNumber++;
    }

    public int getCurrentWhiteCard() {
        return currentWhiteCard;
    }

    public void setCurrentWhiteCard(int currentWhiteCard) {
        this.currentWhiteCard = currentWhiteCard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<WhiteCard> getCurrentWhiteCardHand() {
        return currentWhiteCardHand;
    }

    public void setCurrentWhiteCardHand(ArrayList<WhiteCard> currentWhiteCardHand) {
        this.currentWhiteCardHand = currentWhiteCardHand;
    }

    public HashMap<String, Submission> getSubmittedWhiteCards() {
        return submittedWhiteCards;
    }

    public void setSubmittedWhiteCards(HashMap<String, Submission> submittedWhiteCards) {
        this.submittedWhiteCards = submittedWhiteCards;
    }

    public HashMap<String, ArrayList<Integer>> getDistributedWhiteCards() {
        return distributedWhiteCards;
    }

    public void setDistributedWhiteCards(HashMap<String, ArrayList<Integer>> distributedWhiteCards) {
        this.distributedWhiteCards = distributedWhiteCards;
    }

    public ArrayList<Integer> getMyDitributedWhiteCards() {
        return myDitributedWhiteCards;
    }

    public void setMyDitributedWhiteCards(ArrayList<Integer> myDitributedWhiteCards) {
        this.myDitributedWhiteCards = myDitributedWhiteCards;
    }
}
