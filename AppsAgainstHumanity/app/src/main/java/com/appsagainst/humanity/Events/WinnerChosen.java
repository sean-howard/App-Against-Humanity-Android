package com.appsagainst.humanity.Events;

import java.util.ArrayList;

/**
 * Created by Chris on 18/06/2015.
 */
public class WinnerChosen {

    public ArrayList<Integer> whiteCardIDs;
    public String uniqueID;

    public WinnerChosen(String uniqueID, ArrayList<Integer> whiteCardIDs){
        this.whiteCardIDs = whiteCardIDs;
        this.uniqueID = uniqueID;
    }
}
