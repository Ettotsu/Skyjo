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
        for (int i = 0; i < deck.length; i++) {
            for (int j = 0; j < deck[i].length; j++) {
                card = this.deck[i][j];
                if (card.isFaceUp()) {
                    System.out.print(card.getValue() + " ");
                } else {
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

    public void updateScore() {
        for (Card[] cards : deck) {
            for (Card card : cards) {
                    this.score = this.score + card.getValue();
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
    public void checkPerfectColumn() {
        int firstValue;
        for(int i=0; i<Game.DECK_COLS;i++) {
            firstValue = deck[0][i].getValue();
            for(int j=1;j<Game.DECK_ROWS;j++) {
                if(deck[j][i].getValue() != firstValue) {
                    return;
                }
            }
            for(int j=0;j<Game.DECK_ROWS;j++) {
                deck[j][i].setBlank();
            }
        }
    }

    public Card getCard(int row, int col){
        return this.deck[row][col];
    }


    public Card switchCard(Card card, int row, int col){
        Card temp = this.deck[row][col];
        this.deck[row][col] = card;
        return temp;
    }
    public int getFlippedScore() {
        int score = 0;
        Card card;
        for (int i = 0; i < deck.length; i++) {
            for (int j = 0; j < deck[i].length; j++) {
                card = this.deck[i][j];
                if (card.isFaceUp()) {
                    score += card.getValue();
                }
            }
        }
        return score;
    }
}
