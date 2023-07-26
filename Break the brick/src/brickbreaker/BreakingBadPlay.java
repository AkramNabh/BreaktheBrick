package brickbreaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakingBadPlay extends JPanel implements KeyListener, ActionListener{
	// declaring images :D
    Image paddle;
	Image football;
	//defining coordinates and stuff
	int min = 1;
	int max = 3;
	int min1 = -1;
	int max1 = 1;
	 ThreadLocalRandom random = ThreadLocalRandom.current();
	int randomnum =  random.nextInt(min, max);
	private boolean working = false;
	private int BrNum = 32;
	private Timer render;
	private int speed = 6;
	private int sliderX = 310;
	private int ballX = 350;
	private int ballY = 400;
	private int BdirX = randomnum;
	private int BdirY = -2;
	private Generator map;
	
	//constructing the class
	
	
	public BreakingBadPlay() {
		
		//intitiating the paddle picture
		
		try {
			paddle = ImageIO.read(getClass().getResource("/images/paddle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		//intitiating the ball picture
		
		
		try {
			football = ImageIO.read(getClass().getResource("/images/ball.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		map = new Generator(4,8,0,0);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		render = new Timer(speed,this);
		render.start();
	}
	
	
	// graphics and all... to make things show on the screen
	
	
	public void paint(Graphics graphs) {
		
	//	Graphics2D graphs2dB = (Graphics2D) graphs.create();
	//	Graphics2D graphs2dS = (Graphics2D) graphs.create();
		
		// background
		
		graphs.setColor(Color.LIGHT_GRAY);
		graphs.fillRect(0,0,792,692);
		

		
		//blocks

				map.draw((Graphics2D)graphs);
				
				
		// the slider
				

		graphs.drawImage(paddle,sliderX, 550, 150,50, this);
	//	graphs.fillRoundRect(sliderX, 550, 150, 50, 8, 100);
		 
		// the ball
		
	//	graphs.setColor(Color.orange );
	//	graphs.fillOval(ballX, ballY, 20, 20);
		graphs.drawImage(football,ballX,ballY,25,25,this);
		
		//for the winning 
		
		if(BrNum == 0) {
			working = false;
			BdirX = 0;
			BdirY = 0;
			graphs.setColor(Color.red);
			graphs.setFont(new Font("serif",Font.BOLD,50));
			graphs.drawString("you won :D", 150, 300);
			
			
			graphs.setColor(Color.red);
		//	graphs.setFont(new Font("serif",Font.BOLD,30));
			graphs.drawString("press enter to start again", 40, 350);
		}
		
	//  for the lost or finish frame 
		if(ballY > 700) {
			working = false;
			BdirX = 0;
			BdirY = 0;
			graphs.setColor(Color.red);
			graphs.setFont(new Font("serif",Font.BOLD,50));
			graphs.drawString("game over", 150, 300);
			
			
			graphs.setColor(Color.red);
		//	graphs.setFont(new Font("serif",Font.BOLD,30));
			graphs.drawString("press enter to start again", 40, 350);
		}
		
		graphs.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		render.start();
		if (working) {
			if(new Rectangle(ballX, ballY,25,25).intersects(new Rectangle(sliderX,566,152,50))) {
				BdirY = -BdirY;
			}
			
			A: for(int i = 0; i < map.map.length;i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickx = j * map.Bwidth + 0;
						int bricky = i * map.Bheight + 0;
						int brickw = map.Bwidth;
						int brickh = map.Bheight;
						
						Rectangle rect = new Rectangle(brickx,bricky,brickw,brickh);
						Rectangle ballrec = new Rectangle(ballX,ballY,25,25);
						Rectangle brickrec = rect;
						
						if(ballrec.intersects(brickrec)) {
							map.SetBrick(0,i,j);
							BrNum--;
							
							if(ballX + 26 <= brickrec.x || ballX + 1 <= brickrec.x + brickrec.width) {
								BdirX = -BdirX;
							//	BdirY = -BdirY;
							} else {
								BdirY = -BdirY;
							}
							break A;
						}
					}
				}
			}
			ballY += BdirY;
			ballX += BdirX;
			
			if(ballX < 0) {
				BdirX = -BdirX;
			}
			if(ballY < 0) {
				BdirY = -BdirY;
			}
			if(ballX > 565) {
				BdirX = -BdirX;
			}
		}  
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) { //keys not working
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(sliderX >= 450) {
				sliderX = 450;
			} else {
				goRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(sliderX < 10) {
				sliderX = 10;
			} else {
				goLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!working) {
				working = true;
				 ballX = 350;
				 ballY = 400;
				 BdirX = randomnum;
				 BdirY = -2;
				 sliderX = 310;
				 map = new Generator(4,8,0,0);
				 repaint();
			}
		}
	}
	public void goRight() {
		working = true;
	sliderX += 20;	
	}
	public void goLeft() {
		working = true;
	sliderX -= 20;	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
