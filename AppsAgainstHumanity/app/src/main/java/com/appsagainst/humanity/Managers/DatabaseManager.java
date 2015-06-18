package com.appsagainst.humanity.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.appsagainst.humanity.POJO.Base;
import com.appsagainst.humanity.POJO.BlackCard;
import com.appsagainst.humanity.POJO.WhiteCard;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import io.realm.Realm;

/**
 * Created by Chris on 10/05/2015.
 */
public class DatabaseManager {

    public static void loadDatabase(Context con){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(con);
        if(!preferences.getBoolean("databaseLoaded", false)){
            DatabaseManager.loadCardsIntoDatabase(con);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("databaseLoaded", true);
            editor.commit();
        }
    }

    public static void loadCardsIntoDatabase(Context con){
        Gson gson = new Gson();
        Base base = gson.fromJson(loadJSONFromAsset(con), Base.class);

        Realm realm = Realm.getInstance(con);
        realm.beginTransaction();

        for(WhiteCard card: base.getPack().getWhiteCards()){
            realm.copyToRealm(card);
        }

        for(BlackCard card: base.getPack().getBlackCards()){
            realm.copyToRealm(card);
        }

        realm.commitTransaction();
    }

    public static String loadJSONFromAsset(Context con) {
        String json = null;
        try {
            InputStream is = con.getAssets().open("cards.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static WhiteCard getWhiteCardByID(Context con, int id){
        Realm realm = Realm.getInstance(con);
        WhiteCard result = realm.where(WhiteCard.class)
                .equalTo("id", id)
                .findFirst();

        return result;
    }

    public static BlackCard getBlackCardByID(Context con, int id){
        Realm realm = Realm.getInstance(con);
        BlackCard result = realm.where(BlackCard.class)
                .equalTo("id", id)
                .findFirst();

        return result;
    }

    public static ArrayList<Integer> getSetOfRandomWhiteCardsIDs(Context con, int amount){
        ArrayList<Integer> cardIDs = new ArrayList<>();

        Realm realm = Realm.getInstance(con);
        int count = (int)realm.where(WhiteCard.class).count();

        int min = 2000000;
        int max = min + count;

        Random r = new Random();
        for(int i = 0; i<amount; i++){
            int id = r.nextInt(max - min) + min;
            cardIDs.add(id);
        }

        return cardIDs;
    }

    public static BlackCard getRandomBlackCard(Context con){
        Realm realm = Realm.getInstance(con);
        int count = (int)realm.where(BlackCard.class).count();

        int min = 1000000;
        int max = min + count;

        Random r = new Random();
        int id = r.nextInt(max - min) + min;

        return realm.where(BlackCard.class).equalTo("id", id).findFirst();
    }


}
