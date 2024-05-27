package controller;

import java.util.List;
import java.util.Map;

import allShared.CardsCollectionType;
import allShared.GameType;
import allShared.ICard;
import allShared.ICardsCollection;
import allShared.IGame;
import allShared.IGameView;
import allShared.IPlayer;

/**
 * Cette classe permet la communication entre la (les) View et le Model
 * (qui ne se connaissent pas)
 * Elle gère l'enchainement des actions, de la distribution des cartes
 * à l'affichage du vainqueur [adaptation du DP Mediator]
 * 
 * @author francoise.perrin
 */
public class GameController {

	private IGame game;
	private IGameView view;

	public GameController(IGameView view) {
		super();
		this.game = null;
		this.view = view;	
	}

	public void run() {

		boolean trace = false;	/* à mettre à true pour suivre le détail de l'éxécution */

		/*
		 * Demande à la View le type de Jeu
		 */
		GameType gameType = this.view.getGameType();

		/*
		 * Demande à la View la liste des noms de joueurs
		 */
		List<String> playersNames = this.view.getPlayersName();

		/*
		 * Demande à la View le type du Deck et le crée 
		 */
		CardsCollectionType deckType = this.view.getDeckType();
		ICardsCollection deck = CardsCollectionFactory.getCardsCollection(deckType);

		/*
		 * Crée le Game
		 */
		this.game = GameFactory.getGame(gameType, playersNames, deck);
		if (trace) System.out.println(game);

		int nbTrick=1;

		/*
		 * 1 jeu
		 */
		do {
			Map<IPlayer, ICard> gamingMatRender = null;
			boolean isTrickWon = false;

			/*
			 * 1 tour de jeu
			 */
			do {
				if (trace) System.out.println("Tour N° "+nbTrick++);

				/*
				 * Choix cartes à jouer (si view ne propose rien, le model décide)
				 * et Pose des cartes
				 */

				Map<String, Integer> whichCardsArePlayed = this.view.chooseCardsToPlay();
				this.game.PlayCards(whichCardsArePlayed);

				/*
				 * évaluation du pli, visualisation dans la View
				 */
				isTrickWon = this.game.evaluateTrickWinner();

				gamingMatRender = this.game.getGamingMatRender();
				this.view.showGamingMatAndTrickWinner(gamingMatRender);

				if (trace) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			} while (!this.game.isGameEnd() && !isTrickWon);

			/*
			 * trickwinner remporte le pli
			 */
			this.game.theWinnerTakesItAll();
			if (trace) System.out.println(game);

		} while (!this.game.isGameEnd());

		/*
		 * Affichage vainqueur
		 */
		IPlayer winner = this.game.theWinnerIs();
		this.view.showWinner(winner);
	}
	
}
