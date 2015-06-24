package com.appsagainst.humanity.POJO;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;

public class Base extends RealmObject{

    @Expose
    private Pack Pack;

    /**
     *
     * @return
     * The Pack
     */
    public Pack getPack() {
        return Pack;
    }

    /**
     *
     * @param Pack
     * The Pack
     */
    public void setPack(Pack Pack) {
        this.Pack = Pack;
    }

}