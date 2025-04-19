Simple Java Poker

Christopher Jean

This program runs though as many games of poker until the player chooses not to continue or runs out of tokens. At the beginning of each game, the player enters an amount of tokens to bet from 1 to 5. If the player bets outside of these bounds (either betting more than is in the bank roll, more than 5, or less than one token), the player is prompted until an acceptable amount is entered. The player is then dealt a hand and has the option of discarding 1 to 5 cards. Then player chooses which cards to discard by indicating numbers 1 to 5 representing the card positions from top to bottom. For each card discarded, a new card is dealt. The possible hand evaluations and payouts are the following:

• No pair — The lowest hand, containing five separate cards that do not match up to create any of the hands below. No Payout.

• One pair—Two cards of the same value, for example two queens. Payout: 1

• Two pairs—Two pairs, for example two queens and two 5’s. Payout:2

• Three of a kind—Three cards of the same value, for example three queens. Payout: 3

• Straight—Five cards with consecutive values, not necessarily of the same suit, such as 4, 5, 6, 7, and 8. The ace can either precede a 2 or follow a king. Payout: 4

• Flush—Five cards, not necessarily in order, of the same suit. Payout:5

• Full House—Three of a kind and a pair, for example three queens and two 5’s.
Payout: 6

• Four of a Kind—Four cards of the same value, such as four queens. Payout: 25

• Straight Flush—A straight and a flush: Five cards with consecutive values of the same suit. Payout: 50

• Royal Flush—The best possible hand in poker. A 10, jack, queen, king, and ace, all of the same suit. Payout: 250

In the Player class, I chose to create an additional method, getHand which returns a hand of cards corresponding to the player. This is important in keeping track of and evaluating the hand for a player object. I chose to use the getBankroll method to keep track of the player’s winnings and make sure that the winnings were carried over to new games, and it is invoked in the Game class. 

In the Game class, I added the getNewHand method to provide players with new hands in the case that they play multiple games.

In the Deck class, I chose (or attempted) to use the Fisher-Yates algorithm for shuffling the deck, because I repeatedly ran into the problem of the same cards appearing in each new game.

In the Card class, I chose to include sameSuit, getSuit, and getRank methods to be able to compare cards to evaluate hands as described in the block comments.
