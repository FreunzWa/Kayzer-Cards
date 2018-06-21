package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Freunz Wa
 */
public class Duel extends Thread {


    Card card1;
    Card card2;
    public CardContainer[] decks;
    public CardContainer[] hands;
    public CardContainer[] fields;
    public CardContainer[] graves;
    private boolean execute = true;

    boolean duelRunning = false;
    public int playerTurn;

    public Duel(CardContainer deck1, CardContainer deck2) {
        decks = new CardContainer[2];
        decks[0] = deck1;
        decks[1] = deck2;
        playerTurn = 0;
        hands = new CardContainer[2];
        CardContainer hand1 = new CardContainer(0);
        CardContainer hand2 = new CardContainer(0);
        hands[0] = hand1;
        hand1.id = 1;
        hands[1] = hand2;
        hand2.id = 2;
        fields = new CardContainer[2];
        CardContainer field1 = new CardContainer(1);
        CardContainer field2 = new CardContainer(1);
        fields[0] = field1;
        field1.id = 1;
        fields[1] = field2;
        field2.id = 2;
        graves = new CardContainer[2];
        CardContainer grave1 = new CardContainer(2);
        CardContainer grave2 = new CardContainer(2);
        graves[0] = grave1;
        grave1.id = 1;
        graves[1] = grave2;
        grave2.id = 2;


    }

    public void endDuel() {
        this.execute = false;
    }

    public void displayEffect(int x, int y, String aniName) {
        Effect effect = new Effect(x, y, Main.images.aniMap.get(aniName)[0].copy());
        while (effect.isActive) {
            effect.update();
        }
    }

    public void displayMessage(String text) {
        Message newMessage = new Message(text);
        newMessage.height = 34;
        newMessage.y -=2;
        while (newMessage.isActive) {
                newMessage.update();
        }
    }

    public void displayMessageAlternate(String text0, String text1) {
        if (playerTurn == 0) {
            displayMessage(text0);
        }
        else {
            displayMessage(text1);
        }
    }

    @Override
    public void run() {

        Main.control.setGameState("DUEL");

        Scanner reader = new Scanner(System.in);

        duelRunning = true;
        displayMessage("You were challenged to a duel!");
        while (!decks[0].allGrave()&& !decks[1].allGrave() && this.execute) {


            //DRAW PHASE
            hands[playerTurn].add(decks[playerTurn].draw());
            displayMessageAlternate("You drew a card.", "Your opponent drew a card.");
            //deploying a card.
            //summon immediately for now.


            int useCard = 0;
            //the player's 'MAIN PHASE'
            //player's selections appear as a menu.

            if (playerTurn == 0) {
                Menu newMenu = new Menu(31, 110, 62, 32, "CARDS,END");
                while (newMenu.isActive) {
                    useCard = newMenu.getCommandByInput();

                }
            }
            // CPU's MAIN PHASE
            if (playerTurn == 1) {
                //do nothing
            }
            //SUMMONING (same for both players)
            String s = hands[playerTurn].getCardByInd(useCard).name;
            if (fields[playerTurn].hasSpace()) {
                hands[playerTurn].summon(hands[playerTurn].getCardByInd(useCard), fields[playerTurn]);
                displayMessageAlternate(Main.player.playerName+" summoned "+s+".", "Enemy duelist summoned "+s+".");
            }


            //BATTLE PHASE
            //occurs equally in either players turn if there are 2 cards
            //on the field.
            //battle continues WHILE there are 2 cards on the field.

            while (!fields[0].hasSpace() && !fields[1].hasSpace()) {
                //getting the cards for the battle phase

                card1 = fields[0].top();
                card2 = fields[1].top();

                Random random = new Random();
                int rand = random.nextInt(2);

                for (int i = 0; i < 2; i++) {

                    int ind = i;
                    if (card2.getSP() > card1.getSP()) {
                        ind = 1-i;
                    }
                    Card turnCard = fields[ind].top();
                    Card enemyCard = fields[1-ind].top();
                    //display message
                    displayMessage(turnCard.name + " attacked!");
                    displayEffect(enemyCard.x, enemyCard.y,"hit");
                    enemyCard.damageHP(turnCard.getTB());
                    displayMessage(enemyCard.name + " has " + enemyCard.getHP() + " HP remaining.");
                    if (enemyCard.getHP() <= 0) {
                        displayMessage(enemyCard.name + " was destroyed.");
                        fields[1-ind].destroy(enemyCard, graves[1-ind]);
                        break;

                    }

                }
            }
            //TURN END
            //turn is reversed.
            playerTurn = 1 - playerTurn;
        }


    }


}
