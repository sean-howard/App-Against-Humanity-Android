package com.appsagainst.humanity.POJO;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Pack extends RealmObject{

    @PrimaryKey
    @Expose
    private String name;

    @Expose
    private RealmList<BlackCard> blackCards = new RealmList<>();
    @Expose
    private RealmList<WhiteCard> whiteCards = new RealmList<>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The blackCards
     */
    public RealmList<BlackCard> getBlackCards() {
        return blackCards;
    }

    /**
     *
     * @param blackCards
     * The blackCards
     */
    public void setBlackCards(RealmList<BlackCard> blackCards) {
        this.blackCards = blackCards;
    }

    /**
     *
     * @return
     * The whiteCards
     */
    public RealmList<WhiteCard> getWhiteCards() {
        return whiteCards;
    }

    /**
     *
     * @param whiteCards
     * The whiteCards
     */
    public void setWhiteCards(RealmList<WhiteCard> whiteCards) {
        this.whiteCards = whiteCards;
    }

}