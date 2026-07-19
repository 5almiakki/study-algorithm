import java.io.*;
import java.util.*;

public class CT_20260719_정수_사각형_차이의_최소_2 {

	public class Main {

		public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int gridSize = Integer.parseInt(br.readLine());
			int[][] grid = new int[gridSize][gridSize];
			int minCell = Integer.MAX_VALUE;
			SortedSet<Integer> cells = new TreeSet<>();
			for (int row = 0; row < gridSize; row++) {
				String[] input = br.readLine().split(" ");
				for (int col = 0; col < gridSize; col++) {
					int cell = Integer.parseInt(input[col]);
					grid[row][col] = cell;
					minCell = Math.min(minCell, cell);
					cells.add(cell);
				}
			}

			int answer = Integer.MAX_VALUE;
			int[][] dp = new int[gridSize][gridSize];
			for (int lowerBound : cells) {
				// System.out.println("lowerBound: " + lowerBound);
				if (grid[0][0] < lowerBound) {
					break;
				}
				dp[0][0] = grid[0][0];
				for (int col = 1; col < gridSize; col++) {
					disableIfLower(grid, 0, col, lowerBound);
					dp[0][col] = Math.max(grid[0][col], dp[0][col - 1]);
				}
				for (int row = 1; row < gridSize; row++) {
					disableIfLower(grid, row, 0, lowerBound);
					dp[row][0] = Math.max(grid[row][0], dp[row - 1][0]);
					for (int col = 1; col < gridSize; col++) {
						disableIfLower(grid, row, col, lowerBound);
						dp[row][col] = Math.max(
								Math.min(dp[row - 1][col], dp[row][col - 1]),
								grid[row][col]);
					}
				}
				// for (int[] row : dp) {
				//     System.out.println(Arrays.toString(row));
				// }
				// System.out.println();

				answer = Math.min(answer, dp[gridSize - 1][gridSize - 1] - lowerBound);
			}
			System.out.print(answer);
		}

		static void disableIfLower(int[][] grid, int row, int col, int lowerBound) {
			if (grid[row][col] < lowerBound) {
				grid[row][col] = Integer.MAX_VALUE;
			}
		}

	}

}
