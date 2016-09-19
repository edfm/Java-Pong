package com.efalone.pong;

import java.awt.*;

import com.efalone.lib.*;

public class Ball implements Entity{
	
	// FIELDS
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	private int speed;
	
	private int width;
	private int height;
	
	private Collision coll;
	
	// CONSTRUCTOR
	
	public Ball() {
		width = 16;
		height = 16;
		
		x = GamePanel.WIDTH / 2 - width / 2;
		y = GamePanel.HEIGHT / 2 - height / 2;
	
		speed = 1;
		
		coll = new Collision();
		
		int randNum = (int) Math.round(Math.random() * 1);
		if(randNum == 1){
			dx = -speed;
			dy = speed;
		} else {
			dx = speed;
			dy = -speed;
		}	
	}
	
	// FUNCTIONS
	
	public int getX() { return x;}
	public int getY() { return y;}
	public int getWidth() { return width;}
	public int getHeight() { return height;}
	
	public void update() {
		
		if(x < 0 || x + width > GamePanel.WIDTH){
			dx = -dx;
		}
		
		if(y < 0) {
			
			GamePanel.paddle1.score++;
			
			resetPosition();
		} else if(y + height > GamePanel.HEIGHT){
			
			GamePanel.paddle2.score++;
			
			resetPosition();
		}
		
		

		if(coll.check(x, y, width, height, 
				GamePanel.paddle1.getX(), GamePanel.paddle1.getY(), 
				GamePanel.paddle1.getWidth(), GamePanel.paddle1.getHeight())){
			dy = -dy;
		}
		
		if(coll.check(x, y, width, height, 
				GamePanel.paddle2.getX(), GamePanel.paddle2.getY(), 
				GamePanel.paddle2.getWidth(), GamePanel.paddle2.getHeight())){
			dy = -dy;
		}
		
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		g.fillOval(x, y, width, height);
	}
	
	private void resetPosition() {
		int xRand = (int) Math.round(Math.random() * 1);
		int yRand = (int) Math.round(Math.random() * 1);
		
		x = GamePanel.WIDTH / 2 - width / 2;
		y = GamePanel.HEIGHT / 2 - height / 2;
		
		if(xRand == 1){
			dx = -speed;
		} else {
			dx = speed;
		}
		
		if(yRand == 1){
			dy = speed;
		} else {
			dy = -speed;
		}
		
	}
	
}
