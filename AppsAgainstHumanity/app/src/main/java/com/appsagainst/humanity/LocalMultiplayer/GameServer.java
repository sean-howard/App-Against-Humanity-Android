package com.appsagainst.humanity.LocalMultiplayer;

import com.appsagainst.humanity.Protocol.GamePlayer;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 09/05/2015.
 */
public class GameServer {
    AsyncHttpServer server;
    ArrayList<GamePlayer> players = new ArrayList<>();

    private static final String TAG = "GameServer";

    private int mPort = -1;

    public GameServer() {
        server = new AsyncHttpServer();
        server.websocket("/", new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(final WebSocket webSocket, AsyncHttpServerRequest request) {
                GamePlayer player = new GamePlayer(
                        webSocket,
                        new CompletedCallback() {
                            @Override
                            public void onCompleted(Exception ex) {
                                players.remove(webSocket);
                            }
                        }, new WebSocket.StringCallback() {
                            @Override
                            public void onStringAvailable(String s) {
                                sendMessage(s.toUpperCase());

                            }
                        });

                players.add(player);
            }
        });


        Random r = new Random();
        mPort = r.nextInt(65530);
        server.listen(mPort);
    }

    public void tearDown() {
        server.stop();
    }

    public int getLocalPort() {
        return mPort;
    }

    public void sendMessage(String msg) {
        for (GamePlayer player : players) {
            player.webSocket.send(msg);
        }
    }
}