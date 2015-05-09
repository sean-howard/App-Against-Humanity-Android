package com.appsagainst.humanity.LocalMultiplayer;

import android.os.Handler;
import android.util.Log;

import com.appsagainst.humanity.Events.ClientAdded;
import com.appsagainst.humanity.Events.ServerSetupEvent;
import com.appsagainst.humanity.Global;
import com.appsagainst.humanity.LocalMultiplayer.GameClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by User on 09/05/2015.
 */
public class GameServer {
    ServerSocket mServerSocket = null;
    Thread mThread = null;
    ArrayList<Socket> clientSockets = new ArrayList<>();

    private static final String TAG = "GameServer";

    private Thread mRecThread;

    private int mPort = -1;

    public GameServer() {
        mThread = new Thread(new ServerThread());
        mThread.start();
    }

    public void tearDown() {
        mThread.interrupt();
        try {
            mServerSocket.close();
        } catch (IOException ioe) {
            Log.e(TAG, "Error when closing server socket.");
        }
    }

    public int getLocalPort() {
        return mPort;
    }

    public void setLocalPort(int port) {
        mPort = port;
    }

    public void connectToServer(Socket mSocket) {
        clientSockets.add(mSocket);
    }

    public void sendMessage(String msg) {

    }

    class ReceivingThread implements Runnable {

        @Override
        public void run() {

            BufferedReader input;
            try {
                input = new BufferedReader(new InputStreamReader(
                        mSocket.getInputStream()));
                while (!Thread.currentThread().isInterrupted()) {

                    String messageStr = null;
                    messageStr = input.readLine();
                    if (messageStr != null) {
                        Log.d(TAG, "Read from the stream: " + messageStr);
                        sendMessage(messageStr.toUpperCase());
                    } else {
                        Log.d(TAG, "The nulls! The nulls!");
                        break;
                    }
                }
                input.close();

            } catch (IOException e) {
                Log.e(TAG, "Server loop error: ", e);
            }
        }
    }

    private Socket getSocket() {
        return mSocket;
    }

    class ServerThread implements Runnable {

        @Override
        public void run() {

            try {
                // Since discovery will happen via Nsd, we don't need to care which port is
                // used.  Just grab an available one  and advertise it via Nsd.
                mServerSocket = new ServerSocket(0);
                setLocalPort(mServerSocket.getLocalPort());

                while (!Thread.currentThread().isInterrupted()) {
                    Log.d(TAG, "ServerSocket Created, awaiting connection");
                    clientSockets.add(mServerSocket.accept());
                    Log.d(TAG, "Connected.");

                    Global.getInstance().bus.post(new ClientAdded(clientSockets.get(clientSockets.size()-1).getInetAddress().getHostName()));

                    if(mRecThread == null){
                        mRecThread = new Thread(new ReceivingThread());
                        mRecThread.start();
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "Error creating ServerSocket: ", e);
                e.printStackTrace();
            }
        }
    }
}