package org.skyjo.game;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
    private ArrayList<Card> CardList;
    public CardStack(){
        CardList = new ArrayList<>();
        for(int i=0;i<5;i++){
            CardList.add(new Card(-2));
        }
        for(int i=0;i<10;i++){
            CardList.add(new Card(-1));
        }
        for(int i=0;i<15;i++){
            CardList.add(new Card(0));
        }
        for (int i=0;i<120;i++) {
            CardList.add(new Card(i / 10 + 1));
        }

        Collections.shuffle(CardList);
    }
    public void printCards(){
        for (Card card : CardList) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println();
    }

    public Card drawCard(){
        int nb_of_cards = CardList.size();
        Card card = CardList.get(nb_of_cards - 1);
        CardList.remove(nb_of_cards - 1);
        return card;
    }

    public Card getLastCard(){
        return CardList.get(CardList.size()-1);
    }
}