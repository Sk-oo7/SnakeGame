package Game;

import java.awt.Color;
import javax.swing.JFrame;

public class Board {

	public static void main(String[] args) {

		JFrame obj = new JFrame();
		Game game = new Game();

		obj.setTitle("Snake Game");
		obj.setSize(1194, 700);
		obj.setLocationRelativeTo(null);
		// obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
	}
}
