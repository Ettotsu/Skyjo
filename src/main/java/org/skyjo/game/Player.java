package org.skyjo.game;


public class Player {
    private final String name;
    private int score=0;
    private Card[][] deck;



    public Player(int number, String name){
        if(name.equals("")) {
            this.name = "Player " + number;
        }
        else{
            this.name = name;
        }
    }

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

    public String getName() {
        return name;
    }
    public void setScore(int score) {
        this.score = this.score + score;
    }
    public int getScore() {
        return this.score;
    }
    public void addScore(int score) {
        this.score = this.score + score;
    }
    public int calculateScore() {
        int score = 0;
        for (Card[] cards : deck) {
            for (Card card : cards) {
                    score += card.getValue();
            }
        }
        return score;
    }

    public void flipAll() {
        for (Card[] cards : deck) {
            for (Card card : cards) {
                card.setFaceUp();
            }
        }
    }

    public void drawInitialDeck(CardStack stack, int row, int col){
        deck = new Card[Game.DECK_ROWS][Game.DECK_COLS];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                deck[i][j] = stack.drawCard();
            }
        }
    }

    public Card getCard(int row, int col){
        return this.deck[row][col];
    }

    public int checkPerfectColumn() {
        int firstValue = 13;
        for(int i=0;i<Game.DECK_COLS; i++) {
            firstValue = deck[0][i].getValue();
            System.out.println("Debug first value "+firstValue);
            for(int j=1;j<Game.DECK_ROWS;j++) {
                System.out.println("Debug check value "+deck[j][i].getValue());
                if((deck[j][i].getValue() != firstValue) || (!deck[j][i].isFaceUp()) || (deck[j][i].isBlank())) { //if face down, we do not have a full column, and if blank, cannot too
                    firstValue = 13;
                }
                else{
                    firstValue = deck[j][i].getValue();
                }
            }
            if (firstValue != 13){
                for(int j=0;j<Game.DECK_ROWS;j++) {
                    System.out.println("Debug set blank");
                    deck[j][i].setBlank();
                }
                return firstValue;
            }
        }
        System.out.println("Debug returned value : "+firstValue);
        return firstValue;
    }

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

    public Card switchCard(Card card, int row, int col){
        Card temp = this.deck[row][col];
        this.deck[row][col] = card;
        return temp;
    }
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
