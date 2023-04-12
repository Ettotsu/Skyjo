package org.skyjo.game;

import java.util.LinkedHashMap;

public class Game {
    final int DECK_COLUMN = 4, DECK_LINE = 3;

    private int nb_player=0, endgame=0;
    CardStack stack;
    Card discard=null;
    LinkedHashMap<Integer, Player> map;

    public Game(){
    stack = new CardStack();
    }

    public int getEnd(){
            return endgame;
        }

    public void generatePlayer(int nb_player){
        this.nb_player=nb_player;
        map = new LinkedHashMap<>();
        for(int i=1;i<=this.nb_player;i++){
            map.put(i, new Player(i, DECK_COLUMN, DECK_LINE));
            map.get(i).drawCard(stack, DECK_COLUMN, DECK_LINE);
            System.out.println();
        }
    }
    public void startGame(){}
}

