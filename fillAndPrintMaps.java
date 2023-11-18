public class fillAndPrintMaps {
    public static void printGame(int mapSize, String ymap[][], String emap[][]) {
        System.out.print("\nEnemy Map: ");
        printMap(mapSize, emap);
        System.out.print("\nYour Map: ");
        printMap(mapSize, ymap);
    }

    public static void fillMap(int mapSize, String map[][]) {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = " * ";
            }
        }
    }

    public static void printMap(int mapSize, String map[][]) {
        System.out.print("\n    ");
        for (int k = 0; k < mapSize; k++) {
            char columnLetters = (char)('A' + k);
            System.out.print(columnLetters + "  ");
        }

        System.out.print("\n");

        for (int i = 0; i <= mapSize - 1; i++) {
            if (i + 1 >= 10) {
                System.out.print((i + 1) + "|");
                for (int j = 0; j <= mapSize - 1; j++) {
                    System.out.print(map[i][j]);
                }
            } else {
                System.out.print((i + 1) + " |");
                for (int j = 0; j <= mapSize - 1; j++) {
                    System.out.print(map[i][j]);
                }
            }
            System.out.print("\n");
        }
    }
}