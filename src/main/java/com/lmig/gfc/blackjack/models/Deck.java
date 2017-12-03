package com.lmig.gfc.blackjack.models;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	private Stack<Card> deck;
	private FaceCard faceCard;
	private NumberCard numberCard;
	private AceCard aceCard;

	public Deck() {
		deck = new Stack<Card>();

		for (Suits s : Suits.values()) {
			for (int value = 2; value <= 10; value += 1) {
				numberCard = new NumberCard(s, value);
				// Add number cards to deck for each suit
				deck.push(numberCard);

			}
			for (Faces f : Faces.values()) {
				faceCard = new FaceCard(s, f);
				// Add face cards to deck for each suit
				deck.push(faceCard);

			}

			// Add a single Ace card to the deck in the current suit
			aceCard = new AceCard(s);
			deck.push(aceCard);

		}

	}
//Shuffling time
	public void goShuffleDeck() {
		Collections.shuffle(deck);
	}

	
	public Card pop() {
		return deck.pop();
	}

	public Stack<Card> getDeck() {
		return deck;
	}
}
