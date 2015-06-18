package com.appsagainst.humanity.Protocol;

import com.appsagainst.humanity.Events.GotInitialWhiteCardIDs;
import com.appsagainst.humanity.Events.PlayerJoinedLobby;
import com.appsagainst.humanity.Events.SelectBlackCardPlayer;
import com.appsagainst.humanity.Events.StartGameSession;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.POJO.DataObject;

import java.util.ArrayList;

/**
 * Created by Chris.Owen on 12/05/2015.
 */
public class JsonResolver {

    public static final int unknownAction = -1;
    public static final int playerJoinedLobby = 0;
    public static final int startGameSession = 1;
    public static final int selectBlackCardPlayer = 3;
    public static final int distributeWhiteStartingCards = 4;
    public static final int displayBlackCardQuestion = 5;
    public static final int submitWhiteCardToServer = 6;
    public static final int allCardsSubmitted = 7;
    public static final int chooseWinnerEvent = 8;

    public static void resolveObject(DataObject object){
        if(object.action == playerJoinedLobby){
            Global.getInstance().bus.post(new PlayerJoinedLobby(object.data.playerName, object.data.uniqueID));

        } else if(object.action == startGameSession){
            Global.getInstance().bus.post(new StartGameSession());

        } else if(object.action == selectBlackCardPlayer){
            Global.getInstance().bus.post(new SelectBlackCardPlayer(object.data.blackCardPlayerUniqueID));

        } else if(object.action == distributeWhiteStartingCards){
            ArrayList<Integer> cardIDs = object.data.initialCards.get(Global.getInstance().uniqueID);
            Global.getInstance().bus.post(new GotInitialWhiteCardIDs(cardIDs));

        } else if(object.action == displayBlackCardQuestion){

        } else if(object.action == submitWhiteCardToServer){

        } else if(object.action == allCardsSubmitted){

        } else if(object.action == chooseWinnerEvent){

        } else {

        }
    }
}
