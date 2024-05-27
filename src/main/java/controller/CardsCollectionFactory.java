package controller;


import allShared.CardsCollectionType;
import allShared.ICardsCollection;
import model.cards.Board;
import model.cards.Deck;
import model.cards.Hand;


/**
 * Factory qui permet de créer les différents types de collections de cartes
 * 
 * @author francoise.perrin
 */
public class CardsCollectionFactory {
	
	/**
	 * Constructeur privé empêche la construction de plusieurs objets de ce type
	 * par invocation du constructeur (avec new) :
	 * il y aura donc au maximum 1 seule instance de cette classe
	 */
	private CardsCollectionFactory() {
		
	}
	
	public static ICardsCollection getCardsCollection(CardsCollectionType cardCollectionType) {
        switch (cardCollectionType) {
            case DECK52: return new Deck(52);
            case DECK32: return new Deck(32);
            case BOARD: return new Board();
            case HAND: return new Hand();
        }
        return new Board();
	}
}