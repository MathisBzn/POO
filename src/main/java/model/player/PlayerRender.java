package model.player;

import allShared.IPlayer;
import model.cards.Rank;

/**
 * Cette classe est un Proxy ou un Adapter [Design Pattern] de la classe Player
 * Ses instances sont utilisées par la View qui
 * n'a accès qu'aux "Getters" de l'objet encapsulé
 * 
 * Cette classe restreint donc le nombre de fonctionnalités existantes de l'objet enveloppé
 * et lui délègue le soin de réaliser les traitements
 * 
 * @author francoise.perrin
 *
 */
public class PlayerRender implements IPlayer, Comparable<IPlayer> {

	private final IPlayer player;
	
	public PlayerRender(IPlayer player) {
		this.player = player;
	}
	
	@Override
	public final String getName() {
		String ret = null;
		ret = this.player.getName();
		return ret;
	}
	
	@Override
	public final boolean isStillActive() {
		boolean ret = false;
		ret = this.player.isStillActive();
		return ret;
	}

	@Override
	public final boolean isTrickWinner() {
		boolean ret = false;
		ret = this.player.isTrickWinner();
		return ret;
	}
	

	@Override
	public boolean isGameWinner() {
		boolean ret = false;
		ret = this.player.isGameWinner();
		return ret;
	}

	@Override
	public void setTrickWinner(boolean isTrickWinner) {
		this.player.setTrickWinner(isTrickWinner);
	}

	@Override
	public String toString() {
		String ret = null;
		ret = this.player.getName();
		return ret;
	}

	@Override
	public int hashCode() {	
		int ret = -99999;
		ret = this.player.hashCode();
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		ret = this.player.equals(obj);
		return ret;
	}

	@Override
	public int compareTo(IPlayer o) {
		int ret = -99999;
		ret = this.player.compareTo(o);
		return ret;
	}


}
