package com.appsagainst.humanity.POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 17/06/2015.
 */
public class Data implements Serializable{

    public String playerName;
    public String uniqueID;
    public ArrayList<Integer> whiteCardIDs;
    public String blackCardPlayerUniqueID;
    public int winnerUniqueID = -1;
    public int blackCardID = -1;
    public int whiteCardID = -1;
    public HashMap<String, ArrayList<Integer>> initialCards;
}
