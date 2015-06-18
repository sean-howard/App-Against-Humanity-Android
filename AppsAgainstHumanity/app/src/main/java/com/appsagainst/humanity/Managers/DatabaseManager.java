package com.appsagainst.humanity.Managers;

import android.content.Context;

import com.appsagainst.humanity.POJO.Base;
import com.appsagainst.humanity.POJO.BlackCard;
import com.appsagainst.humanity.POJO.WhiteCard;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Chris on 10/05/2015.
 */
public class DatabaseManager {

    public static void loadCardsIntoDatabase(Context con){
        Gson gson = new Gson();
        Base base = gson.fromJson(loadJSONFromAsset(con), Base.class);

        Realm realm = Realm.getInstance(con);
        realm.beginTransaction();

        for(WhiteCard card: base.getPack().getWhiteCards()){
            //realm.copyToRealm(card);
        }

        for(BlackCard card: base.getPack().getBlackCards()){
            //realm.copyToRealm(card);
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

    public static RealmResults<WhiteCard> getWhiteCardByID(Context con, int id){
        Realm realm = Realm.getInstance(con);
        RealmResults<WhiteCard> result = realm.where(WhiteCard.class)
                .equalTo("id", id)
                .findAll();

        return result;
    }

    public static RealmResults<BlackCard> getBlackCardByID(Context con, int id){
        Realm realm = Realm.getInstance(con);
        RealmResults<BlackCard> result = realm.where(BlackCard.class)
                .equalTo("id", id)
                .findAll();

        return result;
    }

}
