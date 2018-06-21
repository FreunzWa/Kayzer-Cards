package com.company;

import com.company.constants.Colors;
import com.company.constants.Fonts;
import com.company.constants.LOGGER;
import com.company.game.Draw;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Main extends BasicGame {

    static final String gameVersion = " In-dev 0.0.5";
    static final String gameTitle = "Kayzer Cards";
    public static AppGameContainer game;
    public static int windowWidth = 640;
    public static int windowHeight = 480;
    public static Draw drawer;
    static boolean gameRunning;
    public static Input input;
    public static Player player;
    static CardLibrary cardLib;
    public static ImageLoader images;
    public static Control control;
    public static List<Drawable> drawList;
    public static List<Entity> entityList;
    public static List<Tile> tileList;
    public static View view;
    public static MapLoader map;
    public static Fonts fonts;
    public static Duel duel;
    public static LOGGER logger = new LOGGER();

    public Main(String title) {
        super(title);
    }
    @Override
    public void init(GameContainer gc) throws SlickException {
        entityList = new ArrayList<Entity>();
        drawer = new Draw();
        drawList = new ArrayList<Drawable>();
        fonts = new Fonts();
        tileList = new ArrayList<Tile>();
        images = new ImageLoader();
        cardLib = new CardLibrary();
        input = new Input(gc);
        player = new Player(0,0);
        control = new Control();
        view = new View(0,0,160, 144,player);
        //map creation
        map = new MapLoader();
        map.createMap("exportMap.txt");

        //go in the update spot for duel start.
        String cards1 = "forest dude, herbert, plughole, phoenix, fire sheriff, quickblade, kayzer k, fire wrestler, jesty";
        String cards2 = "fire sheriff, fire sheriff, fire sheriff, quickblade, herbert, quickblade, plughole, plughole,plughole,forest dude, herbert, fire wrestler";

        CardContainer playerDeck = new CardContainer(cards1);
        CardContainer deck1 = new CardContainer(cards1);
        deck1.id = 1;
        CardContainer deck2 = new CardContainer(cards2);
        deck2.id = 2;
        duel = new Duel(deck1, deck2);
        //int winner = newDuel.run();


    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        input.update();
        control.update(gc);
        for (Entity e: entityList) {
            e.update();
        }
        view.update();
        List<Drawable> drawableCopy = new ArrayList<Drawable>(drawList);
        for (Drawable d:drawableCopy) {
            try {
                d.update();
            }
            catch (Exception e) {
                //pass
            }
        }

        if (input.inputP) {
            duel.endDuel();
            Main.game.reinit();

        }

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        float sy = (float)windowHeight/view.height;
        float sx = (float)windowWidth/view.width;
        g.scale(sx,sy);
        drawer.update(g);
        //passes the graphics object g into each object, then the methods are run locally for each object.



    }


    public static void main(String[] args) throws SlickException {
        try {
            game = new AppGameContainer(new Main(gameTitle+gameVersion));
            game.setDisplayMode(windowWidth, windowHeight, false);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        game.setTargetFrameRate(60);
        game.setShowFPS(true);
        gameRunning = true;
        while (gameRunning) {
            game.start();
        }




    }
}
