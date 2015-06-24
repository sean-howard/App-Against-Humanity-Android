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
    public int blackCardID= -1;
    public ArrayList<Integer> whiteCardIDs = new ArrayList<>();
    public HashMap<String, ArrayList<Integer>> initialCards;
}
