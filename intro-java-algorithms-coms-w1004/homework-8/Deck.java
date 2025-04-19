// Deck Class
import java.util.Random;
public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
                     // add more instance variables if needed
	public Deck(){
		// make a 52 card deck here
        int cardIndex = 0;
        cards = new Card[52];
        for (int i = 1; i < 5; i++){
            for (int j = 1; j <14; j++){
                Card card = new Card(i, j);
                cards[cardIndex] = card;
                cardIndex++;
            }
        }
        top = 0;  
    }
	
	public Card deal(){
		// deal the top card in the deck
        if (top > 51){
           top = 0;
           return cards[0];
      }
        else{
           top++;
           return cards[top-1];
       }
	}
	// add more methods here if needed
    
public void shuffle() {

    // attempt at implementing Fisher-Yates algorithm
    // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    // start from end of deck and swap with cards closer to front of deck
    // until front of deck reached (at this point all above cards have been shuffled)
    for (int i = 51; i > 0; i--) {
        Random r = new Random();
        int randomIndex = r.nextInt(i);
        Card temp = cards[i];
        cards[i] = cards[randomIndex];
        cards[randomIndex] = temp;
    }//end for loop
}
}