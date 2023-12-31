package ByttleShipPackage;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameLoopMethods {
	
	//allows the user to attack a coordinate on the enemyMap and adjusts the enemeyMap and the enemyShownMap based on a hit or miss
	public static void playerTurn(String playerMap[][], String enemyMap[][], String enemyShownMap[][]) {
        Scanner scanner = new Scanner(System.in);

        GameLoopMethods.slowPrint("Enter coordinates to attack the enemy(e.g., A1): ");
        String target = scanner.next().toUpperCase();


        int row = Integer.parseInt(target.substring(1)) - 1;
        int col = target.charAt(0) - 'A';

        // Check if the coordinates are within bounds
        if (row >= 0 && row < playerMap.length && col >= 0 && col < playerMap[0].length) {
            if (enemyMap[row][col].equals(" # ")) {
            	GameLoopMethods.slowPrint("You HIT an enemy ship!");
            	enemyMap[row][col] = " X ";
            	enemyShownMap[row][col] = " X ";
            } else {
            	GameLoopMethods.slowPrint("You MISSED!");
            	enemyShownMap[row][col] = " O ";
            }
        } else {
            GameLoopMethods.slowPrint("Invalid coordinates. Try again.");
        }
    }
	
	//allows the enemy to attack the user and adjusts the playerMap according to a hit or miss
	public static void enemyTurn(String playerMap[][], String enemyMap[][]) {
        Random random = new Random();

        int row, col;
        do {
            row = random.nextInt(playerMap.length);
            col = random.nextInt(playerMap[0].length);
        } while (!enemyMap[row][col].equals(" * "));

        //if hit change playerMap coordinate to X else change to O
        if (playerMap[row][col].equals(" # ")) {
            GameLoopMethods.slowPrint("The enemy HIT your ship!");
            playerMap[row][col] = " X ";
        } else {
            GameLoopMethods.slowPrint("The enemy MISSED!");
            playerMap[row][col] = " O ";
        }
    }
	//checks if all the ships are sunk on either map
	public static boolean isGameOver(String playerMap[][], String enemyMap[][]) {
        return allShipsSunk(playerMap) || allShipsSunk(enemyMap);
    }
	
	//checks if all the ships are sunk on one map
	public static boolean allShipsSunk(String map[][]) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals(" # ")) {
                    return false;
                }
            }
        }
        return true;
    }

    //put a delay when print out text
    public static void slowPrint(String input) {
        for (int i = 0; i<input.length(); i++) {
            char c = input.charAt(i);
            System.out.print(c);
        try {
        TimeUnit.MILLISECONDS.sleep(30);
        }
        catch (Exception e) {
            }
        }
    }
}
