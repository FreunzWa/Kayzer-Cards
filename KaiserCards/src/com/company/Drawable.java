package com.company;

import com.company.constants.Colors;
import org.newdawn.slick.Animation;

/**
 * Created by Freunz Wa
 */
public class Drawable {

    int x;
    int y;
    int height;
    int width;
    boolean exit = false;
    int pxborder = 1;
    public Animation ani;

    public Drawable() {
       Main.drawList.add(this);
    }


    public void draw(org.newdawn.slick.Graphics g) {
        g.setFont(Main.fonts.basicFont);
        g.setColor(Colors.BLACK);
        g.fillRect(x,y,width,height);
        g.setColor(Colors.WHITE);
        g.fillRect(x+pxborder,y+pxborder,width-pxborder*2,height-pxborder*2);
        g.setColor(Colors.BLACK);
    }

    public void update(){
        //update here
    }
    public void destroy() {
        Main.drawList.remove(this);
    }

    public void setAniIndex(int ind) {
        ani.setCurrentFrame(ind);
    }


    public void setAniSpeed(double newSpeed) {
            ani.setSpeed((float)newSpeed);
            if (newSpeed == 0) {
                ani.stop();
            }
            else if (ani.isStopped()){
                ani.start();
            }
        }

}
