import java.io.*;

public class CT_20260915_숫자_퍼즐 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int targetStoneCount = Integer.parseInt(input[0]); // N
			int targetSum = Integer.parseInt(input[1]); // M
			int targetOrder = Integer.parseInt(input[2]); // K

			// dp[stoneCount][sum][stone0] = caseCount
			int[][][] dp = new int[targetStoneCount + 1][targetSum + 1][targetSum + 1];
			for (int sum = 1; sum <= targetSum; sum++) {
				dp[1][sum][sum] = 1;
			}
			for (int stoneCount = 2; stoneCount <= targetStoneCount; stoneCount++) {
				for (int pastSum = 1; pastSum < targetSum; pastSum++) {
					for (int pastStone0 = 1; pastStone0 <= targetSum; pastStone0++) {
						if (dp[stoneCount - 1][pastSum][pastStone0] == 0) {
							continue;
						}
						int bound = Math.min(pastStone0, targetSum - pastSum);
						for (int stone0 = 1; stone0 <= bound; stone0++) {
							int sum = pastSum + stone0;
							dp[stoneCount][sum][stone0] += dp[stoneCount - 1][pastSum][pastStone0];
						}
					}
				}
			}

			StringBuilder answer = new StringBuilder();
			int sum = targetSum;
			int stone = 1;
			for (int stoneCount = targetStoneCount; stoneCount >= 1; stoneCount--) {
				while (dp[stoneCount][sum][stone] < targetOrder) {
					targetOrder -= dp[stoneCount][sum][stone];
					stone++;
				}
				answer.append(stone).append(' ');
				sum -= stone;
			}
			System.out.print(answer);
		}

	}

}
