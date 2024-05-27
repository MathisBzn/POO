package allShared;

import java.util.Comparator;

import model.cards.Card;

/**
 * Interface commune à tous les types de collections de cartes
 * 
 * Toutes les instances de ces collections implémentent 
 * l'interface {@link java.lang.Iterable Iterable}, c'est à dire qu'elles peuvent être parcourues 
 * à l'aide d'un {@link java.util.Iterator Iterator}
 * 
 * @author francoise.perrin
 */
public interface ICardsCollection extends Iterable<Card> {
	
	public void shuffle() ;
	public Card removeTopCard() ;
	public Card removeCard(int index) ;
	public void addCard(Card pc) ;
	public void clear();
	public int size(); 
	public Card max();
	public Card max(Comparator<Card> comparator) ;
	public void sort();
	public void sort(Comparator<Card> comparator) ;
	public boolean isEmpty();
}
