package mapVisualizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetDataFromCSV {
	// the first row, times (excluding the first cell)
    static String[] times = new String[17];
    // the first column, areas of the library (excluding the first cell)
    static String[] areas = new String[28];
	
	public static int[][] extractData(String path) {
		int[][] data = new int[28][18];
		String csvFile = path;
		BufferedReader br = null;
	    String line = "";
	    int count = 0;
	    

        try {

            br = new BufferedReader(new FileReader(csvFile));
            times = br.readLine().substring(18).split(",");
            // count is for getting the second item of each row (the area name)
            while (count < 28 && (line = br.readLine()) != null) {

                String[] singleLine = line.split(",");
                areas[count] = singleLine[1];
                for(int i = 2; i < singleLine.length - 3; i++)
                {
                	data[count][i - 2] = Integer.parseInt(singleLine[i]);
                }
                count++;
            }
            
            // prints for testing purposes
            /*
            System.out.println(Arrays.toString(times));
            for(int i = 0; i < data.length; i++)
            {
            	System.out.print(areas[i]);
            	System.out.println(Arrays.toString(data[i]));
            }
			*/
            
            return data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        	System.out.println("FileNotFoundException\nInvalid Path");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return data;

	}

	public static String[] getTimes() {
		return times;
	}
	
	public static String[] getAreas() {
		return areas;
	}
}
