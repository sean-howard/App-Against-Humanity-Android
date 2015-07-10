package com.appsagainst.humanity.Events;

/**
 * Created by Chris on 18/06/2015.
 */
public class SubmitWhiteCardToServer {

    public int whitecardID;
    public String uniqueID;

    public SubmitWhiteCardToServer(String uniqueID, int whitecardIDs){
        this.whitecardID = whitecardIDs;
        this.uniqueID = uniqueID;
    }
}
