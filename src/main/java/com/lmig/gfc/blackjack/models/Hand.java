package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	//This is our Hand class. A hand is a collection of cards

	private ArrayList<Card> cards;
	private int sum;

	public Hand() {
		cards = new ArrayList<Card>();
	}
	public void goStartNewHand() {
		cards.clear();
	}

	public void accept(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getTotal() {
		/*
		 * this method calculates the total for the hand. It also handles Aces. By
		 * keeping a count of the Aces in the hand, it should be scalable for multiple
		 * decks. 
		 */
		sum = 0;
		int ace = 0;
		for (Card card : cards) {
			if (card.getFace() == "A") {
				ace += 1;
			}
			sum += card.getValue();
			if ((ace >= 1) && isBust()) {
				ace -= 1;
				sum = sum - 10;
			}

		}
		return sum;
	}

	public boolean isBust() {
		if (sum > 21) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBlackjack() {
		if (sum == 21 && cards.size() == 2) {
			return true;
		} else {
			return false;
		}
	}
}
