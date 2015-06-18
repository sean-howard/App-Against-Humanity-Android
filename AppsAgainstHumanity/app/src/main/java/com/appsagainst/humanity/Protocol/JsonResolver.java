package com.appsagainst.humanity.Protocol;

import com.appsagainst.humanity.Events.PlayerJoinedLobby;
import com.appsagainst.humanity.Events.StartGameSession;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.POJO.DataObject;

/**
 * Created by Chris.Owen on 12/05/2015.
 */
public class JsonResolver {

    public static final int unknownAction = -1;
    public static final int playerJoinedLobby = 0;
    public static final int startGameSession = 1;
    public static final int startGameMatch = 2;
    public static final int selectBlackCardPlayer = 3;
    public static final int distributeBlackStartingCards = 4;
    public static final int displayBlackCardQuestion = 5;
    public static final int submitWhiteCardToServer = 6;
    public static final int allCardsSubmitted = 7;
    public static final int chooseWinnerEvent = 8;

    public static void resolveObject(DataObject object){
        if(object.action == playerJoinedLobby){
            Global.getInstance().bus.post(new PlayerJoinedLobby(object.data.playerName, object.data.uniqueID));
        } else if(object.action == startGameSession){
            Global.getInstance().bus.post(new StartGameSession());
        } else if(object.action == startGameMatch){

        } else if(object.action == selectBlackCardPlayer){

        } else if(object.action == distributeBlackStartingCards){

        } else if(object.action == displayBlackCardQuestion){

        } else if(object.action == submitWhiteCardToServer){

        } else if(object.action == allCardsSubmitted){

        } else if(object.action == chooseWinnerEvent){

        } else {

        }
    }
}
