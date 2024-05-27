package allShared;

public enum GameType {
	WARGAME_CLASSIC("Bataille avec règles classiques"),
	WARGAME_NEW("Bataille avec nouvelles règles"),
	BELOTE("Belote")
	// ...
	;
	
	private String name;
	
	private GameType(String name) {
		this.name = name;	}
	
	public String getName() {
		return name;
	}
}