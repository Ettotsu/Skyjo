package org.skyjo.game;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
    private ArrayList<Card> cardList;

    /**
     * Constructor
     * Creates a new card stack with 5 -2 cards, 10 -1 cards, 15 0 cards and 120 cards with values from 1 to 12
     * Shuffles the card stack
     */
    public CardStack(){
        cardList = new ArrayList<>();
        for(int i=0;i<5;i++){
            cardList.add(new Card(-2));
        }
        for(int i=0;i<10;i++){
            cardList.add(new Card(-1));
        }
        for(int i=0;i<15;i++){
            cardList.add(new Card(0));
        }
        for (int i=0;i<120;i++) {
            cardList.add(new Card(i / 10 + 1));
        }

        Collections.shuffle(cardList);
    }

    /**
     * Prints the cards in the stack
     * Used for debugging
     */
    public void printCards(){
        for (Card card : cardList) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println();
    }

    /**
     * Faces up the last card in the stack
     */
    public void faceUpCard() {
        cardList.get(cardList.size() - 1).setFaceUp();
    }

    /**
     * Draws the last card in the stack
     * @return the card
     */
    public Card drawCard(){
        int nb_of_cards = cardList.size();
        Card card = cardList.get(nb_of_cards - 1);
        cardList.remove(nb_of_cards - 1);
        return card;
    }

    /**
     * @return the last card in the stack
     */
    public Card getLastCard(){
        return cardList.get(cardList.size()-1);
    }
}