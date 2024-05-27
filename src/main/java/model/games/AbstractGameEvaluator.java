package model.games;

import allShared.ICardsCollection;
import allShared.IGameEvaluator;
import model.cards.Card;

/**
 * Classe commune aux différents types d'évaluateurs (IGameEvaluator)
 *
 * Chaque instance d'IGame délègue à son objet IGameEvaluator le soin d'évaluer un pli
 * [Design Pattern Strategy]
 * 
 * @author francoise.perrin
 */
public abstract class AbstractGameEvaluator implements IGameEvaluator {

	/**
	 * Cette méthode est un Template Method (Design Pattern) 
	 * i.e. une méthode qui définit un algo et qui délègue à ses sous-classes  
	 * le soin de réaliser certaines partie de l'algo
	 */
	@Override
	public final Card evaluateTrickWinner(ICardsCollection gamingMat) {

		Card maxCard = null;
		if(gamingMat.isEmpty()) {
			return null;
		}
		maxCard = this.max(gamingMat);
		int frequency = 0;

		for(Card card : gamingMat) {
			if (this.comparaison(card, maxCard)==0) {
				frequency++	;
			}
		}

		if (frequency!=1) {
			 maxCard = null;
		}
		return maxCard;
	}

	protected abstract Card max(ICardsCollection gamingMat) ;
	protected abstract int comparaison(Card card, Card maxCard) ;
}
