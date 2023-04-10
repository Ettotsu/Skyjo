package org.skyjo.game;

import java.util.LinkedHashMap;

public class Game {
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
        map = new LinkedHashMap<>();
        this.nb_player=nb_player;
        for(int i=1;i<=nb_player;i++){
            map.put(i, new Player(i));
            map.get(i).drawCard(stack, 4, 3);
        }
    }
    public void startGame(){}
}

