package com.appsagainst.humanity.Events;

import java.util.ArrayList;

/**
 * Created by Chris on 18/06/2015.
 */
public class WinnerChosen {

    public ArrayList<Integer> whitecardIDs;
    public String uniqueID;

    public WinnerChosen(String uniqueID, ArrayList<Integer> whitecardID){
        this.whitecardIDs = whitecardIDs;
        this.uniqueID = uniqueID;
    }
}
