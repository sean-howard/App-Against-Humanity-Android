package com.appsagainst.humanity.Helpers;

import com.appsagainst.humanity.POJO.WhiteCard;

import java.util.ArrayList;

public class CardHelper {

    public static ArrayList<Integer> getIDsFromCards(ArrayList<WhiteCard> cards){
        ArrayList<Integer> ids = new ArrayList<>();

        for(WhiteCard card: cards){
            ids.add(card.getId());
        }

        return ids;
    }
}
