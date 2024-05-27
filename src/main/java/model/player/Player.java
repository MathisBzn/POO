package model.player;

import java.util.Iterator;
import java.util.Objects;

import allShared.CardsCollectionType;
import allShared.IPlayer;
import controller.CardsCollectionFactory;
import model.cards.Card;
import model.cards.Hand;

/**
 * Classe qui définit le comportement des Joueurs
 * 
 * Discussion Carnet de Bord : pertinence isStillActive() + isHandEmpty() ?
 * 
 * @author francoise.perrin
 */

public class Player implements IPlayer{
	private final String name;
	private final Hand hand;
	private final Hand trickPile;
	private boolean isTrickWinner;
	private boolean isGameWinner;
	
	public Player(String name) {
		this.name = name;
		this.hand = (Hand) CardsCollectionFactory.getCardsCollection(CardsCollectionType.HAND);
		this.trickPile = (Hand) CardsCollectionFactory.getCardsCollection(CardsCollectionType.HAND);
		this.isTrickWinner = false;
		this.isGameWinner = false;
	}
	
	public final void addCardToHand(Card pc) {
		this.hand.addCard(pc);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;
		Player player = (Player) o;
		return isTrickWinner == player.isTrickWinner && isGameWinner == player.isGameWinner && Objects.equals(name, player.name) && Objects.equals(hand, player.hand) && Objects.equals(trickPile, player.trickPile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hand, trickPile, isTrickWinner, isGameWinner);
	}

	public final void addCardToTrickPile(Card pc) {
		this.trickPile.addCard(pc);
	}
	
	public final Card playCard(int index) {
		Card card = null;
		card = this.hand.playCard(index);
		return card;
	}
	
	public final Card removeCardFromHand(int index) {
		Card card = null;
		card = this.hand.removeCard(index);
		return card;
	}
	
	public final Card removeCardFromTrickPile(int index) {
		Card card = null;
		card = this.trickPile.removeCard(index);
		return card;
	}
	
	public final boolean revealeCard(int index) {
		boolean ret = false;
		this.hand.revealeCard(index);
		return ret;
	}
	
	public final boolean hideCard(int index) {
		boolean ret = false;
		ret = this.hand.hideCard(index);
		return ret;
	}
	
	@Override
	public final String getName() {
		return this.name;
	}
	
	public final boolean isHandEmpty() {
		boolean ret = false;
		ret = this.hand.isEmpty();
		return ret;
	}

	public final boolean isTrickPileEmpty() {
		boolean ret = false;
		ret = this.trickPile.isEmpty();
		return ret;
	}
	
	/**
	 * return true si la main du joueur n'est pas vide
	 */
	@Override
	public final boolean isStillActive() {
		boolean ret = false;
		ret = !this.hand.isEmpty();
		return ret;
	}

	
	/**
	 * retourne true si le joueur possède toutes les cartes 
	 * du deck initial 
	 */
	public final boolean hasWonAllCards(int deckSize) {
		boolean ret = false;
		ret = this.hand.size() + this.trickPile.size() == deckSize;
		return ret;
	}
	
	@Override
	public final boolean isTrickWinner() {
		boolean ret = false;
		ret = this.isTrickWinner;
		return ret;
	}

	public final void setTrickWinner(boolean isTrickWinner) {
		this.isTrickWinner = isTrickWinner;
	}

	public final boolean isGameWinner() {
		boolean ret = false;
		ret = this.isGameWinner;
		return ret;
	}

	public final void setGameWinner(boolean isGameWinner) {
		this.isGameWinner = isGameWinner;
	}

	@Override
	public String toString() {
		return "["+ name + " ** Hand" + hand + " ** trickPile" + trickPile + "]";
	}

	
	@Override
	public int compareTo(IPlayer arg0) {
		int ret = -999999;
		ret = this.getName().compareTo(arg0.getName());
		return ret;
	}

	/**
	 * Toutes les cartes gagnées retournent dans la main du joueur
	 * après avoir été mélangées
	 * * Ecrivez et testez cette méthode de 2 manières :
	 *  1 - en utilisant l'itérator de manière implicite
	 *  2 - en utilisant l'Iterator de manière explicite  
	 */
	public void addWonCardsBackToHand() {
		this.trickPile.shuffle();
		for (Card card : this.trickPile) {
			this.hand.addCard(card);
		}
		this.trickPile.clear();

//		Iterator<Card> it = this.trickPile.iterator();
//		while (it.hasNext()) {
//			this.hand.addCard(it.next());
//			it.remove();
//		}
	}
	
}
