package mapVisualizer;

public class Textbox {
	
	private String currentText = "";
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private boolean selected = false;
	
	public Textbox(int x, int y, int width, int height) {
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
	
	public void toggleSelected() {
		selected = !selected;
	}
	
	public void select() {
		selected = true;
	}
	
	public void deselect() {
		selected = false;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setText(String text) {
		currentText = text;
	}
	
	public String getText() {
		return currentText;
	}
	
	public void backspace() {
		if (currentText.length() > 0)
			currentText = currentText.substring(0, currentText.length() - 1);
	}
	
	public void clearText() {
		currentText = "";
	}
	
	public void addChar(char c) {
		currentText += c;
	}
	
}
