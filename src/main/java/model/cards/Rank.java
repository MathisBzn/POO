package model.cards;

/**
 * Valeur des cartes pour différents types de Game
 * 
 * @author francoise.perrin
 */
public enum Rank {
	_2 ("2",2,0,0),
	_3 ("3",3,0,0),
	_4 ("4",4,0,0),
	_5 ("5",5,0,0),
	_6 ("6",6,0,0),
	_7 ("7",7,0,0),
	_8 ("8",8,0,0),
	_9 ("9",9,0,14),
	_10 ("10",10,10,10),
	_VALET ("Valet",11,2,20),
	_DAME ("Dame",12,3,3),
	_ROI ("Roi",13,4,4),
	_AS ("As",14,11,11);
	
	private String name;
	private int rank;
	private int beloteRank;
	private int beloteTrumpRank;
	
	private Rank(String name, int rank, int beloteRank,int beloteTrumpRank) {
		this.name = name;
		this.rank = rank;
		this.beloteRank = beloteRank;
		this.beloteTrumpRank = beloteTrumpRank;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getBeloteRank() {
		return beloteRank;
	} 
	
	public int getBeloteTrumpRank() {
		return beloteTrumpRank;
	} 
	
	public String getName() {
		return name;
	}
}
