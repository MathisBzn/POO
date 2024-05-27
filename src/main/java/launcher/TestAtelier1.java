package launcher;

import model.cards.Card; 
import model.cards.Hand;
import model.cards.Rank;
import model.cards.Suit;
import model.player.Player;

/**
 * Programme de test des classes du Model pour l'atelier 1
 * La trace d'exécution attendue est écrite en commentaire de chaque instruction
 * 
 * @author francoise.perrin
 */
public class TestAtelier1 {

	public static void main(String[] args) {
		
		System.out.println("*********** Test Atelier1 ***********\n\n");
		
		
		//////////////////////////////////
		// Test classe Card
		//////////////////////////////////

		System.out.println("** Test classe Card\n");
		Card c1;
		c1 = new Card(Rank._2, Suit.CARREAU);
		Card c2 = new Card(Rank._ROI, Suit.CARREAU);
		Card c3 = new Card(Rank._2, Suit.CARREAU);
		Card c4 = new Card(Rank._2, Suit.PIQUE);
		Card c2Bis = c2;

		
		/* Test toString() */
		System.out.println("c1 : " + c1.toString());	// c1 : ?-?
		System.out.println("c2 : " + c2);	// c2 : ?-?

		
		/* Test reveale(), hide(), isRevealed */
		c1.reveale();
		System.out.println("c1 : " + c1);	// c1 : 2-Carreau
		System.out.println("c1.isRevealed() : " + c1.isRevealed());	// 	c1.isRevealed() : true
		c1.hide();
		System.out.println("c1 après hide() : " + c1);	// c1 après hide() : ?-?
		System.out.println("c1.isRevealed : " + c1.isRevealed());	// c1.isRevealed : false
		
		/* Test différence Objet et Référence */
		c2.reveale();
		System.out.println("c2 après reveale() : " + c2);	// c2 : ?-?
		c2Bis.hide();
		System.out.println("c2 après hide invoqué depuis c2Bis : " + c2);	// c2 : ?-?
		
		
		/* Test getSuit() */
		System.out.println("c1.getSuit() : " + c1.getSuit());	// c1.getSuit() : CARREAU
		
		
		/* Test getRank() */
		System.out.println("c1.getRank() : " + c1.getRank());	// c1.getRank() : _2
		System.out.println("c2.getRank() : " + c2.getRank());	// c2.getRank() : _ROI
		System.out.println("c1.getRank().getRank() : " + c1.getRank().getRank());	// c1.getRank().getRank() : 2 ]
		System.out.println("c2.getRank().getRank() : " + c2.getRank().getRank());	// c2.getRank().getRank() : 13 ]
		System.out.println("c2.getRank().getName() : " + c2.getRank().getName());	// c2.getRank().getRank() : Roi ]
		
		
		/* Test ==, equals() + hashCode() */
		System.out.println("c1 == c1 : " + (c1 == c1));	// c1 == c1 : true
		System.out.println("c1 == c3 : " + (c1 == c3));	// c1 == c3 : false
		
		// Testez avec méthodes equals() et hashCode() commentées dans classe card [ c1.equals(c3) : false ]
		// recommencez le test après suppression des commentaires [ c1.equals(c3) : true ]
		System.out.println("c1.equals(c1) : " + c1.equals(c1));	// c1.equals(c1) : true
		System.out.println("c1.equals(c3) : " + c1.equals(c3));	// c1.equals(c3) : false / true

		
		/* Test compareTo() */
		System.out.println("c1.compareTo(c1) : " + c1.compareTo(c1));	// c1.compareTo(c1) : 0
		System.out.println("c1.compareTo(c2) : " + c1.compareTo(c2));	// c1.compareTo(c2) : -11
		System.out.println("c3.compareTo(c1) : " + c3.compareTo(c1));	// c3.compareTo(c1) : 0
		System.out.println("c3.compareTo(c4) : " + c3.compareTo(c4));	// c3.compareTo(c4) : 0
		
		
		
		//////////////////////////////////
		// Test classe Hand
		//////////////////////////////////

		System.out.println("\n\n** Test classe Hand\n");
		Hand hand = new Hand();

		/* Test toString() */
		System.out.println("hand - toString() de Object : "+hand); 	// hand - toString() de Object: model.cards.Hand@15db9742
		// Décommentez la méthode toString() de la classe Hand
		System.out.println("hand : " + hand);	// hand : [[]]
		
		
		/* Test addCard() */
		c1.reveale();
		c2.reveale();
		c3.reveale();
		c4.reveale();
		hand.addCard(c1);
		System.out.println("hand : " + hand);	// hand : [[2-Carreau]]
		hand.addCard(c2);
		hand.addCard(c3);
		hand.addCard(c4);
		System.out.println("hand : " + hand);	// hand : [[2-Carreau, Roi-Carreau, 2-Carreau, 2-Pique]]
	
		
		/* Test isEmpty(), size() */
		System.out.println("hand.size() : " + hand.size());	// hand.size() : 4
		System.out.println("hand.isEmpty() : " + hand.isEmpty());	// hand.isEmpty() : false
		
		
		/* Test removeTopCard(), removeCard(), clear() */
		hand.removeTopCard();
		System.out.println("Après removeTopCard() : " + hand);	// Après removeTopCard() : [[Roi-Carreau, 2-Carreau, 2-Pique]]
		hand.removeCard(1);
		System.out.println("Après removeCard(1) : " + hand);	// Après removeCard(1) : [[Roi-Carreau, 2-Pique]]
		hand.removeCard(6);
		System.out.println("Après removeCard(6) : " + hand);	// Après removeCard(6) : [[Roi-Carreau, 2-Pique]]
		hand.clear();
		System.out.println("Après clear() : " + hand);	// Après clear() : [[]]
		System.out.println("hand.isEmpty() : " + hand.isEmpty());	// hand.isEmpty() : true
		hand.removeTopCard();
		System.out.println("Après Clear + removeTopCard() : " + hand);	// Après Clear + removeTopCard() : [[]]
		hand.removeCard(1);
		System.out.println("Après Clear + removeCard(1) : " + hand);	// Après Clear + removeCard(1) : [[]]
		
		
		/* Test hideCard() */
		hand.addCard(c1);
		hand.addCard(c2);
		System.out.println("hand : " + hand);	// hand : [[2-Carreau, Roi-Carreau]]
		hand.hideCard(0);
		System.out.println("Après hideCard(0) : " + hand);	// Après hideCard(0) : [[?-?, Roi-Carreau]]
		hand.hideCard(1);
		System.out.println("Après hideCard(1) : " + hand);	// Après hideCard(1) : [[?-?, ?-?]]
		hand.hideCard(6);
		System.out.println("Après hideCard(6) : " + hand);	// Après hideCard(6) : [[?-?, ?-?]]

	
		/* Test revealedCard() */
		hand.revealeCard(1);
		System.out.println("Après revealeCard(1) : " + hand);	// Après revealeCard(1) : [[?-?, Roi-Carreau]]
		hand.revealeCard(6);
		System.out.println("Après revealeCard(6) : " + hand);	// Après revealeCard(6) : [[?-?, Roi-Carreau]]
		hand.revealeCard(0);
		System.out.println("Après revealeCard(0) : " + hand);	// Après revealeCard(0) : [[2-Carreau, Roi-Carreau]]

		
		/* Test playCard() */
		Card c5 = hand.playCard(0);	// playCard rend visible la carte et la supprime de la List
		System.out.println("c5 : " + c5);	// c5 : 2-Carreau
		System.out.println("Après playCard(0) : " + hand);	// Après playCard(0) : [[Roi-Carreau]]
		Card c6 = hand.playCard(6);	// playCard rend visible la carte et la supprime de la List
		System.out.println("c6 : " + c6);	// c6 : null ]
		System.out.println("Après playCard(6) : " + hand);	// Après playCard(6) : [[Roi-Carreau]]
	
		
		/* Tests playCard(), revealedCard(), hideCard() si List vide */
		hand.clear();
		Card c7 = hand.playCard(0);
		System.out.println("c7 : " + c7);	// c7 : null
		System.out.println("Après clear + playCard(0) : " + hand);	// Après clear + playCard(0) : [[]]
		hand.hideCard(1);
		System.out.println("Après clear + hideCard(1) : " + hand);	// Après clear + hideCard(1) : [[]]
		hand.revealeCard(1);
		System.out.println("Après clear + revealeCard(1) : " + hand);	// Après clear + revealeCard(1) : [[]]]
		System.out.println("Après clear + isEmpty() : " + hand.isEmpty());	// Après clear + isEmpty() : true
		
		
		//////////////////////////////////
		// Test classe Player
		//////////////////////////////////
		
		System.out.println("\n\n** Test classe Player\n");
		Player p1 = new Player("Joueur1");
		
		
		/* Test toString(), getname() */
		System.out.println("p1 : " + p1);	// p1 : [Joueur1 ** Hand[[]] ** trickPile[[]]]
		System.out.println("p1.getname() : " + p1.getName());	// p1.getname() : Joueur1
		
		
		/* Test setTrickWinner() isTrickWinner(), setGameWinner() isGameWinner() */
		p1.setTrickWinner(true);
		System.out.println("p1.isTrickWinner() : " + p1.isTrickWinner());	// p1.isTrickWinner() : true
		p1.setGameWinner(true);
		System.out.println("p1.isGameWinner() : " + p1.isGameWinner());	// p1.isGameWinner() : true

	
		/* Test addCardToHand(), addCardToTrickPile() */
		p1.addCardToHand(c1);
		System.out.println("p1 : " + p1);	// p1 : [Joueur1 ** Hand[[2-Carreau]] ** trickPile[[]]]
		p1.addCardToTrickPile(c2);
		p1.addCardToTrickPile(c4);
		System.out.println("p1 : " + p1);	// p1 : [Joueur1 ** Hand[[2-Carreau]] ** trickPile[[Roi-Carreau, 2-Pique]]]

		
		/* Test hasWonAllCards(), isHandEmpty(), isTrickPileEmpty(), isStillActive() */
		System.out.println("p1.isHandEmpty() : " + p1.isHandEmpty());	// p1.isHandEmpty() : false
		System.out.println("p1.isTrickPileEmpty() : " + p1.isTrickPileEmpty());	// p1.isTrickPileEmpty() : false
		System.out.println("p1.isStillActive() : " + p1.isStillActive());	// p1.isStillActive() : true
		System.out.println("p1.hasWonAllCards(3) : " + p1.hasWonAllCards(3));	// p1.hasWonAllCards(3) : true
		System.out.println("p1.hasWonAllCards(6) : " + p1.hasWonAllCards(6));	// p1.hasWonAllCards(6) : false

		
		/* Test hideCard(), revealeCard(), playCard() */
		p1.hideCard(0);
		System.out.println("Après hideCard(0) : " + p1);	// Après hideCard(0) : [Joueur1 ** Hand[[?-?, Roi-Carreau, 2-Pique]] ** trickPile[[]]]
		p1.revealeCard(0);
		System.out.println("Après revealeCard(0) : " + p1);	// Après revealeCard(0) : [Joueur1 ** Hand[[2-Carreau, Roi-Carreau, 2-Pique]] ** trickPile[[]]]
		Card c8 = p1.playCard(0);
		System.out.println("Après playCard : c8 : " + c8);	// Après playCard : c8 :  2-Carreau
		System.out.println("Après playCard(0) : " + p1);	// Après playCard(0) : [Joueur1 ** Hand[[Roi-Carreau, 2-Pique]] ** trickPile[[]]]
		
		/* Test removeCardFromHand(), removeCardFromTrickPile() */
		p1.addCardToTrickPile(p1.removeCardFromHand(0));
		System.out.println("Après removeCardFromHand(0) : " + p1);	// Après removeCaardFromHand(0) : [Joueur1 ** Hand[[Roi-Carreau]] ** trickPile[[2-Pique]]]
		p1.removeCardFromTrickPile(0);
		System.out.println("Après removeCardFromTrickPile(0) : " + p1);	// Après removeCardFromTrickPile(0) : [Joueur1 ** Hand[[2-Pique]] ** trickPile[[]]]
		
		
		/* Test equals(), compareTo */
		// faites générer les méthodes equals et hashcode par votre IDE
		System.out.println("p1.equals(new Player(\"Joueur2\") : " + p1.equals(new Player("Joueur2")));	// p1.equals(new Player("Joueur2") : false
		System.out.println("p1.compareTo(p1) : " + p1.compareTo(p1));	// p1.compareTo(p1) : 0
		System.out.println("new Player(\"Joueur3\").compareTo(p1) : " + new Player("Joueur3").compareTo(p1));	// new Player("Joueur3").compareTo(p1) : 2
		
	
	}

}


