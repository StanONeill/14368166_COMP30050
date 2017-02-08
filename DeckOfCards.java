package poker;

import java.util.ArrayList;
import java.util.Random;

class DeckOfCards {
	
	public static void shuffle(ArrayList<PlayingCard> Deck){
		int i = 0, j, k;
		PlayingCard temp = new PlayingCard(0, 0, "A", 'A');
		Random rnd = new Random();
		//rnd.setSeed(44);
		while(i < 2704){
				j = rnd.nextInt(52);
				k = rnd.nextInt(52);
				temp = Deck.get(j);
				Deck.set(j, Deck.get(k));
				Deck.set(k, temp);
			i++;
		}
	}

}
