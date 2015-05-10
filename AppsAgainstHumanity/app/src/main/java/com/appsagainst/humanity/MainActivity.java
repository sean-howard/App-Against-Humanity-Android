package com.appsagainst.humanity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.appsagainst.humanity.Fragments.LaunchFragment;
import com.appsagainst.humanity.Managers.DatabaseManager;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.holder, new LaunchFragment()).commit();

        DatabaseManager.loadCardsIntoDatabase(this);
    }
}
