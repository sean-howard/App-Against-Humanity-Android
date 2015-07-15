package com.appsagainst.humanity.Protocol;

import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.LocalMultiplayer.GameClient;
import com.appsagainst.humanity.LocalMultiplayer.GameServer;
import com.appsagainst.humanity.POJO.WhiteCard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public class Game{

    public GameServer gameServer;
    public GameClient gameClient;

    public boolean isHost = false;
    public boolean isBlackCardPlayer = false;

    public int currentPlayerNumber = 0;
    public int currentWhiteCard = Global.MAX_CARDS;

    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<WhiteCard> currentWhiteCardHand = new ArrayList<>();

    public HashMap<String, WhiteCard> submittedWhiteCards = new HashMap<>();

    public HashMap<String, ArrayList<Integer>> distributedWhiteCards = new HashMap<>();
    public ArrayList<Integer> myDitributedWhiteCards = new ArrayList<>();
}
