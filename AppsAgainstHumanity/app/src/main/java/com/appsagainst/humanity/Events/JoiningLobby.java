package com.appsagainst.humanity.Events;

/**
 * Created by User on 09/05/2015.
 */
public class JoiningLobby {

    public String playerName;
    public String uniqueID;

    public JoiningLobby(String clientName, String uniqueID){
        this.playerName = clientName;
        this.uniqueID = uniqueID;

    }
}
