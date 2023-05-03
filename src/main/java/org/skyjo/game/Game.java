package org.skyjo.game;

import org.skyjo.ui.UI;

import java.util.LinkedHashMap;

public class Game {

    public static final int DECK_ROWS = 3, DECK_COLS = 4;
    private int nbPlayers=0, endgame=0, isPlaying=0;
    private CardStack stack;
    private Card discard=null;
    private LinkedHashMap<Integer, Player> playersMap;

    UI window;

    public Game(){

    }


    public int getEnd(){
            return endgame;
        }


    public void makePlayersMap(int nbPlayers, String[] names){
        this.nbPlayers = nbPlayers;
        this.playersMap = new LinkedHashMap<>();
        for(int i=1;i<=nbPlayers;i++){
            playersMap.put(i,new Player(i, names[i]));
        }
        System.out.println("Players map set");
    }

    public void getUI(UI window){
        this.window = window;
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
        System.out.println("Number of players: " + nbPlayers);
    }
}

