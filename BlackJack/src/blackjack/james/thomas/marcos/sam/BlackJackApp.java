/*
 * James
 * Sam
 * Marcos
 * Thomas
 */
package blackjack.james.thomas.marcos.sam;

import javax.swing.JFrame;

public class BlackJackApp {
	
	//Create enum for possible results of a game.
	public enum GameResult{
		WIN,DRAW,LOSS
	}

	public static void main(String[] args) {
		
		
		//Create an array of the enum type GameResult to store the score of player one.
		GameResult[] playerOneResults = new GameResult[11];

		Deck deck = new Deck();

		Player p1 = new Player();
		Player p2 = new Player();
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		System.out.println("Welcome to BlackJack Simulation 2015");
		System.out.println(("By Thomas, James, Marcos and Sam!"));
		System.out.println("Press Enter to start.");
		
		
//		try{
//			System.in.read();
//			System.in.skip(1000);
//		}catch(Exception e){
//			e.printStackTrace();
//		}

		//Game loop for 11 rounds.
		for(int game = 0; game < 11; game++){

			//Check that at least 1 player is not sticking
			while(!p1.sticking() || !p2.sticking()){
				
				//Deal 2 cards to each player for the first hand
				if(p1.getHandValue() == 0 && p2.getHandValue() == 0){
					deck.dealToPlayer(p1);
					deck.dealToPlayer(p2);
					deck.dealToPlayer(p1);
					deck.dealToPlayer(p2);
					
					p1.checkForStick();
					p2.checkForStick();

				}else{

					//If a player isn't sticking then deal them a card and check if they want to stick
					if(!p1.sticking()){
						deck.dealToPlayer(p1);
						p1.checkForStick();
					}
					if(!p2.sticking()){
						deck.dealToPlayer(p2);
						p2.checkForStick();
					}

					//If a player goes bust(over 21) end the current game by breaking
					if(p1.getHandValue() > 21 || p2.getHandValue() > 21){
						System.out.println("\nGame " + (game + 1));
						System.out.println("First player has " + p1.getHandValue() + " points with ( " + p1.getHand() + ")");
						System.out.println("Second player has " + p2.getHandValue() + " points with ( " + p2.getHand() + ")");
						break;
					}



				}
				System.out.println("\nGame " + (game + 1));
				System.out.println("First player has " + p1.getHandValue() + " points with ( " + p1.getHand() + ")");
				System.out.println("Second player has " + p2.getHandValue() + " points with ( " + p2.getHand() + ")");
			}
			
			//Check if the game resulted in a Win, Loss or Draw and add the result to the array
			if(p1.getHandValue() == p2.getHandValue()){
				//draw
				System.out.println("Game " + (game + 1) + " Player 1 and Player 2 Draw");
				playerOneResults[game] = GameResult.DRAW;
				Player.increaseDraws();
			}else if(p1.getHandValue() <= 21 && (p1.getHandValue() > p2.getHandValue() || p2.getHandValue() > 21)){
				//p1 wins
				System.out.println("Game " + (game + 1) + ", Player 1 wins");
				p1.wonGame();
				playerOneResults[game] = GameResult.WIN;
			}else if(p2.getHandValue() <= 21 && (p2.getHandValue() > p1.getHandValue() || p1.getHandValue() > 21)){
				//p2 wins
				System.out.println("Game " + (game + 1) + ", Player 2 wins");
				p2.wonGame();
				playerOneResults[game] = GameResult.LOSS;
			}else{
				//draw
				System.out.println("Game " + (game + 1) + " Player 1 and Player 2 Draw");
				playerOneResults[game] = GameResult.DRAW;
				Player.increaseDraws();
			}
			
			System.out.println("\nPlayer 1 has " + p1.getWins() + " win(s), Player 2 has " + p2.getWins() + " win(s)");
			System.out.println("There have been " + Player.getDraws() + " draws.");
			
			
			//Reset the game for the next round
			p1.resetHandValue();
			p2.resetHandValue();
			p1.resetStick();
			p2.resetStick();
			deck.resetDeck();
			
			//Prompt the user to press enter and wait for it. Skip any additional key presses.
			System.out.println("\nPress Enter to continue");
//			try{
//				System.in.read();
//				System.in.skip(1000);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
			

		}
		
		String sessionResults = "";
		
		//Loop through the results array and concatenate the result of each game to sessionResults.
		for(int i = 0; i < playerOneResults.length;i++){
			
			if(playerOneResults[i] == GameResult.WIN){
				sessionResults += "Game " + (i + 1) + " Player 1 wins\n";
			}else if(playerOneResults[i] == GameResult.LOSS){
				sessionResults += "Game " + (i + 1) + " Player 2 wins\n";
			}else{
				sessionResults += "Game " + (i + 1) + " Player 1 and Player 2 Draw\n";
			}
			
		}
		
		//Display the end of session summary
		System.out.println(sessionResults);
		
		System.out.println("\nPlayer 1 has " + p1.getWins() + " win(s), Player 2 has " + p2.getWins() + " win(s)");
		System.out.println("There have been " + Player.getDraws() + " draws.");
		
		
		if(p1.getWins() > p2.getWins()){
			System.out.println("Player 1 wins.");
		}else if(p2.getWins() > p1.getWins()){
			System.out.println("Player 2 wins.");
		}else{
			System.out.println("Both player draw.");
		}

		


	}

}
