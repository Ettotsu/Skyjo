package org.skyjo.game;

public class Card {
    private boolean isHidden = true;
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
    public void unHidden(){
        isHidden = false;
    }
    public boolean CardIsHidden() {return isHidden;}
}
