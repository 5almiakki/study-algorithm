import java.io.*;
import java.util.*;

public class CT_20260722_배낭_채우기_2 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int gemCount = Integer.parseInt(input[0]);
			int capacity = Integer.parseInt(input[1]);
			int[][] gems = new int[gemCount][2];
			for (int i = 0; i < gemCount; i++) {
				input = br.readLine().split(" ");
				gems[i][0] = Integer.parseInt(input[0]);
				gems[i][1] = Integer.parseInt(input[1]);
			}
			int answer = 0;
			int[] dp = new int[capacity + 1];
			Arrays.fill(dp, -1);
			dp[0] = 0;
			for (int weight = 0; weight <= capacity; weight++) {
				for (int[] gem : gems) {
					int newWeight = weight + gem[0];
					if (newWeight > capacity) {
						continue;
					}
					dp[newWeight] = Math.max(dp[newWeight], dp[weight] + gem[1]);
					answer = Math.max(answer, dp[newWeight]);
				}
			}
			System.out.print(answer);
		}

	}

}
