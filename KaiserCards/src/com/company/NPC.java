package com.company;

import com.company.constants.Colors;

import java.util.Random;

/**
 * Created by Freunz Wa
 */
public class NPC extends Entity {

    private String message;
    private String character;
    private String[] tags;
    private String special;
    private CardContainer deck;

    NPC(int x, int y, String tags, String character) {
        this.character = getName(character.substring(2,4));
        this.tags = tags.split("&");
        this.message = this.tags[0];
        this.special = getSpecial(this.tags);
        initialiseDeck(this.tags);
        this.depth = -1;

        this.x = x;
        this.facing = 3;
        this.width = 16;
        this.height = 16;
        this.y = y;
        isSolid = true;
        //basically the sprite index.
        this.getAnimation(this.character);
        this.hitBoxColor = Colors.RED;
        setAniSpeed(0);
        setAniIndex(0);




    }

    public void update() {
        //checks if the player is talking to this object.
        this.actionCheck();
        this.randomLook();

    }


    public void actionCheck() {
        if (Main.control.isFree() && Main.input.pressX
                && !Main.player.isWalking) {
            if (playerFacing()) {
                this.facePlayer();
                this.talk();
            }
        }
    }

    public void randomLook() {
        if (Main.control.isFree()) {
            if (Main.control.testChance(240)) {
                this.facing = Main.control.getRandom(4);
            }
        }
    }

    public void facePlayer() {
        int pf = Main.player.facing;
        if (pf == 2 || pf == 0) {
            facing = 2-pf;
        }
        else {
            facing = 4-pf;
        }


    }

    public void talk() {
        Message message = new Message(this.message);
        if (special.equals("DUEL")) {
            Main.duel.start();
            message.destroy();
        }
    }

    public String getSpecial(String[] tags) {
        if (tags.length > 1) {
            return tags[1];
        }
        else {
            return "NONE";
        }
    }

    public String getName(String id) {
        if (id.equals("00")) {
            return "npc1";
        }
        else {return "npc1";}
    }

    public void initialiseDeck(String[] tags) {
        if (tags.length > 2) {
            this.deck = new CardContainer(tags[2]);
        }
    }

}
