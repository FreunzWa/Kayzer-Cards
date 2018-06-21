package com.company;

import org.newdawn.slick.GameContainer;

import java.util.Random;

import static com.company.Main.input;

/**
 * Created by Freunz Wa
 */
public class Control {
    public boolean useHitBoxes = false;
    public int mapWidth = 640;
    public int mapHeight = 480;
    public static boolean gamePaused = false;
    private String gameState;
    private Random rand;
    private int latency = 0;

    public Control() {
        gameState = "WORLD";
        rand = new Random();
    }

    public void update(GameContainer gc) {

        if (latency > 0 ){
            latency -=1;
        }

        if (Main.drawList.size() > 0) {
            gamePaused = true;
        }
        else {
            gamePaused = false;
        }

        if (input.inputEscape) {
            gc.exit();
        }


        if (input.pressH) {
            if (useHitBoxes) {
                useHitBoxes = false;
            } else {useHitBoxes = true;}
        }
    }

    public void setLatency(int latency) {
        this.latency = latency;

    }
    public int getLatency() {
        return latency;
    }

    public boolean isFree() {
        if (latency>0 || gamePaused || gameState!="WORLD") {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean testChance(int bound) {
        if (rand.nextInt(bound) == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getRandom(int bound) {
        return rand.nextInt(bound);
    }

    public String getGameState() {
        return this.gameState;
    }

    public void setGameState(String s) {
        if (s.equals("DUEL") || s.equals("WORLD") || s.equals("MENU")) {
            this.gameState = s;
        }
        else {
            System.out.println("An invalid gamestate was entered.");
        }
    }




}
