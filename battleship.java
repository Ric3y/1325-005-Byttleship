public class battleshipVSai 
{
	public static void main(String[] args)
	{
		int mapSize;
		int shipTileNum;
		int shipNum[] =new int[]{0,0,0,0,0};
	
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
		fillAndPrintMaps.fillMap(mapSize, ymap);
		fillAndPrintMaps.fillMap(mapSize, emap);
		fillAndPrintMaps.printGame(mapSize, ymap, emap);
		placeShips.placeYShips(ymap, shipNum, mapSize, emap);
		input.close();
		
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
}
