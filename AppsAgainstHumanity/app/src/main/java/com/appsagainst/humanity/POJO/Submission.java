package com.appsagainst.humanity.POJO;

import java.util.ArrayList;

public class Submission {

    private String uniqueID;
    private ArrayList<WhiteCard> whiteCards;

    public Submission(String uniqueID, ArrayList<WhiteCard> whiteCards){
        this.uniqueID = uniqueID;
        this.whiteCards = whiteCards;
    }

    @Override
    public String toString() {
        return uniqueID;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public ArrayList<WhiteCard> getWhiteCards() {
        return whiteCards;
    }

    public void setWhiteCardIDs(ArrayList<WhiteCard> whiteCards) {
        this.whiteCards = whiteCards;
    }
}
