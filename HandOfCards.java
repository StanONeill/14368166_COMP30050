package poker;

import java.util.ArrayList;
import java.util.Collections;

public class HandOfCards {

	// The constants start at 0 for high hand and increase by 1000000 for each subsequent default as the variance 
	// as my methods to find the variant value will never go above 1 million.
	static private int ROYAL_FLUSH_DEFAULT = 10000000;
	static private int STRAIGHT_FLUSH_DEFAULT = 8000000;
	static private int FOUR_OF_A_KIND_DEFAULT = 7000000;
	static private int FULL_HOUSE_DEFAULT = 6000000;
	static private int FLUSH_DEFAULT = 50000000;
	static private int STRAIGHT_DEFAULT = 40000000;
	static private int THREE_OF_A_KIND_DEFAULT = 3000000;
	static private int TWO_PAIRS_DEFAULT =  2000000;
	static private int ONE_PAIR_DEFAULT = 1000000;
	static private int HIGH_HAND_DEFAULT =  0;
	private static int handSize = 5;
	ArrayList<PlayingCard> hand = new ArrayList<>();
	
	
HandOfCards(ArrayList<PlayingCard> Deck){
		int i =0;
		for(i =0; i <handSize; i++){
		hand.add(DeckOfCards.dealNext());
		}
	}

// Sorts the hand from highest to lowest.
public void sort(){
	Collections.sort(hand);
}

// Defaults have been set, for what each hand is worth. The gap between a high hand and a onePair must
// be huge as we must take every card into account in case the hands match for the first 4 cards for example.
// whereas a straight does not have a big gap between it and the score the default above it, as the only card that needs
// to effect the score is the highest value card. As we know they will be in descending order after that. So if 
// two hands have the same highest card their score will be the same. The score that is added to the default is 
// defined in a seperate method for all except royalFlush as a royalflush has no variance.
public int getGameValue(){
	if(isRoyalFlush()){
		return ROYAL_FLUSH_DEFAULT;
	}
	if(isStraightFlush()){
		return STRAIGHT_FLUSH_DEFAULT + getStraightFlushGameValue();
	}
	if(isFourOfAKind()){
		return FOUR_OF_A_KIND_DEFAULT + getFourOfAKindGameValue();
	}
	if(isFullHouse()){
		return FULL_HOUSE_DEFAULT + getFullHouseGameValue();
	}
	if(isFlush()){
		return FLUSH_DEFAULT + getFlushGameValue();
	}
	if(isStraight()){
		return STRAIGHT_DEFAULT + getStraightGameValue();
	}
	if(isThreeOfAKind()){
		return THREE_OF_A_KIND_DEFAULT + getThreeOfAKindGameValue();
	}
	if(isTwoPairs()){
		return TWO_PAIRS_DEFAULT + getTwoPairsGameValue();
	}
	if(isOnePair()){
		return ONE_PAIR_DEFAULT + getOnePairGameValue();
	}
	if(isHighHand()){
		return HIGH_HAND_DEFAULT + getHighHandGameValue();
	}
	return 0;
}

// checks for royal flush and returns a boolean 
public boolean isRoyalFlush(){
	
	if(this.isFlush()){
		if(hand.get(0).getType() == "A" && hand.get(1).getType() == "K"
				&& hand.get(2).getType() == "Q" && hand.get(3).getType() == "J"
				&& hand.get(4).getType() == "10"){
			return true;
			
		}
	}
	return false;
}

// checks for a flush and returns a boolean
public boolean isFlush(){
	if(isStraightFlush() || isRoyalFlush()){
		return false;
	}
	char suit = hand.get(0).getSuit();
	for(PlayingCard crad: hand){
			if(crad.getSuit() ==  suit){
			}
			else{
				return false;
			}
	}
	return true;
	}

// checks for a straightFlush and and returns a boolean
public boolean isStraightFlush(){
	if(isRoyalFlush()){
		return false;
	}
	char suit = hand.get(0).getSuit();
	int high = hand.get(0).getGameValue();
	
	if(hand.get(1).getSuit() == suit && hand.get(1).getGameValue() == high - 1
		&& hand.get(2).getSuit() == suit && hand.get(2).getGameValue() == high - 2
		&& hand.get(3).getSuit() == suit && hand.get(3).getGameValue() == high - 3
		&& hand.get(4).getSuit() == suit && hand.get(4).getGameValue() == high - 4){
		return true;
	}
	return false;
}

// To get the value of a hand with one pair we take the game value of the card you have two of and multiply it 
// by 10 to the power of 6. We do this because two players can have the same pair, then we must take into account,
// the game value of the cards that are single. We multiply by powers of 10 so that once we reach a point that they 
// do not have cards that match each other, no proceeding card can change who will have the highest score.
public int getOnePairGameValue(){
	
	if(hand.get(0).getGameValue() == hand.get(1).getGameValue()){
		return (int) ((hand.get(2).getGameValue() * Math.pow(10, 3)) + (hand.get(3).getGameValue() * Math.pow(10, 2)) 
				+ (hand.get(4).getGameValue() * Math.pow(10, 1)));
	}
	if(hand.get(1).getGameValue() == hand.get(2).getGameValue()){
		return (int) ((hand.get(0).getGameValue() * Math.pow(10, 3)) + (hand.get(3).getGameValue() * Math.pow(10, 2))
				+ hand.get(4).getGameValue() * Math.pow(10, 1));
	}
	if(hand.get(2).getGameValue() == hand.get(3).getGameValue()){
		return (int) ((hand.get(0).getGameValue() * Math.pow(10, 3)) + (hand.get(1).getGameValue() * Math.pow(10, 2))
				+ (hand.get(4).getGameValue() * Math.pow(10, 1)));
	}
	if(hand.get(3).getGameValue() == hand.get(4).getGameValue()){
		return (int) ((hand.get(0).getGameValue() * Math.pow(10, 3)) + (hand.get(1).getGameValue() * Math.pow(10, 2))
				+ (hand.get(2).getGameValue() * Math.pow(10, 1)));
	}
	return 0;
}

public int getHighHandGameValue(){
	return (int) (hand.get(0).getGameValue() * Math.pow(10, 4) + hand.get(1).getGameValue() * Math.pow(10, 3) +
			hand.get(2).getGameValue() * Math.pow(10, 2) + (hand.get(3).getGameValue() * Math.pow(10, 1))
			+ hand.get(4).getGameValue() * Math.pow(10, 0));
}

// Two players cannot have the same three cards as only 4 per deck.  Once the hand is shuffled 
// the third card will always be a part of the triple. Therefore if both players have three of a kind
// returning only the third card to modify the default will find the winner
public int getThreeOfAKindGameValue(){
	return hand.get(2).getGameValue();
}

// For a hand with two pairs we take the pair with the highest value and place more weight upon that by
// multiplying it by 10 to the power of 6, then we take the second pair and weight that with 10 to the power of 4 and
// then we take the last remaing card and weight that less than the rest. The reason for this
// is two people could have two matching pairs.
public int getTwoPairsGameValue(){
	
	if(hand.get(0).getGameValue() == hand.get(1).getGameValue() 
			&& hand.get(2).getGameValue() == hand.get(3).getGameValue()){
		return (int) ((hand.get(0).getGameValue() * Math.pow(10, 3)) + (hand.get(2).getGameValue() * Math.pow(10, 2)) +
				(hand.get(4).getGameValue() * Math.pow(10, 1)));
	}
	if(hand.get(0).getGameValue() == hand.get(1).getGameValue() 
			&& hand.get(3).getGameValue() == hand.get(4).getGameValue()){
		return (int) ((hand.get(0).getGameValue() * Math.pow(10, 3)) + (hand.get(3).getGameValue() * Math.pow(10, 2)) +
				(hand.get(2).getGameValue() * Math.pow(10, 1)));
	}
	if(hand.get(1).getGameValue() == hand.get(2).getGameValue() 
			&& hand.get(3).getGameValue() == hand.get(4).getGameValue()){
		return (int) ((hand.get(1).getGameValue() * Math.pow(10, 3)) +(hand.get(4).getGameValue() * Math.pow(10, 2))
				+ (hand.get(0).getGameValue() * Math.pow(10, 1)));
	}
	return 0;
}	
	

// For a flush, we only need to take the card of highest value as that will tell us what the other cards in 
// the hand are. as such we dont need to weight the highest card by multiplying it.
public int getStraightFlushGameValue(){
	
	return hand.get(0).getGameValue();
}

// A flush can be a combination of cards with any gamevalue, and can easily match another players hand
// as such we need to take every card into account in case they do match. AS such the gap between it's default 
// value and the one above it must be at least 14*Math.pow(10,8) +1
public int getFlushGameValue(){
	
	return (int) (hand.get(0).getGameValue() * Math.pow(10, 4) + hand.get(1).getGameValue() * Math.pow(10, 3) +
			hand.get(2).getGameValue() * Math.pow(10, 2) + hand.get(3).getGameValue() * Math.pow(10, 1)
			+ hand.get(4).getGameValue() * Math.pow(10, 0));
}
// In a full House we take the value of the triple only as there are only 4 cards of the same gamevalue and two players
// can't have the same triple
public int getFullHouseGameValue(){
	
	int value = hand.get(0).getGameValue();
	int value2 = hand.get(3).getGameValue();
	
	if(hand.get(0).getGameValue() == value && hand.get(1).getGameValue() == value
			&& hand.get(2).getGameValue() == value2 && hand.get(3).getGameValue() == value2
			&& hand.get(4).getGameValue() == value2){
		return (int) (hand.get(4).getGameValue());
	}
	
	if(hand.get(3).getGameValue() == value && hand.get(4).getGameValue() == value
			&& hand.get(0).getGameValue() == value2 && hand.get(1).getGameValue() == value2
			&& hand.get(2).getGameValue() == value2){
		return (int) (hand.get(0).getGameValue());
	}
	return 0;
}

// two players can't have the same 4 cards as such the fifth card doesn't matter, the middle card will always be a
// part of the 4 cards of same gameValue and as such we can just return it
public int getFourOfAKindGameValue(){
	return hand.get(2).getGameValue();
}

public int getStraightGameValue(){
	
	return (int) hand.get(0).getGameValue();
}

	


// checks for a straight and returns a boolean
public boolean isStraight(){
	if(isStraightFlush() || isRoyalFlush()){
		return false;
	}
	int high = hand.get(0).getGameValue();
	
	if(hand.get(1).getGameValue() == high - 1
			&& hand.get(2).getGameValue() == high - 2
			&& hand.get(3).getGameValue() == high - 3
		    && hand.get(4).getGameValue() == high - 4){
			return true;
		}
	return false;
}

// checks for a four of a kind and returns a boolean
public boolean isFourOfAKind(){

	int value = hand.get(0).getGameValue();
	
	if(hand.get(0).getGameValue() == value
			&& hand.get(1).getGameValue() == value
			&& hand.get(2).getGameValue() == value
		    && hand.get(3).getGameValue() == value){
			return true;
		}
	value = hand.get(1).getGameValue();
	if(hand.get(1).getGameValue() == value
			&& hand.get(2).getGameValue() == value
			&& hand.get(3).getGameValue() == value
		    && hand.get(4).getGameValue() == value){
			return true;
		}
	return false;
}

// checks for a three of a kind and returns a boolean
public boolean isThreeOfAKind(){
	int value = hand.get(0).getGameValue();
	
	if(isFourOfAKind()){
		return false;
	}
	if(hand.get(0).getGameValue() == value
			&& hand.get(1).getGameValue() == value
			&& hand.get(2).getGameValue() == value){
			return true;
		}
	value = hand.get(1).getGameValue();
	if(hand.get(1).getGameValue() == value
			&& hand.get(2).getGameValue() == value
			&& hand.get(3).getGameValue() == value){
			return true;
		}
	value = hand.get(2).getGameValue();
	if(hand.get(2).getGameValue() == value
			&& hand.get(3).getGameValue() == value
			&& hand.get(4).getGameValue() == value){
			return true;
		}
	return false;	
}

// checks for a single pair and returns a boolean
public boolean isOnePair(){
	if(isThreeOfAKind() || isFourOfAKind() || isTwoPairs()){
		return false;
	}
	
	if(hand.get(0).getGameValue() == hand.get(1).getGameValue()){
		return true;
	}
	if(hand.get(1).getGameValue() == hand.get(2).getGameValue()){
		return true;
	}
	if(hand.get(2).getGameValue() == hand.get(3).getGameValue()){
		return true;
	}
	if(hand.get(3).getGameValue() == hand.get(4).getGameValue()){
		return true;
	}
	return false;
}

// checks for a full house and returns a boolean 
public boolean isFullHouse(){
	int value = hand.get(0).getGameValue();
	int value2 = hand.get(3).getGameValue();
	
	if(hand.get(0).getGameValue() == value && hand.get(1).getGameValue() == value
			&& hand.get(2).getGameValue() == value2 && hand.get(3).getGameValue() == value2
			&& hand.get(4).getGameValue() == value2){
		return true;
	}
	
	if(hand.get(3).getGameValue() == value && hand.get(4).getGameValue() == value
			&& hand.get(0).getGameValue() == value2 && hand.get(1).getGameValue() == value2
			&& hand.get(2).getGameValue() == value2){
		return true;
	}
	return false;
}

// checks for two pairs and returns a boolean
public boolean isTwoPairs(){
	
	if(hand.get(0).getGameValue() == hand.get(1).getGameValue() 
			&& hand.get(2).getGameValue() == hand.get(3).getGameValue()){
		return true;
	}
	if(hand.get(0).getGameValue() == hand.get(1).getGameValue() 
			&& hand.get(3).getGameValue() == hand.get(4).getGameValue()){
		return true;
	}
	if(hand.get(1).getGameValue() == hand.get(2).getGameValue() 
			&& hand.get(3).getGameValue() == hand.get(4).getGameValue()){
		return true;
	}
	return false;
}	

// if there are no combinations in the hand returns true for a high hand 
public boolean isHighHand(){
	if(isRoyalFlush() || isStraightFlush() || isFourOfAKind()
			&& isFullHouse() || isFlush() || isStraight()
			&& isThreeOfAKind() || isTwoPairs() || isOnePair()){
		return false;
	}
	return true;
}

// Sets the hand to specified cards for checking if they work 
public void setHand(PlayingCard first, PlayingCard second, PlayingCard third, PlayingCard fourth, PlayingCard fifth){
	hand.set(0, first);
	hand.set(1, second);
	hand.set(2, third);
	hand.set(3, fourth);
	hand.set(4, fifth);
}

// checks to see what is in the hand and returns the relevant string 
public String handString(){
	String string = "";
	
	if(isRoyalFlush()){
		string = "Hand is a Royal Flush";
		return string;
	}
	if(isStraightFlush()){
		string = "Hand is a Straight Flush";
		return string;
	}
	if(isHighHand()){
		string = "You got nothing but a high hand";
		return string;
	}
	if(isFlush()){
		string = "Hand is a Flush";
		return string;
	}
	if(isFullHouse()){
		string = "Hand is a Full House";
		return string;
	}
	if(isOnePair()){
		string = "Hand is One Pair";
		return string;
	}
	if(isTwoPairs()){
		string = "Hand is Two Pairs";
		return string;
	}
	if(isFullHouse()){
		string = "Hand is Full House";
		return string;
	}
	if(isFourOfAKind()){
		string = "Hand is Four Of A Kind";
		return string;
	}
	return string;
}

}
