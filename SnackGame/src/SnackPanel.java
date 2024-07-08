import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class SnackPanel extends JPanel implements ActionListener{
	final int WIDTH =500;
	final int HEIGHT = 500;
	final int UNIT = 25;
	final int DELAY =100;
	int xFood;
	int yFood;
	int bodySize = 7;
	int biggestBody = (WIDTH+HEIGHT)/UNIT;
	int xSnack[] = new int[biggestBody];
	int ySnack[] = new int[biggestBody];
	int foodEaten =0;
	char direction = 'd';
	boolean running = false;
	
	
	
	
	Timer timer;
	Random random;
	SnackPanel(){
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new keyControl());
		runGame();
		
	}
	public void runGame() {
		running = true;
		foodRandom();
		timer = new Timer(100,this);
		timer.start();
	}
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawComponent(g);
	}
	public void drawComponent(Graphics g) {
		
		for ( int i=0; i<(WIDTH/UNIT);i++) {
			g.drawLine(i*UNIT, 0, i*UNIT, HEIGHT);
		}
		for ( int i=0; i<(WIDTH/UNIT);i++) {
			g.drawLine(0, i*UNIT, 500, i*UNIT);
		}
		
		// draw food 
		g.setColor(Color.red);
		g.fillOval(xFood, yFood, UNIT, UNIT);
		
		//draw Snack
		for ( int i=0; i<bodySize; i++) {
			if(i==0) {
			g.setColor(new Color(0,204,0));
			g.fillRect(xSnack[i], ySnack[i], UNIT, UNIT);
			}
			else {
				g.setColor(Color.green);
				g.fillRect(xSnack[i], ySnack[i], UNIT, UNIT);
			}
			
		}
		if (!running) {
			String msg = "Game Over";
			FontMetrics metrics = getFontMetrics(new Font("MV Boli",Font.PLAIN,50));
			g.setColor(Color.red);
			g.setFont(new Font("MV Boli",Font.PLAIN,50));
			g.drawString(msg,( (WIDTH -metrics.stringWidth(msg))/2), HEIGHT/2);
			}
		if (running) {
			String score = "Score :"+foodEaten;
			FontMetrics metrics = getFontMetrics(new Font("MV Boli",Font.PLAIN,30));
			g.setColor(Color.red);
			g.setFont(new Font("MV Boli",Font.PLAIN,30));
			g.drawString(score,(WIDTH-metrics.stringWidth(score))/2, 50);
			
			
			}
	}
	
	
	public void foodRandom() {
		random = new Random();
		xFood = random.nextInt((WIDTH/UNIT))*UNIT;
		yFood = random.nextInt((HEIGHT/UNIT))*UNIT;
	}
	
	
	public void move() {
		for ( int i=bodySize; i>0;i--) {
			xSnack[i] = xSnack[i-1];
			ySnack[i] = ySnack[i-1];
		}
		
		switch(direction) {
		
			case 'd': xSnack[0] = xSnack[0]+UNIT;
					
				break;
			case 'a': xSnack[0] = xSnack[0]-UNIT;
					
				break;
			case 'w': ySnack[0] = ySnack[0]-UNIT;
					
				break;
			case 's': ySnack[0] = ySnack[0]+UNIT;
					
				break;
		}
		
		repaint();
	}
	
	
	public void checkFood() {
		if (xSnack[0] == xFood && ySnack[0]==yFood) {
			xFood = random.nextInt((WIDTH/UNIT))*UNIT;
			yFood = random.nextInt((HEIGHT/UNIT))*UNIT;
			bodySize++;
			foodEaten++;
			repaint();
		}
	}
	
	
	public void breakCase() {
	  // the snack crash into right Panel
	   if(xSnack[0] >WIDTH ) {
		   running = false;
	   }
	   // the snack crash into left Panel
	   if(xSnack[0] <0 ) {
		   running = false;
	   }
	   // the snack crash into top Panel
	   if(ySnack[0] <0) {
		   running = false;
	   }
	   // the snack crash into bottom Panel
	   if(ySnack[0] >HEIGHT ) {
		   running = false;
	   }
	   for (int i=1; i<bodySize;i++) {
		   if (xSnack[0] ==xSnack[i] && ySnack[0] == ySnack[i]) {
			   running = false;
		   }
		  
	   }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkFood();
			breakCase();
			
		}
		
	}
	
	private class keyControl extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				if (direction !='a') {
				direction = 'd';}
				 break;
	
			case KeyEvent.VK_LEFT:
				if (direction !='d') {
				direction = 'a';}
					break;
				
			case KeyEvent.VK_UP:
				if (direction !='s') {
				direction = 'w';}
					break;
				
			case KeyEvent.VK_DOWN:
				if (direction !='w') {
				direction = 's';}
					break;
			
			}
		}
	}
	
}