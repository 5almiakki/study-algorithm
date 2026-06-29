import java.io.*;

public class CT_20260629_정수_사각형_최대_합 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            int[][] grid = new int[n][n];
            for (int row = 0; row < n; row++) {
                String[] splittedInput = br.readLine().split(" ");
                for (int col = 0; col < n; col++) {
                    grid[row][col] = Integer.parseInt(splittedInput[col]);
                }
            }
            for (int col = 1; col < n; col++) {
                grid[0][col] += grid[0][col - 1];
            }
            for (int row = 1; row < n; row++) {
                grid[row][0] += grid[row - 1][0];
                for (int col = 1; col < n; col++) {
                    grid[row][col] += Math.max(grid[row - 1][col], grid[row][col - 1]);
                }
            }
            System.out.print(grid[n - 1][n - 1]);
        }

    }

}
