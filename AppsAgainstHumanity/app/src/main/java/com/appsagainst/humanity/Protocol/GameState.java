package com.appsagainst.humanity.Protocol;

/**
 * Created by Chris.Owen on 01/06/2015.
 */
public enum GameState {
    GAME_START(0), GAME2(1), GAME3(2);
    private int value;

    private GameState(int value) {
        this.value = value;
    }

    public int getValue() { return value; }
};

