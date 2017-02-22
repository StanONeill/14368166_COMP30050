package poker;

import java.util.ArrayList;
import java.util.Random;

class DeckOfCards {
	
	
	private static ArrayList<PlayingCard> deck = new ArrayList<>();
	private static int next = -1;
	private static ArrayList<PlayingCard> discarded = new ArrayList<>();
	
	public static ArrayList<PlayingCard> createDeck(){
		int i = 0; // counter variable for the 52 cards in a deck
		int j =0; // counter variable for the 13 cards in a suit gets reset once it hits 13
		int[] gameValues = {14,2,3,4,5,6,7,8,9,10,11,12,13};
		String[] Type = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		
		for(i = 0; i < 52; i++){
			
			if(j == 13){
				j=0;
			}
			//first 13 cards will be the suit of hearts
			if(i < 13){
				deck.add(new PlayingCard(j+1, gameValues[j], Type[j], PlayingCard.HEARTS));
			}
			// the second group of 13 cars will be the suit of daimonds
			if(i<=13 && i < 26){
				deck.add(new PlayingCard(j+1, gameValues[j], Type[j], PlayingCard.DIAMONDS));
			}
			// the third set of 13 cards will be the suit of spades
			if(i<=26 && i <39){
				deck.add(new PlayingCard(j+1, gameValues[j], Type[j], PlayingCard.SPADES));
			}
			//the last 13 cards will be the suit of clubs
			if(i <=39){
				deck.add(new PlayingCard(j+1, gameValues[j], Type[j], PlayingCard.CLUBS));
			}
			j++;		
			
	}
		return deck;
}
	
	// Swaps the position of 2 random cards 52*52 times
	public static void shuffle(){
		int i = 0, j, k;
		PlayingCard temp = new PlayingCard(0, 0, "A", 'A');
		Random rnd = new Random();
		//rnd.setSeed(44);
		while(i < 2704){
				j = rnd.nextInt(52);
				k = rnd.nextInt(52);
				temp = deck.get(j);
				deck.set(j, deck.get(k));
				deck.set(k, temp);
			i++;
		}
	}
	//creates a new deck shuffles it and clears the discarded array
	public static void reset(){
		deck = createDeck();
		shuffle();
		next =0;
		discarded.clear();
	}
	
	// deals the next playingcard in the deck
	public static PlayingCard dealNext(){
		if(next > 50){
			reset();
			next++;
			return deck.get(next);
		}
		next++;
		return deck.get(next);
	}
	
	// method to add a Playing Card Object to the discarded arraylist
	public static void returnCard(PlayingCard used){
		discarded.add(used);
	}

}
