package org.skyjo.game;

public class Card {
    private boolean isFaceUp = false; // True if the card is face up
    private int value; // Value of the card
    private boolean blank = false; // Is true if the column containing the card is "perfect" (sames values)

    /**
     * Constructor
     * @param value value of the card
     */
    public Card(int value){
        this.value=value;
    }

    /**
     * Sets the value of the card
     * @param value value of the card
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the card face up
     */
    public void setFaceUp(){
        this.isFaceUp=true;
    }

    /**
     * @return true if the card is face up
     */
    public boolean isFaceUp(){
        return this.isFaceUp;
    }

    /**
     * @return value of the card
     */
    public int getValue(){
        return this.value;
    }

    /**
     * Sets the card blank
     */
    public void setBlank(){
        this.blank=true;
    }

    /**
     * @return true if the card is blank
     */
    public boolean isBlank(){
        return this.blank;
    }

}
