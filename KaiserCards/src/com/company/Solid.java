package com.company;


/**
 * Created by Freunz Wa
 */
public class Solid extends Entity{

    public String id;

    Solid(int x, int y, String id) {
        spr = "solid";
        this.x = x;
        this.y = y;
        isSolid = true;
        this.id = id;
        int[] spriteCoords = new int[2];
        //based on the map generation ID.
        spriteCoords[0] = Integer.parseInt(id.substring(2,3));
        spriteCoords[1] = Integer.parseInt(id.substring(3,4));
        this.spriteIndex = Main.images.tileSet1.getSprite(spriteCoords[0], spriteCoords[1]);

    }

}
