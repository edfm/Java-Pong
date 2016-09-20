package com.efalone.pong;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	
	// FIELDS
	
	public static int WIDTH = 360;
	public static int HEIGHT = 512;
	
	private boolean running;
	private Thread thread;
	
	private Graphics2D g;
	private BufferedImage image;
	
	private int FPS = 30;
	
	public static Paddle paddle1;
	public static Paddle paddle2;
	public static Ball ball;
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}
	
	public void init() {
		running = true;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
		g = (Graphics2D) image.getGraphics();
		
		paddle1 = new Paddle(WIDTH / 2, HEIGHT - 32);
		paddle2 = new Paddle(WIDTH / 2, 16);
		
		ball = new Ball();
	}
	
	public void run() {
		
		init();
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		
		long targetTime = FPS / 1000;
		
		while(running){
			startTime = System.nanoTime();
			
			gameUpdate();
			gameRender();
			gameDraw();
			
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			
			waitTime = (URDTimeMillis - targetTime);
			try{
				Thread.sleep(waitTime);
			}catch(Exception e){}
			
		}
	}

	public void gameUpdate(){
		paddle1.update();
		paddle2.update();
		ball.update();
		
		
	}
	
	public void gameRender(){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
		g.drawRect(0,0,WIDTH - 1,HEIGHT - 1);
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString(Integer.toString(paddle2.score), 20, HEIGHT / 2 - 10);
		g.drawString(Integer.toString(paddle1.score), 20, HEIGHT / 2 + 23);
		
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
	}
	
	public void gameDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			paddle1.setLeft(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			paddle1.setRight(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle2.setLeft(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle2.setRight(true);
		}
	
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			paddle1.setLeft(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			paddle1.setRight(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle2.setLeft(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle2.setRight(false);
		}
	}

	public void keyTyped(KeyEvent e) {}

	
}
