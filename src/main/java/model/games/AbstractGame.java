package model.games;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import allShared.CardsCollectionType;
import allShared.ICard;
import allShared.ICardsCollection;
import allShared.IGame;
import allShared.IGameEvaluator;
import allShared.IPlayer;
import controller.CardsCollectionFactory;
import model.cards.Board;
import model.cards.Card;
import model.cards.CardRender;
import model.cards.Rank;
import model.cards.Suit;
import model.player.Player;
import model.player.PlayerRender;

import java.util.TreeMap;

/**
 * Classe qui décrit les attributs et méthodes commun(e)s à les types de Game 
 * C'est le point d'entrée du Model- elle sert de facade aux autres classes
 * (AbstractGame sert de facade à Player qui sert de facade à Hand qui sert de facade à Card)
 * 
 * @author francoise.perrin
 */
public abstract class AbstractGame implements IGame {

	protected final ICardsCollection deck;			// Les cartes du jeu initial
	protected final int initDeckSize;				// Nb de carte du jeu en début de partie
	private final ICardsCollection board;			// les cartes sur le tapis
//	private final ICardsCollection discardPile; 	// La défausse (pas utile dans tous les jeux)

	protected final List<Player> players;			// La liste des joueurs

	private final Map<Player, Card> gamingMatMap;	// Les cartes jouées et par qui lors d'un tour de jeu


	public AbstractGame(List<String> playersNames, ICardsCollection deck) {
		super();

		this.gamingMatMap = new TreeMap<Player, Card>();

		/*
		 * Création des joueurs et initialisation tapis <joueur, carte> (gamingMatMap)
		 * et tapis <cartes> (Board)
		 */
		this.players = new LinkedList<Player>();
		for(String playerName : playersNames){
			Player player = new Player(playerName);
			this.players.add(player);
			this.gamingMatMap.put(player, null);
		}
		this.board = CardsCollectionFactory.getCardsCollection(CardsCollectionType.BOARD);
		
		/*
		 * "ouverture" du paquet de cartes et distribution
		 * des cartes du jeu de carte aux joueurs 
		 * de manière différente selon le type de jeu
		 */
		this.deck = deck;
		
		/* 
		 * [Ces lignes ne sont utiles que pour tester les algos avec 
		 * 4 cartes : à commenter/supprimer après les tests]
		 */
//		this.initDeckSize = 4;
//		Player player;
//		player = this.players.get(0);
//		player.addCardToHand(new Card(Rank._AS, Suit.CARREAU));
//		player.addCardToHand(new Card(Rank._10, Suit.CARREAU));
//		player = this.players.get(1);
//		player.addCardToHand(new Card(Rank._AS, Suit.COEUR));
//		player.addCardToHand(new Card(Rank._9, Suit.CARREAU));
		
		/* 
		 * [Ces lignes seront à décommenter après les tests avec 4 cartes]
		 */
		this.initDeckSize = this.deck.size();
		this.deck.shuffle();
		this.dealCardsFromDeck(this.initDeckSize / this.players.size());
	
	}
	

	/*
	 * La méthode de distribution des cartes est différente selon le type de jeu
	 * 1 par 1, 2 puis 3 puis 2, etc.
	 */
	protected abstract void dealCardsFromDeck(int nbCars);
	

	
	
