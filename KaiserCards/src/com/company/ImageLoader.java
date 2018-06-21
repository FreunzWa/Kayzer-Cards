package com.company;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.*;
import java.util.Map;

import org.newdawn.slick.SpriteSheet;

/**
 * Created by Freunz Wa
 */
public class ImageLoader {

    public static SpriteSheet tileSet1;
    public static Animation[] blazSheet;
    private List<String> characterArray;
    private List<String> aniArray;
    static List<String> imageList = new ArrayList<String>();
    public static Map<String, Image> imageMap = new HashMap<String, Image>();
    public static Map<String, Animation[]> aniMap = new HashMap<String, Animation[]>();
    private Image loadimg;

    public ImageLoader() throws SlickException {
        //sprite names
        imageList.add("piglet");
        imageList.add("solid");
        imageList.add("cardBack");
        imageList.add("speedSymbol");
        //char list
        blazSheet = new Animation[4];

        String charString = "blaz,npc1,greenArrow";
        characterArray = Arrays.asList(charString.split(","));
        String aniString = "flower,hit";
        aniArray = Arrays.asList(aniString.split(","));
        /**
         * Loads sprite sheets, adding them to an array of animations.
         */

        //animation object sprite loading
        // those that only have one sprite index.
        for (String s:aniArray) {
            loadimg = new Image("/res/ani/"+s+".png", false, Image.FILTER_NEAREST);
            int xsize = 16;
            int ysize = 16;
            if (s.equals("hit")) {xsize = 61; ysize=37;}
            SpriteSheet ss =  new SpriteSheet(loadimg, xsize, ysize);
            Animation ani = new Animation(ss,1);
            Animation[] aniArr = new Animation[1];
            aniArr[0] = ani;
            aniMap.put(s, aniArr);



        }
        //character animation sprites loading
        //each character, playable or NPC has 4-directional animations which
        //are loaded and then added to a map.
        for (String s:characterArray) {
            //finds /char images 0-3 for each image.
            Animation[] aniArr = new Animation[4];
            for (int i = 0; i<4; i+=1) {
                loadimg = new Image("/res/char/"+s+"/"+s+i+".png", false, Image.FILTER_NEAREST);
                SpriteSheet ss =  new SpriteSheet(loadimg, 16, 16);
                Animation ani = new Animation(ss,1);
                aniArr[i] = ani;

            }
            aniMap.put(s, aniArr);

        }


        /**
         * Produce a map of sprite associated with a spr "key" value to
         * look them up.
         * This is used for non-animated objects. The distinction must be made
         * early.
         */
        for (String s: imageList) {
            Image img;
            img = new Image("/res/"+s+".png", false, Image.FILTER_NEAREST);
            imageMap.put(s, img);
        }
        /**
         * Tilesets are loaded here.
         * Produce tilesets from by converting the image into a
         * SpriteSheet.
         */
        loadimg = new Image("/res/"+"tileset1"+".png", false, Image.FILTER_NEAREST);
        tileSet1 = new SpriteSheet(loadimg, 16, 16);





    }



}
