package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import allShared.CardsCollectionType;
import allShared.GameType;
import allShared.ICard;
import allShared.IGameView;
import allShared.IPlayer;


/**
 * Cette classe est un Composite [Design Pattern]
 * qui permet à une classe cliente (le Controller)
 * de croire qu'elle ne manipule qu'une seule View 
 * alors qu'en réalité il y en a plusieurs 
 * 
 * @author francoise.perrin
 */
public class GameViews implements IGameView {
	List<IGameView> views;
	boolean isMasterView;
	
	public GameViews (boolean isMasterView) {
		super();
		views = new ArrayList<IGameView> ();
		this.isMasterView = isMasterView;
	}

	@Override
    public void addViewable (IGameView view) {
        views.add(view);
    }
	
	@Override
	public void showGamingMatAndTrickWinner(Map<IPlayer, ICard> gamingMatRender) {
		for (IGameView view : views) {
			view.showGamingMatAndTrickWinner(gamingMatRender);
		}
	}
	
	@Override
	public void showWinner(IPlayer winner) {
		for (IGameView view : views) {
			view.showWinner(winner);
		}
	}

	@Override
	public GameType getGameType() {
		GameType gameType = null;
		for (IGameView view : views) {
			if (view.isMasterView()){
				gameType = view.getGameType();
			}
		}
		return gameType;
	}

	@Override
	public List<String> getPlayersName() {
		List<String> playersName = null;
		for (IGameView view : views) {
			if (view.isMasterView()){
				playersName = view.getPlayersName();
			}
		}
		return playersName;
	}

	@Override
	public CardsCollectionType getDeckType() {
		CardsCollectionType deckType = null;
		for (IGameView view : views) {
			if (view.isMasterView()){
				deckType = view.getDeckType();
			}
		}
		return deckType;
	}

	@Override
	public Map<String, Integer> chooseCardsToPlay() {
		Map<String, Integer> cardsToPlay = null;
		for (IGameView view : views) {
			if (view.isMasterView()){
				cardsToPlay = view.chooseCardsToPlay();
			}
		}
		return cardsToPlay;
	}

	@Override
	public boolean isMasterView() {
		return this.isMasterView;
	}
}
