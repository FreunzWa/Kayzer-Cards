package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.company.Main.control;
import static com.company.Main.player;
import static jdk.nashorn.internal.objects.NativeString.substring;


/**
 * Created by Freunz Wa
 */
public class MapLoader {
    int loadMapWidth =0 ;
    int loadMapHeight =0 ;
    public String[][] loadMapArr;


    public MapLoader() {

    }

    public void createMap(String mapName) {
        //algorithm for map creation
        File file = new File("res/"+mapName);
        Scanner input = null;
        try {
            input = new Scanner(file);
            System.out.println("Successfully loaded file.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<String>();
        List<String> tags = new ArrayList<String>();
        int count = 0;

        while (input.hasNextLine()) {
            count++;
            String str = input.nextLine();


            if (!str.substring(0,1).equals("#")) {
                if (loadMapWidth == 0) {
                    loadMapWidth = (str.split(",").length);
                }

                list.add(str);

                }
            else {
                System.out.println(str);
                tags.add(str);
            }
        }
        loadMapHeight = count;
        System.out.println(loadMapHeight);
        System.out.println(loadMapWidth);
        loadMapArr = new String[loadMapWidth][loadMapHeight];
        //adding tags to the loadMapArray:
        if (tags.size()>0) {
            for (String s : tags) {
                if (s.substring(0, 1).equals("#")) {
                    String[] arr = s.substring(1, s.length()).split("#");
                    System.out.println(arr.length);
                    loadMapArr[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])] = arr[2];
                    System.out.println(arr[2]);
                }
            }
        }

        for (int j = 0; j<list.size(); j++)  {
            String row = list.get(j);
            List<String> rowList = Arrays.asList(row.split(","));

            for (int i = 0; i < rowList.size(); i++) {
                String s = rowList.get(i);
                int xCoord = i * 16;
                int yCoord = j * 16;
                if (s.length()==4 && !s.equals("0b00")) {
                    if (s.substring(1,2).equals("b")) {
                        Solid newSolid = new Solid(xCoord, yCoord, s);
                    }
                    if (s.substring(1,2).equals("a")) {
                        Tile newTile = new Tile(xCoord, yCoord, s);
                    }
                    if (s.substring(1,2).equals("n")) {
                        //System.out.printf("%d,%d\n",i,j);
                        NPC newNPC = new NPC(xCoord, yCoord, getTagInfo(i,j),s);
                    }
                    if (s.equals("0p00")) {
                        player.x = xCoord;
                        player.y = yCoord;
                    }
                }

                control.mapWidth = 16*(i+1);
            }
            control.mapHeight = 16*(j+1);

        }
        System.out.printf("New map initialised with width:%d and height:%d\n",control.mapWidth, control.mapHeight);

    }

    public String getTagInfo(int xCoord, int yCoord) {

        if (this.loadMapArr[xCoord][yCoord]!=null) {
            return this.loadMapArr[xCoord][yCoord];
        }
        else {
            return "NONE";
        }
    }


}
