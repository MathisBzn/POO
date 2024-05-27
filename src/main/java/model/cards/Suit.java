package model.cards;

/**
 * Couleurs des cartes
 * 
 * @author francoise.perrin
 */
public enum Suit {
	CARREAU("Carreau",1), 
	COEUR("Coeur",2), 
	PIQUE("Pique",3), 
	TREFLE("Trefle",4)
	;

	private int suit;
	private String name;

	private Suit(String name, int value) {
		this.name = name;
		suit = value;
	}

	public int value() {
		return suit;
	}
	public String getName() {
		return name;
	}
}
