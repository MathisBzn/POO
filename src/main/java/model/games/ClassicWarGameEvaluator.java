package model.games;

import allShared.ICardsCollection;
import allShared.IGameEvaluator;
import model.cards.Card;


/**
 * Cet évaluateur s'appuie sur la comparaison "naturelle" des cartes
 * i.e. la méthode compareTo() définie dans classe Card qui compare
 * les valeurs des cartes
 * 
 * @author francoise.perrin
 */
public class ClassicWarGameEvaluator extends AbstractGameEvaluator implements IGameEvaluator {

	protected final Card max(ICardsCollection gamingMat) {
		Card maxCard = null;
		maxCard = gamingMat.max();
		return maxCard;
	}

	protected final int comparaison(Card card, Card maxCard) {
		int diff = -99999;
		diff = card.compareTo(maxCard);
		return diff;
	}
}
