package snakeGame;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GameFrame(){
		
		this.add(new GamePanel());
		this.setTitle("Snakey boy");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setResizable(false);
		
	}

}
