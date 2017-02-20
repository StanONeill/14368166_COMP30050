package poker;

import java.util.ArrayList;
import java.util.Collections;

public class HandOfCards {

	private static int handSize = 5;
	ArrayList<PlayingCard> hand = new ArrayList<>();
	
HandOfCards(ArrayList<PlayingCard> Deck){
		int i =0;
		
		
		for(i =0; i <handSize; i++){
		hand.add(DeckOfCards.dealNext());
		}
	}

public void sort(){
	Collections.sort(hand);
}

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

public boolean isOnePair(){
	if(isThreeOfAKind() || isFourOfAKind() || isTwoPairs()){
		return false;
	}
	PlayingCard card =hand.get(0);
	int value = card.getGameValue();
	
	if(hand.get(0).getGameValue() == value && hand.get(1).getGameValue() == value){
		return true;
	}
	if(hand.get(1).getGameValue() == value && hand.get(2).getGameValue() == value){
		return true;
	}
	if(hand.get(2).getGameValue() == value && hand.get(3).getGameValue() == value){
		return true;
	}
	if(hand.get(3).getGameValue() == value && hand.get(4).getGameValue() == value){
		return true;
	}
	return false;
}

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

public boolean isHighHand(){
	if(isRoyalFlush() || isStraightFlush() || isFourOfAKind()
			&& isFullHouse() || isFlush() || isStraight()
			&& isThreeOfAKind() || isTwoPairs() || isOnePair()){
		return false;
	}
	return true;
}

}
