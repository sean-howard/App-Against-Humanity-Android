package com.appsagainst.humanity.Events;

import java.util.ArrayList;

/**
 * Created by Chris on 18/06/2015.
 */
public class SubmitWhiteCardToServer {

    public ArrayList<Integer> whitecardID;
    public String uniqueID;

    public SubmitWhiteCardToServer(String uniqueID, ArrayList<Integer> whitecardIDs){
        this.whitecardID = whitecardIDs;
        this.uniqueID = uniqueID;
    }
}
