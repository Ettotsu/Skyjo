package org.skyjo.game;

public class Card {
    private boolean isFaceUp = false;
    private int value=0;

    public Card(){}
    public Card(int value){
        this.value=value;
    }
    public int getValue(){
        return this.value;
    }
    public void setFaceUp(){
        this.isFaceUp=true;
    }
    public boolean isFaceUp(){
        return this.isFaceUp;
    }
}
