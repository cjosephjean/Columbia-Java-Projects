// Card Class

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		//Makes a card with suit s and rank r
        suit = s;
        rank = r;
	}
	
    public int getRank() {
		return rank;
	}
    
    public int getSuit() {
		return suit;
	}

	public int compareTo(Card c){
		// Compares and stores card ranks
        
		if (this.getRank() < c.getRank())
			return -1;
		if (this.getRank() > c.getRank())
			return 1;
		if (this.getRank() == c.getRank()) {
			if (this.getSuit() < c.getSuit())
				return -1;
			if (this.getSuit() > c.getSuit())
				return 1;
		}
        return 0;
	}
	
	public String toString(){
		// use this method to easily print a Card object
        
        String cardName;
        
        // added "" element to beginning of arrayRank and arraySuits to accommodate indexing at 13 and 4 respectively
        // moved "Ace" to index 1 of arrayRank to reflect ordering suggested in Game.java
        
		String[] arrayRank = { "", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		String[] arraySuits = { "", "Diamonds", "Hearts", "Clubs", "Spades" };

		cardName = arrayRank[rank] + " of " + arraySuits[suit];
		return cardName;

	}
	// add some more methods here if needed
    //Method definition of sameSuit:
    //It returns true if the cards have the same suit and false otherwise
    public boolean sameSuit(Card c)
    {
        return this.getSuit()==c.getSuit();
    }
    //Method definition of sameRank:
    //It returns true if the cards have the same value and false otherwise
    public boolean sameRank(Card c)
    {
        return this.getRank()==c.getRank();
    }
    
    //Method definition of diffRank:
    //It returns the difference between the values of the two cards
    public int diffRank(Card c)
    {
        return this.getRank() - c.getRank();
    }
}