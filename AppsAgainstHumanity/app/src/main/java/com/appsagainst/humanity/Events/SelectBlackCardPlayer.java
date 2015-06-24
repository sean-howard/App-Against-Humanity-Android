package com.appsagainst.humanity.Events;

/**
 * Created by Chris on 18/06/2015.
 */
public class SelectBlackCardPlayer {

    public String uniqueID;
    public int blackCardID;

    public SelectBlackCardPlayer(String uniqueID, int blackCardID){
        this.uniqueID = uniqueID;
        this.blackCardID = blackCardID;
    }
}
