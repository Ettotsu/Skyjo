package org.skyjo.game;

import org.skyjo.ui.UI;

import java.util.LinkedHashMap;

public class Game {

    public static final int DECK_ROWS = 3, DECK_COLS = 4;
    private int nbPlayers=0;

    private boolean isEnded;
    private int currentPlayer=1;


    private CardStack stack;
    private Card discard=null;
    private LinkedHashMap<Integer, Player> playersMap;

    UI ui; // UI object

    public Game(){
    }


    public void makePlayersMap(int nbPlayers, String[] names){
        this.nbPlayers = nbPlayers;
        this.playersMap = new LinkedHashMap<>();
        for(int i=1;i<=nbPlayers;i++){
            playersMap.put(i,new Player(i, names[i]));
        }
    }

    private boolean over120(LinkedHashMap<Integer, Player> playersMap){
        for(int i=1;i<=nbPlayers;i++){
            if(playersMap.get(i).getScore()>=120){
                return true;
            }
        }
        return false;
    }

    public void setUI(UI ui){
        this.ui = ui;
    }

    public Card getPlayerCard(int player, int row, int col){
        return playersMap.get(player).getCard(row,col);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public int getNbPlayers() {
        return nbPlayers;
    }

    public void startGame(){
        boolean isEnded = false;
        int isPlaying = 1;
        stack = new CardStack();
        stack.printCards();
        for(int i=1;i<=this.nbPlayers;i++){
            playersMap.get(i).drawInitialDeck(stack,DECK_ROWS,DECK_COLS);
            playersMap.get(i).printCards();
            System.out.println(playersMap.get(i).getName() + " has " + playersMap.get(i).getScore() + " points");
        }
        stack.printCards();
        System.out.println("Number of players: " + nbPlayers);

        ui.gameInterface();

    }
}

