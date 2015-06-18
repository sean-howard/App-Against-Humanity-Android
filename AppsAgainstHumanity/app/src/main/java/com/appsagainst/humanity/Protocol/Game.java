package com.appsagainst.humanity.Protocol;

import com.appsagainst.humanity.LocalMultiplayer.GameClient;
import com.appsagainst.humanity.LocalMultiplayer.GameServer;
import com.appsagainst.humanity.POJO.WhiteCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public class Game implements Serializable{

    public GameServer gameServer;
    public GameClient gameClient;

    public boolean isHost = false;
    public boolean isBlackCardPlayer = false;

    public int currentPlayerNumber = 0;

    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Match> match;
    public ArrayList<WhiteCard> currentWhiteCards = new ArrayList<>();
    public HashMap<String, WhiteCard> submittedWhiteCards = new HashMap<>();
    public ArrayList<Integer> blackCardIDs;

}
