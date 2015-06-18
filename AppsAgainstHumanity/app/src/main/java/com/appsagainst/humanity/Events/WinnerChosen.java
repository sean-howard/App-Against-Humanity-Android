package com.appsagainst.humanity.Events;

/**
 * Created by Chris on 18/06/2015.
 */
public class WinnerChosen {

    public int whitecardID;
    public String uniqueID;

    public WinnerChosen(String uniqueID, int whitecardID){
        this.whitecardID = whitecardID;
        this.uniqueID = uniqueID;
    }
}
