package com.appsagainst.humanity.Protocol;

import com.appsagainst.humanity.Events.AllCardsSubmitted;
import com.appsagainst.humanity.Events.GotInitialWhiteCardIDs;
import com.appsagainst.humanity.Events.PlayerDisconnected;
import com.appsagainst.humanity.Events.PlayerJoinedLobby;
import com.appsagainst.humanity.Events.SelectBlackCardPlayer;
import com.appsagainst.humanity.Events.StartGameSession;
import com.appsagainst.humanity.Events.SubmitWhiteCardToServer;
import com.appsagainst.humanity.Events.WinnerChosen;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.POJO.DataObject;

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
    public static final int chooseWinner = 8;
    public static final int playerDisconnected = 9;

    public static void resolveObject(DataObject object){
        if(object.action == playerJoinedLobby){
            Global.getInstance().bus.post(new PlayerJoinedLobby(object.data.playerName, object.data.uniqueID));

        } else if(object.action == startGameSession){
            Global.getInstance().bus.post(new StartGameSession());

        } else if(object.action == selectBlackCardPlayer){
            Global.getInstance().bus.post(new SelectBlackCardPlayer(object.data.uniqueID, object.data.blackCardID));

        } else if(object.action == distributeWhiteStartingCards){
            Global.getInstance().bus.post(new GotInitialWhiteCardIDs(object.data.initialCards));

        } else if(object.action == submitWhiteCardToServer){
            Global.getInstance().bus.post(new SubmitWhiteCardToServer(object.data.uniqueID, object.data.whiteCardIDs));

        } else if(object.action == allCardsSubmitted){
            Global.getInstance().bus.post(new AllCardsSubmitted());

        } else if(object.action == chooseWinner){
            Global.getInstance().bus.post(new WinnerChosen(object.data.uniqueID, object.data.whiteCardIDs));

        } else if(object.action == playerDisconnected){
            Global.getInstance().bus.post(new PlayerDisconnected(object.data.uniqueID));
        } else {

        }
    }
}
