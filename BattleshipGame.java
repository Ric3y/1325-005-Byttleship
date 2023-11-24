package test;

import java.util.Random;
import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set up the game
        System.out.print("WELCOME TO BATTLESHIP\nENTER MAP SIZE: ");
        int mapSize = scanner.nextInt();

        String playerMap[][] = new String[mapSize][mapSize];
        String enemyMap[][] = new String[mapSize][mapSize];

        fillMap(playerMap);
        fillMap(enemyMap);

        int shipTileNum = (int) Math.round((mapSize * mapSize) * 0.17);
        int shipNum[] = new int[5];
        ShipCalculator.calcShipNum(shipTileNum, shipNum);

        // Place ships for the player
        placeShips(playerMap, shipNum, mapSize, null);

        // Place ships for the enemy
        placeShips(enemyMap, shipNum, mapSize, new Random());

        // Game loop
        while (!isGameOver(playerMap, enemyMap)) {
            System.out.println("\nYour turn:");
            playerTurn(playerMap, enemyMap);

            // Check if the game is over
            if (isGameOver(playerMap, enemyMap)) {
                break;
            }

            System.out.println("\nEnemy's turn:");
            enemyTurn(playerMap, enemyMap);

            // Check if the game is over
            if (isGameOver(playerMap, enemyMap)) {
                break;
            }

            // Print the current state of the game
            printGame(playerMap, enemyMap);
        }

        // Print the final result
        printResult(playerMap, enemyMap);

        scanner.close();
    }


    public static void fillMap(String map[][]) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = " * ";
            }
        }
    }

    public static void printGame(String playerMap[][], String enemyMap[][]) {
        System.out.print("\nEnemy Map: ");
        printMap(enemyMap);
        System.out.print("\nYour Map: ");
        printMap(playerMap);
    }

    public static void printMap(String map[][]) {
        System.out.print("\n    ");
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

    public static void placeShips(String map[][], int shipNum[], int mapSize, Random random) {
        Scanner input = new Scanner(System.in);

        for (int k = 4; k >= 0; k--) {
            for (int n = 0; n < shipNum[k]; n++) {
                int shipSize = k + 1;

                if (random == null) {
                    System.out.println("\nEnter two coordinates (e.g., A1 B2) to place your " + shipSize + "x1 ship: ");
                    String coordinate1 = input.next();
                    String coordinate2 = input.next();

                    // Check if the coordinates match the ship size
                    while (!isMatchingShipSize(coordinate1, coordinate2, shipSize) || !drawLine(map, coordinate1, coordinate2)) {
                        System.out.println("Invalid coordinates. Please enter new coordinates that match the ship size.");
                        coordinate1 = input.next();
                        coordinate2 = input.next();
                    }
                } else {
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

        input.close();
    }

    public static void playerTurn(String playerMap[][], String enemyMap[][]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter target coordinates (e.g., A1): ");
        String target = scanner.next();

        int row = Integer.parseInt(target.substring(1)) - 1;
        int col = target.charAt(0) - 'A';

        // Check if the coordinates are within bounds
        if (row >= 0 && row < playerMap.length && col >= 0 && col < playerMap[0].length) {
            if (enemyMap[row][col].equals(" # ")) {
                System.out.println("You hit an enemy ship!");
                playerMap[row][col] = " X ";
                enemyMap[row][col] = " X ";
            } else {
                System.out.println("You missed!");
                playerMap[row][col] = " O ";
            }
        } else {
            System.out.println("Invalid coordinates. Try again.");
        }
    }

    public static void enemyTurn(String playerMap[][], String enemyMap[][]) {
        Random random = new Random();

        int row, col;
        do {
            row = random.nextInt(playerMap.length);
            col = random.nextInt(playerMap[0].length);
        } while (!enemyMap[row][col].equals(" * "));

        if (playerMap[row][col].equals(" # ")) {
            System.out.println("The enemy hit your ship!");
            playerMap[row][col] = " X ";
            enemyMap[row][col] = " X ";
        } else {
            System.out.println("The enemy missed!");
            playerMap[row][col] = " O ";
        }
    }

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

    public static boolean drawLine(String[][] map, String coord1, String coord2) {
        int row1 = Integer.parseInt(coord1.substring(1)) - 1;
        int col1 = coord1.charAt(0) - 'A';
        int row2 = Integer.parseInt(coord2.substring(1)) - 1;
        int col2 = coord2.charAt(0) - 'A';

        if (row1 >= 0 && row1 < map.length && col1 >= 0 && col1 < map[0].length &&
            row2 >= 0 && row2 < map.length && col2 >= 0 && col2 < map[0].length) {

            if (isPathClear(map, row1, col1, row2, col2)) {
                if (row1 == row2) {
                    int minCol = Math.min(col1, col2);
                    int maxCol = Math.max(col1, col2);
                    for (int col = minCol; col <= maxCol; col++) {
                        map[row1][col] = " # ";
                    }
                } else if (col1 == col2) {
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

    public static boolean isGameOver(String playerMap[][], String enemyMap[][]) {
        return allShipsSunk(playerMap) || allShipsSunk(enemyMap);
    }

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

    public static void printResult(String playerMap[][], String enemyMap[][]) {
        System.out.println("\nGame Over!");

        if (allShipsSunk(playerMap)) {
            System.out.println("You lost! Better luck next time.");
        } else {
            System.out.println("Congratulations! You won!");
        }

        System.out.println("\nFinal Maps:");
        System.out.println("Your Map:");
        printMap(playerMap);
        System.out.println("\nEnemy Map:");
        printMap(enemyMap);
    }
}