	/**
	 * Cette méthode réalise 1 tour de jeu
	 * 
	 * Si whichCardArePlayed vide : le IGame décide seul de la manière de jouer les cartes 
	 * de manière différente en fonction du type de jeu (prévoir une méthode dans classes dérivées donc)
	 * 
	 * Chaque joueur joue la carte indiquée
	 * Elle est stockée dans le gamingMatMap qui sera évalué pour déterminer le gagnant du pli ;
	 * puis dans le Board qui contient toutes les cartes jouées lors d'un tour de jeu 
	 * Si "Bataille", nb cartes Board > nb de cartes gamingMatMap.
	 * 
	 * Selon le jeu,le joueur remet les cartes gagnées dans sa Main 
	 * 
	 * @param whichCardArePlayed
	 */
	@Override
	public final void PlayCards(Map<String, Integer> whichCardArePlayed) {

		if(whichCardArePlayed == null || whichCardArePlayed.isEmpty()){
			for(Player player : this.players){
				Card card = player.playCard(0);
				this.gamingMatMap.put(player,card);
				this.board.addCard(card);
			}
		}else{
			for (Entry<String, Integer> entry : whichCardArePlayed.entrySet()) {
				String playerName = entry.getKey();
				Integer cardIndex = entry.getValue();
				Player player = this.players.stream().filter(p -> p.getName().equals(playerName)).findFirst().get();
				Card card = player.playCard(cardIndex);
				this.gamingMatMap.put(player,card);
				this.board.addCard(card);
			}
		}
	}

	
	/**
	 * Evaluation d'un pli à l'aide d'un évaluateur (IGameEvaluator) spécifique à chaque jeu
	 * Au delà de simplement retourner si le pli a été gagné, 
	 * cette méthode "taggue" le vainqueur du pli
	 * 
	 * Remarque : le IGame délègue à un IGameEvaluator le soin d'évaluer le pli (Design Pattern Strategy)
	 * Cette méthode doit donc invoquer les méthodes getGameEvaluator() de ces classes dérivées
	 * et evaluateTrickWinner() du IGameEvaluator concerné
	 */
	@Override
	public final boolean evaluateTrickWinner() {

		boolean isTrickWon = false;
		
		IGameEvaluator gameEvaluator = this.getGameEvaluator();
		final Card winningCard = gameEvaluator.evaluateTrickWinner(new Board(this.gamingMatMap.values()));
		for(Entry<Player, Card> entry : this.gamingMatMap.entrySet()){
			Player player = entry.getKey();
			Card card = entry.getValue();
			player.setTrickWinner(false);
			if(card.equals(winningCard)){
				player.setTrickWinner(true);
				isTrickWon = true;
			}
			if(player.isHandEmpty()){
				player.addWonCardsBackToHand();
			}
		}
		return isTrickWon;
	}

	/*
	 * La méthode d'évaluation d'un pli est différente selon le type de jeu
	 * Elle est confiée à un IGameEvaluator
	 */
	protected abstract IGameEvaluator getGameEvaluator() ;
	

	/**
	 * Constitution de la Map visible par la ou les Views à partir de gamingMatMap
	 * les objets envoyés doivent être des CardRender et des PlayerRender 
	 * (qui n'ont pas de setter)
	 */
	@Override
	public final Map<IPlayer, ICard> getGamingMatRender() {
		Map<IPlayer, ICard> gamingMatMapRender = new TreeMap<IPlayer, ICard>();

		for(Entry<Player, Card> entry : this.gamingMatMap.entrySet()){
			Player player = entry.getKey();
			Card card = entry.getValue();
			PlayerRender playerRender = new PlayerRender(player);
			CardRender cardRender = new CardRender(card);
			gamingMatMapRender.put(playerRender, cardRender);
		}
		 
		return gamingMatMapRender;
	}


	/**
	 * Le joueur qui a gagné le pli ramasse toutes les cartes du tapis (board)
	 * dans sa TrickPile et non dans sa Hand
	 */
	@Override
	public final void theWinnerTakesItAll() {
		for(Player player : this.players){
			if(player.isTrickWinner()){
				while(!this.board.isEmpty()){
					player.addCardToTrickPile(this.board.removeTopCard());
				}
			}
		}

	}



	/**
	 * La méthode de test de la fin du jeu est différente selon le type de Game
	 * Au delà de retourner si le Game a été gagné, cette méthode
	 * "taggue" le vainqueur du Game
	 */
	@Override
	public abstract boolean isGameEnd() ;
	


	/**
	 * Retourne le joueur qui a gagné le Game 
	 */
	@Override
	public final PlayerRender theWinnerIs() {
		PlayerRender winnerPlayer = null;

		for(Player player : this.players){
			if(player.isGameWinner()){
				winnerPlayer = new PlayerRender(player);
				break;
			}
		}
		return winnerPlayer;
	}


	@Override
	public String toString() {
		String st = "";
		for (Player player : players) {
			st += player.toString();
			st+= "\n";
		}
		return st;
	}

}
