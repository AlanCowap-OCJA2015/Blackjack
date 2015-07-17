package blackjack.james.thomas.marcos.sam;

public class Player {

	private int handValue = 0;
	private Player opponent;
	private int wins = 0;
	private boolean sticking = false;
	private static int draws = 0;
	private String hand = "";

	public static void increaseDraws(){
		draws++;
	}
	
	public String getHand(){
		return hand;
	}

	public static int getDraws(){
		return draws;
	}

	public void increaseHandValue(int value){
		handValue += value;
	}

	public void setOpponent(Player p2){
		opponent = p2;
	}

	public void resetHandValue(){
		handValue = 0;
		hand = "";
	}

	public int getHandValue(){
		return handValue;
	}

	public void wonGame(){
		wins++;
	}

	public int getWins(){
		return wins;
	}

	public boolean sticking(){
		return sticking;
	}

	public void checkForStick(){
		
		calculateHandValue();
		
		if(handValue >= 15 && opponent.getHandValue() < handValue){
			sticking = true;
		}

		if(handValue >= 21){
			sticking = true;
		}






	}

	public void resetStick(){
		sticking = false;
	}

	public void calculateHandValue(){

		String[] cards = hand.split(" ");
		int cardValue = 0;
		String rearrangedCards = "";
		
		for(String card:cards){
			if(card.charAt(0) == 'A'){
				rearrangedCards += card + " ";
			}else{
				rearrangedCards = card + " " + rearrangedCards;
			}
		}
		cards = rearrangedCards.split(" ");
		
		for(String card:cards){
			
			if(card.charAt(0) == 'J' || card.charAt(0) == 'Q' || card.charAt(0) == 'K' || card.charAt(0) == '1'){
				cardValue += 10;
			}else if(card.charAt(0) == 'A'){
				cardValue += aceChoice();
			}else{
				int cardNum = Integer.parseInt("" + card.charAt(0));
				cardValue += cardNum;
			}
		}
		
		handValue = cardValue;
		
		
	}

	public void addToHand(String card){
		hand += card + " ";
	}

	public int aceChoice(){
		
		

		if(handValue > 10){
			return 1;
		}else{
			return 11;
		}
	}





}
