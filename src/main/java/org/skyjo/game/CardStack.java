package org.skyjo.game;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {
    private ArrayList<Integer> CardList;
    public CardStack(){
        CardList = new ArrayList<>();
        for(int i=0;i<5;i++){
            CardList.add(-2);
        }
        for(int i=0;i<10;i++){
            CardList.add(-1);
        }
        for(int i=0;i<15;i++){
            CardList.add(0);
        }
        for (int i=0;i<120;i++) {
            CardList.add(i / 10 + 1);
        }
        
        Collections.shuffle(CardList);
    }
    public void printCards(){
        for(int i=0;i<CardList.size();i++){
            System.out.print(CardList.get(i) + " ");
        }
    }

    public Card drawCard(){
        int nb_of_cards = CardList.size(), temp_val;
        temp_val = CardList.get(nb_of_cards-1);
        CardList.remove(nb_of_cards-1);
        return new Card(temp_val);
    }
}