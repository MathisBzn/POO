package allShared;

import model.cards.Card;


/**
 * Interface qui définit le comportement attendu de tous les évaluateurs de Game
 * Chaque instance d'{@link IGame} délègue à son objet IGameEvaluator le soin d'évaluer un pli
 * C'est une illustration du Design Pattern Strategy
 * 
 * @author francoise.perrin
 */
public interface IGameEvaluator {
	
	public Card evaluateTrickWinner(ICardsCollection gamingMat);

}
