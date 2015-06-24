package com.appsagainst.humanity.Events;

import java.util.ArrayList;

/**
 * Created by Chris on 18/06/2015.
 */
public class SubmitWhiteCardToServer {

    public ArrayList<Integer> whitecardIDs;
    public String uniqueID;

    public SubmitWhiteCardToServer(String uniqueID, ArrayList<Integer> whitecardIDs){
        this.whitecardIDs = whitecardIDs;
        this.uniqueID = uniqueID;
    }
}
