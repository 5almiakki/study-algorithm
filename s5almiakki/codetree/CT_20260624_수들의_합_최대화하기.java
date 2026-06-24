import java.io.*;

public class CT_20260624_수들의_합_최대화하기 {

    public class Main {

        static int answer = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int gridSize = Integer.parseInt(br.readLine());
            int[][] grid = new int[gridSize][gridSize];
            for (int row = 0; row < gridSize; row++) {
                String[] input = br.readLine().split(" ");
                for (int col = 0; col < gridSize; col++) {
                    grid[row][col] = Integer.parseInt(input[col]);
                }
            }
            backtrack(grid, 0, 0, 0);
            System.out.print(answer);
        }

        static void backtrack(int[][] grid, int row, int colVisitedMask, int sum) {
            if (row == grid.length) {
                if (answer < sum) {
                    answer = sum;
                }
                return;
            }
            for (int col = 0; col < grid[row].length; col++) {
                int mask = 1 << col;
                if ((colVisitedMask & mask) != 0) {
                    continue;
                }
                backtrack(grid, row + 1, colVisitedMask | mask, sum + grid[row][col]);
            }
        }

    }

}
