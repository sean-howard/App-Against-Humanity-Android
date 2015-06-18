package com.appsagainst.humanity.Protocol;

import android.util.Log;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.WebSocket;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public class GamePlayer {

    public WebSocket webSocket;

    public GamePlayer(WebSocket webSocket, final CompletedCallback cc, final WebSocket.StringCallback wsc){
        this.webSocket = webSocket;

        webSocket.setClosedCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                try {
                    if (ex != null) {
                        ex.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    cc.onCompleted(ex);
                }
            }
        });

        webSocket.setStringCallback(new WebSocket.StringCallback() {
            @Override
            public void onStringAvailable(String s) {
                Log.d("GameServer", s);
                wsc.onStringAvailable(s);
            }
        });
    }
}

