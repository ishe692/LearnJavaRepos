package snakeGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HIEGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HIEGHT)/UNIT_SIZE;
	static final int DELAY = 90;

	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int tailShrink = 20;// a inverse factor for gradual tail shrink
	int eyeDirect = 3;
	int applesEaten,appleX,appleY = 0;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;


	GamePanel(){

		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HIEGHT));
		this.setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.setLocationReletiveTo(null);
		startGame();


	}

	private void setLocationReletiveTo(Object object) {
		// TODO Auto-generated method stub

	}

	public void startGame()
	{
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}

	public void newApple() {

		appleX = random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
		appleY = random.nextInt(SCREEN_HIEGHT/UNIT_SIZE)*UNIT_SIZE;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		draw(g);

	}

	public void draw(Graphics g)
	{

		if(running) {


			// draw grid
			for(int i = 0; i<SCREEN_HIEGHT/UNIT_SIZE;i++) {

				g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
				g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HIEGHT);
			}

			// draw apple
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);


			// draw snake
			for(int i = 0; i<bodyParts;i++) {

				if(i==0) {

					g.setColor(Color.green);
					g.drawOval(x[i],y[i], UNIT_SIZE, UNIT_SIZE);
					g.fillOval(x[i],y[i], UNIT_SIZE, UNIT_SIZE);
					g.setColor(Color.black);
					g.fillOval(x[i]+UNIT_SIZE/4,y[i]+eyeDirect*UNIT_SIZE/10, UNIT_SIZE/3, UNIT_SIZE/3);
					g.fillOval(x[i]+3*UNIT_SIZE/4,y[i]+eyeDirect*UNIT_SIZE/10, UNIT_SIZE/3, UNIT_SIZE/3);

				}else {

					g.setColor(Color.cyan);
					g.drawOval(x[i],y[i], (tailShrink+1)*UNIT_SIZE/(i+tailShrink), (tailShrink+1)*UNIT_SIZE/(i+tailShrink));
					g.fillOval(x[i],y[i], (tailShrink+1)*UNIT_SIZE/(i+tailShrink), (tailShrink+1)*UNIT_SIZE/(i+tailShrink));
				}
			}
			
			
			// draw score
			g.setColor(Color.red);
			g.setFont(new Font("Ariel", Font.BOLD, 30));
			FontMetrics fontyBoy = getFontMetrics(g.getFont());
			g.drawString("score:"+ applesEaten, (SCREEN_WIDTH-fontyBoy.stringWidth("score:"+ applesEaten))/2, SCREEN_HIEGHT/12);
			
			
			

		}else
			gameOver(g);

	}


	public void move() {

		for(int i = bodyParts; i>0;i--) {

			x[i]= x[i-1];
			y[i]= y[i-1];
		}

		switch(direction) {

		case'U':
			y[0]= y[0]-UNIT_SIZE;
			break;
		case'D':
			y[0]= y[0]+UNIT_SIZE;
			break;
		case'L':
			x[0]= x[0]-UNIT_SIZE;
			break;
		case'R':
			x[0]= x[0]+UNIT_SIZE;
			break;



		}
	}


	public void checkApple() {

		if((x[0]==appleX)&&(y[0]==appleY)) {

			bodyParts++;
			newApple();
			applesEaten++;

		}
	}

	public void checkCollisions() {

		// check if head collides with body
		for(int i = bodyParts;i>0;i--) {

			if(x[0]==x[i]&&y[0]==y[i]) {
				running = false;
			}

		}

		// check left border
		if(x[0]<0)
			running = false;
		// check right border
		if(x[0]>SCREEN_WIDTH)
			running = false;
		// check top border
		if(y[0]<0)
			running = false;
		// check bottom border
		if(y[0]>SCREEN_HIEGHT)
			running = false;
		// stop timer if game over
		if(!running)
			timer.stop();

	}

	public void gameOver(Graphics g) {
		
		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman", Font.BOLD, 80));
		FontMetrics fontyBoy = getFontMetrics(g.getFont());
		g.drawString("GAME OVER!!", (SCREEN_WIDTH-fontyBoy.stringWidth("GAME OVER!!"))/2, SCREEN_HIEGHT/2);
		
		
		g.setColor(Color.red);
		g.setFont(new Font("Ariel", Font.BOLD, 30));
		FontMetrics fontyBoy2 = getFontMetrics(g.getFont());
		g.drawString("score:"+ applesEaten, (SCREEN_WIDTH-fontyBoy2.stringWidth("score:"+ applesEaten))/2, SCREEN_HIEGHT-SCREEN_HIEGHT/12);
		


	}
	@Override
	public void actionPerformed(ActionEvent e) {


		if(running) {

			move();
			checkApple();
			checkCollisions();

		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {

			switch(e.getKeyCode()) {

			case KeyEvent.VK_LEFT:
				if(direction!='R') 
					direction = 'L';
				break;

			case KeyEvent.VK_RIGHT:
				if(direction!='L') 
					direction = 'R';
				break;

			case KeyEvent.VK_UP:
				if(direction!='D') 
					direction = 'U';
					eyeDirect = -1;
				break;

			case KeyEvent.VK_DOWN:
				if(direction!='U') 
					direction = 'D';
					eyeDirect = 6;
				break;





			}

		}

	}

}
