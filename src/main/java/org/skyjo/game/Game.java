package org.skyjo.game;

import org.skyjo.ui.UI;

import java.util.LinkedHashMap;

public class Game {

    public static final int DECK_ROWS = 3, DECK_COLS = 4;
    private int nbPlayers; // Number of players
    private int currentPlayer; // Number of the player who is playing
    private boolean firstRoundDone=false; // True if the players have chosen their 2 cards
    private int cardsSelected=0; // Number of cards selected by the player during the first round
    private int endRound; // Number of the player who ends the round
    private boolean discardChosen=false; // True if the player has chosen to put the card from the stack on the discard pile


    private CardStack stack;
    private Card discard;
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
        return this.playersMap.get(player).getCard(row,col);
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }
    public int getNbPlayers() {
        return this.nbPlayers;
    }

    public boolean isFirstRoundDone() {
        return this.firstRoundDone;
    }

    public void resetGame() {
        this.currentPlayer = 1;
        this.firstRoundDone = false;
        this.cardsSelected = 0;
        this.endRound = 0;
        this.discardChosen = false;
        stack = new CardStack();
        stack.printCards();
        discard = stack.drawCard();


        for(int i=1;i<=this.nbPlayers;i++){
            playersMap.get(i).drawInitialDeck(stack,DECK_ROWS,DECK_COLS);
            playersMap.get(i).printCards();
            System.out.println(playersMap.get(i).getName() + " has " + playersMap.get(i).getScore() + " points");
        }
    }

    public void startGame() {
        this.resetGame();

        stack.printCards();
        System.out.println("Number of players: " + nbPlayers);

        ui.gameInterface();

    }

    public Card getDiscard() {
        return this.discard;
    }
    public String getPlayerName(int value){
        return playersMap.get(value).getName();
    }
}

