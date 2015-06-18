package com.appsagainst.humanity;

import android.app.Application;

import com.appsagainst.humanity.Events.MainThreadBus;

import java.util.UUID;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by User on 09/05/2015.
 */
public class Global extends Application {

    public MainThreadBus bus = new MainThreadBus();
    private static Global singleton;
    public String name = "Chris";
    public String uniqueID = "";

    // Returns the application instance
    public static Global getInstance() {
        return singleton;
    }

    public final void onCreate() {
        super.onCreate();
        singleton = this;

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/RalewayThin.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        uniqueID = UUID.randomUUID().toString();
    }
}
