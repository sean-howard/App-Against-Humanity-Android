package com.appsagainst.humanity.LocalMultiplayer;

import android.util.Log;

import com.appsagainst.humanity.Events.JoiningLobby;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.POJO.DataObject;
import com.appsagainst.humanity.Protocol.JsonResolver;
import com.google.gson.Gson;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;

import java.net.InetAddress;
import java.util.UUID;

/**
 * Created by User on 09/05/2015.
 */
public class GameClient {

    private InetAddress mAddress;
    private int PORT;

    private final String CLIENT_TAG = "GameClient";

    WebSocket socket;
    Gson gson;

    public GameClient(InetAddress address, int port) {
        Log.d(CLIENT_TAG, "Creating GameClient");
        this.mAddress = address;
        this.PORT = port;
        this.gson = new Gson();

        AsyncHttpClient.WebSocketConnectCallback mWebSocketConnectCallback = new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                socket = webSocket;

                socket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        JsonResolver.resolveObject(gson.fromJson(s, DataObject.class));
                    }
                });

                UUID uniqueKey = UUID.randomUUID();
                DataObject obj = new DataObject();
                obj.action = JsonResolver.joiningLobby;
                obj.data.playerName = Global.getInstance().name;
                obj.data.uniqueID = uniqueKey.toString();
                sendMessage(gson.toJson(obj));

            }
        };

        AsyncHttpClient mAsyncHttpClient = AsyncHttpClient.getDefaultInstance();
        mAsyncHttpClient.websocket("http://" + mAddress.getHostAddress() + ":" + PORT, null, mWebSocketConnectCallback);
    }

    public GameClient(String url, int port) {
        Log.d(CLIENT_TAG, "Creating GameClient");
        this.PORT = port;

        AsyncHttpClient.WebSocketConnectCallback mWebSocketConnectCallback = new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                socket = webSocket;

                socket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        Log.d(CLIENT_TAG, s);

                    }
                });

                sendMessage("hello");
            }
        };

        AsyncHttpClient mAsyncHttpClient = AsyncHttpClient.getDefaultInstance();
        mAsyncHttpClient.websocket(url + ":" + PORT, null, mWebSocketConnectCallback);
    }


    private WebSocket getSocket() {
        return socket;
    }

    public void tearDown() {
        getSocket().close();
    }

    public void sendMessage(String msg) {
        Log.d(CLIENT_TAG, msg);
        getSocket().send(msg);
    }
}
