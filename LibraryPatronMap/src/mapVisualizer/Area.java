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
		this.setColor();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPatronCount(int p) {
		patronCount = p;
		this.setColor();
	}
	
	public int getPatronCount() {
		return patronCount;
	}
		
	public void setColor() {
		// determine color based on patronCount
		if (patronCount == 0)
			color[3] = 20;
		else if (patronCount < 5)
			color[3] = 90;
		else if (patronCount < 10)
			color[3] = 130;
		else if (patronCount < 15)
			color[3] = 170;
		else
			color[3] = 210;
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
