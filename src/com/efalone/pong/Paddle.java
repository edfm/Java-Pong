package com.efalone.pong;

import com.efalone.lib.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class Paddle implements Entity{
	// FIELDS
	
	private int x;
	private int y;
	
	private int dx;
	private int speed;
	
	private boolean left;
	private boolean right;
	
	private int width;
	private int height;
	
	public int score;
	
	// CONSTRUCTOR
	
	public Paddle(int x, int y) {
		width = 48;
		height = 16;
	
		this.x = x - width / 2;
		this.y = y;
		
		speed = 2;
		
		score = 0;
		
	}

	// FUNCTIONS

	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	
	public void update() {
		
		if(left){
			dx = -speed;
		}
		if(right){
			dx = speed;
		}
		
		x += dx;
		
		if(x < 0){
			x = 0;
		}
		if(x + width > GamePanel.WIDTH){
			x = GamePanel.WIDTH - width;
		}
		
		dx = 0;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
}

