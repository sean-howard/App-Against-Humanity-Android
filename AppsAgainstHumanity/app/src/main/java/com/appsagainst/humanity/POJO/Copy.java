package com.appsagainst.humanity.POJO;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Copy {

    private Random randomGenerator;

    @Expose
    private List<String> lobby = new ArrayList<String>();
    @Expose
    private List<String> submit = new ArrayList<String>();
    @Expose
    private List<String> chooseWinner = new ArrayList<String>();

    /**
     *
     * @return
     * The lobby
     */
    public List<String> getLobby() {
        return lobby;
    }

    /**
     *
     * @param lobby
     * The lobby
     */
    public void setLobby(List<String> lobby) {
        this.lobby = lobby;
    }

    /**
     *
     * @return
     * The submit
     */
    public List<String> getSubmit() {
        return submit;
    }

    /**
     *
     * @param submit
     * The submit
     */
    public void setSubmit(List<String> submit) {
        this.submit = submit;
    }

    /**
     *
     * @return
     * The chooseWinner
     */
    public List<String> getChooseWinner() {
        return chooseWinner;
    }

    /**
     *
     * @param chooseWinner
     * The chooseWinner
     */
    public void setChooseWinner(List<String> chooseWinner) {
        this.chooseWinner = chooseWinner;
    }

    public String getAnyLobby(){
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(lobby.size());
        return lobby.get(index);
    }

    public String getAnySubmit(){
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(submit.size());
        return submit.get(index);
    }

    public String getAnyChooseWinner(){
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(chooseWinner.size());
        return chooseWinner.get(index);
    }

}