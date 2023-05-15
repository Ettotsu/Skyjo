package org.skyjo.game;


public class Player {
    private final String name; // name of the player
    private int score=0; // score of the player
    private Card[][] deck; // deck of the player

    /**
     * Constructor
     * Sets the name of the player
     * @param number number of the player
     * @param name name of the player
     */
    public Player(int number, String name){
        if(name.equals("")) {
            this.name = "Player " + number;
        }
        else{
            this.name = name;
        }
    }

    /**
     * Prints the cards of the player for the CLI version
     */
    public void printCards() {
        Card card;
        int cardCounter = 0;
        for (Card[] cards : deck) {
            for (Card value : cards) {
                card = value;
                if (card.isBlank()){
                    System.out.print("/ ");
                }
                else if (card.isFaceUp()) {
                    System.out.print(card.getValue() + " ");
                }
                else {
                    System.out.print("X ");
                }
                cardCounter++;
                if (cardCounter % 4 == 0) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the score
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the score of the player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Adds the score to the current score of the player
     * @param score the score to add
     */
    public void addScore(int score) {
        this.score = this.score + score;
    }

    /**
     * Calculates the score of the player
     * @return the score
     */
    public int calculateScore() {
        int score = 0;
        for (Card[] cards : deck) {
            for (Card card : cards) {
                    score += card.getValue();
            }
        }
        return score;
    }

    /**
     * Face up all the cards of the player
     */
    public void flipAll() {
        for (Card[] cards : deck) {
            for (Card card : cards) {
                card.setFaceUp();
            }
        }
    }

    /**
     * Draw the initial deck of the player
     * @param stack the stack of cards
     * @param row number of rows
     * @param col number of columns
     */
    public void drawInitialDeck(CardStack stack, int row, int col){
        deck = new Card[Game.DECK_ROWS][Game.DECK_COLS];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                deck[i][j] = stack.drawCard();
            }
        }
    }

    /**
     * Get a card based on its position
     * @param row the row
     * @param col the column
     * @return card
     */
    public Card getCard(int row, int col){
        return this.deck[row][col];
    }

    /**
     * Checks if there is a perfect column
     * @return the value of the cards in the perfect column if there is one, 13 otherwise
     */
    public int checkPerfectColumn() {
        int firstValue = 13; // Default value
        for(int i=0;i<Game.DECK_COLS; i++) {
            firstValue = deck[0][i].getValue();
            for(int j=1;j<Game.DECK_ROWS;j++) {
                if((deck[j][i].getValue() != firstValue) || (!deck[j][i].isFaceUp()) || (deck[j][i].isBlank())) { // If the value is not the same or the card is not face up or the card is blank
                    firstValue = 13;
                }
                else{
                    firstValue = deck[j][i].getValue();
                }
            }
            if (firstValue != 13){
                for(int j=0;j<Game.DECK_ROWS;j++) {
                    deck[j][i].setBlank();
                    deck[j][i].setValue(0);
                }
                return firstValue;
            }
        }
        return firstValue;
    }

    /**
     * Checks if all the cards are flipped
     * @return true if all the cards are flipped, false otherwise
     */
    public boolean checkCardsFlipped(){
        int counter = 0;
        for (int i = 0; i<Game.DECK_ROWS; i++){
            for (int j = 0; j<Game.DECK_COLS; j++){
                if (this.deck[i][j].isBlank() || this.deck[i][j].isFaceUp()){
                    counter += 1;
                }
            }
        }
        return counter == Game.DECK_COLS * Game.DECK_ROWS;
    }

    /**
     * Switches the card with the card in the position (row, col)
     * @param card the card to put in the position (row, col)
     * @param row the row
     * @param col the column
     * @return the previous card that goes to the discard
     */
    public Card switchCard(Card card, int row, int col){
        Card temp = this.deck[row][col];
        this.deck[row][col] = card;
        return temp;
    }

    /**
     * Calculate the score of the flipped cards, used only for the first round in the CLI version
     * @return the score of the flipped cards
     */
    public int getFlippedScore() {
        int score = 0;
        Card card;
        for (Card[] cards : deck) {
            for (Card value : cards) {
                card = value;
                if (card.isFaceUp()) {
                    score += card.getValue();
                }
            }
        }
        return score;
    }
}
