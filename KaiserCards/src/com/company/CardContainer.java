package com.company;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Freunz Wa
 */
public class CardContainer {

    public int maxSize = -1;
    private List<Card> contents;
    public String type = "deck";
    public int id = 1;
    private int cardsInDeck;

    public CardContainer(String cards) {
            cards = cards.replace(", ", ",");
            String[] arr = cards.split(",");
            contents = new ArrayList<Card>();
            for (String s : arr) {
                Card newCard = new Card(s.toUpperCase());
                add(newCard);
        }

    }

    public int getCardsInDeck() {
        int count = 0;
        for (Card c:contents) {
            if (c.getState().equals("deck")) {
                count++;
            }
        }
        return count;
    }

    public CardContainer(int i) {
        if (i==0) {
            //then this is considered a "hand"
            type = "hand";
            maxSize = 3;
            contents = new ArrayList<Card>();
        }
        if (i==1) {
            //then this is considered a "field"
            type = "field";
            maxSize = 1;
            contents = new ArrayList<Card>();
        }
        if (i==2) {
            //then this is considered a "grave"
            type = "grave";
            maxSize = -1;
            contents = new ArrayList<Card>();
        }
    }


    public boolean add(Card c) {
        if (!hasSpace() || c == null) {
            error("The cardContainer is too full for adding a card.");
            return false;
        }
        else {
            contents.add(c);
            c.container = this;
            c.setState(this.type);
            return true;
        }
    }



    public Card top() {
        //returns the top card
        return contents.get(0);
    }

    /**
     *
     * @return the first card with state equal to deck.
     */

    public Card draw() {

        for (Card c:contents) {
            if (c.getState().equals("deck")) {
                return c;
            }
        }
        error("Null card returned!");
        return null;
    }



    public boolean summon(Card c, CardContainer targetField){
        if (type.equals("hand") && c.getState().equals("hand")) {
            c.setState("field");
            System.out.println(c.name + " was summoned!");
            transferCard(c, targetField);
            return true;
        }
        else if (type.equals("deck")) {
            error("Tried to summon a card from the deck!");
            return false;
        }
        else {
            error("Failed summon.");
            return false;}
    }

    public boolean destroy(Card c, CardContainer targetGrave){

        if (type.equals("field") && c.getState().equals("field")) {
            c.setState("grave");
            transferCard(c, targetGrave);
            return true;
        }
        else if (type.equals("deck")) {
            error("Tried to destroy a card from the deck!");
            return false;
        }
        else {
            error("Failed destroy.");
            return false;}
    }

    public List<Card> getContents() {
        return contents;
    }

    public Card getCardByInd(int ind) {
        return contents.get(ind);
    }

    public boolean transferCard(Card c, CardContainer to) {
        if (to.hasSpace()) {
            to.add(c);
            this.contents.remove(c);
            return true;
        }
        else {
            error("Transfer failed. The target does not have enough space for transfer.");
            return false;
        }
    }

    public boolean hasSpace() {
        if (maxSize<0) {
            return true;
        }
        else {
            //System.out.printf("There was %d slots available from a possible %d slots.\n",maxSize-contents.size(), maxSize);
            return (maxSize-contents.size()>0);
        }
    }

    public void remove(Card c) {
        contents.remove(c);
    }
    public int size() {
        return contents.size();
    }
    public boolean allGrave() {
        for (Card c:contents) {
            if (!c.getState().equals("grave")) {
                return false;
            }
        }
        return true;
    }

    public void resetStates() {
        for (Card c:contents) {
            c.setState("deck");
        }
    }

    public void error(String s) {

        //System.out.println("ERROR MESSAGE: "+s);
    }

    public void draw(Graphics g) {
        if (this.type.equals("deck")) {
            int cardNumber = getCardsInDeck();
            int drawx = 2;
            int drawy = 113;

            for (int i = 0; i < Math.ceil(cardNumber/4.0); i++) {
                g.drawImage(Main.images.imageMap.get("cardBack"), 2 + i, drawy - i * 1 - (drawy - 5) * (this.id - 1));
            }
        }
        else if (this.type.equals("hand")) {
            for (int i = 0; i<this.contents.size(); i++) {
                this.contents.get(i).draw(g, 97+21*i, 113-(112)*(this.id-1));
            }
        }
        else if (this.type.equals("field")) {
            for (int i = 0; i<this.contents.size(); i++) {
                this.contents.get(i).draw(g, 32, 71-(39)*(this.id-1));
            }
        }
    }
}
