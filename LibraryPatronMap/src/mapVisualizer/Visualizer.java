package mapVisualizer;

import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;

public class Visualizer extends PApplet{
	
	// length of array times
	static final int LEN_OF_TIMES = 17;
	// length of array areas
	static final int LEN_OF_AREAS = 28;
	static final int WINDOW_WIDTH = 1700;
	static final int WINDOW_HEIGHT = 1100;
	static final String FILETYPE = ".png";
	
	PImage bg;
	static int[][] data;
	static int timeIndex = 0;
	static String time = "8:30 AM";
	static Area[] allAreas = new Area[LEN_OF_AREAS];
	static boolean hideTime = true;
	// local copies of vars to make things run faster?
	static int[] currentColor;
	static Rectangle currentRect;
	// setup textboxes and input/output
	static String openPath = "";
	static String savePath = "";
	static Textbox openFrom = new Textbox(1300, 100, 380, 40);
	static Textbox saveTo = new Textbox(1300, 850, 380, 40);
	static Textbox saveName = new Textbox(1300, 930, 380, 40);
	static Textbox[] allTextboxes = {openFrom, saveTo, saveName};
	static int selectedTextbox = -1;
	static Button saveSingle = new Button("Save this page", 1300, 1010, 160, 40);
	static Button saveAll = new Button("Save all pages", 1520, 1010, 160,40);
	static Button[] allButtons = {saveSingle, saveAll};
	
	public static void main(String[] args) {
		// sets up processing
		PApplet.main("mapVisualizer.Visualizer");
		// default open path
		loadData("C:\\");
	}

