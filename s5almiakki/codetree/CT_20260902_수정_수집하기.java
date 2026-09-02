import java.io.*;
import java.util.*;

public class CT_20260902_수정_수집하기 {

	public class Main {

		// static {
		//     StringBuilder sb = new StringBuilder();
		//     for (int i = 0; i < 9; i++) {
		//         sb.append(Math.random() < 0.5 ? 'L' : 'R');
		//     }
		//     System.out.println(sb);
		// }

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int crystalCount = Integer.parseInt(input[0]);
			int maxWarpCount = Integer.parseInt(input[1]);
			String seq = br.readLine();
			int[][] crystals = new int[crystalCount][2];
			for (int i = 0; i < crystalCount; i++) {
				crystals[i][seq.charAt(i) == 'L' ? 0 : 1] = 1;
				// System.out.println(Arrays.toString(crystals[i]));
			}

			int[][] dp = new int[maxWarpCount + 1][2]; // dp[warpCount][position] = maxCrystalSum
			for (int[] arr : dp) {
				Arrays.fill(arr, -1);
			}
			dp[0][0] = crystals[0][0];
			dp[1][1] = crystals[0][1];
			// System.out.println("i: " + 0);
			// System.out.println(Arrays.toString(dp[0]));
			for (int i = 1; i < crystalCount; i++) {
				for (int prevWarpCount = maxWarpCount; prevWarpCount >= 0; prevWarpCount--) {
					for (int pos = 0; pos <= 1; pos++) {
						// 이동 안 하는 경우
						if (dp[prevWarpCount][pos] != -1 && prevWarpCount <= maxWarpCount) {
							dp[prevWarpCount][pos] += crystals[i][pos];
						}
						// 이동하는 경우
						if (dp[prevWarpCount][1 - pos] != -1 && prevWarpCount + 1 <= maxWarpCount) {
							dp[prevWarpCount + 1][pos] = Math.max(
									dp[prevWarpCount][1 - pos] + crystals[i][pos],
									dp[prevWarpCount + 1][pos]);
						}
					}
				}
				// System.out.println();
				// System.out.println("i: " + i);
				// for (int j = 0; j <= maxWarpCount; j++) {
				//     if (dp[j][0] == -1 && dp[j][1] == -1) {
				//         break;
				//     }
				//     System.out.println("warpCount: " + j + ", " + Arrays.toString(dp[j]));
				// }
			}
			int answer = 0;
			for (int[] row : dp) {
				for (int cell : row) {
					answer = Math.max(answer, cell);
				}
			}
			System.out.print(answer);
		}

	}

}
