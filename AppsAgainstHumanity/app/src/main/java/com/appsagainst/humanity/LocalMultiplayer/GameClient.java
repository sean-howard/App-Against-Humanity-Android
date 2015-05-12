package com.appsagainst.humanity.LocalMultiplayer;

import android.util.Log;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;

import java.net.InetAddress;

/**
 * Created by User on 09/05/2015.
 */
public class GameClient {

    private InetAddress mAddress;
    private int PORT;

    private final String CLIENT_TAG = "GameClient";

    WebSocket socket;

    public GameClient(InetAddress address, int port) {
        Log.d(CLIENT_TAG, "Creating GameClient");
        this.mAddress = address;
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
