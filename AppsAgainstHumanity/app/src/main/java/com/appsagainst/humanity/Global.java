package com.appsagainst.humanity;

import android.app.Application;
import android.os.Build;

import com.appsagainst.humanity.Events.MainThreadBus;

import java.util.UUID;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by User on 09/05/2015.
 */
public class Global extends Application {

    public MainThreadBus bus = new MainThreadBus();
    private static Global singleton;
    public String name = "";
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
        name = getDeviceName();
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


}
