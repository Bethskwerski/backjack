package com.lmig.gfc.blackjack.models;

public class BlackjackGame {
	private Deck deck;
	private Player player;
	private Dealer dealer;
	private double bet;
	private double bankRoll = 200;
	private boolean dealerDone;

	public BlackjackGame() {
		deck = new Deck();
		player = new Player();
		dealer = new Dealer();
		deck.goShuffleDeck();
	}

	// This method starts the hand by giving each player 2 cards.
	// This method confirms if the player has the funds to play a hand
	public void startHand() {
		dealerDone = false;
		Card firstCard = deck.pop();
		player.addToHand(firstCard);
		Card secondCard = deck.pop();
		dealer.addToHand(secondCard);
		Card thirdCard = deck.pop();
		player.addToHand(thirdCard);
		Card fourthCard = deck.pop();
		dealer.addToHand(fourthCard);

	}

	// this method hits dealer and player
	public void doHit() {
		if (player.getPlayerHand().getTotal() < 21) {
			hitPlayer();
		}
	}

	public boolean doesPlayerWin() {
		if (isHandOver() && !dealer.getDealerHand().isBlackjack() && !isPush()) {
			if (!player.getPlayerHand().isBust()
					&& ((dealer.getDealerHand().isBust()) 
							|| (player.getPlayerHand().isBlackjack())
							|| (dealer.getDealerHand().getTotal() < player.getPlayerHand().getTotal()))) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean doesHouseWin() {
		if (isHandOver() && !player.getPlayerHand().isBlackjack() && !isPush() && !dealer.getDealerHand().isBust()) {
			if (player.getPlayerHand().isBust()
					|| (player.getPlayerHand().getTotal() < dealer.getDealerHand().getTotal())
					|| (dealer.getDealerHand().isBlackjack())) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean isPush() {
		if (dealerDone && dealer.getDealerHand().getTotal() == player.getPlayerHand().getTotal()) {
			return true;
		} else
			return false;
	}

	public boolean canHit() {
		if (!player.getPlayerHand().isBust() && !dealer.getDealerHand().isBlackjack()
				&& !player.getPlayerHand().isBlackjack() && !dealerDone) {
			return true;
		} else
			return false;
	}

	// I created this method to simplify the logic for other methods.
	public boolean isHandOver() {
		if (player.getPlayerHand().isBust() 
				|| dealer.getDealerHand().isBust() 
				|| dealer.getDealerHand().isBlackjack()
				|| player.getPlayerHand().isBlackjack() 
				|| dealerDone) {

			return true;
		}
		return false;
	}

	/*
	 * This method deals another card to the player as long as the game is not over
	 * and the player does not elect to stand.
	 */
	public void hitPlayer() {
		if (isHandOver() == false) {
			Card hitCard = deck.pop();
			player.getPlayerHand().accept(hitCard);
		}
	}
	public void doDoubleDown() {
		bet = 2 * bet;
		hitPlayer();
	}
	public boolean canDoubleDown() {
		if(canHit()&& player.getPlayerHand().getCards().size()==2) {
			return true;
		}
		return false;
	}
	/*
	 * This method deals another card to the dealer as long as the game is not over
	 * and the dealer's hand total is less than 17.
	 */
	public void hitDealer() {
		while (isHandOver() == false 
				&& dealer.getDealerHand().getTotal() <= 16) {
			Card hitCard = deck.pop();
			dealer.getDealerHand().accept(hitCard);

		}
		dealerDone = true;
	}


	public void goWrapUpHand() {
		if (isHandOver()) {
			calculateBankroll();
		}
	}

	public boolean isPlayerBlackjack() {
		if (player.getPlayerHand().isBlackjack() && !dealer.getDealerHand().isBlackjack()) {
			return true;
		} else
			return false;
	}

	public boolean isDealerBlackjack() {
		if (!player.getPlayerHand().isBlackjack() && dealer.getDealerHand().isBlackjack()) {
			return true;
		} else
			return false;
	}

	// Need to build this out for different types of wins
	public void calculateBankroll() {
		if (isPlayerBlackjack()) {
			bankRoll = bankRoll + (1.5 * bet);
		} else if (doesPlayerWin()) {
			bankRoll = bankRoll + bet;
		} else if (doesHouseWin()) {
			bankRoll = bankRoll - bet;
		} else
			bankRoll = bankRoll + 0;
	}

	public Deck getDeck() {
		return deck;
	}

	public Player getPlayer() {
		return player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public double getBankRoll() {
		return bankRoll;
	}

	public boolean isDealerDone() {
		return dealerDone;
	}

}
