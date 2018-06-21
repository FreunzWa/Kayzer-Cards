package com.company;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Freunz Wa
 */
public class CardLibrary {

    private String fileName = "cards.txt";
    private Map<String,Map<String, Integer>> cardLib;
    private Image loadimg;
    public SpriteSheet cardSpriteSheet;


    public int getStat(String name, String key) {
        if (!key.equals("TB")&& !key.equals("HP")&& !key.equals("SP") && !key.equals("ID")) {
            System.out.println("ERROR GETTING STAT: An inappropriate parameter was entered.");
        }
        return (cardLib.get(name)).get(key);
    }

    public CardLibrary() throws SlickException {
        cardLib = new HashMap<String, Map<String, Integer>>();
        loadimg = new Image("res/duel/cards.png", false, Image.FILTER_NEAREST);
        cardSpriteSheet = new SpriteSheet(loadimg, 61,37);

        File file = new File("res/"+fileName);
        Scanner input = null;
        try {
            input = new Scanner(file);
            System.out.println("Successfully loaded card database.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<String>();

        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }

        String keyv = "";
        Map<String, Integer> templib;
        templib = new HashMap<String, Integer>();
        int idCount = 0;
        for (String s:list) {

            if (s.length() ==0) {
                continue;
            }
            //System.out.println(list.indexOf(s));
            if (s.charAt(0) == '$') {
                if (templib.size()>0 && !keyv.equals("")) {
                    templib.put("ID",idCount);
                    cardLib.put(keyv, templib);
                    idCount++;
                }
                keyv = s.substring(1,s.length());
                templib = new HashMap<String, Integer>();
            }
            else {
                s = s.replace(" ","");
                String[] arr = s.split(":");
                templib.put(arr[0],Integer.parseInt(arr[1]));
            }
        }
        //System.out.println(cardLib); //draw the card library
    }
}
