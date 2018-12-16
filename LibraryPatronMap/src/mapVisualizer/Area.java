package mapVisualizer;

import java.util.ArrayList;
import java.util.List;

public class Area {
	private String name = "";
	private int patronCount = 0;
	private int[] color = {0, 140, 0, 0};
	// each area contains a list of rectangles that are drawn on the map
	private List<Rectangle> allRectangles = new ArrayList<Rectangle>();
	
	public Area(String name, int patronCount) {
		this.name = name;
		this.patronCount = patronCount;
		// determine color
		if (patronCount == 0)
			this.setColor(50);
		else if (patronCount < 5)
			this.setColor(80);
		else if (patronCount < 10)
			this.setColor(110);
		else if (patronCount < 15)
			this.setColor(190);
		else
			this.setColor(220);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPatronCount(int p) {
		patronCount = p;
	}
	
	public int getPatronCount() {
		return patronCount;
	}
	
	public void setColor(int[] c) {
		color = c;
	}
	
	public void setColor(int o) {
		color[3] = o;
	}
	
	public void setColor(int r, int g, int b) {
		color[0] = r;
		color[1] = g;
		color[2] = b;
	}
	
	public int[] getColor() {
		return color;
	}
	
	public void appendToRectangles(Rectangle r) {
		allRectangles.add(r);
	}
	
	public List<Rectangle> getRectangles(){
		return allRectangles;
	}
	
	public String toString() {
		return this.getName() + ": " + this.getPatronCount();
	}
	
}
