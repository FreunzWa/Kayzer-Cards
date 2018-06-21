package com.company;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**
 * Created by Freunz Wa
 */
public class Effect extends Drawable {

    public boolean isActive = true;

    public Effect (int x, int y, Animation ani) {
        this.ani = ani;
        this.x = x;
        this.y = y;
        setAniSpeed(0.003);
        setAniIndex(0);
        this.ani.setLooping(false);

    }

    @Override
    public void draw (Graphics g) {
        ani.draw(x,y);
    }

    public void update() {
        if (isActive) {
            System.out.println("updating the aniammation");
            if (ani.isStopped()) {
                this.isActive = false;
                Main.drawList.remove(this);
            }
        }
    }


}
