package game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Mainframe extends JFrame {

	/**
	 * attribs
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MapNode[][] gameboard = new MapNode[29][71];
	private Random rnd = new Random();
	private Mazes m = new Mazes();
	private Player p = new Player(0, 0, 100, 0);
	private int level = 1;


	
	/**
	 * Create the frame.
	 */
	public Mainframe() {
		
		generateMap();
		
		setTitle("ExplorationTest");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLevel = new JLabel("Level: " + level);
		lblLevel.setBounds(731, 253, 177, 14);
		contentPane.add(lblLevel);
		
		JTextPane txtpnGamescreen = new JTextPane();
		txtpnGamescreen.setEditable(false);
		txtpnGamescreen.setFont(new Font("Courier New", Font.PLAIN, 15));
		txtpnGamescreen.setBackground(Color.WHITE);
		txtpnGamescreen.setText("GameScreen");
		txtpnGamescreen.setBounds(10, 11, 711, 525);
		contentPane.add(txtpnGamescreen);
		
		JLabel lblPlayerhealth = new JLabel("Health: " + String.valueOf(p.getHealth()));
		lblPlayerhealth.setBounds(731, 203, 177, 14);
		contentPane.add(lblPlayerhealth);
		
		JLabel lblPlayerscore = new JLabel("Score: " + String.valueOf(p.getScore()));
		lblPlayerscore.setBounds(731, 228, 177, 14);
		contentPane.add(lblPlayerscore);

		
		JButton btnNewButton = new JButton("DOWN");
		btnNewButton.setBounds(772, 104, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	moveplayer(2,txtpnGamescreen, lblPlayerhealth, lblPlayerscore, lblLevel);
                
            }
        });
		
		JButton btnNewButton_1 = new JButton("UP");
		btnNewButton_1.setBounds(772, 37, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	moveplayer(1,txtpnGamescreen, lblPlayerhealth, lblPlayerscore, lblLevel);
                
            }
        });
		
		JButton btnNewButton_2 = new JButton("LEFT");
		btnNewButton_2.setBounds(731, 70, 89, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	moveplayer(4,txtpnGamescreen, lblPlayerhealth, lblPlayerscore, lblLevel);
                
            }
        });
		
		JButton btnNewButton_3 = new JButton("RIGHT");
		btnNewButton_3.setBounds(819, 70, 89, 23);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	moveplayer(3,txtpnGamescreen, lblPlayerhealth, lblPlayerscore, lblLevel);
                
            }
        });
		
		drawMap(txtpnGamescreen);
		
		
	}
	
	
	public void generateMap() {
		
		MapNode[][] maze = new MapNode[gameboard.length - 2][gameboard[0].length - 2];
		m.generateByDFS(maze);

		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[0].length; j++) {
				if(i == 0 || j == 0 || i == gameboard.length - 1 || j == gameboard[0].length - 1) {
					gameboard[i][j] = new MapNode(5);
					gameboard[i][j].x = i;
					gameboard[i][j].y = j;
					gameboard[i][j].setVisible(true);
				}
				else {
					gameboard[i][j] = maze[i-1][j-1];
				}
				
			}
		}
		
		p.setX(1); p.setY(1);
		

		// populateMap
		populateMap();

		
		
	}
	

	private void moveplayer(int direction, JTextPane txt, JLabel health, JLabel score, JLabel levelindicator) {
		
		boolean didmove = false;
		// 1 = up, 2 = down, 3 = right, 4 = left
		
		if(direction == 3 && !(gameboard[p.getX()][p.getY() + 1].getType() == 5)) {
			p.setY(p.getY() + 1);
			didmove = true;
		}
		else if(direction == 4 && !(gameboard[p.getX()][p.getY() - 1].getType() == 5)) {
			p.setY(p.getY() - 1);
			didmove = true;
		}
		else if(direction == 2 && !(gameboard[p.getX() + 1][p.getY()].getType() == 5)) {
			p.setX(p.getX() + 1);
			didmove = true;
		}
		else if(direction == 1 && !(gameboard[p.getX() - 1][p.getY()].getType() == 5)) {
			p.setX(p.getX() - 1);
			didmove = true;
		}
		
		if(didmove) {
			doEffect(gameboard[p.getX()][p.getY()], levelindicator);
			p.setScore(p.getScore() + 1);
		}
		
		drawMap(txt);
		health.setText("Health: " + p.getHealth());
		score.setText("Score: " + p.getScore());
		
		
	}
	
	public void visibleOnly() {
		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[0].length; j++) {
				gameboard[i][j].setVisible(false);
			}
		}
		
		gameboard[p.getX()][p.getY()].setVisible(true);
		gameboard[p.getX() - 1][p.getY()].setVisible(true);
		gameboard[p.getX()][p.getY() - 1].setVisible(true);
		gameboard[p.getX() + 1][p.getY()].setVisible(true);
		gameboard[p.getX()][p.getY() + 1].setVisible(true);
		gameboard[p.getX() + 1][p.getY() - 1].setVisible(true);
		gameboard[p.getX() - 1][p.getY()+ 1].setVisible(true);
		gameboard[p.getX() + 1][p.getY() + 1].setVisible(true);
		gameboard[p.getX() - 1][p.getY() - 1].setVisible(true);
		
	}

	
	public void populateMap() {
		
		// populate the map
		for (int i = 0; i < gameboard.length; i++)
			for (int j = 0; j < gameboard[0].length; j++)
				
				if(i != p.getX() && j != p.getY() && gameboard[i][j].getType() == 2) {
					int rnd = genrng(0, 100);
					
					if(rnd == 0) gameboard[i][j].setType(0);
					if(rnd == 1) gameboard[i][j].setType(4);
					if(1 < rnd && rnd < 10) gameboard[i][j].setType(1);
					if(10 <= rnd && rnd < 20) gameboard[i][j].setType(3);
				}
		
		// add flag

		while(true) {
			int x = genrng(1, gameboard.length-2);
			int y = genrng(1, gameboard[0].length-2);
			if(gameboard[x][y].getType() == 2) {
				gameboard[x][y].setType(6);
				break;
			}
		}

		
	}
	
	public int genrng(int start, int end) {
		return rnd.nextInt((end - start) + 1) + start;
		
	}
	
	public void drawMap(JTextPane text) {
		
		//visibleOnly();
		
		String resultString = "";
		
		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[0].length; j++) {
				if(!(p.getX() == i && p.getY() == j))
					resultString += gameboard[i][j].drawMe();
				else {
					resultString += "P";
				}
				
				
			}
			resultString += "\n";
		}
		
		text.setText(resultString);
	}
	
	public void doEffect(MapNode node, JLabel levelindicator) {
		if(node.isTaken()) {
			return;
		}
		else {
			boolean didEffect = false;
			if(node.getType() == 0) {
				// # -> randomly teleport to an empty area
				int x;
				int y;
				while(true) {
					x = genrng(1, gameboard.length-2);
					y = genrng(1, gameboard[0].length-2);
					if(gameboard[x][y].getType() == 2) {
						break;
					}
				}
				
				p.setX(x); p.setY(y);
				didEffect = true;
			}
			else if(node.getType() == 1) {
				// - -> decrease health by 5
				p.setHealth(p.getHealth()-5);
				didEffect = true;
			}
			else if(node.getType() == 3 && p.getHealth() <= 95) {
				// + -> increase health by 5
				p.setHealth(p.getHealth()+5);
				didEffect = true;
			}
			else if(node.getType() == 3 && p.getHealth() > 95 && p.getHealth() < 100) {
				// + -> increase health by 5
				p.setHealth(100);
				didEffect = true;
			}
			else if(node.getType() == 4) {
				// heart -> fullhp
				p.setHealth(100);
				didEffect = true;
			}
			else if(node.getType() == 6) {
				// flag -> level up
				levelUp(levelindicator);
			}
			if(didEffect) {
				node.setType(2);
				node.setTaken(true);
			}
		}
		
		
	}
	
	public void levelUp(JLabel levelindicator) {
		//
		p.setScore(p.getScore() + 120);
		level++;
		levelindicator.setText("Level: " + level);
		generateMap();
	}
}
