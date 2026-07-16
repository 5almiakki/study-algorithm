import java.io.*;

public class CT_20260716_알바로_부자_되기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int partTimeCount = Integer.parseInt(br.readLine());
			int[][] partTimes = new int[partTimeCount][3];
			for (int i = 0; i < partTimeCount; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 0; j < 3; j++) {
					partTimes[i][j] = Integer.parseInt(input[j]);
				}
			}
			int[] dp = new int[partTimeCount];
			int answer = 0;
			for (int current = 0; current < partTimeCount; current++) {
				dp[current] += partTimes[current][2];
				answer = Math.max(answer, dp[current]);
				for (int next = current + 1; next < partTimeCount; next++) {
					if (partTimes[next][0] <= partTimes[current][1]) {
						continue;
					}
					// System.out.println(current + " -> " + next);
					dp[next] = Math.max(dp[next], dp[current]);
				}
			}
			// System.out.println(Arrays.toString(dp));
			System.out.print(answer);
		}

	}

}
