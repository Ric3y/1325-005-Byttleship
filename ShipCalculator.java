package test;

public class ShipCalculator {
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