import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Integer;

public class Game {
	
	private Player p;
	private Deck cards;
    private ArrayList<Card> hand;
    int handValue = 1;
    
    
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		p = new Player();
        cards = new Deck();
        cards.shuffle();
        makeHand(testHand);
        hand = new ArrayList<Card>(p.getHand());
        System.out.println(checkHand(hand));
    }

	
	public Game(){
		// This no-argument constructor is to actually play a normal game
        {
            p = new Player();
            cards = new Deck();
            cards.shuffle();
            for(int i = 0; i < 5; i++)
            {
                p.addCard(cards.deal());
            }
            hand = new ArrayList<Card>(p.getHand());
        }	
	}
	
	public ArrayList<Card> getNewHand(){
	    for (Card element: hand) {
	        p.removeCard(element);
	    }
	    cards.shuffle();
        for(int i = 0; i < 5; i++)
        {
            p.addCard(cards.deal());
        }
        hand = new ArrayList<Card>(p.getHand());
        return hand;
	}
	
    public void play()
        // this method should play the game	
        {
            //Declare variables
            int playerInput;
            String checkingHand;
            double bet = 1;
            //Create a Scanner object
            Scanner scn = new Scanner(System.in);
            Boolean continueGame = true;
            cards.shuffle();
            while (continueGame == true) {
           //Prompt and read the input from the player
            System.out.println("Poker Game: START!");
            cards.shuffle();
            String bankValue = String.valueOf(p.getBankroll());
            System.out.println("You have " + bankValue + " token(s) in the bank.");
            System.out.println("Place your bets! (Enter amount of tokens 1-5):");
            
           bet = scn.nextDouble();
            
        
                 while(bet < 1 || bet > p.getBankroll() || bet > 5){
                     if(bet > p.getBankroll()){
                         System.out.print("Insufficient amount of tokens. Please enter again: ");
                     }  
                     else if(bet < 1 || bet > 5){
                         System.out.print("Bet should be from 1 to 5, inclusive. Please enter again: ");
                     }
                    bet = scn.nextDouble();
                 }
            p.bets(bet);
        
            System.out.println("These are the cards dealt:");
            for (Card element: hand)
                {
                    System.out.println("" + element);
                }
                System.out.println("How many cards do you want to discard?(Enter 1-5): ");
                playerInput = scn.nextInt();

            if (playerInput > 0)
                {
                for(int i = 0; i < playerInput; i++)
                {
                System.out.println("Which card do you want to discard?(Enter position 1-5): ");
                int discarded = scn.nextInt();
                p.removeCard(hand.get(discarded - 1));
                p.addCard(cards.deal());
                        }
                }
                    hand = new ArrayList<Card>(p.getHand());
                    System.out.println("This is your new hand.");
                    for (Card element: hand){
                                System.out.println("" + element);
                            }
                    checkingHand = checkHand(hand);
                    System.out.println("Your hand has " + checkingHand);
                    System.out.println("You have won " + bet * handValue + " tokens!");
                    p.updateBankroll(bet * handValue);
                    if (p.getBankroll() == 0) {
                    System.out.println("You are out of tokens and your game is over.");
                    continueGame = false;
                    } else {
                    System.out.println("Would you like to keep playing? Enter y to continue or any other key to stop.");
                    String userSelection = scn.next();
                    if (!"y".equals(userSelection)) {
                        System.out.println("Thank you for playing!");
                        continueGame = false;
                    } else {
                        hand = getNewHand();
                    }
                    }
                    
                }
    }
        public String checkHand(ArrayList<Card> hand){
            // this method should take an ArrayList of cards
            // as input and then determine what evaluates to and
            // return that as a String
            hand = sortHand(hand);
            if (royalFlush(hand) == 1)
            {
                handValue = 250;
                return "a Royal Flush";
            }
            else if (straightFlush(hand) == 1)
            {
                handValue = 50;
                return "a Straight Flush";
            }
            else if (fourKind(hand) == 1)
            {
                 handValue = 25;
                return "a Four of a Kind";
            }
            else if(fullHouse(hand) == 1)
            {
                 handValue = 6;
                return "a Full House";
            }
            else if (flush(hand) == 1)
            {
                handValue = 5;
                return "a Flush";
            }
            else if (straightHand(hand) == 1)
            {
                handValue = 4;
                return "a Straight";
            }
            else if (threeKind(hand) == 1)
            {
                handValue = 3;
                return "a Three of a kind";
            }
            else if (twoPairs(hand) == 1)
            {
                handValue = 2;
                return "Two Pairs";
            }
            else if (pair(hand) == 1)
            {
                handValue = 1;
                return "a Pair";
            }
            else
            {
                handValue = 0;
                return "No Pair";
            }

        }
	
	
	// you will likely want many more methods here
	// per discussion in class
    
    //Method definition of pair:
    //This method returns the number of pairs in the hand; three of a kind count as one pair
    public int pair(ArrayList<Card> hand)
    {
        Card tempCard;
        int i = 1;
        int numPairs = 0;
        while (i < hand.size()){
            tempCard = hand.get(i - 1);
            if (tempCard.sameRank(hand.get(i)))
            {
                i++;
                numPairs++;
            }
            i++;
        }
        return numPairs;
    }
    //Method definition of twoPairs: It returns 1 if the hand has two pairs and 0 otherwise
    public int twoPairs(ArrayList<Card> hand)
    {
        if(pair(hand) == 2)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    //Method definition of threeKind:
    public int threeKind(ArrayList<Card> hand)
    {
        if (pair(hand) == 0)
        {
            return 0;
        }
        else if( hand.get(0).sameRank(hand.get(2))
                || hand.get(1).sameRank(hand.get(3))
                || hand.get(2).sameRank(hand.get(4)))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    //Method definition of straightHand: It returns 1 if the hand has a straight and 0 otherwise
    public int straightHand(ArrayList<Card> hand)
    {
        Card oneCard = hand.get(0);
        Card fiveCards = hand.get(4);
        Card twoCards = hand.get(1);
        if (pair(hand) > 0)
        {
            return 0;
        }
        else if(fiveCards.diffRank(oneCard) == 4)
        {
            return 1;
        }
        else if(oneCard.getRank() == 1
                && fiveCards.getRank() == 13
                && twoCards.getRank() == 10)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    //Method definition of flush: It returns 1 if the hand has a flush and 0 otherwise
    public int flush(ArrayList<Card> hand)
    {
        if(pair(hand) > 0)
        {
            return 0;
        }
        else
        {
            int i = 1;
            for(Card element: hand)
            {
                if (! element.sameSuit(hand.get(i)))
                {
                    return 0;
                }
                if(i < hand.size() - 1)
                {
                    i++;
                }
            }
            return 1;
        }
    }
    
    //Method definition of fullHouse: It returns 1 if the hand has a full house and 0 otherwise
    public int fullHouse(ArrayList<Card> hand)
    {
        if (pair(hand) == 2 && threeKind(hand) == 1 && fourKind(hand) == 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    //Method definition of fourKind: It returns 1 if the hand has 4 cards with the same value
    public int fourKind(ArrayList<Card> hand)
    {
        if (pair(hand) == 2 && threeKind(hand) == 1){
            if(hand.get(0).sameRank(hand.get(3))
               || hand.get(1).sameRank(hand.get(4)))
            {
                return 1;
            }
        }
        return 0;
    }
    //Method definition of straightFlush: It returns 1 if the hand has a straight flush
    public int straightFlush(ArrayList<Card> hand)
    {
        
        if (flush(hand) == 1
            && straightHand(hand) == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    //Method definition of royalFlush: It returns 1 if the hand has a royal flush
    public int royalFlush(ArrayList<Card> hand)
    {
        if (straightFlush(hand) == 1
            && hand.get(0).getRank() == 1)
        {
            return 1;
        }
        else{
            return 0;
        }
    }

    
    //Method definition of makeHand: This method makes a hand from a array of strings
        public void makeHand(String[] testHand){
            Card card;
            char suitChar;
            int suit;
            int rank = 1;
            String rankString;

        for(int i = 0; i < 5; i++){
            suitChar = testHand[i].charAt(0);
            rankString= testHand[i].substring(1);
            rank= Integer.parseInt(rankString);
        if (suitChar=='c')
            {
                suit=0;
            }
        else if (suitChar=='d')
            {
                suit=1;
            }
        else if (suitChar=='h')
            {
                suit=2;
            }
        else
            {
                suit=3;
            }
        card= new Card(suit, rank);
        p.addCard(card);
        }
    }
    
    
     //Method definition of sortHand: It returns a sorted hand
    public ArrayList<Card> sortHand(ArrayList<Card> hand)
        {
            Collections.sort(hand);
            return hand;
        }   
}