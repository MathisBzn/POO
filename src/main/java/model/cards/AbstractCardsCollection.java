package model.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import allShared.ICardsCollection;

/**
 * Classe qui décrit les attributs et méthodes commun(e)s à toutes les Collections de cartes
 * elle implémente l'interface Iterable pour être parcourue par un Iterator [Design pattern]
 * 
 * Elle s'appuie sur les méthodes de sa Collection enveloppée (ici List)  
 * enrichies des méthodes shuffle(), max() et iterator()
 * [On peut la voir comme un adapter de List - Design Pattern]
 * 
 * [Question du Carnet de Bord : qu'est-ce qui justifie l'existence de cette classe ?
 * La réponse n'est pas "... factoriser le code commun à ses classes dérivées" 
 * (même si cette phrase est néanmoins vraie ;-)) ]
 * 
 * @author francoise.perrin
 */
public abstract class AbstractCardsCollection implements ICardsCollection, Iterable<Card> {

	protected List<Card> cards;

	public AbstractCardsCollection() {
		super();
		cards = new ArrayList<Card>();
	}

	public AbstractCardsCollection(Collection<Card> collection) {
		cards = new ArrayList<Card>(collection);
	}

	public AbstractCardsCollection(ICardsCollection iCardsCollection) {
		cards = new ArrayList<Card>();
		for(Card card : iCardsCollection) {
			cards.add(card);
		}
	}

	@Override
	public final Card removeTopCard() {
		Card card = null;
		if (this.size() > 0)
			card = this.cards.remove(0);
		return card;
	}

	@Override
	public final Card removeCard(int index) {
		Card card = null;
		if (index < this.size())
			card = this.cards.remove(index);
		return card;
	}

	@Override
	public final void clear() {
		this.cards.clear();
	}

	@Override
	public final int size() {
		int ret = 0;
		ret = this.cards.size();
		return ret;
	}

	@Override
	public final void sort() {
		Collections.sort(this.cards);
	}

	@Override
	public final void sort(Comparator<Card> comparator) {
		Collections.sort(this.cards, comparator);
	}

	@Override
	public final Card max() {
		return Collections.max(this.cards);
	}

	@Override
	public final Card max(Comparator<Card> comparator) {
		return Collections.max(this.cards, comparator);
	}

	@Override
	public final void shuffle() {
		if (this.size() > 0){
			Collections.shuffle(this.cards);
			Random rand = new Random();
			int randomNum = rand.nextInt((20 - 1) + 1) + 1;

			for(int i = 0; i < randomNum; i++) {
				Collections.swap(this.cards, rand.nextInt(this.size()), rand.nextInt(this.size()));
			}
		}
	}

	@Override
	public final void addCard(Card pc) {
		this.cards.add(pc);
	}

	@Override
	public final boolean isEmpty() {
		boolean ret = false;
		ret = this.cards.isEmpty();
		return ret;
	}

	@Override
	public final Iterator<Card> iterator() {

		return new Iterator<Card>() {
			Iterator<Card> it =  cards.iterator();
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Card next() {
				return it.next();
			}

		};
	}

	@Override
	public String toString() {
		return "[" + cards + "]";
	}

}
