package model.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import allShared.ICardsCollection;


/**
 * Objet qui contient l'ensemble des cartes de la main d'un joueur
 * 
 * Il est capable d'exécuter les traitements communs à toutes 
 * les collections de cartes (ajouter, supprimer, mélanger, trier, etc.)
 * et en plus révéler, cacher, jouer une carte
 * 
 * @author francoise.perrin
 */
public class Hand extends AbstractCardsCollection {

//	private final List<Card> cards; /* ToChange Atelier2 */

	public Hand() {
		super();
//		cards = new ArrayList<Card>();
	}

	public Hand(Collection<Card> collection) {
		super(collection);
//		cards = new ArrayList<Card>(collection);
	}

	public Hand(ICardsCollection iCardsCollection) {
		super(iCardsCollection);
//		cards = null;
	}

	/**
	 * @param index
	 * @return la carte à jouer si elle existe
	 * Supprime la carte de la liste
	 */
	public final Card playCard(int index) {

		Card card = null;
		if (index < this.size()){
			this.revealeCard(index);
			card = this.removeCard(index);
		}
		return card;
	}

	/**
	 * @param index
	 * @return true si la carte existe 
	 */
	public final boolean revealeCard(int index) {

		Card card = null;
		if (index < this.size()){
			this.cards.get(index).reveale();
			card = this.cards.get(index);
		}
		return card != null ? true : false;
	}

	/**
	 * @param index
	 * @return true si la carte existe 
	 */
	public final boolean hideCard(int index) {

		Card card = null;
		if (index < this.size()){
			this.cards.get(index).hide();
			card = this.cards.get(index);
		}
		return card != null ? true : false;
	}

}