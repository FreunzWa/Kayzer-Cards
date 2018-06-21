package com.company;


import com.company.constants.Colors;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static com.company.constants.Colors.BLACK;

/**
 * Created by Freunz Wa
 */
public class Card {

    String name;
    private int TB;
    public int x = 0;
    public int y = 0;
    private int HP;
    private int SP;
    private int ID;
    private Image cardPicture;
    private Image smallCardSprite;
    private Image cardBack;
    private String state = "deck"; //{field, hand, graveyard, deck}
    public CardContainer container;

    public Image getSprite() {
        Image returnimg;
        //System.out.printf("the ID of the ccard %s was%d\n" , name,ID);
        try {
            returnimg = Main.cardLib.cardSpriteSheet.getSprite(ID, 0);
        }
        catch (Exception e) {
            returnimg = Main.cardLib.cardSpriteSheet.getSprite(0, 0);
        }
        return returnimg;
    }


    public Card(String name) {

        this.name = name;
        try {
            this.ID = Main.cardLib.getStat(name, "ID");
            this.TB = Main.cardLib.getStat(name, "TB");
            this.HP = Main.cardLib.getStat(name, "HP");
            this.SP = Main.cardLib.getStat(name, "SP");
            cardPicture = getSprite();
            smallCardSprite = new Image("res/duel/cardBrown.png",false, Image.FILTER_NEAREST);
            cardBack = Main.images.imageMap.get("cardBack");
        }
        catch (Exception e) {
            System.out.println("The card does not exist!");
            e.printStackTrace();
            System.exit(0);
        }
    }


    public int getTB() {
        return this.TB;
    }
    public int getHP() {
        return this.HP;
    }
    public void damageHP(int damage) {
        this.HP -= damage;
    }
    public void healHP(int heal) {
        this.HP += heal;
    }
    public int getSP() {
        return this.SP;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String newState) {
        if (newState != "field" && newState != "grave" && newState != "deck" && newState != "hand") {
            System.out.println("A disallowed state was entered!");
        }
        else {
            this.state = newState;
        }

    }
    public void draw(Graphics g, int x, int y) {
        Image drawspr;
        this.x = x;
        this.y = y;
        if (this.getState().equals("field")) {
            drawspr = this.cardPicture;
        }
        else if (y >= Main.view.height/2) {
            drawspr = this.smallCardSprite;
        }
        else {
            drawspr = this.cardBack;
        }

        g.drawImage(drawspr, x,y);
        //information drawn by each card

        if (this.getState().equals("field")) {
            g.setColor(Colors.RED);
            g.setFont(Main.fonts.basicFont);
            g.drawString("HP:" + this.getHP(), x - 30, y + 16);
            g.drawString("TB: " + this.getTB(), x - 30, y + 1);
            for (int i = 0; i < this.getSP(); i++) {
                g.drawImage(Main.images.imageMap.get("speedSymbol"), x + 2 + i * 4, y + 32);
            }
        }
    }




}
