package controller;

import java.util.List;

import allShared.GameType;
import allShared.ICardsCollection;
import allShared.IGame;
import model.games.ClassicWarGame;
import model.games.NewWarGame;

/**
 * Factory qui permet de créer les différents types de Game
 * 
 * @author francoise.perrin
 */
public class GameFactory {
	
	/**
	 * Constructeur privé empêche la construction de plusieurs objets de ce type
	 * par invocation du constructeur (avec new) :
	 * il y aura donc au maximum 1 seule instance de cette classe
	 */
	private GameFactory() {
		
	}
	
	public static IGame getGame(GameType gameType, List<String> playersNames, ICardsCollection deck) {
	
        switch (gameType) {
            case WARGAME_CLASSIC: return new ClassicWarGame(playersNames, deck);
            case WARGAME_NEW: return new NewWarGame(playersNames, deck);
 //         case BELOTE: return new Belote(playersNames, deck);
		default:
			break;
          
        }
        return new ClassicWarGame(playersNames, deck); // Game par défaut
	}
}