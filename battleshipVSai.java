import java.util.Scanner;

public class battleshipVSai {
	public static void main(String[] args) {
		int mapSize;
		int shipTileNum;
		int shipNum[] =new int[]{0,0,0,0,0};
	
		Scanner input = new Scanner(System.in);
		
		while (true) {
			try {
				System.out.print("WELCOME TO BATTLESHIP\nENTER MAP SIZE: ");
				mapSize = input.nextInt();
				input.nextLine();
				break; 
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid input. Please enter an integer.");
				input.nextLine();
			}
		}
	
		String ymap[][] = new String[mapSize][mapSize];
		String emap[][] = new String[mapSize][mapSize];
	
		// Calculate the number of ship tiles
		shipTileNum = (int) Math.round((mapSize * mapSize) * 0.17);
		System.out.println("Number of Ship Tiles: " + shipTileNum);
	
		// Calls method to calculate the number of ships the player gets
		calcShipNum(shipTileNum, shipNum);
	
		// Fills and prints the first set of maps
		fillAndPrintMaps.fillMap(mapSize, ymap);
		fillAndPrintMaps.fillMap(mapSize, emap);
		fillAndPrintMaps.printGame(mapSize, ymap, emap);
		placeShips.placeYShips(ymap, shipNum, mapSize, emap);
		placeShips.placeEShips(emap, shipNum, mapSize);

		// Only to see that placeEShips is working; will not be in final code
        fillAndPrintMaps.printMap(mapSize, emap);
		input.close();
		
	}
	
	//Function to calculates the number of ships players get
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
}
