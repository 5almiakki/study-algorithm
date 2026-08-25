import java.io.*;
import java.util.*;

public class CT_20260825_축구와_야구 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int statCount = Integer.parseInt(br.readLine());
			int[][] stats = new int[statCount][2];
			for (int i = 0; i < statCount; i++) {
				String[] input = br.readLine().split(" ");
				stats[i][0] = Integer.parseInt(input[0]);
				stats[i][1] = Integer.parseInt(input[1]);
			}
			int[][] dp = new int[12][10];
			for (int[] row : dp) {
				Arrays.fill(row, -1);
			}
			dp[0][0] = 0;
			for (int[] stat : stats) {
				for (int i = dp.length - 1; i >= 0; i--) {
					for (int j = dp[i].length - 1; j >= 0; j--) {
						if (dp[i][j] == -1) {
							continue;
						}
						if (i + 1 < dp.length) {
							dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + stat[0]);
						}
						if (j + 1 < dp[i].length) {
							dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + stat[1]);
						}
					}
				}
			}
			System.out.print(dp[11][9]);
		}

	}

}
