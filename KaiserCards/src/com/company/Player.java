package com.company;


import com.company.constants.Colors;

import static com.company.Main.control;
import static com.company.Main.input;
/**
 * Created by Freunz Wa
 */
public class Player extends Entity {

    private int speedSetting = 2;
    public boolean isWalking = false;
    private int walkDistance;
    private String character;
    public String playerName;

    Player(int x, int y) {
        System.out.println("New player initialised.");
        this.character = "blaz";
        this.depth = -1;
        this.playerName = "Jack";
        this.x = x;
        this.facing = 0;
        this.width = 16;
        this.height = 16;
        this.y = y;
        //basically the sprite index.
        this.getAnimation(character);
        this.hitBoxColor = Colors.RED;
        setAniSpeed(0);
        setAniIndex(0);



    }


    @Override
    public void update() {

        //move actions.
        super.update();
        //player movement (walking etc.)
        if (!control.gamePaused) {
            this.move();
        }
        else {
            setAniSpeed(0);
            setAniIndex(0);
        }



    }

    public void move() {

        if (!isWalking) {
            if (input.inputRight) {
                facing = 0;
                isWalking = true;
            }
            else if (input.inputUp) {
                isWalking = true;
                facing = 1;
            }
            else if (input.inputLeft) {
                isWalking = true;
                facing = 2;
            }
            else if (input.inputDown) {
                isWalking = true;
                facing = 3;
            }
            if (isWalking) {
                walkDistance = 16;
                setAniSpeed(0.005);
            }
            else {
                setAniSpeed(0);
                setAniIndex(0);
            }
        }
        else {
            if (walkDistance>14) {
                if (!input.inputRight && facing == 0){walkDistance =0;}
                if (!input.inputUp && facing == 1)   {walkDistance =0;}
                if (!input.inputLeft && facing == 2) {walkDistance =0;}
                if (!input.inputDown && facing == 3) {walkDistance =0;}
            }
            walkDistance -= speedSetting;
            if (facing == 0)     {moveIfFree(speedSetting, 0); }
            if (facing == 1)    {moveIfFree(0, -speedSetting); }
            if (facing == 2)   {moveIfFree(-speedSetting, 0); }
            if (facing == 3)   { moveIfFree(0, speedSetting); }

            if (walkDistance<1) {
                this.alignWithGrid(16);
                isWalking = false;
            }
        }
    }

}
