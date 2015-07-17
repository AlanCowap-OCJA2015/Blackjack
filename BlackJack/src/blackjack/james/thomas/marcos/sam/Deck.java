package blackjack.james.thomas.marcos.sam;

import java.util.Random;

public class Deck {
	
	String[] deck = {
			"2H","3H","4H","5H","6H","7H","8H","9H","10H","JH","QH","KH","AH",
			"2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC","AC",
			"2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD","AD",
			"2S","3S","4S","5S","6S","7S","8S","9S","10S","JS","QS","KS","AS",
	};
	
	String[] deckDefault = {
			"2H","3H","4H","5H","6H","7H","8H","9H","10H","JH","QH","KH","AH",
			"2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC","AC",
			"2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD","AD",
			"2S","3S","4S","5S","6S","7S","8S","9S","10S","JS","QS","KS","AS",
	};
	
	public void dealToPlayer(Player p){
		
		String card = "";
		
		Random rand = new Random(System.nanoTime());
		boolean cardFound = false;
		while(!cardFound){
			
			int randomNum = rand.nextInt(52);
			
			if(!deck[randomNum].equals("")){
				cardFound = true;
				card = deck[randomNum];
				deck[randomNum] = "";
			}
			
		}
		
		int cardValue = 0;
		
		if(card.charAt(0) == 'J' || card.charAt(0) == 'Q' || card.charAt(0) == 'K' || card.charAt(0) == '1'){
			cardValue = 10;
		}else if(card.charAt(0) == 'A'){
			cardValue = p.aceChoice();
		}else{
			int cardNum = Integer.parseInt("" + card.charAt(0));
			cardValue = cardNum;
		}
		
		
		
		
		p.increaseHandValue(cardValue);
		p.addToHand(card);
		
		p.calculateHandValue();
		
	}
	
	public void resetDeck(){
		
		for(int i = 0; i < deck.length; i++){
			deck[i] = deckDefault[i];
		}
		
		
	}

}
