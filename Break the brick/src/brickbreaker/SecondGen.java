package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SecondGen implements ImageObserver {
	public Image brickpic;
	public int map[][];
	public int Bwidth;
	public int Bheight;
	public int Xaxis;
	public int Yaxis;
	public SecondGen(int rows, int columns,int x,int y) {
		this.Xaxis = x;
		this.Yaxis = y;
		map = new int[rows][columns];
		for(int i = 0; i < map.length;i++) {
			for(int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		
		// size
		
		Bwidth = 590 / columns;
		Bheight = 150 / rows;
		
	}
//Xaxis 48
	//Yaxis 50
	
	public void draw(Graphics2D g) {
		
		//red = ImageIO.read(getClass().getResource("/images/red.png"));
		try {
			brickpic = ImageIO.read(getClass().getResource("/images/rectangle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < map.length;i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] > 0) {
					// the blocks place 
				
					//	g.setColor(Color.red);
					//	g.fillRect(j * Bwidth + Xaxis, i * Bheight + Yaxis, Bwidth, Bheight);
						g.drawImage(brickpic ,j * Bwidth + Xaxis, i * Bheight + Yaxis, Bwidth, Bheight,this);
						//to create edges between blocks
						
						g.setStroke(new BasicStroke(3));
						g.setColor(Color.LIGHT_GRAY);
						g.drawRect(j * Bwidth + Xaxis, i * Bheight + Yaxis, Bwidth, Bheight);
					
					
				}
			}
		}
	}
	
	public void SetBrick(int value, int row, int column) {
		
		map[row][column] = value;
	}
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
