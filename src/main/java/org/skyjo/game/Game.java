package org.skyjo.game;

import org.skyjo.ui.UI;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Game {

    public static final int DECK_ROWS = 3, DECK_COLS = 4;
    private int nbPlayers; // Number of players
    private int currentPlayer; // Number of the player who is playing
    private boolean firstRoundDone=false; // True if the players have chosen their 2 cards
    private boolean cardSelected=false; // True if the player has chosen a card (for the first round)
    private int endRound; // Number of the player who ends the round
    private boolean stackChosen=false; // True if the player has chosen stack at the beginning of its turn
    private boolean discardChosen=false; // True if the player has chosen the discard at the beginning of its turn or after having chosen the stack

    private CardStack stack;
    private Card discard;
    private LinkedHashMap<Integer, Player> playersMap; // LinkedHashMap to keep the order of the players

    private TreeMap<Integer, Integer> scoresMap; // TreeMap to sort the scores of the players

    UI ui; // UI object

    public CardStack getStack() {
        return this.stack;
    }
    public void setDiscard(Card card) {
        this.discard=card;
    }
    public Card getDiscard() {
        return this.discard;
    }

    public void makePlayersMap(int nbPlayers, String[] names){
        this.nbPlayers = nbPlayers;
        this.playersMap = new LinkedHashMap<>();
        for(int i=1;i<=nbPlayers;i++){
            playersMap.put(i,new Player(i, names[i]));
        }
    }
    public Player getPlayer(int player) {
        return this.playersMap.get(player);
    }
    public String getPlayerName(int value) {
        return playersMap.get(value).getName();
    }
    public int getNbPlayers() {
        return this.nbPlayers;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void incrementCurrentPlayer() {
        if(this.currentPlayer==this.nbPlayers) {
            this.currentPlayer = 1;
        }
        else {
            this.currentPlayer++;
        }
    }
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }


    public void setUI(UI ui) {
        this.ui = ui;
    }

    public void setFirstRoundDone() {
        this.firstRoundDone = true;
    }
    public boolean getFirstRoundDone() {
        return this.firstRoundDone;
    }
    public void setCardSelected(boolean b) {
        this.cardSelected = b;
    }
    public boolean getCardSelected() {
        return this.cardSelected;
    }

    public void setStackChosen(boolean b) {
        this.stackChosen=b;
    }
    public boolean getStackChosen() {
        return this.stackChosen;
    }
    public void setDiscardChosen(boolean b) {
        this.discardChosen=b;
    }
    public boolean getDiscardChosen() {
        return this.discardChosen;
    }

    public void addScore(int player, int score) {
        playersMap.get(player).addScore(score);
    }
    public int getMaxScore() {
        TreeMap<Integer, Integer> scoresMap = new TreeMap<>();
        for(int i=1;i<=nbPlayers;i++){
            scoresMap.put(playersMap.get(i).getScore(),i);
        }
        return scoresMap.lastEntry().getValue();
    }
    private boolean over120(LinkedHashMap<Integer, Player> playersMap){
        for(int i=1;i<=nbPlayers;i++){
            if(playersMap.get(i).getScore()>=120){
                return true;
            }
        }
        return false;
    }


    public void resetGame() {
        this.currentPlayer = 1;
        this.firstRoundDone = false;
        this.cardSelected = false;
        this.endRound = 0;
        this.stackChosen = false;
        this.discardChosen = false;
        stack = new CardStack();
        stack.printCards();
        discard = stack.drawCard();

        for(int i=1;i<=this.nbPlayers;i++) {
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


}

