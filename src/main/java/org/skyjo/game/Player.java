package org.skyjo.game;

import java.util.ArrayList;

public class Player {
    private String name;
    private int number=0, score=0;
    private ArrayList<ArrayList<Card>> deck;

    public Player(int number){
        deck = new ArrayList<>();
        this.number = number;
    }
    /*public void printCards(){
        for(int i=0;i<deck.size();i++){
            System.out.print(deck.get(i).getValue() + " ");
        }
        System.out.print("\n");
    }*/
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
        ArrayList<Card> temp;
        for(int i=0;i<column;i++){
            temp = new ArrayList<>();
            for(int j=0;j<line;j++){
                temp.add(list.drawCard());
            }
            deck.add(temp);
            temp = null;
        }
    }

    public void addScore(int score){
        this.score = this.score + score;
    }
}
