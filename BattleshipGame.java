package projectTest;

import java.util.Random;
import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set up the game
        System.out.print("WELCOME TO BATTLESHIP\nENTER MAP SIZE: ");
        int mapSize = scanner.nextInt();

        String playerMap[][] = new String[mapSize][mapSize];
        String enemyShownMap[][] = new String[mapSize][mapSize];
        String enemyMap[][] = new String[mapSize][mapSize];

        FillAndPrintMaps.fillMap(playerMap);
        FillAndPrintMaps.fillMap(enemyShownMap);
        FillAndPrintMaps.fillMap(enemyMap);

        int shipTileNum = (int) Math.round((mapSize * mapSize) * 0.17);
        int shipNum[] = new int[5];
        ShipPlacement.calcShipNum(shipTileNum, shipNum);

        // Place ships for the player
        ShipPlacement.placeShips(playerMap, shipNum, mapSize, null, playerMap, enemyMap);

        // Place ships for the enemy
        ShipPlacement.placeShips(enemyMap, shipNum, mapSize, new Random(), playerMap, enemyMap);
        
        // Print both maps to Shown the user for first attack
        FillAndPrintMaps.printGame(playerMap, enemyShownMap);

        // Game loop
        while (!GameLoopMethods.isGameOver(playerMap, enemyMap)) {
            System.out.println("\nYour turn:");
            GameLoopMethods.playerTurn(playerMap, enemyMap, enemyShownMap);

            // Check if the game is over
            if (GameLoopMethods.isGameOver(playerMap, enemyMap)) {
                break;
            }

            System.out.println("\nEnemy's turn:");
            GameLoopMethods.enemyTurn(playerMap, enemyMap);

            // Check if the game is over
            if (GameLoopMethods.isGameOver(playerMap, enemyMap)) {
                break;
            }

            // Print the current state of the game
            FillAndPrintMaps.printGame(playerMap, enemyShownMap);
        }

        // Print the final result
        FillAndPrintMaps.printResult(playerMap, enemyShownMap);

        scanner.close();
    }
}
