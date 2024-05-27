package model.cards;

import allShared.ICard;

/**
 * Classe qui décrit le comportement des instances de Cartes 
 * utilisées par le model
 * 
 * @author francoise.perrin
 */
public class Card implements ICard, Comparable<Card>{ 
	
	/** Valeur */
	private Rank rank;

	/** Couleur */
	private Suit suit;

	/** true = face visible, false = face cachée */
	private boolean isFaceUp;
	
	public Card(Rank rank,  Suit suit) {
		super();
		this.rank = rank;
		this.suit = suit;
		this.isFaceUp = false;
//		this.isFaceUp = true;
	}
	
	@Override
	public final Rank getRank() {
		return rank;
	}
	
	@Override
	public final Suit getSuit() {
		return this.suit;
	}
	
	@Override
	public final boolean isRevealed() {
		return this.isFaceUp;
	}
	
	public final void reveale() {
		this.isFaceUp = true;
	}

	public final void hide() {
		this.isFaceUp = false;
	}
	
	@Override
	public String toString() {
		String ret = "?-?";
		if (this.isFaceUp)
			ret = this.rank.getName() + "-" + this.suit.getName();

		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	/**
	 * compare le rang des cartes
	 */
	@Override
	public int compareTo(Card pc) {
		int ret = 0;
		ret = this.rank.getRank() - pc.rank.getRank();
	
		return ret;
	}

}


