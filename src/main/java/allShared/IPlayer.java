package allShared;

/**
 * Interface commune à tous les joueurs
 * ces 3 méthodes + compareTo()
 * 
 * @author francoise.perrin
 */
public interface IPlayer extends Comparable<IPlayer>{

	public String getName() ;
	public boolean isStillActive();
	public boolean isTrickWinner();
	public boolean isGameWinner();
	public void setTrickWinner(boolean isTrickWinner);
}
