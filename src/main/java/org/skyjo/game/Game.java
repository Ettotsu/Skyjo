package org.skyjo.game;

import java.util.LinkedHashMap;

public class Game {

    public static final int DECK_ROWS = 3, DECK_COLS = 4;
    private int nbPlayers=0, endgame=0;
    private CardStack stack;
    private Card discard=null;
    private LinkedHashMap<Integer, Player> playersMap;

    public Game(){

    }

    public int getDeckRows(){
        return DECK_ROWS;
    }
    public int getDeckCols(){
        return DECK_COLS;
    }

    public int getEnd(){
            return endgame;
        }
    public void setNbPlayers(int nbPlayers){
        this.nbPlayers=nbPlayers;
    }
    public int getNbPlayers(){
        return nbPlayers;
    }

    public void setPlayersMap(LinkedHashMap<Integer,Player> playersMap){
        this.playersMap = new LinkedHashMap<>();
        this.playersMap.putAll(playersMap);
        System.out.println("Players map set");
    }
    public void startGame(){
        System.out.println("Game started");
        stack = new CardStack();
        stack.printCards();
        for(int i=1;i<=this.nbPlayers;i++){
            playersMap.get(i).drawInitialDeck(stack,DECK_ROWS,DECK_COLS);
            playersMap.get(i).printCards();
            System.out.println(playersMap.get(i).getName() + " has " + playersMap.get(i).getScore() + " points");
        }
        stack.printCards();
    }
}

