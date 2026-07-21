import java.io.*;
import java.util.*;

public class CT_20260721_배낭_채우기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int gemCount = Integer.parseInt(input[0]);
			int targetWeight = Integer.parseInt(input[1]);
			int[][] gems = new int[gemCount][2];
			for (int[] gem : gems) {
				input = br.readLine().split(" ");
				gem[0] = Integer.parseInt(input[0]);
				gem[1] = Integer.parseInt(input[1]);
			}
			int answer = 0;
			int[] dp = new int[targetWeight + 1];
			Arrays.fill(dp, -1);
			dp[0] = 0;
			for (int[] gem : gems) {
				for (int weight = targetWeight - gem[0]; weight >= 0; weight--) {
					if (dp[weight] == -1) {
						continue;
					}
					int newWeight = weight + gem[0];
					dp[newWeight] = Math.max(dp[newWeight], dp[weight] + gem[1]);
					answer = Math.max(answer, dp[newWeight]);
				}
			}
			System.out.print(answer);
		}

	}

}
