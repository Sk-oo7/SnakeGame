package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private int snakexlength[] = new int[750];
	private int snakeylength[] = new int[750];

	private int wallx[] = new int[500];
	private int wally[] = new int[500];

	private int minex[] = new int[500];
	private int miney[] = new int[500];

	private int scores[] = new int[20];
	private int index = 0;
	private int lengthofsnake = 3;

	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;

	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;

	private Timer timer;
	private int delay = 120;
	private ImageIcon snakeimage;
	private int score = 0;
	private int level = 1;

	private int xpos = ((int) (Math.random() * 34) + 1) * 25;
	private int ypos = ((int) (Math.random() * 21) + 3) * 25;

	private int moves = 0;

	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void printarray(Graphics g) {
		if (index == 0)
			return;

		int max = scores[0], min = scores[0];
		for (int i = 0; i < index; i++) {
			if (scores[i] > max) {
				max = scores[i];
			}
			if (scores[i] < min) {
				min = scores[i];
			}
		}

		int h = 422;
		for (int i = 0; i < index; i++) {

			g.setColor(new java.awt.Color(33, 47, 60));
			g.setFont(new Font("comic sans ms", Font.BOLD, 18));
			if (scores[i] == max) {
				g.drawString(i + 1 + ") " + scores[i] + " (Highest)", 935, h);
			}
			if (scores[i] == min && i > 0) {
				g.drawString(i + 1 + ") " + scores[i] + " (Lowest)", 935, h);
			}
			g.drawString(i + 1 + ") " + scores[i], 935, h);
			h += 23;
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1194, 700);

		if (moves == 0) {
			snakexlength[0] = 100;
			snakexlength[1] = 75;
			snakexlength[2] = 50;

			snakeylength[0] = 100;
			snakeylength[1] = 100;
			snakeylength[2] = 100;

			while ((xpos == 100 && ypos == 100) || (xpos == 75 && ypos == 100) || (xpos == 50 && ypos == 100)) {
				xpos = ((int) (Math.random() * 34) + 1) * 25;
				ypos = ((int) (Math.random() * 21) + 3) * 25;
			}
		}
		for (int s = 1; s <= lengthofsnake; s++) {
			if (xpos == snakexlength[s] && ypos == snakeylength[s]) {
				xpos = ((int) (Math.random() * 34) + 1) * 25;
				ypos = ((int) (Math.random() * 21) + 3) * 25;
			}
		}
		// print title
		g.setColor(new java.awt.Color(214, 234, 248));
		g.setFont(new Font("comic sans ms", Font.PLAIN, 60));
		g.drawString("Snake Game", 400, 60);

		// set border for play area
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 852, 577);

		// set background for play area
		g.setColor(new java.awt.Color(33, 47, 60));
		g.fillRect(25, 75, 851, 575);

		// set right panel
		g.setColor(new java.awt.Color(33, 47, 60));
		g.fillRect(890, 75, 265, 575);
		g.setColor(Color.WHITE);
		g.drawRect(889, 74, 266, 577);

		// print level in right panel
		g.setColor(new java.awt.Color(214, 234, 248));
		g.setFont(new Font("comic sans ms", Font.PLAIN, 40));
		g.drawString(" Level: " + level, 935, 200);

		// print scores at top

		g.setColor(new java.awt.Color(214, 234, 248));
		g.setFont(new Font("comic sans ms", Font.PLAIN, 40));
		g.drawString("Score: " + score, 935, 250);

		// recent scores list box

		g.setColor(new java.awt.Color(33, 97, 140));
		g.fillRoundRect(900, 400, 245, 240, 15, 15);
		g.setColor(Color.WHITE);
		g.drawRoundRect(900, 400, 245, 240, 15, 15);

		g.setColor(new java.awt.Color(133, 193, 233));
		g.setFont(new Font("comic sans ms", Font.BOLD, 20));
		g.drawString("Recent scores:", 950, 380);

		printarray(g);

		// rightmouth = new ImageIcon("rightmouth.png");
		// rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

		for (int a = 0; a < lengthofsnake; a++) {

			if (a == 0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}

			if (a == 0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}

			if (a == 0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}

			if (a == 0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			}

			if (a != 0) {
				snakeimage = new ImageIcon("skin.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			// set border for play area
			g.setColor(Color.WHITE);
			g.drawRect(24, 74, 853, 577);

		}

		candy = new ImageIcon("apple.png");

		if (xpos == snakexlength[0] && ypos == snakeylength[0]) {
			score++;
			lengthofsnake++;

			xpos = ((int) (Math.random() * 34) + 1) * 25;
			ypos = ((int) (Math.random() * 21) + 3) * 25;

			for (int x = 0, w = 0; (wallx[w] != 0 && wally[w] != 0) || x <= lengthofsnake; w++) {
				if ((xpos == wallx[w] && ypos == wally[w]) || (xpos == snakexlength[x] && ypos == snakeylength[x])
						|| (xpos == minex[w] && ypos == miney[w])) {
					xpos = ((int) (Math.random() * 34) + 1) * 25;
					ypos = ((int) (Math.random() * 21) + 3) * 25;
				}
				x++;
				if (w == 499)
					w = 0;
			}

		}

		for (int i = 1; i < lengthofsnake; i++) {
			if (snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				g.setColor(new java.awt.Color(133, 193, 233));
				g.setFont(new Font("comic sans ms", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);

				g.setColor(new java.awt.Color(235, 245, 251));
				g.setFont(new Font("comic sans ms", Font.BOLD, 20));
				g.drawString("Press Space to RESTART", 312, 340);

				scores[index++] = score;
				timer.setDelay(100);
				for (int o = 0; wallx[o] != 0 && wally[o] != 0; o++) {
					wallx[o] = 0;
					wally[o] = 0;
				}
				timer.stop();
			}
		}
		for (int w = 0; wallx[w] != 0 && wally[w] != 0; w++) {

			if ((snakexlength[0] == wallx[w] && snakeylength[0] == wally[w])
					|| (snakexlength[0] == minex[w] && snakeylength[0] == miney[w])) {
				right = false;
				left = false;
				up = false;
				down = false;
				g.setColor(new java.awt.Color(133, 193, 233));
				g.setFont(new Font("comic sans ms", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);

				g.setColor(new java.awt.Color(235, 245, 251));
				g.setFont(new Font("comic sans ms", Font.BOLD, 20));
				g.drawString("Press Space to RESTART", 312, 340);

				scores[index++] = score;
				timer.setDelay(100);

				for (int o = 0; wallx[o] != 0 && wally[o] != 0; o++) {
					wallx[o] = 0;
					wally[o] = 0;
				}
				timer.stop();
			}
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();
		if (right) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakeylength[r + 1] = snakeylength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakexlength[r] += 25;
				} else {
					snakexlength[r] = snakexlength[r - 1];
				}
				if (snakexlength[r] > 850)
					snakexlength[r] = 25;
			}
			repaint();
		}

		if (left) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakeylength[r + 1] = snakeylength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakexlength[r] -= 25;
				} else {
					snakexlength[r] = snakexlength[r - 1];
				}
				if (snakexlength[r] < 25)
					snakexlength[r] = 850;
			}
			repaint();
		}

		if (down) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakexlength[r + 1] = snakexlength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeylength[r] += 25;
				} else {
					snakeylength[r] = snakeylength[r - 1];
				}
				if (snakeylength[r] > 625)
					snakeylength[r] = 75;
			}
			repaint();
		}
		if (up) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakexlength[r + 1] = snakexlength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeylength[r] -= 25;
				} else {
					snakeylength[r] = snakeylength[r - 1];
				}
				if (snakeylength[r] < 75)
					snakeylength[r] = 625;
			}
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (left == false && right == false && up == false && down == false) {
				timer.restart();
				moves = 0;
				lengthofsnake = 3;
				level = 1;
				score = 0;
				right = true;
				repaint();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!(moves != 0 && left == false && right == false && up == false && down == false)) {

				moves++;
				right = true;
				if (!left) {
					right = true;
				} else {
					left = true;
					right = false;
				}
				up = false;
				down = false;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!(moves != 0 && left == false && right == false && up == false && down == false)) {
				moves++;
				left = true;
				if (!right) {
					left = true;
				} else {
					right = true;
					left = false;
				}
				up = false;
				down = false;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!(moves != 0 && left == false && right == false && up == false && down == false)) {
				moves++;
				up = true;
				if (!down) {
					up = true;
				} else {
					down = true;
					up = false;
				}
				right = false;
				left = false;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (!(moves != 0 && left == false && right == false && up == false && down == false)) {
				moves++;
				down = true;
				if (!up) {
					down = true;
				} else {
					up = true;
					down = false;
				}
				right = false;
				left = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}