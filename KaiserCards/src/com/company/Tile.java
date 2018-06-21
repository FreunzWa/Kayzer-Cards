package com.company;

import org.newdawn.slick.Animation;

/**
 * Created by Freunz Wa
 */
public class Tile extends Entity {


    public Tile(int x, int y, String id) {
        this.x = x;
        this.y = y;
        String name = getName(id.substring(2,4));
        this.getAnimation(name);
        Main.tileList.add(this);
        this.isTile = true;
        this.facing = 0;
        setAniSpeed(0.003);

    }

    public String getName(String id) {
        if (id.equals("00")) {
            return "flower";
        }
        else {return "flower";}
    }


}
