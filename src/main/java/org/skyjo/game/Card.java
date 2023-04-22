package org.skyjo.game;

public class Card {
    private boolean isFaceUp = false;
    private int value=13;

    public Card(){}
    public Card(int value){
        if(value < -2 || value > 12){
            System.out.println(("Warning, Wrong card value!"));
        }
        else{
            this.value=value;
        }
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
