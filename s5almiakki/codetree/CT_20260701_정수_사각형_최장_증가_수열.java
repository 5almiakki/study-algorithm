import java.io.*;
import java.util.*;

public class CT_20260701_정수_사각형_최장_증가_수열 {

    public class Main {

        static final int[][] DELTAS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        static int gridSize;
        static int[][] grid;
        static int[][] hopCounts;
        static List<Cell> cells = new ArrayList<>();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            gridSize = Integer.parseInt(br.readLine());
            grid = new int[gridSize][gridSize];
            for (int row = 0; row < gridSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    grid[row][col] = Integer.parseInt(input[col]);
                    cells.add(new Cell(grid[row][col], row, col));
                }
            }
            cells.sort((cell1, cell2) -> Integer.compare(cell1.value, cell2.value));
            hopCounts = new int[gridSize][gridSize];
            int maxHopCount = 0;
            for (Cell cell : cells) {
                int hopCount = hopCounts[cell.row][cell.col];
                if (maxHopCount < hopCount) {
                    maxHopCount = hopCount;
                }
                int newHopCount = hopCount + 1;
                for (int[] delta : DELTAS) {
                    int adjRow = cell.row + delta[0];
                    int adjCol = cell.col + delta[1];
                    if (isOutOfBounds(adjRow, adjCol)) {
                        continue;
                    }
                    if (grid[adjRow][adjCol] <= grid[cell.row][cell.col]) {
                        continue;
                    }
                    if (hopCounts[adjRow][adjCol] >= newHopCount) {
                        continue;
                    }
                    hopCounts[adjRow][adjCol] = hopCounts[cell.row][cell.col] + 1;
                }
            }
            System.out.print(maxHopCount + 1);
        }

        static boolean isOutOfBounds(int row, int col) {
            return row < 0 || gridSize <= row
                    || col < 0 || gridSize <= col;
        }

        static class Cell {

            final int value;
            final int row;
            final int col;

            Cell(int value, int row, int col) {
                this.value = value;
                this.row = row;
                this.col = col;
            }

        }

    }

}
