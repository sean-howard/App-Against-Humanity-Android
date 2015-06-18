package com.appsagainst.humanity.Protocol;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public class Player {

    public String name;
    public String uniqueID;

    public Player(String name2, String uniqueID2){
        this.name = name2;
        this.uniqueID = uniqueID2;
    }

    @Override
    public String toString() {
        return name;
    }
}

