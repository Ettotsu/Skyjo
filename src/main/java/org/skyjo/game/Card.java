package org.skyjo.game;

public class Card {
    private boolean isFaceUp = false;
    private int value;
    private boolean blank = false;

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
    public void setBlank(){ this.blank=true; }
}
