package ByttleShipPackage;

import java.util.Random;
import java.util.Scanner;

public class ShipPlacement {
	
	//allows for the user and the enemy to place their ships
	public static void placeShips(String map[][], int shipNum[], int mapSize, Random random, String playerMap[][], String enemyMap[][]) {
        Scanner input = new Scanner(System.in);

        for (int k = 4; k >= 0; k--) {
            for (int n = 0; n < shipNum[k]; n++) {
                int shipSize = k + 1;

                if (random == null) {
                	FillAndPrintMaps.printGame(playerMap, enemyMap);
                    System.out.println("\nEnter two coordinates (e.g., A1 B2) to place your " + shipSize + "x1 ship: ");
                    String coordinate1 = input.next();
                    String coordinate2 = input.next();

                    // Check if the coordinates match the ship size
                    while (!isMatchingShipSize(coordinate1, coordinate2, shipSize) || !drawLine(map, coordinate1, coordinate2)) {
                        System.out.println("Invalid coordinates. Please enter new coordinates that match the ship size.");
                        coordinate1 = input.next();
                        coordinate2 = input.next();
                    }
                 
                }
                else {
                    // AI's turn
                    String coordinate1, coordinate2;

                    do {
                        int row = random.nextInt(mapSize);
                        int col = random.nextInt(mapSize);
                        int orientation = random.nextInt(2); // 0 for horizontal, 1 for vertical

                        if (orientation == 0 && col + shipSize <= mapSize) {
                            coordinate1 = "" + (char) ('A' + col) + (row + 1);
                            coordinate2 = "" + (char) ('A' + col + shipSize - 1) + (row + 1);
                        } else if (orientation == 1 && row + shipSize <= mapSize) {
                            coordinate1 = "" + (char) ('A' + col) + (row + 1);
                            coordinate2 = "" + (char) ('A' + col) + (row + shipSize - 1 + 1);
                        } else {
                            coordinate1 = "Invalid";
                            coordinate2 = "Invalid";
                        }
                    } while (!isMatchingShipSize(coordinate1, coordinate2, shipSize) || !drawLine(map, coordinate1, coordinate2));
                }
            }
        }
    }
	 
	 //checks if the coordinates inputed match the size of the ship given
	 public static boolean isMatchingShipSize(String coord1, String coord2, int shipSize) {
	        if ("Invalid".equals(coord1) || "Invalid".equals(coord2)) {
	            return false;
	        } else {
	            int row1 = Integer.parseInt(coord1.substring(1)) - 1;
	            int col1 = coord1.charAt(0) - 'A';
	            int row2 = Integer.parseInt(coord2.substring(1)) - 1;
	            int col2 = coord2.charAt(0) - 'A';

	            int length = Math.abs(row1 - row2) + 1;
	            int width = Math.abs(col1 - col2) + 1;

	            return length == shipSize || width == shipSize;
	        }
	 }
	 
	 //draws a line between the two coordinates given by the user
	 public static boolean drawLine(String[][] map, String coord1, String coord2) {
		 	int row1 = Integer.parseInt(coord1.substring(1)) - 1;
	        int col1 = coord1.charAt(0) - 'A';
	        int row2 = Integer.parseInt(coord2.substring(1)) - 1;
	        int col2 = coord2.charAt(0) - 'A';

	        //check if coordinates are in bounds
	        if (row1 >= 0 && row1 < map.length && col1 >= 0 && col1 < map[0].length &&
	            row2 >= 0 && row2 < map.length && col2 >= 0 && col2 < map[0].length) {

	            if (isPathClear(map, row1, col1, row2, col2)) {
	                if (row1 == row2) { 
	                	//horizontal line
	                    int minCol = Math.min(col1, col2);
	                    int maxCol = Math.max(col1, col2);
	                    for (int col = minCol; col <= maxCol; col++) {
	                        map[row1][col] = " # ";
	                    }
	                } else if (col1 == col2) {
	                	//vertical line
	                    int minRow = Math.min(row1, row2);
	                    int maxRow = Math.max(row1, row2);
	                    for (int row = minRow; row <= maxRow; row++) {
	                        map[row][col1] = " # ";
	                    }
	                }
	                return true;
	            } else {
	                return false;
	            }
	        } else {
	            return false;
	        }
	  }
	 
	 //checks to see if there is  already a ship placed in between the two given coordinates
	 public static boolean isPathClear(String[][] map, int row1, int col1, int row2, int col2) {
	        if (row1 == row2) {
	            int minCol = Math.min(col1, col2);
	            int maxCol = Math.max(col1, col2);
	            for (int col = minCol; col <= maxCol; col++) {
	                if (map[row1][col].equals(" # ")) {
	                    return false;
	                }
	            }
	        } else if (col1 == col2) {
	            int minRow = Math.min(row1, row2);
	            int maxRow = Math.max(row1, row2);
	            for (int row = minRow; row <= maxRow; row++) {
	                if (map[row][col1].equals(" # ")) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	 
	 //calculates the number of ships the user will get based mapSize
	 public static void calcShipNum(int shipTileNum, int shipNum[]) {
	        while (shipTileNum / 17 > 0) {
	            shipTileNum -= 5;
	            shipNum[4]++;
	        }
	        while (shipTileNum / 3 >= 4) {
	            shipTileNum -= 4;
	            shipNum[3]++;
	        }
	        while (shipTileNum / 2 >= 2) {
	            shipTileNum -= 3;
	            shipNum[2]++;
	        }
	        while (shipTileNum / 2 >= 1) {
	            shipTileNum -= 2;
	            shipNum[1]++;
	        }
	        while (shipTileNum != 0) {
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

