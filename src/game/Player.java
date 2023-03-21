package game;

public class Player {

	private int x;
	private int y;
	private int health;
	private int score;
	public Player(int x, int y, int health, int score) {
		this.x = x;
		this.y = y;
		this.health = health;
		this.score = score;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
