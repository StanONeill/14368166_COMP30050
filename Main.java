package poker;

import java.util.ArrayList;

public class Main {
	// Main tests my methods by creating a new deck and printing it out
	// then shuffling the deck and printing again. The reset function is
	// then called and the reset deck is printed. Then the dealNext method is 
	// called to deal the first card in the reset deck. That card is 
	// then returned and added to the discarded card array list
	public static void main(String[] Args){
		int i = 0;
		ArrayList<PlayingCard> Deck = new ArrayList<PlayingCard>();
		Deck = DeckOfCards.createDeck();
		DeckOfCards.shuffle();
		DeckOfCards.reset();
		HandOfCards hand = new HandOfCards(Deck);
//		System.out.println("The next card to be dealt is: \n");
//		PlayingCard next = DeckOfCards.dealNext();
//		String string = PlayingCard.toString(next);
//		System.out.println(string);
//		DeckOfCards.returnCard(next);
	}
}


