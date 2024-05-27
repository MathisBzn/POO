package view;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import allShared.CardsCollectionType;
import allShared.GameType;
import allShared.IGameView;

/**
 * View en mode Console
 * 
 * @author francoise.perrin
 */
public class GameConsoleView extends AbstractGameView implements IGameView {

	public GameConsoleView(boolean isMasterView) {
		super(isMasterView);
	}

	
	protected void createAndShowView() {
		System.out.println("  Jeu de " + this.gameType.getName() + "\n");
	}
	
	protected void display(StringBuilder text) {
		System.out.println(text);
	}
	
	/**
	 * En l'absence de décision par la View, le Model décidera des cartes à jouer
	 * [Il devrait être demandé aux utilisateurs...]
	 */
	@Override
	public Map<String, Integer> chooseCardsToPlay(){
		return null;
	}

	/**
	 * le type de Deck est sélectionné par la View 
	 * [Il devrait être demandé aux utilisateurs...]
	 * puis le Deck sera fabriqué par le controller
	 */
	@Override
	public CardsCollectionType getDeckType() {
		CardsCollectionType deckType = CardsCollectionType.DECK32;
		return deckType;
	}

	/**
	 * @param playerNumber
	 * @return liste noms joueurs
	 */
	@Override
	public List<String> getPlayersName() {
	
		List<String> playersNames = new LinkedList<String>();
		
		int playerNumber = 2;
		for(int i=1; i<=playerNumber;i++){
			playersNames.add("Joueur"+i);
		}
		return playersNames;
	}

	@Override
	public void addViewable(IGameView gameView) {
		// Unsupported Method
	}

	
	/*
	 *  Méthode qui demande aux utilisateurs (joueurs) le type de jeu
	 *  [Ici codé en dur dans GameConsoleView]
	 */
	protected GameType chooseGameType() {
		return GameType.WARGAME_CLASSIC;
	}
}
