package model.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import allShared.ICardsCollection;

/**
 * Objet qui contient l'ensemble des cartes déposées sur le tapis
 *
 * Il est capable d'exécuter les traitements communs à toutes 
 * les collections de cartes (ajouter, supprimer, mélanger, trier, etc.)
 *  
 * Question CB : qu'est-ce qui justifie existence de cette classe 
 * qui n'a aucun comportement spécifique ?
 * 
 * @author francoise.perrin
 */
public  class Board extends AbstractCardsCollection {
	

	public Board() {
		super();
	}

	public Board(Collection<Card> collection) {
		super(collection);
	}

	public Board(ICardsCollection iCardsCollection) {
		super(iCardsCollection);
	}
}
