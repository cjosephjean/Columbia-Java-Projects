
// Player Class

import java.util.ArrayList;
public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){	
	    // create a player here
            bankroll = 1;
            hand = new ArrayList<Card>();
        }

	public void addCard(Card c){
	    // add the card c to the player's hand
                hand.add(c);  
        }

	public void removeCard(Card c){
	    // remove the card c from the player's hand
                hand.remove(c);
            }
       
		
        public void bets(double amt){
            // player makes a bet
            bet = amt;
            bankroll -= amt;
            //amt = bankroll - bet;
        }

        public void winnings(double odds){
            //	adjust bankroll if player wins
            if (bankroll > 0){
                bankroll++;} 
            else{
                bankroll--;
            } 
                
        }
        
        public void updateBankroll(double earnings){
            bankroll += earnings; 
            }
    
        public double getBankroll(){
            // return current balance of bankroll
            return bankroll;
        }

        // you may wish to use more methods here
    
        public ArrayList<Card> getHand()
        {
            return hand;
        }
}