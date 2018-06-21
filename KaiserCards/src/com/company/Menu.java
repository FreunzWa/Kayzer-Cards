package com.company;

import org.newdawn.slick.Animation;

/**
 * Created by Freunz Wa
 */
public class Menu extends Drawable {

    private String[] options;
    private Animation[] aniSheet;
    private int selectPosition;
    private int arrowFacing = 0;
    public boolean isActive = true;


    public Menu(int x,int y,int width,int height, String options) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.options = options.split(",");
        aniSheet = Main.images.aniMap.get("greenArrow");
        this.selectPosition = 0;


    }

    public int getCommandByInput(){
        int lat = 15;
        if (Main.input.pressDown && selectPosition < options.length-1) {
            Main.control.setLatency(lat);
            selectPosition+=1;

        }
        else if (Main.input.pressUp && selectPosition> 0) {
            Main.control.setLatency(lat);
            selectPosition-=1;
        }
        else if (Main.input.pressX) {
            String str = options[selectPosition];
            if (str.equals("CARDS")) {
                //System.out.println("Cards selected");
            }
            else if (str.equals("END")) {
                System.out.println("Ended the turn");
                isActive = false;
                return 0;
            }
        }
        return 2;
    }


    @Override
    public void draw(org.newdawn.slick.Graphics g) {
        super.draw(g);
        Animation ani = aniSheet[arrowFacing];
        for (int i = 0; i<options.length; i++) {
            String s = options[i];
            g.drawString(s, x + pxborder*4+ani.getWidth(), y + 2 + i*ani.getHeight());
        }
        ani.draw(x+pxborder, y+1+ani.getHeight()*selectPosition);
    }
}
