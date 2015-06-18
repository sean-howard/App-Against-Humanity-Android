package com.appsagainst.humanity.POJO;

import io.realm.RealmObject;

public class BlackCard extends RealmObject {

    private String text;
    private int pick;
    private int id;

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The pick
     */
    public int getPick() {
        return pick;
    }

    /**
     *
     * @param pick
     * The pick
     */
    public void setPick(int pick) {
        this.pick = pick;
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }
}