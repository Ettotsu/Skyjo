package org.skyjo.game;

import org.skyjo.ui.UI;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.ArrayList;

public class Game {

    public static final int DECK_ROWS = 3, DECK_COLS = 4;
    private int nbPlayers; // Number of players
    private int currentPlayer; // Number of the player who is playing
    private boolean firstRoundDone = false; // True if the players have chosen their 2 cards
    private boolean cardSelected = false; // True if the player has chosen a card (for the first round)
    private int endRound = 0; // Number of the player who ends the round
    private boolean stackChosen = false; // True if the player has chosen stack at the beginning of its turn
    private boolean discardChosen = false; // True if the player has chosen the discard at the beginning of its turn or after having chosen the stack

    private CardStack stack;
    private Card discard;
    private LinkedHashMap<Integer, Player> playersMap; // LinkedHashMap to keep the order of the players

    UI ui; // UI object

    public CardStack getStack() {
        return this.stack;
    }

    public void setDiscard(Card card) {
        card.setFaceUp();
        this.discard = card;
    }

    public Card getDiscard() {
        return this.discard;
    }

    public void makePlayersMap(int nbPlayers, String[] names) {
        this.nbPlayers = nbPlayers;
        this.playersMap = new LinkedHashMap<>();
        for (int i = 1; i <= nbPlayers; i++) {
            playersMap.put(i, new Player(i, names[i]));
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

    public int getEndRound(){
        return this.endRound;
    }

    public void setEndRound(int player) {
        this.endRound = player;
    }

    public void incrementCurrentPlayer() {
        if (this.currentPlayer == this.nbPlayers) {
            this.currentPlayer = 1;
        } else {
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
        this.stackChosen = b;
    }

    public boolean getStackChosen() {
        return this.stackChosen;
    }

    public void setDiscardChosen(boolean b) {
        this.discardChosen = b;
    }

    public boolean getDiscardChosen() {
        return this.discardChosen;
    }

    public boolean checkPerfectColumn(int player) {
        int value = this.getPlayer(player).checkPerfectColumn();
        if(value != -3){
            Card card = new Card(value);
            card.setFaceUp();
            this.setDiscard(card);
            return true;
        }
        else{
            return false;
        }
    }

    public void addScore(int player, int score) {
        playersMap.get(player).addScore(score);
    }

    public int getMaxScore() {
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.naturalOrder());
        for (int i = 1; i <= nbPlayers; i++) {
            map.put(playersMap.get(i).getScore(), i);
        }
        System.out.println("The winner is " + getPlayer(map.lastEntry().getValue()).getName() + " with " + map.lastEntry().getKey() + " points");
        return map.lastEntry().getValue();

    }

    public void resetlAllScores() {
        for (int i = 1; i <= nbPlayers; i++) {
            playersMap.get(i).setScore(0);
        }
    }

    public int over120() {
        for (int i = 1; i <= nbPlayers; i++) {
            if (this.playersMap.get(i).getScore() >= 120) {
                return i;
            }
        }
        return 0;
    }

    public ArrayList<Integer> getWinners() {
        ArrayList<Integer> winners = new ArrayList<Integer>();
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


    public Card getPlayerCard(int player, int row, int col) {
        return this.playersMap.get(player).getCard(row, col);
    }


    public void makeCurrentPlayer() {
        int firstPlayer = 1;
        for (int i = 2; i <= this.nbPlayers; i++) {
            if (this.getPlayer(i).getFlippedScore() > this.getPlayer(firstPlayer).getFlippedScore()) {
                firstPlayer = i;
            }
        }
        this.setCurrentPlayer(firstPlayer);
    }


    public void resetGame() {
        this.currentPlayer = 1;
        this.firstRoundDone = false;
        this.cardSelected = false;
        this.endRound = 0;
        this.stackChosen = false;
        this.discardChosen = false;
        stack = new CardStack();
        discard = stack.drawCard();

        for (int i = 1; i <= this.nbPlayers; i++) {
            playersMap.get(i).drawInitialDeck(stack, DECK_ROWS, DECK_COLS);
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
