package launcher;

import allShared.IGameView;
import controller.GameController;
import view.GameConsoleView;
import view.GameSwingView;
import view.GameViews;

/**
 * Classe principale de l'application qui crée les objets 
 * et lance le jeu
 * (le Model est créé par le Controller)
 * 
 * Le Model (instance d'IGame) gère les aspects métier 
 * La ou les Views (instances de IGameView) gèrent 
 * les interactions avec les utilisateurs
 * 
 * Le Controller gère l'enchaמnement des échanges 
 * (invocation de méthodes) entre le Model et la/les Views
 * 
 * @author francoise.perrin
 */
public class GameLauncher {
	private final IGameView views;
	private final GameController gameController;
	
	public GameLauncher() {
		super();
		
		this.views = new GameViews(false);
		IGameView commandLineView = new GameConsoleView(true);
		IGameView gameSwingView = new GameSwingView(false);
		this.views.addViewable(commandLineView);
		this.views.addViewable(gameSwingView);
		
		this.gameController = new GameController(this.views);
		this.gameController.run();
	}

	public static void main(String args[]) {
		
		new GameLauncher();
	}
}
