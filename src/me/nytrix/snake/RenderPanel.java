package me.nytrix.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel{
	
	public Color background = new Color(19302);
	public Font font;
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.font = new Font("Times New Roman", 0, 30);
		Snake snake = Snake.snake;
		g.setColor(this.background);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.GREEN);
		for (Point point : snake.snakeParts){
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.RED);
		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		String string = "Score: " + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.time / 20;
		g.setColor(Color.white);
		g.setFont(this.font);
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 6f), 30);
		string = "Game Over!";
		if (snake.over){
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 6f), (int) snake.dim.getHeight() / 4);
		}
		string = "Paused!";
		if (snake.paused && !snake.over){
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}

	}
}
