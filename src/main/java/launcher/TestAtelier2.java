package launcher;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import allShared.CardsCollectionType;
import allShared.ICard;
import allShared.ICardsCollection;
import allShared.IGameEvaluator;
import allShared.IGameView;
import allShared.IPlayer;
import controller.CardsCollectionFactory;
import model.cards.Board;
import model.cards.Card;
import model.cards.CardRender;
import model.cards.Deck;
import model.cards.Hand;
import model.cards.NewWarGameCardComparator;
import model.cards.Rank;
import model.cards.Suit;
import model.games.ClassicWarGameEvaluator;
import model.games.NewWarGameEvaluator;
import model.player.Player;
import model.player.PlayerRender;
import view.GameConsoleView;
import view.GameSwingView;
import view.GameViews;

/**
 * Programme de test des classes du Model pour l'atelier 2
 * La trace d'exécution attendue est écrite en commentaire de chaque instruction
 * 
 * @author francoise.perrin
 */
public class TestAtelier2 {

	public static void main(String[] args) {
		
		System.out.println("*********** Test Atelier2 ***********");

		Card c1 = new Card(Rank._2, Suit.CARREAU);
		Card c2 = new Card(Rank._ROI, Suit.CARREAU);
		Card c3 = new Card(Rank._2, Suit.CARREAU);
		Card c4 = new Card(Rank._2, Suit.PIQUE);
//
//
//
//		////////////////////////////////////////////////////////
//		// Test classe Hand - Méthodes de la classe Collections
//		////////////////////////////////////////////////////////
//
		System.out.println("\n\n** Test classe Hand\n");
		Hand hand = new Hand();

		hand.addCard(c1);
		hand.addCard(c2);
		hand.addCard(c3);
		hand.addCard(c4);

		/*
		 * Vérifiez dans constructeur classe Card que
		 * ligne "this.isFaceUp : true;" ne soit pas commentée
		 */
		System.out.println("hand : " + hand);	// hand : [[2-Carreau, Roi-Carreau, 2-Carreau, 2-Pique]]
//
//
//		/* Test shuffle() avec 2 algos différents (commentez l'un puis l'autre pour tester) */
		hand.shuffle();
//		// le résultat après mélange ALEATOIRE des cartes sera différent d'une exécution à l'autre...
		System.out.println("hand après shuffle() : " + hand);	// hand après shuffle() : [[Roi-Carreau, 2-Carreau, 2-Pique, 2-Carreau]]
//
//
//		/* Test sort() */
		hand.sort();
		System.out.println("hand après sort() : " + hand);	// hand après sort() : [[2-Carreau, 2-Carreau, 2-Pique, Roi-Carreau]]
//
//
//		/* Test max() */
		Card maxCard = hand.max();
		System.out.println("maxCard : " + maxCard);	// maxCard : Roi-Carreau
//
//
//
//		///////////////////////////////////////////////////////////////////////////////////
//		// Test classe Deck et AbstractCardsCollection
//		// La classe Deck sait faire quasi les mêmes opérations que la classe Hand
//		// Il est temps de factoriser ces méthodes dans la classe AbstractCardsCollection
//		//////////////////////////////////////////////////////////////////////////////////
//
//
//		/* Déplacez les méthodes communes dans AbstractCardsCollection
//		 * Notifiez le lien d'héritage dans la classe Hand
//		 * Testez : le résultat doit être le même que précédemment ...
//		 */
//
//
		System.out.println("\n\n** Test classe Deck\n");
//
//		/* Test Deck() et autres méthodes de la classe Deck */
		ICardsCollection deck = new Deck(32);
		System.out.println("Deck : " + deck); // Deck : [[7-Carreau, 7-Coeur, 7-Pique, 7-Trefle, 8-Carreau, 8-Coeur, ... As-Pique, As-Trefle]]
		deck.shuffle();
		System.out.println("Deck après shuffle : " + deck); // Deck après shuffle : [[ ... résultat différent à chaque test ... ]]
		System.out.println("Max du Deck : " + deck.max()); 	// Max du Deck : As-Carreau
//
//
//
//		///////////////////////////////////////////////////////////////////////////////////
//		// Test classe Board
//		// Elle sait faire les mêmes opérations que la classe AbstractCardsCollection
//		//////////////////////////////////////////////////////////////////////////////////
//
		System.out.println("\n\n** Test classe Board\n");
//
//		/* Test Board() */
		ICardsCollection board1 = CardsCollectionFactory.getCardsCollection(CardsCollectionType.BOARD);
		System.out.println("Board1 : " + board1); // Board1 : [[]]
//
//
//		/* Test Board(Collection) */
		Card[] tabCards = {new Card(Rank._7, Suit.CARREAU),new Card(Rank._ROI, Suit.PIQUE)};
		List<Card> listCards = Arrays.asList(tabCards);
		ICardsCollection board2 = new Board(listCards);
		System.out.println("Board2 : " + board2); 	// Board2 : [[7-Carreau, Roi-Pique]]
//
//
//		/* Test autres méthodes héritées de AbstractCardsCollection */
		board2.addCard(c4);
		System.out.println("Board2 après add : " + board2); 	// Board2 après add : [[7-Carreau, Roi-Pique, 2-Pique]]
		board2.removeCard(2);
		System.out.println("Board2 après remove : " + board2); 	// Board2 après remove : [[7-Carreau, Roi-Pique]]
//
//
//
//		//////////////////////////////////////////////////////////////////////////////////////////
//		// Test construction d'une ICardsCollection à partir de n'importe quelle autre
//		// Complétez ce constructeur dans les classes AbstractCardsCollection, Hand, Deck et Board
//		// et décommentez les lignes suivantes pour tester
//		//////////////////////////////////////////////////////////////////////////////////////////
//
		System.out.println("\n\n** Test Ajout constructeur par copie dans toute la hiérarchie de classes\n");

		ICardsCollection board3 = new Board(board2);
		System.out.println("Board3 : " + board3); 			// Board3 : [[7-Carreau, Roi-Pique]]
		System.out.println("Hand2 : " + new Hand(board2)); 	// Hand2 : [[7-Carreau, Roi-Pique]]
		System.out.println("Deck2 : " + new Deck(board2)); 	// Deck2 : [[7-Carreau, Roi-Pique]]



		//////////////////////////////////
		// Test Comparator
		//////////////////////////////////

		System.out.println("\n\n** Test comparateur de cartes\n");


		/* Test compareTo() et compare() sur objets de type Card */
		NewWarGameCardComparator cardComparator = new NewWarGameCardComparator();
		System.out.println("Comparaison c1 et c2 : " + c1.compareTo(c2) +
				" *** " + cardComparator.compare(c1, c2));	// Comparaison c1 et c2 : -11 *** -11
		System.out.println("Comparaison c3 et c4 : " + c3.compareTo(c4) +
				" *** " + cardComparator.compare(c3, c4));	// Comparaison c3 et c4 : 0 *** -2


		/* Test tri selon l'ordre naturel ou avec le Comparator */
		board2.addCard(c4);
		board2.addCard(c1);
		board3 = new Board(board2);
		System.out.println("\nboard2 avant tri : " + board2);	// board2 avant tri : [[7-Carreau, Roi-Pique, 2-Pique, 2-Carreau]]
		System.out.println("board3 avant tri : " + board3);	// board3 avant tri : [[7-Carreau, Roi-Pique, 2-Pique, 2-Carreau]]
		board2.sort();
		board3.sort(cardComparator);
		System.out.println("board2 après tri : " + board2);	// board2 après tri : [[2-Pique, 2-Carreau, 7-Carreau, Roi-Pique]]
		System.out.println("board3 après tri Comparator : " + board3);	// board3 après tri Comparator : [[2-Carreau, 2-Pique, 7-Carreau, Roi-Pique]]


		/* Test recherche du max selon l'ordre naturel ou avec le Comparator */
		hand.clear();
		hand.addCard(c1);
		hand.addCard(c4);
		System.out.println("\nhand : " + hand);	// hand : [[2-Carreau, 2-Pique]]
		System.out.println("hand.max() : " + hand.max());	// hand.max() : 2-Carreau
		System.out.println("hand.max(cardComparator) : " + hand.max(cardComparator));	// hand.max(cardComparator) : 2-Pique



		/////////////////////////////////////////////////////////////
		// Test Iterator de ICardsCollection
		// dans la méthode addWonCardsBackToHand() de la classe Player
		////////////////////////////////////////////////////////////

		System.out.println("\n\n** Test Iterator dans addWonCardsBackToHand() de classe Player\n");
		Player p1 = new Player("Joueur1");

		p1.addCardToHand(c1);
		p1.addCardToTrickPile(c2);
		p1.addCardToTrickPile(c4);
		System.out.println("p1 : " + p1);	// p1 : [Joueur1 ** Hand[[2-Carreau]] ** trickPile[[Roi-Carreau, 2-Pique]]]


		/* Test addWonCardsBackToHand() avec un iterator implicite, puis explicite */
		p1.addWonCardsBackToHand();
		System.out.println("Après addWonCardsBackToHand() : " + p1);	// Après addWonCardsBackToHand() : [Joueur1 ** Hand[[2-Carreau, 2-Pique, Roi-Carreau]] ** trickPile[[]]]



		////////////////////////////////////////////////////////////////////////
		// Test Evaluateurs de plis - classe AbstractGameEvaluator et dérivées
		// Etudiez et comprenez la structure de evaluateTrickWinner()
		// puis codez les méthodes dans les classes dérivées
		////////////////////////////////////////////////////////////////////////

		System.out.println("\n\n** Test evaluateTrickWinner() de classe AbstractGameEvaluator\n");
		System.out.println("hand : " + hand);		// hand : [[2-Carreau, 2-Pique]]
		System.out.println("board3 : " + board3);	// board3  : [[2-Carreau, 2-Pique, 7-Carreau, Roi-Pique]]


		/* Test evaluateTrickWinner() de classe ClassicWarGameEvaluator */
		IGameEvaluator classicEvaluator = new ClassicWarGameEvaluator();
		System.out.println("classicEvaluator board3 : " + classicEvaluator.evaluateTrickWinner(board3));	// classicEvaluator board3 : Roi-Pique
		System.out.println("classicEvaluator hand : " + classicEvaluator.evaluateTrickWinner(hand));		// classicEvaluator hand : null


		/* Test evaluateTrickWinner() de classe NewWarGameEvaluator */
		IGameEvaluator newEvaluator = new NewWarGameEvaluator();
		System.out.println("newEvaluator board3 : " + newEvaluator.evaluateTrickWinner(board3));	// newEvaluator board3 : Roi-Pique
		System.out.println("newEvaluator hand : " + newEvaluator.evaluateTrickWinner(hand));		// newEvaluator hand : 2-Pique



		////////////////////////////////////////////////////////////////////////
		// Test CardRender et PlayerRender
		// Ces objets se substituent aux objets initiaux
		// et réduisent leur nb de méthodes accessibles
		////////////////////////////////////////////////////////////////////////

		System.out.println("\n\n** Test CardRender et PlayerRender\n");

		/* Test CardRender */
		ICard cardRender = new CardRender(c2); /* un objet CardRender enveloppe un objet Card */
		System.out.println("c2 : " + c2);	// c2 : Roi-Carreau
		System.out.println("cardRender : " + cardRender);	// cardRender : Roi-Carreau
		System.out.println("cardRender.getRank() : " + cardRender.getRank());	// cardRender.getRank() : _ROI
		System.out.println("cardRender.getSuit() : " + cardRender.getSuit());	// cardRender.getSuit() : CARREAU
		System.out.println("cardRender.isRevealed() : " + cardRender.isRevealed());	// cardRender.isRevealed() : true


		/* Décommentez la ligne suivante et constatez que vous ne pouvez invoquer que des
		 * méthodes définies dans ICard et non pas dans Card sur cet objet CardRender
		 */
//				System.out.println("cardRender.reveale() : " + cardRender.reveale());	//


		/* COMPLETEZ le programme ci-dessous pour tester les autres méthodes de CardRender */
		// ...

		/* Test PlayerRender */
		IPlayer playerRender = new PlayerRender(p1);
		System.out.println("\np1 : " + p1);	// p1 : [Joueur1 ** Hand[[2-Carreau]] ** trickPile[[Roi-Carreau, 2-Pique]]]
		System.out.println("playerRender : " + playerRender);	// playerRender : Joueur1

		/* COMPLETEZ le programme ci-dessous pour tester les autres méthodes de PlayerRender */
		// ...


		////////////////////////////////////////////////////////////////////////
		// Etudiez et testez les méthodes des classes du package view
		// Il n'y a rien à coder, seulement à comprendre la conception et le code
		// et en particulier les DP Composite et Template Method
		//
		// Les instructions suivantes vous serviront d'inspiration pour
		// coder les classes du package model.game dans l'atelier 3
		// Inutile de les comprendre toutes maintenant, en particulier
		// les instructions liées aux Map
		////////////////////////////////////////////////////////////////////////

		System.out.println("\n\n** Simulation Jeu et Test Views");
		System.out.println("** L'affichage va s'effectuer sur la console et sur la GUI\n");


		/* Initialisation du jeu */
		Player player1 = new Player("Joueur1");
		Player player2 = new Player("Joueur2");
		Player trickWinnerPlayer = null;
		player1.addCardToHand(c1);
		player2.addCardToHand(c2);

		Map<Player, Card> gamingMatMap = new TreeMap<Player, Card>();
		gamingMatMap.put(player1, c1);
		gamingMatMap.put(player2, c2);


		/* Evaluation pli */
		ICardsCollection boardToEvaluate = new Board(gamingMatMap.values());
		Card trickWinnerCard = newEvaluator.evaluateTrickWinner(boardToEvaluate);


		/* Tag vainqueur du pli */
		if(trickWinnerCard!=null) {
			for (Entry<Player, Card> entry : gamingMatMap.entrySet()) {
				if (entry.getValue().equals(trickWinnerCard)) {
					trickWinnerPlayer = entry.getKey();
					trickWinnerPlayer.setTrickWinner(true);
					break;
				}
			}
		}


		/* Fabrication de la Map que les Views afficheront */
		Map<IPlayer, ICard> gamingMatMapRender = new TreeMap<IPlayer, ICard>();
		for (Entry<Player, Card> entry : gamingMatMap.entrySet()) {
			Player player = entry.getKey();
			playerRender = new PlayerRender(player);
			cardRender = new CardRender(entry.getValue());
			gamingMatMapRender.put(playerRender, cardRender);
		}


		/* Affichage du jeu par les Views */

		IGameView views = new GameViews(false);
		IGameView commandLineView = new GameConsoleView(true);
		IGameView gameSwingView = new GameSwingView(false);
		views.addViewable(commandLineView);
		views.addViewable(gameSwingView);
		views.showGamingMatAndTrickWinner(gamingMatMapRender);

		
		//////////////////////////////////////////////////////////////////////////////////
		// Après toutes ces évolutions, effectuez un test de non régresion
		// pour vérifier  que ce qui a fonctionné tout au long du TP fonctionne toujours
		// Vérifiez également la trace d'exécution du TestAtelier1 
		// re-activez au préalable "isfaceUp=false" dans la classe Card
		//
		// Si tout est OK, Bravo !
		//////////////////////////////////////////////////////////////////////////////////
	}
	
	

}


