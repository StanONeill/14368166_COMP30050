package poker;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] Args){
		// testsing to see if it works in main
		int i = 0;
		ArrayList<PlayingCard> Deck = new ArrayList<PlayingCard>();
		Deck = DeckOfCards.createDeck();
		
		System.out.println("Unshuffled Deck: \n\n");
		// for loop to print out the string of every card in the deck
		for(i = 0; i < 52; i++){
			PlayingCard card = Deck.get(i);
			String string = PlayingCard.toString(card);
			System.out.println(string);
		}
		DeckOfCards.shuffle();
		System.out.println("Shuffled Deck: \n\n");
		for(i = 0; i < 52; i++){
			PlayingCard card = Deck.get(i);
			String string = PlayingCard.toString(card);
			System.out.println(string);
		}
	}
}


