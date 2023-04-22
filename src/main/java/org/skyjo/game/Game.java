package org.skyjo.game;

import org.skyjo.ui.UI;

import java.util.LinkedHashMap;

public class Game {
    private int nb_player=0, endgame=0;
    private int rows, cols;
    private CardStack stack;
    private Card discard=null;
    private LinkedHashMap<Integer, Player> map;

    public Game(int rows, int cols){
        this.rows=rows;
        this.cols=cols;
        stack = new CardStack();
        stack.printCards();
    }



    public int getEnd(){
            return endgame;
        }

    public void generatePlayer(int nb_player){
        this.nb_player=nb_player;
        map = new LinkedHashMap<>();
        for(int i=1;i<=this.nb_player;i++){
            map.put(i, new Player(i, rows, cols));
            map.get(i).drawCard(stack, rows, cols);
            map.get(i).printCards();
            System.out.println();
        }
    }
    public void startGame(){
        stack = new CardStack();
    }
}

