package com.appsagainst.humanity;

import android.app.Application;

import com.appsagainst.humanity.Events.MainThreadBus;

/**
 * Created by User on 09/05/2015.
 */
public class Global extends Application {

    public MainThreadBus bus = new MainThreadBus();
    private static Global singleton;

    // Returns the application instance
    public static Global getInstance() {
        return singleton;
    }

    public final void onCreate() {
        super.onCreate(); singleton = this;
    }
}
