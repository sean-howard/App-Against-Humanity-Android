package com.appsagainst.humanity.POJO;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WhiteCard extends RealmObject {

    @Expose
    private String text;

    @PrimaryKey
    @Expose
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