	@Override
	public void settings() {
		size(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	@Override
	public void setup() {
		bg = loadImage("C:\\Users\\Lani\\Desktop\\library_map_1700_1100.bmp");
	}
	
	@Override
	public void draw() {
		background(bg);
		textSize(20);
		stroke(0);
		// display buttons
		for (Button b : allButtons) {
			fill(200);
			strokeWeight(0);
			rect(b.getXCoordinate(), b.getYCoordinate(), b.getWidth(), b.getHeight());
			fill(0);
			text(b.getName(), b.getXCoordinate() + 10, b.getYCoordinate() + 26);
		}
		// display each text box, if selected also show outline
		for (Textbox t : allTextboxes) {
			fill(200);
			
			if (t.isSelected())
				strokeWeight(5);
			else
				strokeWeight(0);
			rect(t.getXCoordinate(), t.getYCoordinate(), t.getWidth(), t.getHeight());
			fill(0);
			text(t.getText(), t.getXCoordinate() + 10, t.getYCoordinate() + t.getHeight() - 10);
		}

		// displays time and textbox descriptors
		text("Open from: ", openFrom.getXCoordinate(), openFrom.getYCoordinate() - 10);
		text("Save to: ", saveTo.getXCoordinate(), saveTo.getYCoordinate() - 10);
		text("Save file as: ", saveName.getXCoordinate(), saveName.getYCoordinate() - 10);
		textSize(42);
		text(time, 510, 700);
		
		// draws all rectangles in all areas
		for(Area a : allAreas) {
			currentColor = a.getColor();
			fill(currentColor[0], currentColor[1], currentColor[2], currentColor[3]);
			noStroke();
			for(Rectangle r : a.getRectangles()) {
				rect(r.getXCoordinate(), r.getYCoordinate(), r.getWidth(), r.getHeight());
				r.rollover(mouseX, mouseY);
				if (r.isOver()) {
					fill(0);
					text(a.toString(), 510, 900);
					// after drawing text, changes fill color back before drawing other rectangles in same area
					fill(currentColor[0], currentColor[1], currentColor[2], currentColor[3]);
				}
			}
		} 
	}	
	
	// if the textbox is selected, edits that text, enter to perform action
	// otherwise, uses q and e to move through times and w to save the image
	@Override
	public void keyPressed() {
		if (selectedTextbox > -1) {
			if (key >= 'A' && key <= 'Z' || key >= 'a' && key <= 'z' || key >= '0' && key <= '9' ||
				key == ' ' || key == '\\' || key ==':' || key == '.' || key == '_') {
				allTextboxes[selectedTextbox].addChar(key);
			}
			else if (key == 8) {
				allTextboxes[selectedTextbox].backspace();
			}
			// when enter is pressed, either opens, edits filepath, or saves depending on textbox
			else if (key == 10) {
				if (selectedTextbox == 0) {
					loadData(openFrom.getText());
					openFrom.clearText();
				}
					
				if (selectedTextbox == 1) {
					savePath = saveTo.getText();
					saveTo.clearText();
				}
					
				if (selectedTextbox == 2) {
					saveToPath(saveName.getText());
					saveName.clearText();
				}
			}
		}
		else {
			boolean changed = false;
			// when q presses, goes back one hour (if not already at the beginning)
			if (key == 'q') {
				if (timeIndex != 0) {
					timeIndex--;
					time = GetDataFromCSV.getTimes()[timeIndex];
					changed = true;
				}
			}
			// when e pressed, goes forward one hour (if not already at the end)
			else if (key == 'e') {
				if (timeIndex != LEN_OF_TIMES) {
					timeIndex++;
					time = GetDataFromCSV.getTimes()[timeIndex];
					changed = true;
				}
			}
			// saves image 
			else if (key == 'w') {
				saveToPath("map");
			}
			if (changed) {
				// reads patron count of specified time into each area in allAreas
				for(int i=0 ; i<LEN_OF_AREAS; i++) {
					allAreas[i].setPatronCount(data[i][timeIndex]);
				}
			}
		}
	}
	
	// when clicked, checks if mouse position is over any textbox/button
	// selects that textbox (or no box) and deselects all other boxes
	// only one box can be selected at a time, tracked by selectedTextbox
	@Override
	public void mouseClicked() {
		int xpos = mouseX, ypos = mouseY;
		// clicking textboxes
		selectedTextbox = -1;
		if (xpos > openFrom.getXCoordinate() && xpos < openFrom.getXCoordinate() + openFrom.getWidth() &&
			ypos > openFrom.getYCoordinate() && ypos < openFrom.getYCoordinate() + openFrom.getHeight()) {
				openFrom.select();
				selectedTextbox = 0;
		}
		else openFrom.deselect();
		if (xpos > saveTo.getXCoordinate() && xpos < saveTo.getXCoordinate() + saveTo.getWidth() &&
			ypos > saveTo.getYCoordinate() && ypos < saveTo.getYCoordinate() + saveTo.getHeight()) {
				saveTo.select();
				selectedTextbox = 1;
		}
		else saveTo.deselect();
		if (xpos > saveName.getXCoordinate() && xpos < saveName.getXCoordinate() + saveName.getWidth() &&
			ypos > saveName.getYCoordinate() && ypos < saveName.getYCoordinate() + saveName.getHeight()) {
				saveName.select();
				selectedTextbox = 2;
		}
		else saveName.deselect();
		// clicking save buttons
		if (saveSingle.isOver(xpos, ypos)){
			saveToPath(saveName.getText());
			saveName.clearText();
		}
		else if (saveAll.isOver(xpos, ypos)) {
			saveAll("snapshots", saveName.getText());
			saveName.clearText();
		}
	}
	
	public void saveToPath(String name) {
		// draws white rectangle to hide textbox while saving
		fill(255);
		rect(1300, 50, 400, 120);
		rect(1280, 800, 450, 400);
		if (hideTime) 
			rect(490, 650, 300, 400);
		if (savePath.substring(savePath.length() - 1) != "\\")
			savePath += "\\";
		if (name == "")
			save(savePath + "map" + FILETYPE);
		else save(savePath + name + FILETYPE);
	}

	public void saveAll(String folder, String name) {
		// makes sure path ends with '/'
		if (savePath.substring(savePath.length() - 1) != "\\")
			savePath += "\\";
		// create directory
		savePath += folder;
		File file = new File(savePath);
		if (file.mkdir())
			System.out.println("Successfully created directory");
		else System.out.println("Failed to create directory");
		// saves one picture for each time in file
		for (int i=0; i<=LEN_OF_TIMES; i++) {
			timeIndex = i;
			time = GetDataFromCSV.getTimes()[timeIndex];
			for(int j=0 ; j<LEN_OF_AREAS; j++) {
				allAreas[j].setPatronCount(data[j][timeIndex]);
			}
			draw();
			saveToPath(name + "_" + time.substring(0, 1) + time.substring(2,4) + time.substring(5));
		}
	}
	
	public static void loadData(String filepath) {
		data = GetDataFromCSV.extractData(filepath);
		
		// sets up list of areas with name and patron count
		int i = 0;
		for(i=0; i<LEN_OF_AREAS; i++) {
			allAreas[i] = new Area(GetDataFromCSV.getAreas()[i], data[i][timeIndex]);
			// System.out.println(allAreas[i].toString());
		}
		
		// manually set up each rectangle
		// general format: allAreas[area #].appendToRectangles(new Rectangle(x, y, w, h));
		
		// 4 - Stacks
		allAreas[0].appendToRectangles(new Rectangle(1030, 735, 161, 223));
		// 4 - Study Area
		allAreas[1].appendToRectangles(new Rectangle(1191, 904, 43, 52));
		// 4 - Group Study
		allAreas[2].appendToRectangles(new Rectangle(1065, 710, 103, 25));
		allAreas[2].appendToRectangles(new Rectangle(1140, 956, 104, 71));
		
		// 3.5 - Periodicals
		allAreas[3].appendToRectangles(new Rectangle(1422, 438, 132, 84));
		// 3 - Stacks
		allAreas[4].appendToRectangles(new Rectangle(1395, 459, 26, 219));
		allAreas[4].appendToRectangles(new Rectangle(1421, 522, 135, 156));
		// 3 - Study Area
		allAreas[5].appendToRectangles(new Rectangle(1555, 419, 98, 156));
		// 3 - Group Study
		allAreas[6].appendToRectangles(new Rectangle(1283, 443, 80, 73));
		allAreas[6].appendToRectangles(new Rectangle(1570, 388, 68, 31));
		allAreas[6].appendToRectangles(new Rectangle(1575, 626, 59, 124));
		// 309
		allAreas[7].appendToRectangles(new Rectangle(1461, 678, 65, 71));
		// 314
		allAreas[8].appendToRectangles(new Rectangle(1305, 407, 56, 36));
		// 3F - same coordinates as 3.5, to be solved at a later time
		
		// 2 - Stacks
		allAreas[10].appendToRectangles(new Rectangle(1045, 280, 130, 151));
		// 2 - Study Areas
		allAreas[11].appendToRectangles(new Rectangle(955, 211, 90, 62));
		allAreas[11].appendToRectangles(new Rectangle(1175, 151, 94, 180));
		allAreas[11].appendToRectangles(new Rectangle(1175, 382, 62, 49));
		// HP Room
		allAreas[12].appendToRectangles(new Rectangle(926, 273, 90, 253));
		allAreas[12].appendToRectangles(new Rectangle(1016, 273, 30, 158));
		// Microform
		allAreas[13].appendToRectangles(new Rectangle(1045, 201, 129, 80));
		// 208
		allAreas[14].appendToRectangles(new Rectangle(1054, 430, 91, 70));
		
		// Circulation
		allAreas[15].appendToRectangles(new Rectangle(158, 772, 190, 180));
		// 1 - Computers
		allAreas[16].appendToRectangles(new Rectangle(348, 611, 95, 219));
		// Reference
		allAreas[17].appendToRectangles(new Rectangle(49, 763, 109, 187));
		// Lounge
		allAreas[18].appendToRectangles(new Rectangle(80, 687, 77, 76));
		// 108
		allAreas[19].appendToRectangles(new Rectangle(158, 710, 33, 31));
		// 109
		allAreas[20].appendToRectangles(new Rectangle(158, 686, 33, 24));
		// 110
		allAreas[21].appendToRectangles(new Rectangle(57, 552, 80, 135));
		
		// LL - Stacks
		allAreas[22].appendToRectangles(new Rectangle(512, 306, 31, 144));
		allAreas[22].appendToRectangles(new Rectangle(543, 372, 104, 76));
		allAreas[22].appendToRectangles(new Rectangle(512, 234, 121, 72));
		// LL - Reading Area
		allAreas[23].appendToRectangles(new Rectangle(646, 403, 39, 45));
		// Cyber Cafe
		allAreas[24].appendToRectangles(new Rectangle(544, 448, 150, 66));
		// STELAR
		allAreas[25].appendToRectangles(new Rectangle(544, 309, 102, 63));
		allAreas[25].appendToRectangles(new Rectangle(646, 185, 150, 170));
		allAreas[25].appendToRectangles(new Rectangle(633, 218, 13 ,92));
		
		// SL - Stacks
		allAreas[26].appendToRectangles(new Rectangle(56, 301, 121, 118));
		allAreas[26].appendToRectangles(new Rectangle(177, 158, 112, 178));
		// SL - Computers
		allAreas[27].appendToRectangles(new Rectangle(289, 158, 52, 179));
		
		System.out.println("End.");
	}
	
}
