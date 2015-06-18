package com.appsagainst.humanity.Events;

/**
 * Created by User on 09/05/2015.
 */
public class PlayerJoinedLobby {

    public String playerName;
    public String uniqueID;

    public PlayerJoinedLobby(String clientName, String uniqueID){
        this.playerName = clientName;
        this.uniqueID = uniqueID;

    }
}
