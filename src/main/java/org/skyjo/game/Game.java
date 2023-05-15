package org.skyjo.game;

import org.skyjo.ui.UI;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.ArrayList;

public class Game {

    public static final int DECK_ROWS = 3, DECK_COLS = 4; // Number of rows and columns of the deck
    private int nbPlayers; // Number of players
    private int currentPlayer; // Number of the player who is playing
    private boolean firstRoundDone = false; // True if the players have chosen their 2 cards
    private boolean cardSelected = false; // True if the player has chosen a card (for the first round)
    private int endRound = 0; // Number of the player who ends the round
    private boolean stackChosen = false; // True if the player has chosen the stack at the beginning of its turn
    private boolean discardChosen = false; // True if the player has chosen the discard at the beginning of its turn or after having chosen the stack

    private CardStack stack; // The stack of cards
    private Card discard; // The discard card (there is no use to store the other cards of the discard because they are not playable)
    private LinkedHashMap<Integer, Player> playersMap; // LinkedHashMap to keep the order of the players
    UI ui; // UI object

    /**
     * @return the cardstack
     */
    public CardStack getStack() {
        return this.stack;
    }

    /**
     * Put a card on the discard
     * @param card the card to put on the discard
     */
    public void setDiscard(Card card) {
        card.setFaceUp();
        this.discard = card;
    }

    /**
     * @return the discard card
     */
    public Card getDiscard() {
        return this.discard;
    }

    /**
     * Generates the map containing the players
     * @param nbPlayers number of players in the current game
     * @param names array containing the names of the players
     */
    public void makePlayersMap(int nbPlayers, String[] names) {
        this.nbPlayers = nbPlayers;
        this.playersMap = new LinkedHashMap<>();
        for (int i = 1; i <= nbPlayers; i++) {
            playersMap.put(i, new Player(i, names[i]));
        }
    }

    /**
     * @param player number of the player
     * @return the player
     */
    public Player getPlayer(int player) {
        return this.playersMap.get(player);
    }

    /**
     * @return the number of players
     */
    public int getNbPlayers() {
        return this.nbPlayers;
    }

    /**
     * Sets the currentPlayer to the value of the parameter
     * @param currentPlayer the number of the player who is playing
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the number of the player who ends the round
     */
    public int getEndRound(){
        return this.endRound;
    }

    /**
     * Sets the number of the player who ends the round
     */
    public void setEndRound(int player) {
        this.endRound = player;
    }

    /**
     * Increements the number of the currentPlayer
     */
    public void incrementCurrentPlayer() {
        if (this.currentPlayer == this.nbPlayers) {
            this.currentPlayer = 1;
        } else {
            this.currentPlayer++;
        }
    }

    /**
     * Sets the reference to the UI object
     * @param ui the UI object
     */
    public void setUI(UI ui) {
        this.ui = ui;
    }

    /**
     * @return the number of the currentPlayer
     */
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Sets the firstRoundDone to true, which means that the players have chosen their 2 cards
     */
    public void setFirstRoundDone() {
        this.firstRoundDone = true;
    }

    /**
     * @return true if the players have chosen their 2 cards
     */
    public boolean getFirstRoundDone() {
        return this.firstRoundDone;
    }

    /**
     * Sets the cardSelected boolean to either true or false, depending on the parameter
     * @param b true if the player has chosen a card
     */
    public void setCardSelected(boolean b) {
        this.cardSelected = b;
    }

    /**
     * @return true if the player has chosen a card
     */
    public boolean getCardSelected() {
        return this.cardSelected;
    }

    /**
     * Sets the stackChosen boolean to either true or false, depending on the parameter
     * @param b true if the player has chosen the stack
     */
    public void setStackChosen(boolean b) {
        this.stackChosen = b;
    }

    /**
     * @return true if the player has chosen the stack
     */
    public boolean getStackChosen() {
        return this.stackChosen;
    }

