/**
 * 
 */
package com.cagj.blackjack;

import java.util.Collections;

/**
 * @author User1
 *
 */
public class BlackjackApp {
	Cards deck;
	Cards playerOne;
	Cards playerTwo;
	
	
	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.runGame();
	}
	
	void setupGame(){
		System.out.println("Playing Game");
		deck = new Cards();
		createDeck(deck);
		System.out.println("Deck created");
		playerOne = new Cards();
		playerTwo = new Cards();
		
//		for(Card c: deck.getCards()){
//			System.out.print(c.toString() + " ");
//		}
	}
	
	void runGame(){
		boolean isPlaying = true;
		boolean isRunning = true;
		do{
			int playerOneHandValue = 0;
			int playerTwoHandValue = 0;
			
			setupGame();
			dealCard(deck, playerOne);
			dealCard(deck, playerOne);
			dealCard(deck, playerTwo);
			dealCard(deck, playerTwo);
			System.out.println("PlayerOne hand contains: " + playerOne.toString());
			System.out.println("PlayerTwo hand contains: " + playerTwo.toString());
			do{
				playerOneHandValue = addUpHand(playerOne);
				playerTwoHandValue = addUpHand(playerTwo);
				
				playerOneHandValue = playGame(playerOneHandValue, playerOne);
				playerTwoHandValue = playGame(playerTwoHandValue, playerTwo);
				
				if(playerOneHandValue > playerTwoHandValue){
					System.out.println("Player One wins");
				}
				
				isPlaying = false;
			} while(isPlaying);
			isRunning = false;
		} while (isRunning);
			
	}
	
	int addUpHand(Cards player){
		int totalCardValue = 0;
		
		for(Card c: player.getCards()){
			totalCardValue+= c.getRankInInt();
		}
		System.out.println("Value of players hand: " + totalCardValue);
		
		return totalCardValue;
	}
	
	void dealCard(Cards deck, Cards player){
		player.getCards().add(deck.getCards().remove(0));
	}
	
	static void createDeck(Cards player){
		Suits suit = Suits.DEFAULT;
		String rank = "";
		for(int s = 0; s < 4; ++s){
			//set suits
			switch(s){
				case 0 : suit = Suits.SPADES; break;
				case 1 : suit = Suits.HEARTS; break;
				case 2 : suit = Suits.CLUBS; break;
				case 3 : suit = Suits.DIAMONDS; break;
			}

			for(int r = 2; r < 15; ++r){
				//set rank
				switch(r){
				case 11 : rank = "J"; break;
				case 12 : rank = "Q"; break;
				case 13 : rank = "K"; break;
				case 14 : rank = "A"; break;
				default : rank = "" + r; break;
			}
				
				player.getCards().add(new Card(suit, rank));
				//System.out.print(suit);
			}
		}
		
		Collections.shuffle(player.getCards());
	}
	
	int checkForAce(Card c, int playerHandValue){
		if(c.getRankInInt() == 1 && playerHandValue < 11){
			return 11;
		} else if (c.getRankInInt() == 1 && playerHandValue > 11){
			return 1;
		} else {
			//return playerHandValue;
			return c.getRankInInt();
		}
	
	}
	
	public int playGame(int playerHandValue, Cards player){
		boolean hold = false;

		do{
			int nextCard = 0;

			if(playerHandValue > 16){

				hold = true;

			}else if(playerHandValue < 17){
				dealCard(deck,player);
				System.out.print("Updated Player HAND:" + player.toString());
				nextCard = player.getCards().get(player.getCards().size()-1).getRankInInt();
				nextCard = checkForAce(player.getCards().get(player.getCards().size()-1),playerHandValue);
				System.out.print("Next Card: " +nextCard);
				playerHandValue += nextCard;
				System.out.print("New Value : " + playerHandValue);
			}

		}while(!hold);
		
		if(playerHandValue > 21){
			//player is bust return 0, ie always lose)
			System.out.print("Player has gone over 21 and busted");
			playerHandValue = 0;
			System.out.println("New value : " + playerHandValue);
		}
		
		return playerHandValue;

	}
	
	
}
