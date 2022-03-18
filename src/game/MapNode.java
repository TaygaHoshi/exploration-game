package game;

public class MapNode {

	// attribs
	private int type = 2;
	/// 0 = very bad
	/// 1 = bad
	/// 2 = neutral
	/// 3 = good
	/// 4 = very good
	/// 5 = wall
	/// 6 = end

	private String effect = "none";
	private boolean isVisible = false;
	private boolean isTaken; //veya üstüne geldiği anda çağırıp tekrar hareket edene kadar çağırma?
	boolean isVisited = false;
	public int x;
	public int y;
	
	// constructor
	public MapNode(int type, String effect) {
		this.type = type;
		this.effect = effect;
		
	}
	
	public MapNode(int type) {
		this.type = type;
		
	}
	
	
	//functions
	public String drawMe() {
		
		String result = "█";
		
		if(isVisible) {
			
			switch(type) {
			case 0: result = "#"; break;
			case 1: result = "-"; break;
			case 2: result = " "; break;
			case 3: result = "+"; break;
			case 4: result = "♥"; break;
			case 5: result = "█"; break;
			case 6: result = "F"; break;
			default: result = "½"; break;
		
		
			}
			
		}
		
		
		return result;
	}
	
	// getset
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
}
