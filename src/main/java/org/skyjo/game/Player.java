package org.skyjo.game;

import java.util.ArrayList;

public class Player {
    private String name;
    private int number=0, score=0;
    private Card[][] deck;

    public Player(int number, int column, int line){
        deck = new Card[column][line];
        this.number = number;
    }
    public void printCards(){
        for(int i=0;i<deck.length;i++){
            for(int j=0;j<deck[i].length;j++){
                System.out.print(deck[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    public void setName(String name){
        this.name=name;
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
    public void drawCard(CardStack list, int column, int line){
        for(int i=0;i<column;i++){
            for(int j=0;j<line;j++){
                deck[i][j] = list.drawCard();
            }
        }
    }

    public void addScore(int score){
        this.score = this.score + score;
    }
}
