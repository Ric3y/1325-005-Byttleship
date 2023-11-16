import java.util.Scanner;
import java.util.Random;

public class placeShips
{
	//prompts user for inputs for placing ships
	public static void placeYShips(String ymap[][], int shipNum[], int mapSize, String emap[][]) 
	{
        	Scanner input = new Scanner(System.in);
        	for (int k = 4; k >= 0; k--) 
		{
            		for (int n = 0; n < shipNum[k]; n++) 
            		{
                		int shipSize = k + 1;
                		System.out.println("\nEnter two coordinates (e.g., A1 B2) to place your " + shipSize + "x1 ship: ");
                		String coordinate1 = input.next();
                		String coordinate2 = input.next();

                		// Check if the coordinates match the ship size
                		while (!isMatchingShipSize(coordinate1, coordinate2, shipSize) || !drawLine(ymap, coordinate1, coordinate2)) 
                		{
                    			System.out.println("Invalid coordinates. Please enter new coordinates that match the ship size.");
                    			coordinate1 = input.next();
                    			coordinate2 = input.next();
                		}

                		fillAndPrintMaps.printGame(mapSize, ymap, emap);
            		}
        	}
       		input.close();
    	}

	public static void placeEShips(String emap[][], int shipNum[], int mapSize) 
	{
        	Random random = new Random();
        	for (int k = 4; k >= 0; k--) 
        	{
            		for (int n = 0; n < shipNum[k]; n++) 
            		{
                		int shipSize = k + 1;
                		String coordinate1, coordinate2;

                		do 
                		{
                    			// Generate random coordinates for the computer's ships
                    			int row = random.nextInt(mapSize);
                    			int col = random.nextInt(mapSize);
                    			int orientation = random.nextInt(2); // 0 for horizontal, 1 for vertical

                    			if (orientation == 0 && col + shipSize <= mapSize) 
                    			{
                        			coordinate1 = "" + (char) ('A' + col) + (row + 1);
                        			coordinate2 = "" + (char) ('A' + col + shipSize - 1) + (row + 1);
                    			} 
                    			else if (orientation == 1 && row + shipSize <= mapSize) 
                    			{
                        			coordinate1 = "" + (char) ('A' + col) + (row + 1);
                        			coordinate2 = "" + (char) ('A' + col) + (row + shipSize - 1 + 1);
                    			} 
                    			else 
                    			{
                        			coordinate1 = ""; // Invalid coordinates, will try again in the next iteration
                        			coordinate2 = "";
                    			}
                		} 
                		while (!isMatchingShipSize(coordinate1, coordinate2, shipSize) || !drawLine(emap, coordinate1, coordinate2));

                		// Do not display computer's ships on the map
            		}
        	}
    	}
	
	//checks if the ship coords match the ships
    	public static boolean isMatchingShipSize(String coord1, String coord2, int shipSize) 
    	{
        	int row1 = Integer.parseInt(coord1.substring(1));
        	int col1 = coord1.charAt(0) - 'A' + 1;
        	int row2 = Integer.parseInt(coord2.substring(1));
        	int col2 = coord2.charAt(0) - 'A' + 1;

        	int length = Math.abs(row1 - row2) + 1;
        	int width = Math.abs(col1 - col2) + 1;

        	return length == shipSize || width == shipSize;
    	}

	//checks if the coords given are in bounds and draws line. if not in bound it will prompt the user to enter new coords.
    	public static boolean drawLine(String[][] map, String coord1, String coord2)
    	{
        	// Extract row and column indices from coordinates
        	int row1 = Integer.parseInt(coord1.substring(1)) - 1;
        	int col1 = coord1.charAt(0) - 'A';
        	int row2 = Integer.parseInt(coord2.substring(1)) - 1;
        	int col2 = coord2.charAt(0) - 'A';

        	// Check if the coordinates are within bounds
        	if (row1 >= 0 && row1 < map.length && col1 >= 0 && col1 < map[0].length &&
            	row2 >= 0 && row2 < map.length && col2 >= 0 && col2 < map[0].length) 
        	{

            		// Check if the path is clear (no existing lines or ships)
            		if (isPathClear(map, row1, col1, row2, col2)) 
            		{
                		// Draw a line by updating the map
                		if (row1 == row2) 
                		{
                    			// Horizontal line
                    			int minCol = Math.min(col1, col2);
                    			int maxCol = Math.max(col1, col2);
                    			for (int col = minCol; col <= maxCol; col++) 
                    			{
                        			map[row1][col] = " + ";
                    			}
                		} 
                		else if (col1 == col2) 
                		{
                    			// Vertical line
                    			int minRow = Math.min(row1, row2);
                    			int maxRow = Math.max(row1, row2);
                    			for (int row = minRow; row <= maxRow; row++) 
                    			{
                        			map[row][col1] = " + ";
                    			}
                		} 
                		else 
                		{
                    			System.out.println("Invalid coordinates for drawing a line.");
                		}
                		return true; // Path is clear
            		} 
            		else 
            		{
                		return false; // Path is not clear
            		}
        	} 
        	else 
        	{
            		System.out.println("Coordinates are out of bounds.");
            		return false; // Coordinates are out of bounds
        	}
    	}

    	// checks if a ship is in the way
    	public static boolean isPathClear(String[][] map, int row1, int col1, int row2, int col2) 
    	{
        	if (row1 == row2) 
        	{
            		// Horizontal line
            		int minCol = Math.min(col1, col2);
            		int maxCol = Math.max(col1, col2);
            		for (int col = minCol; col <= maxCol; col++) 
            		{
                		if (map[row1][col].equals(" + "))
                		{
                    			return false; // Path is not clear
                		}
            		}
        	} 
        	else if (col1 == col2) 
        	{
            		// Vertical line
            		int minRow = Math.min(row1, row2);
            		int maxRow = Math.max(row1, row2);
            		for (int row = minRow; row <= maxRow; row++) 
            		{
                		if (map[row][col1].equals(" + ")) 
                		{
                    			return false; // Path is not clear
                		}
            		}
        	}
        	return true; // Path is clear
    	}
}
