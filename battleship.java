import java.util.Scanner;
import java.util.Random;
public class battleship
{
	public static void main(String[] args)
	{
		int mapSize;
		int shipTileNum;
		int shipNum[] =new int[]{0,0,0,0,0};
		int shipSize1 = 1, shipSize2 = 2, shipSize3 = 3, shipSize4 = 4, shipSize5 = 5;
		
	
		Scanner input = new Scanner(System.in);
		
		//initializes the maps to chosen map size
		System.out.print("WELCOME TO BATTLE SHIP\nENTER MAP SIZE: ");
		mapSize = input.nextInt();
		String ymap[][] = new String[mapSize][mapSize];
		String emap[][] = new String[mapSize][mapSize];
		
		//calculates number of ship tiles
		shipTileNum = (int) Math.round((mapSize * mapSize)*0.17);
		System.out.println("Number of Ship Tiles: " + shipTileNum);
		
		//calls method to calculate number of ships the player gets
		calcShipNum(shipTileNum, shipNum);
		
		//fills and prints the first set of maps
		fillMap(mapSize, ymap);
		fillMap(mapSize, emap);
		printGame(mapSize, ymap, emap);
		placeYShips(ymap,shipNum,mapSize, emap);
		input.close();
		
	}
	
	
	// prints both the filled maps
	public static void printGame(int mapSize, String ymap[][], String emap[][])
	{
		System.out.print("\nEnemy Map: ");
		printMap(mapSize, ymap);
		System.out.print("\nYour Map: ");
		printMap(mapSize, emap);
	}
	
	//fills arrays (maps) with " * "
	public static void fillMap(int mapSize, String map[][])
	{
		for(int i = 0; i < mapSize; i++)
		{
			for(int j = 0; j < mapSize; j++)
			{
				map[i][j] = " * ";
			}
		}
	}
	
	//function for printing the filled maps
	public static void printMap(int mapSize, String map[][])
	{
		System.out.print("\n    ");
		for(int k = 0; k < mapSize; k++)
		{
			char columnLetters = (char)('A' + k);
			System.out.print(columnLetters + "  ");
		}
				
		System.out.print("\n");
				
		for(int i = 0; i <= mapSize -1; i++)			
		{
			if(i +1 >= 10)
			{
				System.out.print((i+1) + "|");
				for(int j = 0; j <= mapSize -1; j++)
				{
					System.out.print(map[i][j]);
				}
			}
			else 
			{
				System.out.print((i + 1) + " |");
				for(int j = 0; j <= mapSize -1; j++)
				{
					System.out.print(map[i][j]);
				}
			}
			System.out.print("\n");
		}
	}
	
	//calculates the number of ships players get
	public static void calcShipNum(int shipTileNum, int shipNum[])
	{
		
		while(shipTileNum/17 > 0)
		{
		    shipTileNum -= 5;
		    shipNum[4]++;
		}
		while(shipTileNum/3 >= 4)
		{
		    shipTileNum -= 4;
		    shipNum[3]++;
		}
		while(shipTileNum/2 >= 2)
		{
		    shipTileNum -= 3;
		    shipNum[2]++;
		}
		while(shipTileNum/2 >= 1)
		{
		    shipTileNum -= 2;
		    shipNum[1]++;
		}
		while(shipTileNum != 0)
		{
		    shipTileNum -= 1;
		    shipNum[0]++;
		}
		
		System.out.println("\nYOU HAVE THESE SHIPS: " +
						   "\n5x1 ships: " + shipNum[4] +
		                   "\n4x1 ships: " + shipNum[3] +
		                   "\n3x1 ships: " + shipNum[2] +
		                   "\n2x1 ships: " + shipNum[1] +
		                   "\n1x1 ships: " + shipNum[0]);
	}
	
	public static void placeYShips(String ymap[][], int shipNum[], int mapSize, String emap[][] )
	{
		Scanner input = new Scanner(System.in);
		for(int n = 0; n < shipNum[4]; n++)
		{
			System.out.println("\nEnter two coordinates (e.g., A1 B2) to draw a line:");
	        String coordinate1 = input.next();
	        String coordinate2 = input.next();
	        drawLine(ymap, coordinate1, coordinate2);
	        printGame(mapSize, ymap, emap);
		}
		for(int n = 0; n < shipNum[3]; n++)
		{
			System.out.println("\nEnter two coordinates (e.g., A1 B2) to draw a line:");
	        String coordinate1 = input.next();
	        String coordinate2 = input.next();
	        drawLine(ymap, coordinate1, coordinate2);
	        printGame(mapSize, ymap, emap);
		}
		for(int n = 0; n < shipNum[2]; n++)
		{
			System.out.println("\nEnter two coordinates (e.g., A1 B2) to draw a line:");
	        String coordinate1 = input.next();
	        String coordinate2 = input.next();
	        drawLine(ymap, coordinate1, coordinate2);
	        printGame(mapSize, ymap, emap);
		}
		for(int n = 0; n < shipNum[1]; n++)
		{
			System.out.println("\nEnter two coordinates (e.g., A1 B2) to draw a line:");
	        String coordinate1 = input.next();
	        String coordinate2 = input.next();
	        drawLine(ymap, coordinate1, coordinate2);
	        printGame(mapSize, ymap, emap);
		}
		for(int n = 0; n < shipNum[0]; n++)
		{
			System.out.println("\nEnter two coordinates (e.g., A1 B2) to draw a line:");
	        String coordinate1 = input.next();
	        String coordinate2 = input.next();
	        drawLine(ymap, coordinate1, coordinate2);
	        printGame(mapSize, ymap, emap);
		}
		input.close();
	}
	
	// Draw a line on the map between two coordinates
	public static void drawLine(String[][] map, String coord1, String coord2) 
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
	        } 
	        else 
	        {
	            System.out.println("There's already a line or ship in the path.");
	        }
	    } 
	    else 
	    {
	        System.out.println("Coordinates are out of bounds.");
	    }
	}

    
    public static boolean isPathClear(String[][] map, int row1, int col1, int row2, int col2) {
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
