package mapVisualizer;

import processing.core.PApplet;
import processing.core.PImage;

public class Visualizer extends PApplet{

	// length of array times
	static final int LEN_OF_TIMES = 17;
	// length of array areas
	static final int LEN_OF_AREAS = 28;
	
	// opacity of rectangles, value between 0 and 255
	//static final int OPACITY = 60;
	
	PImage bg;
	static int[][] data;
	static String time = "4:30 PM";
	static int timeIndex = 1;
	static Area[] allAreas = new Area[LEN_OF_AREAS];
	
	static int[] currentColor;
	static Rectangle currentRect;
	
	
	
	public static void main(String[] args) {
		
		PApplet.main("mapVisualizer.Visualizer");
		data = GetDataFromCSV.extractData("C:\\Users\\Lani\\Desktop\\SampleData.csv");
		
		// finds index of time
		int i = 0;
		/*
		while(i < LEN_OF_TIMES && timeIndex == -1) {
			if (time == GetDataFromCSV.getTimes()[i]) {
				timeIndex = i;
			}
		}
		System.out.println("one");
		// for testing purposes
		if(timeIndex == -1)
			System.out.println("Invalid time");
			*/
		// sets up list of areas with name and patron count
		for(i=0; i<LEN_OF_AREAS; i++) {
			allAreas[i] = new Area(GetDataFromCSV.getAreas()[i], data[i][timeIndex]);
			System.out.println(allAreas[i].toString());
		}
		// manually set up each rectangle
		// general format: allAreas[area #].appendToRectangles(new Rectangle(x, y, w, h));
		
		// 4 - Stacks
		
		// 4 - Study Area
		
		// 4 - Group Study
		
		
		// 3.5 - Periodicals
		
		// 3 - Stacks
		
		// 3 - Study Area
		
		// 3 - Group Study
		
		// 309
		
		// 314
		
		// 3F
		
		
		// 2 - Stacks
		
		// 2 - Study Areas
		
		// HP Room
		
		// Microform
		
		// 208
		
		
		// Circulation
		allAreas[15].appendToRectangles(new Rectangle(158, 772, 190, 180));
		// 1 - Computers
		allAreas[16].appendToRectangles(new Rectangle(348, 611, 95, 219));
		// Reference
		allAreas[17].appendToRectangles(new Rectangle(49, 763, 109, 187));
		// Lounge
		allAreas[18].appendToRectangles(new Rectangle(80, 687, 77, 71));
		// 108
		allAreas[19].appendToRectangles(new Rectangle(158, 710, 33, 31));
		// 109
		allAreas[20].appendToRectangles(new Rectangle(158, 686, 33, 23));
		// 110
		allAreas[21].appendToRectangles(new Rectangle(57, 557, 80, 120));
		
		// LL - Stacks
		allAreas[22].appendToRectangles(new Rectangle(512, 306, 31, 144));
		allAreas[22].appendToRectangles(new Rectangle(543, 372, 98, 76));
		allAreas[22].appendToRectangles(new Rectangle(512, 234, 118, 72));
		// LL - Reading Area
		allAreas[23].appendToRectangles(new Rectangle(641, 403, 44, 45));
		// Cyber Cafe
		allAreas[24].appendToRectangles(new Rectangle(544, 448, 150, 66));
		// STELAR
		allAreas[25].appendToRectangles(new Rectangle(544, 309, 90, 63));
		allAreas[25].appendToRectangles(new Rectangle(634, 185, 162, 168));
		
		// SL - Stacks
		allAreas[26].appendToRectangles(new Rectangle(56, 301, 121, 118));
		allAreas[26].appendToRectangles(new Rectangle(177, 158, 112, 175));
		// SL - Computers
		allAreas[27].appendToRectangles(new Rectangle(289, 158, 50, 175));
		
		
		System.out.println("End.");
	}

	public void settings() {
		size(1700, 1100);
	}
	
	public void setup() {
		bg = loadImage("C:\\Users\\Lani\\Desktop\\library_map_1700_1100.bmp");
	}
	
	public void draw() {
		background(bg);
		// for each area
		for(Area a : allAreas) {
			currentColor = a.getColor();
			fill(currentColor[0], currentColor[1], currentColor[2], currentColor[3]);
			noStroke();
			
			for(Rectangle r : a.getRectangles()) {
				rect(r.getXCoordinate(), r.getYCoordinate(), r.getWidth(), r.getHeight());
			}	
		} 
	}
	
	// on mouse hover, display patron count of area
	
}
