package com.appsagainst.humanity.Events;

import android.net.nsd.NsdServiceInfo;

/**
 * Created by User on 09/05/2015.
 */
public class ServerFoundEvent {

    public String serverName;
    public NsdServiceInfo serviceInfo;

    public ServerFoundEvent(String serverName, NsdServiceInfo serviceInfo){
        this.serverName = serverName;
        this.serviceInfo = serviceInfo;
    }
}
