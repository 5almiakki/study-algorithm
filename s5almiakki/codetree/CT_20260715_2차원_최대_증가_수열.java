import java.io.*;

public class CT_20260715_2차원_최대_증가_수열 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int height = Integer.parseInt(input[0]);
			int width = Integer.parseInt(input[1]);
			int[][] grid = new int[height][width];
			for (int row = 0; row < height; row++) {
				input = br.readLine().split(" ");
				for (int col = 0; col < width; col++) {
					grid[row][col] = Integer.parseInt(input[col]);
				}
			}
			int[][] dp = new int[height][width];
			dp[0][0] = 1;
			int answer = 1;
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					answer = Math.max(answer, dp[row][col]);
					if (dp[row][col] == 0) {
						continue;
					}
					int newCell = dp[row][col] + 1;
					for (int newRow = row + 1; newRow < height; newRow++) {
						for (int newCol = col + 1; newCol < width; newCol++) {
							if (grid[newRow][newCol] <= grid[row][col]) {
								continue;
							}
							dp[newRow][newCol] = Math.max(dp[newRow][newCol], newCell);
						}
					}
				}
			}
			// for (int row = 0; row < height; row++) {
			//     for (int col = 0; col < width; col++) {
			//         System.out.print(dp[row][col] + "  ");
			//     }
			//     System.out.println();
			// }
			System.out.print(answer);
		}

	}

}
