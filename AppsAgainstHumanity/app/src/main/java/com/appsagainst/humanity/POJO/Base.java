package com.appsagainst.humanity.POJO;

import com.google.gson.annotations.Expose;

public class Base{

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