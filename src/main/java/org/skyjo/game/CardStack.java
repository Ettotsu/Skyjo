package org.skyjo.game;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
    private ArrayList<Card> cardList;
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
    public void printCards(){
        for (Card card : cardList) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println();
    }
    public void faceUpCard() {
        cardList.get(cardList.size() - 1).setFaceUp();
    }
    public Card drawCard(){
        int nb_of_cards = cardList.size();
        Card card = cardList.get(nb_of_cards - 1);
        cardList.remove(nb_of_cards - 1);
        return card;
    }

    public Card getLastCard(){
        return cardList.get(cardList.size()-1);
    }
}