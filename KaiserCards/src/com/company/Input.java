package com.company;

import org.newdawn.slick.GameContainer;

/**
 * Created by Freunz Wa
 */
public class Input {

    public boolean inputUp;
    public boolean inputRight;
    public boolean inputLeft;
    public boolean inputDown;
    public boolean inputEscape;
    public boolean inputH;
    public boolean inputX;
    public boolean inputZ;
    public boolean pressX;
    public boolean releaseX;
    public boolean inputP;
    public boolean pressDown;
    public boolean pressUp;

    public boolean pressH;

    public boolean releaseUp;
    public GameContainer gc;



    public Input(GameContainer gc) {
        this.gc = gc;
    }



    public void update() {

        if (gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_UP) && !inputUp) {pressUp = true;} else {pressUp = false;}
        if (!gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_UP) && inputUp) {releaseUp = true;} else {releaseUp = false;}
        inputUp = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_UP);


        inputRight = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_RIGHT);
        inputLeft = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_LEFT);


        inputZ = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_Z);
        inputP = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_P);
        inputEscape = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_ESCAPE);

        if (gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_H) && !inputH) {pressH = true;} else {pressH = false;}
        inputH = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_H);
        if (gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_X) && !inputX) {pressX = true;} else {pressX = false;}
        inputX = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_X);

        if (gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_DOWN) && !inputDown) {pressDown = true;} else {pressDown = false;}
        inputDown = gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_DOWN);


    }


}
