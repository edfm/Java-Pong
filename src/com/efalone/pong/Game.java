package com.efalone.pong;

import javax.swing.*;

public class Game{

		public Game(){
			
			JFrame frame = new JFrame("Pong");
			frame.setResizable(false);
			frame.setSize(GamePanel.WIDTH,GamePanel.HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setUndecorated(true);
			frame.setContentPane(new GamePanel());
			frame.pack();
			frame.setVisible(true);
		}
		
		public static void main(String args[]) {
			new Game();
		}
}
