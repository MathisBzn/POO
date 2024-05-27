package allShared;

import model.cards.Rank;
import model.cards.Suit;

/**
 * Interface commune à tous les types de cartes
 * 
 * @author francoise.perrin
 */
public interface ICard {
	
	public Rank getRank() ;
	public Suit getSuit() ;
	public boolean isRevealed() ;
}


