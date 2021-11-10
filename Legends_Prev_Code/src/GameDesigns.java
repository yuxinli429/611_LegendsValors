import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.event.ListSelectionEvent;

//Used to create design patterns in the game
public class GameDesigns {
	
	public static void welcomeDesign(String content) {
		int width = 150;
        int height = 20;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 24));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(content, 10, 20);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "*");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
public static void tableWithLines(ArrayList<ArrayList<String>> objectList, boolean header) {
	/*
	 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
	 * make it left justified. Otherwise right justified.
	 */
	boolean leftJustifiedRows = false;
	String[][] table;
	if(header) {
		table = new String[objectList.size()][objectList.get(0).size()+1];
		table[0][0] = "S.No";
		for(int i=1, j=0; i<objectList.size();i++) {
			table[i][j] = String.valueOf(i); 
		}
		for(int i=0, j=0; i<objectList.size();i++) {
			table[i][j] = String.valueOf(i); 
		}
		
		for(int i=0; i<objectList.size();i++) {
			for(int j= 1; j<objectList.get(0).size()+1; j++) {
				table[i][j] = objectList.get(i).get(j-1);
			}
		}
	}
	else {
		table = new String[objectList.size()][objectList.get(0).size()];
		for(int i=0; i<objectList.size();i++) {
			for(int j= 0; j<objectList.get(0).size(); j++) {
				table[i][j] = objectList.get(i).get(j);
			}
		}
	}
	
 
	Map<Integer, Integer> columnLengths = new HashMap<>();
	Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
		if (columnLengths.get(i) == null) {
			columnLengths.put(i, 0);
		}
		if (columnLengths.get(i) < a[i].length()) {
			columnLengths.put(i, a[i].length());
		}
	}));
 
	/*
	 * Prepare format String
	 */

	final StringBuilder formatString = new StringBuilder("");
	String flag = leftJustifiedRows ? "-" : "";
	columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
	formatString.append("|\n");
 
	/*
	 * Prepare line for top, bottom & below header row.
	 */
	String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
		String templn = "+-";
		templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
				(a1, b1) -> a1 + b1);
		templn = templn + "-";
		return ln + templn;
	}, (a, b) -> a + b);
	line = line + "+\n";
 
	/*
	 * Print table
	 */
	int firstLine = 0;
	System.out.print(line);
	if(header) {
		Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), (Object[])a));
		System.out.print(line);
		firstLine =1;
	}
 
	Stream.iterate(firstLine, (i -> i < table.length), (i -> ++i)).forEach(a -> System.out.printf(formatString.toString(), (Object[])table[a]));
	System.out.print(line);
}

public static void displayMonsterDesign() {
	int i = 8;
	while(i != 0) {
		
		addSpace();
		addSpace();
		addSpace();
		System.out.println("     ^              ^      ");
		System.out.println("    / \\           / \\    ");
		System.out.println("   /   \\         /   \\   ");
		System.out.println("  *      *********      *  ");
		System.out.println(" *                       * ");
		System.out.println("*    ~              ~     *");
		System.out.println("*   ( )            ( )    *");
		System.out.println("*    *              *     *");
		System.out.println("*           <>            *");
		System.out.println("*                         *");
		System.out.println("*        __________       *");
		System.out.println(" *                       * ");
		System.out.println("  *                     *  ");
		System.out.println("   *                   *   ");
		System.out.println("    *******************    ");
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addSpace();
		addSpace();
		addSpace();
		
		System.out.println("     ^              ^      ");
		System.out.println("    / \\           / \\    ");
		System.out.println("   /   \\         /   \\   ");
		System.out.println("  *      *********      *  ");
		System.out.println(" *                       * ");
		System.out.println("*    ~              ~     *");
		System.out.println("*   ( )            ( )    *");
		System.out.println("*    *              *     *");
		System.out.println("*           <>            *");
		System.out.println("*                         *");
		System.out.println("*        __________       *");
		System.out.println(" *       *        *      * ");
		System.out.println("  *       ********      *  ");
		System.out.println("   *                   *   ");
		System.out.println("    *******************    ");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i--;
	}
	
}

	public static void addSpace() {
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");
		System.out.println("                           ");	
	}

}
