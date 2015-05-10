package com.appsagainst.humanity.POJO;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import io.realm.annotations.PrimaryKey;


public class Pack {

    @PrimaryKey
    @Expose
    private String name;

    @Expose
    private List<BlackCard> blackCards = new ArrayList<BlackCard>();
    @Expose
    private List<WhiteCard> whiteCards = new ArrayList<WhiteCard>();

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
    public List<BlackCard> getBlackCards() {
        return blackCards;
    }

    /**
     *
     * @param blackCards
     * The blackCards
     */
    public void setBlackCards(List<BlackCard> blackCards) {
        this.blackCards = blackCards;
    }

    /**
     *
     * @return
     * The whiteCards
     */
    public List<WhiteCard> getWhiteCards() {
        return whiteCards;
    }

    /**
     *
     * @param whiteCards
     * The whiteCards
     */
    public void setWhiteCards(List<WhiteCard> whiteCards) {
        this.whiteCards = whiteCards;
    }

}