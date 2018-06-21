package com.company.constants;

import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

/**
 * Created by Freunz Wa
 */
public class Fonts {

    public static TrueTypeFont basicFont;

    public Fonts() {
        Font font = new Font("Courier", Font.BOLD, 10);
        basicFont = new TrueTypeFont(font, false);

    }

}



