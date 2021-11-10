import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Parser class created to parse through the config files
public class FileToList {
	public ArrayList<ArrayList<String>> readFile(String fileName) {
		
		String actualFilePath = GameConstants.LNM_FILE_PATH+fileName;

	    List<String> lines = new ArrayList<String>();
	    File file = new File(actualFilePath);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
        String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					if(line.length() > 0) {
						//System.out.println(line);
						lines.add(line);
					}
				             //do lots of stuff to sort the data into lists etc
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		//lines.forEach(System.out::println);
	    String[][] objectArray = new String[lines.size()][(lines.get(0).split("/").length)];
	    objectArray[0] = lines.get(0).split("/");
	    for(int i=1; i< lines.size(); i++) {
		    objectArray[i] = lines.get(i).split("\\s+");
	    }
	    
	    ArrayList<ArrayList<String>> objectList = new ArrayList<ArrayList<String>>();
	    for(int i=0; i<objectArray.length;i++) {
	    	ArrayList<String> objectLine = new ArrayList<String>();
	    	for(int j=0; j<objectArray[0].length;j++) {
	    		objectLine.add(j, objectArray[i][j]);
	    	}
	    	objectList.add(i, objectLine);
	    }
	    //objectList.forEach(System.out::println);
	    
	    //System.out.println(Arrays.deepToString(objectArray));
	    return objectList;
	  }
	
	public int[] readFileDimensions(String fileName) {
		return null;
		
	}

}
