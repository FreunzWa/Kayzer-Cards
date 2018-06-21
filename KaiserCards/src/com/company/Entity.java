package com.company;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import static com.company.constants.Colors.BLACK;
import static com.company.Main.control;

/**
 * Created by Freunz Wa
 * A class for all objects which are drawn to the screen, and are capable of physical interactions with the player.
 */
public class Entity {
    int x;
    int y;
    double z;
    int width = 16;
    int height = 16;
    int depth;
    int facing = 1;
    boolean isSolid = false;
    public boolean useAnimation = false;
    double gravity;
    Image spriteIndex;
    String spr;
    Color hitBoxColor = BLACK;
    public Animation[] aniSheet;
    public Animation ani;
    public boolean isTile = false;


    public Entity() {
        Main.entityList.add(this);
        this.z = 100;
    }


    public void draw(org.newdawn.slick.Graphics g) {


        if (control.useHitBoxes) {
            g.setColor(hitBoxColor);
            g.fillRect((int) x - Main.view.x, (int) y - Main.view.y, width, height);
        }
        else {
            if (useAnimation) {
                Animation ani = aniSheet[facing];
                ani.draw(x - Main.view.x, (int) y - Main.view.y);
            }
            else {

                g.drawImage(spriteIndex, (int) x - Main.view.x, (int) y - Main.view.y);
            }
        }

    }

    public void update() {
        //pass


    }

    public void moveIfFree(int xMove, int yMove) {
        //only move if there is no solid object and if not ede
        if (spaceFree(this.x + xMove, this.y + yMove)) {
            this.x += xMove;
            this.y += yMove;
        }
    }

    public void alignWithGrid(int size) {
        double divisor = (double)size;
        x = size*(int)Math.round(x/divisor);
        y = size*(int)Math.round(y/divisor);
    }

    public boolean isAligned(int size) {
        double divisor = (double)size;
        return (x == size*(int)Math.round(x/divisor)
                && y == size*(int)Math.round(y/divisor));

    }


    public void moveToCollision(int xMove, int yMove) {
        while (spaceFree(x+xMove, y+yMove)) {
            moveIfFree(xMove, yMove);
        }
    }

    public boolean spaceFree(int x, int y) {
        int[] xCoords = new int[2];
        int[] yCoords = new int[2];
        //altering coordinates to make player occupy a smaller box
        //any solid object
        xCoords[0] = x+1;
        xCoords[1] = x + width - 1;
        yCoords[0] = y+1;
        yCoords[1] = y + height -1 ;

        for (Entity e : Main.entityList) {
            if (e.isSolid) {
                for (int i : xCoords) {
                    for (int j : yCoords) {
                        if ((i > e.x && (i <(e.x + e.width)) && j > e.y && (j < (e.y + e.height)))
                                || i<0 || j<0 || i > control.mapWidth || j > control.mapHeight ) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void getAnimation(String character) {
        this.useAnimation = true;
        this.aniSheet = Main.images.aniMap.get(character);

    }
    //animation methods
    public void setAniSpeed(double newSpeed) {
        for (Animation ani:aniSheet) {
            ani.setSpeed((float)newSpeed);
            if (newSpeed == 0) {
                ani.stop();
            }
            else if (ani.isStopped()){
                ani.start();
            }
        }
    }


    public boolean playerFacing() {
        int px = Main.player.x;
        int py = Main.player.y;
        int pf = Main.player.facing;
        if (px == x && (py == this.y-16&& pf==3|| py == this.y+16 && pf ==1)
                || py == y && (px == x-16 && pf==0||px == x+16 && pf==2)) {
            return true;
        }
        else {
            return false;
        }
    }


    public void setAniIndex(int ind) {
        for (Animation ani:aniSheet) {
            ani.setCurrentFrame(ind);
        }
    }
}



