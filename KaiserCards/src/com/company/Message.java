package com.company;


import com.company.constants.Colors;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freunz Wa
 */
public class Message extends Drawable {

    private String text;
    private List<String> textList;
    private String[] textArray;
    private boolean hasFocus = false;
    private int displaySection;
    private int latencySetting = 30;
    public boolean isActive = true;

    public Message(String text)  {
        Main.control.setLatency(latencySetting);
        System.out.println(text);
        this.height = 32;
        this.width = Main.view.width;
        this.displaySection = 0;
        this.hasFocus = true;
        this.x = 0;
        if (Main.player.y < Main.view.height-32) {
            this.y = Main.view.height - 32;
        }
            else {
            this.y = 0;
        }
        this.textList = new ArrayList<String>();
        this.textArray = (text.toUpperCase()).split(" "); // splits by spaces
        splitText(text.toUpperCase());


    }

    public void update()  {
        if (Main.input.pressX && Main.control.getLatency() == 0) {
            if (displaySection + 2 <textList.size()) {
                this.displaySection += 2;
                Main.control.setLatency(latencySetting);
            }
            else {
                this.destroy();
                this.isActive = false;
            }
        }
    }

    public void splitText(String text) {
        text = text.toUpperCase();
        for (int i =1; i <textArray.length; i+=1) {
            String str = "";
            i-=1;
            while (Main.fonts.basicFont.getWidth(str+" "+textArray[i]) <this.width-2*pxborder && i<textArray.length-1) {
                str = str + " "+ textArray[i];
                i++;

            }
            this.textList.add(str);

        }
        this.textList.add(textArray[textArray.length-1]);
        if (textList.size()%2==1) {
            textList.add("");
        }
        System.out.println(textList.size());
    }


    @Override
    public void draw(org.newdawn.slick.Graphics g) {
        //drawing the message
        super.draw(g);
        for (int i = 0; i<2; i++) {
            String s = textList.get(i+displaySection);
            g.drawString(s, x + pxborder * 2, y + 2 + i*16);
        }


    }
}
