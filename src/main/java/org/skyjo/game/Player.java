package org.skyjo.game;

import static org.skyjo.game.Game.DECK_COLS;
import static org.skyjo.game.Game.DECK_ROWS;

public class Player {
    private String name;
    private int number=0, score=0;
    private Card[][] deck;



    public Player(int number, String name){
        this.number = number;
        if(name.equals("")) {
            this.name = "Player " + number;
        }
        else{
            this.name = name;
        }
    }
    public void printCards(){
        for(int i=0;i<deck.length;i++){
            for(int j=0;j<deck[i].length;j++){
                System.out.print(deck[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    public String getName(){
        return name;
    }
    public void setScore(int score){
        this.score = this.score + score;
    }
    public int getScore(){
        return this.score;
    }
    public void addScore(int score){
        this.score = this.score + score;
    }
    public void drawInitialDeck(CardStack stack, int row, int col){
        deck = new Card[DECK_ROWS][DECK_COLS];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                deck[i][j] = stack.drawCard();
            }
        }
    }

    public Card getCard(int row, int col){
        return this.deck[row][col];
    }

}
