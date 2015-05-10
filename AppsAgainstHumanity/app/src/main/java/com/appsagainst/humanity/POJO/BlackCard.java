package com.appsagainst.humanity.POJO;

import com.google.gson.annotations.Expose;

public class BlackCard {

    @Expose
    private String text;
    @Expose
    private Integer pick;
    @Expose
    private Integer id;

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
    public Integer getPick() {
        return pick;
    }

    /**
     *
     * @param pick
     * The pick
     */
    public void setPick(Integer pick) {
        this.pick = pick;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

}