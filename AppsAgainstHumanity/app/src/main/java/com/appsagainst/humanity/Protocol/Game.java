package com.appsagainst.humanity.Protocol;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public class Game implements Serializable{

    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Match> match;
    public ArrayList<Integer> currentWhiteCards;
    public ArrayList<Integer> blackCardIDs;


    public Game(){

    }
}
