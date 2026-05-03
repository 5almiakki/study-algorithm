
public class PG_20260503_60059_자물쇠와_열쇠 {

    class Solution {

        public boolean solution(int[][] key, int[][] lock) {
            int[][][] keys = new int[4][][];
            keys[0] = key;
            for (int i = 1; i <= 3; i++) {
                keys[i] = makeRotatedKey(keys[i - 1]);
            }
            int emptyCellCount = 0;
            for (int[] row : lock) {
                for (int cell : row) {
                    emptyCellCount += 1 - cell;
                }
            }
            for (int rowBase = 1 - key.length; rowBase < lock.length; rowBase++) {
                for (int colBase = 1 - key.length; colBase < lock.length; colBase++) {
                    // System.out.println();
                    // System.out.println("base: " + rowBase + ", " + colBase);
                    for (int[][] k : keys) {
                        boolean conflicts = false;
                        int filledCellCount = 0;
                        // for (int[] row : k) {
                        //     for (int cell : row) {
                        //         System.out.print(cell);
                        //     }
                        //     System.out.println();
                        // }

                        for (int rowOffset = 0; rowOffset < key.length; rowOffset++) {
                            for (int colOffset = 0; colOffset < k[rowOffset].length; colOffset++) {
                                if (isOutOfBounds(lock, rowBase + rowOffset, colBase + colOffset)) {
                                    continue;
                                }
                                // System.out.println("offset: " + rowOffset + ", " + colOffset);
                                if (k[rowOffset][colOffset] == 1) {
                                    if (lock[rowBase + rowOffset][colBase + colOffset] == 1) {
                                        conflicts = true;
                                        break;
                                    }
                                    filledCellCount++;
                                }
                            }
                            if (conflicts) {
                                break;
                            }
                        }
                        // System.out.println("conflicts: " + conflicts + ", filledCellCount: " + filledCellCount);
                        if (!conflicts && filledCellCount == emptyCellCount) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        int[][] makeRotatedKey(int[][] key) {
            int[][] newKey = new int[key.length][key[0].length];
            for (int row = 0; row < key.length; row++) {
                for (int col = 0; col < key[row].length; col++) {
                    newKey[col][key.length - row - 1] = key[row][col];
                }
            }
            return newKey;
        }

        boolean isOutOfBounds(int[][] lock, int row, int col) {
            return row < 0 || lock.length <= row
                    || col < 0 || lock[row].length <= col;
        }

    }

}
