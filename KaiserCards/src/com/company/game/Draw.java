package com.company.game;

import com.company.*;
import com.company.constants.Colors;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

import static com.company.Main.logger;

/**
 * Created by Freunz Wa
 */
public class Draw {

    public static Image duelBackground;

    public Draw() throws SlickException {
        duelBackground = new Image("res/duel/templateDuel.png", false, Image.FILTER_NEAREST);

    }

    public void update(Graphics g) {


        //Drawing the world objects
        if (Main.control.getGameState().equals("WORLD")) {
            //Draw the background
            g.setColor(Colors.WHITE);
            g.fillRect(0,0,Main.windowWidth, Main.windowHeight);

            for (Tile t : Main.tileList) {
                t.draw(g);
            }
            for (Entity e : Main.entityList) {
                if (!e.isTile) {
                    e.draw(g);
                }
            }

        }


        //Draw menus/messages



        if (Main.control.getGameState().equals("DUEL")) {

            //Draw the background
            g.setColor(Colors.SIMPLE_DUEL_BACKGROUND);
            g.fillRect(0, 0, Main.windowWidth, Main.windowHeight);
            g.drawImage(duelBackground,0,0);
            for (int i = 0; i<Main.duel.decks.length; i++) {
                Main.duel.decks[i].draw(g);
                Main.duel.hands[i].draw(g);
                Main.duel.fields[i].draw(g);
            }

        }
        List<Drawable> drawableCopy = new ArrayList<Drawable>(Main.drawList);
        for (Drawable d:drawableCopy) {
            try {
                d.draw(g);
            }
            catch (Exception renderException) {
                logger.log("Failure to render graphics.");
            }
        }


    }
}
