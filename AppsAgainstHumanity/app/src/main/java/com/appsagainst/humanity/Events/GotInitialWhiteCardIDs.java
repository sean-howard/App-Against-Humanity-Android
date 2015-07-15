package com.appsagainst.humanity.Events;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chris on 18/06/2015.
 */
public class GotInitialWhiteCardIDs {

    public HashMap<String, ArrayList<Integer>> cardIDs;

    public GotInitialWhiteCardIDs(HashMap<String, ArrayList<Integer>> cardIDs){
        this.cardIDs = cardIDs;
    }
}
