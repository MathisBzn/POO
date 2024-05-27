package model.games;

import java.util.Collections;
import java.util.Comparator;

import allShared.ICardsCollection;
import allShared.IGameEvaluator;
import model.cards.Card;
import model.cards.NewWarGameCardComparator;


/**
 * Cet évaluateur s'appuie sur un comparateur de cartes (Comparator)
 * i.e. la méthode compare() définie dans classe NewWarGameCardComparator
 * qui compare les valeurs des cartes et en cas d'égalité, leur couleur
 * 
 * @author francoise.perrin
 */
public class NewWarGameEvaluator extends AbstractGameEvaluator implements IGameEvaluator {

	protected final Card max(ICardsCollection gamingMat) {
		Card maxCard = null;
		maxCard = gamingMat.max();
		return maxCard;
	}

	protected final int comparaison(Card card, Card maxCard) {
		int diff = -99999;
		Comparator<Card> comparator = new NewWarGameCardComparator();
		diff = comparator.compare(card, maxCard);
		return diff;
	}
	

}