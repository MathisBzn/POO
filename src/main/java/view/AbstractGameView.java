package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import allShared.CardsCollectionType;
import allShared.GameType;
import allShared.ICard;
import allShared.IGameView;
import allShared.IPlayer;

/**
 * Cette classe factorise les attributs et méthodes des différentes View
 * 
 * Cette conception est uniquement "Pédagogique" pour illustrer le DP Template Method : 
 * il y a en effet peu de chance que dans la vraie vie, 
 * l'affichage en mode graphique soit le même que sur une console ...
 * 
 * @author francoise.perrin
 */
public abstract class AbstractGameView implements IGameView {
	
	private final boolean isMasterView;
	protected GameType gameType;
	
	/**
	 * Le constructeur délègue aux classes dérivées le soin de
	 * choisir le type de Game et d'afficher le jeu (Mode console ou graphique)
	 * [DP template Method]
	 * @param isMasterView
	 */
	public AbstractGameView(boolean isMasterView) {
		super();
		this.isMasterView = isMasterView;
		this.gameType = this.chooseGameType();
		this.createAndShowView();
	}
	
	protected abstract GameType chooseGameType();

	protected abstract void createAndShowView();

	/**
	 * En l'absence de décision par la View, le Model décidera des cartes à jouer
	 */
	@Override
	public abstract Map<String, Integer> chooseCardsToPlay();
	
	/**
	 * Cette méthode gère le résultat qui s'affiche à chaque tour de jeu
	 * Elle délègue aux classes dérivées le soin de l'afficher
	 * [DP Template Method]
	 */
	@Override
	public void showGamingMatAndTrickWinner(Map<IPlayer, ICard> gamingMatRender) {
		
		StringBuilder text = new StringBuilder("")  ;
		IPlayer trickWinner = null;
		ICard trickWinnerCard = null;
		
		for (Entry<IPlayer, ICard> entry : gamingMatRender.entrySet()) {
			IPlayer player = entry.getKey();
			ICard card = entry.getValue();
			text.append("  ["+player+", "+card+"], ");
		
			if (player.isTrickWinner()) {
				trickWinner = player;
				trickWinnerCard = card;
//				player.setTrickWinner(false);
			}
		}
		if(trickWinner != null) {
			text.append("  ---  ["+trickWinner+", "+trickWinnerCard+"] remporte le pli");
		}
		else {
			text.append("  ---  Pas de vainqueur pour ce pli");
		}
		this.display(text);
	};
	
	
	/**
	 * Cette méthode gère le résultat qui s'affiche à la fin du jeu
	 * Elle délègue aux classes dérivées le soin de l'afficher
	 * [DP Template Method]
	 */
	@Override
	public void showWinner(IPlayer winner) {
		StringBuilder text = new StringBuilder("")  ;
		if(winner != null) {
			text.append("\n  Le gagnant est : " + winner.getName() + " !");
		}
		else {
			text.append("\n  Aucun gagnant ! ");
		}
		this.display(text);

	}

	protected abstract void display(StringBuilder text) ;
	
	
	/**
	 * le type de jeu est sélectionné par la View (dans classes dérivées)
	 * puis utilisé par le controller pour fabriquer le Game
	 */
	@Override
	public final GameType getGameType() {
		return this.gameType;
	}

	/**
	 * le type de Deck est sélectionné par la View (dans classes dérivées)
	 * puis le Deck est fabriqué par le controller
	 */
	@Override
	public abstract CardsCollectionType getDeckType() ;

	/**
	 * @param playerNumber
	 * @return liste noms joueurs
	 */
	@Override
	public abstract List<String> getPlayersName();

	/**
	 * Cette méthode permet d'ajouter une View à une autre View
	 * Cela permet au controller de ne communiquer qu'avec une seule View
	 * qui elle propagera les infos aux autres View
	 * [DP Composite]
	 */
	@Override
	public abstract void addViewable(IGameView gameView) ;

	@Override
	public final boolean isMasterView() {
		return this.isMasterView;
	}
	
}
