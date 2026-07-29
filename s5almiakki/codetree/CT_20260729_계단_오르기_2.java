import java.io.*;
import java.util.*;

public class CT_20260729_계단_오르기_2 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int floorCount = Integer.parseInt(br.readLine());
			int[] floors = new int[floorCount + 1];
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < floorCount; i++) {
				floors[i + 1] = Integer.parseInt(input[i]);
			}

			int[][] dp = new int[floorCount + 1][4];
			for (int[] row : dp) {
				Arrays.fill(row, -1);
			}
			dp[0][0] = 0;
			dp[1][1] = floors[1];
			for (int floor = 2; floor <= floorCount; floor++) {
				// singleJumpCount가 0인 경우
				// 무조건 2계단 올랐어야 함
				if (dp[floor - 2][0] != -1) {
					dp[floor][0] = dp[floor - 2][0] + floors[floor];
				}

				// singleJumpCount가 1 이상인 경우
				int bound = Math.min(floor, 3);
				for (int singleJumpCount = 1; singleJumpCount <= bound; singleJumpCount++) {
					if (dp[floor - 1][singleJumpCount - 1] == -1) {
						if (dp[floor - 2][singleJumpCount] != -1) {
							dp[floor][singleJumpCount] = dp[floor - 2][singleJumpCount] + floors[floor];
						}
						continue;
					} else if (dp[floor - 2][singleJumpCount] == -1) {
						dp[floor][singleJumpCount] = dp[floor - 1][singleJumpCount - 1] + floors[floor];
						continue;
					}
					dp[floor][singleJumpCount] = floors[floor] + Math.max(
							dp[floor - 1][singleJumpCount - 1],
							dp[floor - 2][singleJumpCount]);
				}
			}
			int answer = Math.max(
					Math.max(dp[floorCount][0], dp[floorCount][1]),
					Math.max(dp[floorCount][2], dp[floorCount][3]));
			System.out.print(answer);
		}

	}

}
