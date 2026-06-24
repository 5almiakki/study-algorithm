import java.io.*;

public class CT_20260624_수들_중_최솟값_최대화하기 {

    public class Main {

        static int answer = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridSize][gridSize];
            for (int row = 0; row < gridSize; row++) {
                String[] inputCells = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    grid[row][col] = Integer.parseInt(inputCells[col]);
                }
            }
            backtrack(grid, 0, 0, new int[gridSize]);
            System.out.print(answer);
        }

        static void backtrack(int[][] grid, int row, int colVisitedMask, int[] chosenNums) {
            if (row == grid.length) {
                int minNum = chosenNums[0];
                for (int i = 1; i < chosenNums.length; i++) {
                    if (chosenNums[i] < minNum) {
                        minNum = chosenNums[i];
                    }
                }
                if (answer < minNum) {
                    answer = minNum;
                }
                return;
            }
            for (int col = 0; col < grid[row].length; col++) {
                int mask = 1 << col;
                if ((colVisitedMask & mask) != 0) {
                    continue;
                }
                chosenNums[row] = grid[row][col];
                backtrack(grid, row + 1, colVisitedMask | mask, chosenNums);
            }
        }

    }

}
