package allShared;

import java.util.List;
import java.util.Map;

/**
 * Interface qui définit le comportement attendu des Views
 * 
 * @author francoise.perrin
 */
public interface IGameView {

	public boolean isMasterView();
	public GameType getGameType();
	public List<String> getPlayersName();
	public CardsCollectionType getDeckType();

	public Map<String, Integer> chooseCardsToPlay();
	public void showGamingMatAndTrickWinner(Map<IPlayer, ICard> gamingMatRender) ;
	public void showWinner(IPlayer winner);
	
	public void addViewable(IGameView gameView);
}
