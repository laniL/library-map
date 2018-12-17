package mapVisualizer;

public class Button {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private String name = "";
	
	public Button(String name, int x, int y, int width, int height) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public String getName() {
		return name;
	}
	
	public void setXCoordinate(int x) {
		this.x = x;
	}

	public int getXCoordinate() {
		return x;
	}
	
	public void setYCoordinate(int y) {
		this.y = y;
	}
	
	public int getYCoordinate() {
		return y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isOver(int posx, int posy) {
		if (posx > this.getXCoordinate() && posx < this.getXCoordinate() + this.getWidth() && 
			posy > this.getYCoordinate() && posy < this.getYCoordinate() + this.getHeight())
				return true;
		return false;
	}
}
