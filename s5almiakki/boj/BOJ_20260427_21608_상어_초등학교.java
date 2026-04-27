import java.io.*;
//import java.util.*;

public class BOJ_20260427_21608_상어_초등학교 {

    public static class Main {

        static final int[] SATISFACTIONS = { 0, 1, 10, 100, 1000 };
        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        //static final String LN = System.lineSeparator();
        //static StringBuilder log = new StringBuilder();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridSize][gridSize];
            int cellCount = gridSize * gridSize;
            int[][] objects = new int[cellCount][4];
            for (int i = 0; i < cellCount; i++) {
                String[] line = br.readLine().split(" ");
                int subject = Integer.parseInt(line[0]);
                for (int j = 0; j < 4; j++) {
                    objects[subject - 1][j] = Integer.parseInt(line[j + 1]);
                }
                putStudent(grid, subject, objects[subject - 1]);
                //for (int[] row : grid) {
                //    for (int cell : row) {
                //        log.append(String.format("%3d", cell));
                //    }
                //    log.append(LN);
                //}
                //log.append(LN);
            }
            int answer = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    int subject = grid[row][col];
                    int adjObjectCount = countAdjObjects(grid, row, col, objects[subject - 1]);
                    answer += SATISFACTIONS[adjObjectCount];
                }
            }
            //System.out.println(log);
            System.out.print(answer);
        }

        static void putStudent(int[][] grid, int subject, int[] objects) {
            int maxAdjObjectCount = 0;
            int maxAdjEmptyCellCount = 0;
            int targetRow = Integer.MAX_VALUE;
            int targetCol = Integer.MAX_VALUE;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] != 0) {
                        continue;
                    }
                    // [0]: adjObjectCount, [1]: adjEmptyCellCount
                    int[] adjCells = searchAdjCells(grid, row, col, objects);
                    //log.append(targetRow).append(",").append(targetCol)
                    //        .append(", obj: ").append(maxAdjObjectCount).append(", empty: ").append(maxAdjEmptyCellCount)
                    //        .append(" -- record").append(LN);
                    //log.append(row).append(",").append(col)
                    //        .append(", obj: ").append(adjCells[0]).append(", empty: ").append(adjCells[1])
                    //        .append(" -- current").append(LN).append(LN);
                    if (maxAdjObjectCount < adjCells[0]) {
                        maxAdjObjectCount = adjCells[0];
                        maxAdjEmptyCellCount = adjCells[1];
                        targetRow = row;
                        targetCol = col;
                        continue;
                    }
                    if (adjCells[0] < maxAdjObjectCount) {
                        continue;
                    }
                    if (maxAdjEmptyCellCount < adjCells[1]) {
                        maxAdjEmptyCellCount = adjCells[1];
                        targetRow = row;
                        targetCol = col;
                        continue;
                    }
                    if (adjCells[1] < maxAdjEmptyCellCount) {
                        continue;
                    }
                    if (row < targetRow) {
                        targetRow = row;
                        targetCol = col;
                        //log.append("checkpoint").append(LN);
                        continue;
                    }
                    if (targetRow < row) {
                        continue;
                    }
                    if (col < targetCol) {
                        targetCol = col;
                    }
                }
            }
            grid[targetRow][targetCol] = subject;
        }

        static int[] searchAdjCells(int[][] grid, int row, int col, int[] objects) {
            int[] result = new int[2];
            for (int[] delta : DELTAS) {
                int newRow = row + delta[0];
                int newCol = col + delta[1];
                if (isOutOfBounds(grid, newRow, newCol)) {
                    continue;
                }
                if (grid[newRow][newCol] == 0) {
                    result[1]++;
                    continue;
                }
                if (contains(objects, grid[newRow][newCol])) {
                    result[0]++;
                }
            }
            return result;
        }

        static int countAdjObjects(int[][] grid, int row, int col, int[] objects) {
            int result = 0;
            for (int[] delta : DELTAS) {
                int newRow = row + delta[0];
                int newCol = col + delta[1];
                if (isOutOfBounds(grid, newRow, newCol)) {
                    continue;
                }
                if (contains(objects, grid[newRow][newCol])) {
                    result++;
                }
            }
            return result;
        }

        static boolean contains(int[] objects, int target) {
            for (int object : objects) {
                if (object == target) {
                    return true;
                }
            }
            return false;
        }

        static boolean isOutOfBounds(int[][] grid, int row, int col) {
            return row < 0 || grid.length <= row
                    || col < 0 || grid[row].length <= col;
        }

    }

}