    /**
     * Sets the discardChosen boolean to either true or false, depending on the parameter
     * @param b true if the player has chosen the discard
     */
    public void setDiscardChosen(boolean b) {
        this.discardChosen = b;
        System.out.println("Warning, discardChosen set to " + b + " !");
    }

    /**
     * @return true if the player has chosen the discard
     */
    public boolean getDiscardChosen() {
        return this.discardChosen;
    }

    /**
     * Checks if there is a perfect column (same values for all the cards in a column) in the player's deck
     * @param player number of the player
     * @return true if there is a perfect column
     */
    public boolean checkPerfectColumn(int player) {
        int value = this.getPlayer(player).checkPerfectColumn();
        if(value != 13){
            Card card = new Card(value);
            card.setFaceUp();
            this.setDiscard(card);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds the score to the current score of the player
     * @param player number of the player
     * @param score score to add to the current score
     */
    public void addScore(int player, int score) {
        playersMap.get(player).addScore(score);
    }

    /**
     * @param b True if we want the player with the highest score, false if we want the player with the lowest score
     * @return the number of the player with the highest score
     */
    public int getMaxScore(boolean b) {
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.naturalOrder()); // TreeMap to sort the scores
        for (int i = 1; i <= nbPlayers; i++) {
            map.put(playersMap.get(i).getScore(), i); // Puts the scores (key) and the players (value) in the TreeMap
        }
        if(b) {
        return map.lastEntry().getValue();
        }
        else {
            return map.firstEntry().getValue();
        }
    }


    /**
     * Resets the score of all the players to 0
     */
    public void resetlAllScores() {
        for (int i = 1; i <= nbPlayers; i++) {
            playersMap.get(i).setScore(0);
        }
    }

    /**
     * @return the number of the player who has a score of 120 or more
     */
    public int over120() {
        for (int i = 1; i <= nbPlayers; i++) {
            if (this.playersMap.get(i).getScore() >= 120) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @return a list containing the winners. If there is only one winner, the list will contain only one value
     */
    public ArrayList<Integer> getWinners() {
        ArrayList<Integer> winners = new ArrayList<>();
        winners.add(1);
        for (int i = 1; i < nbPlayers; i++) {
            if (this.playersMap.get(i).getScore() < this.playersMap.get(winners.get(0)).getScore()){ // checks if the current player has a lower score than the first player of the winners
                winners.clear(); //clears all the previous winners
                winners.add(i);
            }
            else if (this.playersMap.get(i).getScore() == this.playersMap.get(winners.get(0)).getScore()){ //checks if the current player has the same score as the winner
                winners.add(i);
            }
        }

        return winners;
    }

    /**
     * @param player number of the player
     * @param row row of the card
        * @param col column of the card
     * @return a Card based on the parameters
     */
    public Card getPlayerCard(int player, int row, int col) {
        return this.playersMap.get(player).getCard(row, col);
    }

    /**
     * Determines the player with the highest score and sets it as the currentPlayer at the end of the first round
     */
    public void makeCurrentPlayer() {
        int firstPlayer = 1;
        for (int i = 2; i <= this.nbPlayers; i++) {
            if (this.getPlayer(i).getFlippedScore() > this.getPlayer(firstPlayer).getFlippedScore()) {
                firstPlayer = i;
            }
        }
        this.setCurrentPlayer(firstPlayer);
    }

    /**
     * Resets the variables and objects used in the game
     */
    public void resetGame() {
        this.currentPlayer = 1;
        this.firstRoundDone = false;
        this.cardSelected = false;
        this.endRound = 0;
        this.stackChosen = false;
        this.discardChosen = false;
        stack = new CardStack();
        Card card = stack.drawCard();
        card.setFaceUp();
        discard = card;

        for (int i = 1; i <= this.nbPlayers; i++) {
            playersMap.get(i).drawInitialDeck(stack, DECK_ROWS, DECK_COLS);
        }
    }

    /**
     * Starts the game
     */
    public void startGame() {
        this.resetGame(); //resets the game
        ui.gameInterface(); //starts the game interface
    }
}
