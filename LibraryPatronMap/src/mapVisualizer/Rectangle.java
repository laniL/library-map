package mapVisualizer;

public class Rectangle {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private boolean over = false;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	
	public void rollover(int posx, int posy) {
		if (posx > this.getXCoordinate() && posx < this.getXCoordinate() + this.getWidth() && 
			posy > this.getYCoordinate() && posy < this.getYCoordinate() + this.getHeight())
				over = true;
		else over = false;
	}
	
	public boolean isOver() {
		return over;
	}
	
	public String toString() {
		return "Coordinates: " + this.getXCoordinate() + ", " + this.getYCoordinate();
	}
	
}
