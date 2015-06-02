package com.appsagainst.humanity.Protocol;

import android.util.Log;

import com.appsagainst.humanity.Events.ClientAdded;
import com.appsagainst.humanity.Global;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.WebSocket;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public class GamePlayer {

    public WebSocket webSocket;
    public Player player;

    public GamePlayer(WebSocket webSocket, final CompletedCallback cc, final WebSocket.StringCallback wsc){
        this.webSocket = webSocket;

        webSocket.setClosedCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                try {
                    if (ex != null)
                        Log.e("WebSocket", "Error");
                } finally {
                    cc.onCompleted(ex);
                }
            }
        });

        webSocket.setStringCallback(new WebSocket.StringCallback() {
            @Override
            public void onStringAvailable(String s) {
                Log.d("GameServer", s);
                wsc.onStringAvailable(s);

                JsonParser parser = new JsonParser();
                int action = parser.parse(s).getAsJsonObject().get("action").getAsInt();

                if(GameState.GAME_START.getValue() == action){
                    player = new Gson().fromJson(s,Player.class);
                    Global.getInstance().bus.post(new ClientAdded(player.name));

                    Log.d("PLAYER NAME", player.name);
                } else if(GameState.GAME_START.getValue() == action){

                }
            }
        });
    }
}

