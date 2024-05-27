package model.games;

import java.util.List;

import allShared.ICardsCollection;
import allShared.IGame;
import allShared.IGameEvaluator;

/**
 * Jeu de Bataille qui utilise un évaluateur de type NewWarGameEvaluator
 * 
 * Il faudra discuter de la pertinence de cette classe...
 * 
 * @author francoise.perrin
 */
public class NewWarGame extends AbstractWarGame implements IGame {

	public NewWarGame(List<String> playersNames,ICardsCollection deck) {
		super(playersNames, deck);
	}

	protected final IGameEvaluator getGameEvaluator() {
		return new NewWarGameEvaluator();
	}

}
