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
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }

                socket = webSocket;
            }
        };

        AsyncHttpClient mAsyncHttpClient = AsyncHttpClient.getDefaultInstance();
        mAsyncHttpClient.websocket("http://127.0.0.1:" + PORT, null, mWebSocketConnectCallback);
    }

    private WebSocket getSocket() {
        return socket;
    }

    public void tearDown() {
        getSocket().close();
    }

    public void sendMessage(String msg) {
        getSocket().send(msg);
    }
}
