/***************
 * A template for a Nim game
 **************/

import java.util.Random;

public class Game{
    
    // Variables
    public int marblesAtStart;
    public int marblesLeftover;
    public Human humanPlayer;
    public Computer computerPlayer;
    public Random r;
    public int playOrder;
    public int humanTake;
    public int computerTake;
    public Game(int level){
        
        // Set the computer level and start the game
        computerPlayer = new Computer(level);
        humanPlayer = new Human();
        Random r = new Random();
        
        // Choosing a random number 0-90 + 10 yields random number 10-100
        marblesAtStart = r.nextInt(90) + 10;
        marblesLeftover = marblesAtStart;
        
        // Randomly decide who goes first
        playOrder = r.nextInt(2);
    }
    
    public void play(){
        
        // The starting and ending points of the game
        System.out.println("The game begins with " + marblesAtStart + " marbles.  Each player must take between 1 marble and half of the remaining marbles: " + marblesAtStart/2);
        
        while (marblesLeftover > 0) {
            makeMoves();
            if (marblesLeftover <= 1) {
                if (playOrder == 0) {
                    System.out.println("Computer takes the final marble(s) - Human wins!");
                } else {
                    System.out.println("Human takes the final marble(s) - Computer wins!");
                }
            }
        }
    }
    
    
    // Describes the move system
    public void makeMoves() {
        
        // If the playOrder == 0, the human goes first, otherwise, the computer goes first
        if (playOrder == 0) {
            humanTake = humanMove();
        } else {
            computerTake = computerMove();
        }
        
        // Debit the player selection from total number of marbles
        marblesLeftover -= humanTake;
        
        // If game continues (i.e., marbles remain) the other player takes a turn
        if (marblesLeftover > 0) {
            if (playOrder == 0) {
                computerTake = computerMove();
            } else {
                humanTake = humanMove();
            }
            marblesLeftover -= humanTake;
            marblesLeftover -= computerTake;
            System.out.println(marblesLeftover + " marbles remain! How many marbles would you like to take?");
        } else {
            // If no marbles remain, the game is over and there a winner is declared
            if (playOrder == 0) {
                System.out.println("Human takes the final marbles - Computer wins!");
            } else {
                System.out.println("Computer takes the final marbles - Human wins!");
            }
        }
    }
    
    // Describes the computer's moves
    public int computerMove() {
        computerPlayer.move(marblesLeftover);
        int computerChoice = computerPlayer.getChoice();
        System.out.println("Computer takes " + computerChoice + " marbles.");
        return computerChoice;
    }
    
    
    // Describes the human's moves
    public int humanMove() {
        humanPlayer.move();
        int playerChoice = humanPlayer.getChoice();
        // To make sure the human selected a legal move
        while (playerChoice < marblesLeftover/2 || playerChoice >= 1) {
            humanPlayer.move();
            playerChoice = humanPlayer.getChoice();
            System.out.println("Human takes " + playerChoice + " marbles.");
        }
        return playerChoice;
    }
    
    
    
}