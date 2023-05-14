package org.skyjo.terminal;
import org.skyjo.game.*;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Scanner;

public class CLI {
    private Game game ;

    public CLI(){
    }
    public void printDiscard(){
        System.out.println("Discard : "+this.game.getDiscard().getValue());
    }
    public int askChoice(String option1, String option2){
        int n = -1;
        Scanner scanner = new Scanner(System.in);
        while (n<0 || n>1){
            System.out.println("Do you want to "+option1+" (0) or "+option2+" (1) ?");
            n = scanner.nextInt();
        }
        scanner.nextLine();
        return n;
    }
    public void setPlayers(){
        this.game = new Game();
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        while (n<2 || n>8){
            System.out.println("Enter a number of players (2 to 8)");
            n = scanner.nextInt();
            scanner.nextLine(); // consommer le retour chariot restant
            System.out.println("Number selected : "+n);
        }

        String[] listOfNames = new String[n+1];
        for (int i = 1; i<=n; i++){
            System.out.println("Enter the name of Player "+i);
            listOfNames[i] = scanner.nextLine();
        }
        this.game.makePlayersMap(n,listOfNames);
    }
    public int[] getCard(Player currentPlayer){
        int[] chosenCard = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println(currentPlayer.getName() + ", choose a card. Your cards:");
        currentPlayer.printCards();
        int row = -1;
        int col = -1;
        while (row < 0 || row > 2) {
            System.out.println("Enter the row (1-3):");
            row = scanner.nextInt() - 1;
        }
        chosenCard[0] = row;
        while (col < 0 || col > 3) {
            System.out.println("Enter the column (1-4):");
            col = scanner.nextInt() - 1;
        }
        chosenCard[1] = col;
        scanner.nextLine();
        return chosenCard;
    }
    public void flipTwoCards(){
        Player currentPlayer;
        int[] chosenCard = new int[2];
        for (int i = 1; i<=this.game.getNbPlayers(); i++){
            currentPlayer = game.getPlayerN(i);
            System.out.println("----------------");
            for (int j = 0; j<2; j++){
                System.out.println("Flip card n°"+i);
                chosenCard = this.getCard(currentPlayer);
                currentPlayer.flipCard(chosenCard[0], chosenCard[1]);
            }
            System.out.println(currentPlayer.getName()+", your cards :");
            currentPlayer.printCards();
        }
    }
    public boolean turn(Player finishing){
        int choice = 0;
        Card chosenCard; //drawn from the discard or from the draw
        int[] switchCard; //the card that the user will potentially choose from his cards

        Player currentPlayer = this.game.getPlayerN(this.game.getCurrentPlayer());
        System.out.println(currentPlayer.getName()+"'s turn.");
        this.printDiscard();
        choice = askChoice("draw a new card", "get a card from the discard"); //draw : 0, discard : 1
        if (choice == 0){
            chosenCard = this.game.getStack();
            chosenCard.setFaceUp();
            System.out.println("Drawn card : "+chosenCard.getValue());
            choice = askChoice("switch a card", "throw your card in the discard"); //switch : 0, discard : 1
            if (choice == 0){
                System.out.println(currentPlayer.getName()+", select a card to switch.");
                switchCard = this.getCard(currentPlayer);
                chosenCard = currentPlayer.switchCard(chosenCard, switchCard[0], switchCard[1]);
                this.game.setDiscard(chosenCard);
            }
            else{
                this.game.setDiscard(chosenCard);
                System.out.println(currentPlayer.getName()+", select a card to turn.");
                switchCard = this.getCard(currentPlayer);
                //verif retournee
                currentPlayer.flipCard(switchCard[0], switchCard[1]);
            }
        }
        else{
            chosenCard = this.game.getDiscard();
            System.out.println(currentPlayer.getName()+", select a card to switch.");
            switchCard = this.getCard(currentPlayer);
            chosenCard = currentPlayer.switchCard(chosenCard, switchCard[0], switchCard[1]);
            this.game.setDiscard(chosenCard);
        }
        currentPlayer.printCards();
        //verif ligne
        //verif cartes tournees
        //changer joueur
        return true;
    }
    public void startGame(){
        this.game.resetGame();
        this.flipTwoCards();
        this.game.makeCurrentPlayer();
        System.out.println("First player : "+game.getCurrentPlayer());
        boolean running = true;
        Player finishing = null;
        while (running){
            running = turn(finishing);

        }
    }
}