package view;

import java.awt.Component;
import java.awt.Container;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import allShared.CardsCollectionType;
import allShared.GameType;
import allShared.IGameView;

/**
 * View en mode graphique Swing
 * [Pourrait être travaillée davantage puisque'ici, 
 * c'est juste l'équivalent de la console dans une fenêtre graphique...]
 * 
 * @author francoise.perrin
 */
public class GameSwingView extends AbstractGameView implements IGameView {

	private JTextArea textArea;
	private JFrame frame;

	/**
	 * Aucune instance de cette classe ne sera la View
	 * qui permet de sélectionner le type de jeu, la taille du Deck
	 * ni le nom des joueurs
	 * 
	 * @param isMasterView
	 */
	public GameSwingView(boolean isMasterView) {
		super(isMasterView);
	}
	
	protected void createAndShowView() {

		/*
		 *  Création fenêtre du jeu
		 */
		frame = new JFrame("  Jeu de carte sur GUI  ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLocation(10,10);

		/*
		 *  Affichage vertical dans le panneau de la fenêtre
		 *  d'une zone de texte et d'un ascenseur
		 */
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        textArea = new JTextArea("   Trace d'exécution du jeu\n\n", 100, 1);
        textArea.setSize(500, 500);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
	    scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(scrollPane);
        
        /*  
		 *  Dès que la fenêtre est visible, 
		 *  l'utilisateur peut interagir avec le pgm
		 */
		frame.setVisible(true);
	}
	
	protected void display(StringBuilder text) {
		textArea.append(text + "\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	

	@Override
	public void addViewable(IGameView gameView) {
		// Unsupported Method
	}

	/**
	 * En l'absence de décision par la View, le Model décidera des cartes à jouer
	 */
	@Override
	public Map<String, Integer> chooseCardsToPlay() {
		return null;
	}


	/*
	 *  Méthode qui demande aux utilisateurs (joueurs) leur nom
	 *  [Ici codé en dur dans GameConsoleView]
	 */
	@Override
	public List<String> getPlayersName() {
		return null;
	}

	
	/*
	 *  Méthode qui demande aux utilisateurs (joueurs) le type de Deck
	 *  [Ici codé en dur dans GameConsoleView]
	 */
	@Override
	public CardsCollectionType getDeckType() {
		return null;
	}

	
	/*
	 *  Méthode qui demande aux utilisateurs (joueurs) le type de jeu
	 *  [Ici codé en dur dans GameConsoleView]
	 */
	@Override
	protected GameType chooseGameType() {
		return null;
	}
	

}
