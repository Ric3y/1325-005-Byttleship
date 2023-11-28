package ByttleShipPackage;

public class FillAndPrintMaps {
	
	//fills a String array (maps) with " * "
	public static void fillMap(String map[][]) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = " * ";
            }
        }
    }
	
	//prints a map
	public static void printMap(String map[][]) {
        System.out.print("    ");
        for (int k = 0; k < map.length; k++) {
            char columnLetters = (char) ('A' + k);
            System.out.print(columnLetters + "  ");
        }

        System.out.print("\n");

        for (int i = 0; i < map.length; i++) {
            if (i + 1 >= 10) {
                System.out.print((i + 1) + "|");
            } else {
                System.out.print((i + 1) + " |");
            }

            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }

            System.out.print("\n");
        }
    }
	
	//prints the current playerMap and enemyShownMap
	public static void printGame(String playerMap[][], String enemyMap[][]) {
        GameLoopMethods.slowPrint("\nEnemy Map: \n");
        printMap(enemyMap);
        GameLoopMethods.slowPrint("\nYour Map: \n");
        printMap(playerMap);
        System.out.println("\n=================================================================\n==============================================================");
    }
	
	//prints whether the user won or lost and displays the final maps
	public static void printResult(String playerMap[][], String enemyShownMap[][]) {
        GameLoopMethods.slowPrint("\nGAME OVER!");

        if (GameLoopMethods.allShipsSunk(playerMap)) {
            GameLoopMethods.slowPrint("You lost! Better luck next time.");
        } else {
            GameLoopMethods.slowPrint("Congratulations! You won!");
        }

        System.out.println("\nFINAL MAPS:");
        System.out.println("Enemy Map:");
        FillAndPrintMaps.printMap(enemyShownMap);
        System.out.println("\nYour Map:");
        FillAndPrintMaps.printMap(playerMap);
    }
}
