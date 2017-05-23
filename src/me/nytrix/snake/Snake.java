package me.nytrix.snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener,KeyListener {
	
	public static Snake snake;
	
	public JFrame frame;
	public RenderPanel panel;
	public Dimension dim;
	
	public Timer timer = new Timer(20,this);
	public ArrayList<Point> snakeParts = new ArrayList<>();
	
	public Point head, cherry;
	public Random random;
	
	public boolean over = false;
	public boolean paused;

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	
	public int ticks = 0, direction = DOWN, score, tailLength = 10, time;

	
	public Snake(){
		this.dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame = new JFrame("Snake");
		this.frame.setVisible(true);
		this.frame.setSize(800,800);
		this.frame.setLocationRelativeTo(null);
		this.frame.add(this.panel = new RenderPanel());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.addKeyListener(this);
		this.start();
	}
	public void start(){
		this.over = false;
		this.paused = false;
		this.time = 0;
		this.score = 0;
		this.tailLength = 14;
		this.ticks = 0;
		this.direction = DOWN;
		this.head = new Point(0, -1);
		this.random = new Random();
		this.snakeParts.clear();
		this.cherry = new Point(random.nextInt(79), random.nextInt(66));
		this.timer.start();
	}
	public void stop(){
		
	}
	public void restart(){
		this.stop();
		this.start();
	}
	
	private boolean noTailAt(int x, int y){
		for (Point point : snakeParts){
			if (point.equals(new Point(x, y))){
				return false;
			}
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.panel.repaint();
		this.ticks++;
		if (ticks % 2 == 0 && head != null && !over && !paused){
			time++;
			snakeParts.add(new Point(head.x, head.y));
			if (direction == UP){
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)){
					head = new Point(head.x, head.y - 1);
				}else{
					over = true;
				}
			}
			if (direction == DOWN){
				if (head.y + 1 < 76 && noTailAt(head.x, head.y + 1)){
					head = new Point(head.x, head.y + 1);
				}else{
					over = true;
				}
			}
			if (direction == LEFT){
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)){
					head = new Point(head.x - 1, head.y);
				}
				else{
					over = true;
				}
			}
			if (direction == RIGHT){
				if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)){
					head = new Point(head.x + 1, head.y);
				}else{
					over = true;
				}
			}
			if (snakeParts.size() > tailLength){
				snakeParts.remove(0);

			}
			if (cherry != null){
				if (head.equals(cherry)){
					score += 10;
					tailLength++;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		int i = e.getKeyCode();
		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT){
			direction = LEFT;
		}
		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT){
			direction = RIGHT;
		}
		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN){
			direction = UP;
		}
		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP){
			direction = DOWN;
		}
		if (i == KeyEvent.VK_SPACE){
			if (over){
				this.start();
			}else{
				paused = !paused;
			}
		}
	}

	public static void main(String args[]){
		snake = new Snake();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
