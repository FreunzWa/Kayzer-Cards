package com.company;

import static com.company.Main.control;

/**
 * Created by Freunz Wa
 */
public class View {


    int x;
    int y;
    int width;
    int height;
    boolean boundByRoom = true;
    Player followPlayer;
    public View(int x, int y, int width, int height, Player followPlayer) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.followPlayer = followPlayer;
    }
    public void update() {
        x = followPlayer.x - width/2 + followPlayer.width/2;
        y = followPlayer.y - height/2+ followPlayer.height/2;

        if (boundByRoom) {
            if (x + width > control.mapWidth) {
                x = control.mapWidth - width;
            }
            if (y + height > control.mapHeight) {
                y = control.mapHeight - height;
            }
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
        }

    }
}

