package com.appsagainst.humanity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.appsagainst.humanity.Fragments.LaunchFragment;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.holder, new LaunchFragment()).commit();
    }

}
