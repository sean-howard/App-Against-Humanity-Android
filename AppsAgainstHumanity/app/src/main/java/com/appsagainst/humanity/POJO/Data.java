package com.appsagainst.humanity.POJO;

import com.appsagainst.humanity.Protocol.Game;

import java.util.ArrayList;

/**
 * Created by User on 17/06/2015.
 */
public class Data {

    public String playerName;
    public String uniqueID;
    public ArrayList<Integer> whiteCardIDs;
    public int blackCardUniqueID = -1;
    public int winnerUniqueID = -1;
    public int blackCardID = -1;
    public int whiteCardID = -1;
    public Game game;
}
