package allShared;

import java.util.Map;

import model.player.PlayerRender;

/**
 * Interface qui définit le comportement attendu du Model
 * 
 * @author francoise.perrin
 */
public interface IGame {
	public void PlayCards(Map<String, Integer> whichCardArePlayed);
	public boolean evaluateTrickWinner() ;
	public Map<IPlayer, ICard> getGamingMatRender();
	public boolean isGameEnd() ;
	public void theWinnerTakesItAll() ;
	public PlayerRender theWinnerIs() ;
}
