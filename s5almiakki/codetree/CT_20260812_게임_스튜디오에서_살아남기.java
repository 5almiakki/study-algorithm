import java.io.*;

public class CT_20260812_게임_스튜디오에서_살아남기 {

	public class Main {

		static final int DIVISOR = 1_000_000_007;

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int targetDayCount = Integer.parseInt(br.readLine());
			// [bCount][tCount]
			int[][] dp = {
					{ 1, 1, 0 },
					{ 1, 0, 0 },
					{ 0, 0, 0 }
			};
			for (int day = 1; day < targetDayCount; day++) {
				int[][] newDp = new int[3][3];
				// G
				for (int bCount = 0; bCount <= 2; bCount++) {
					for (int tCount = 0; tCount <= 2; tCount++) {
						addMod(dp, bCount, tCount, newDp, 0, tCount);
					}
				}
				// B
				for (int bCount = 0; bCount <= 1; bCount++) {
					for (int tCount = 0; tCount <= 2; tCount++) {
						addMod(dp, bCount, tCount, newDp, bCount + 1, tCount);
					}
				}
				// T
				for (int bCount = 0; bCount <= 2; bCount++) {
					for (int tCount = 0; tCount <= 1; tCount++) {
						addMod(dp, bCount, tCount, newDp, 0, tCount + 1);
					}
				}
				dp = newDp;
			}
			int answer = 0;
			for (int[] row : dp) {
				for (int cell : row) {
					answer = (answer + cell) % DIVISOR;
				}
			}
			System.out.print(answer);
		}

		static void addMod(int[][] srcDp, int srcIdx1, int srcIdx2, int[][] destDp, int destIdx1, int destIdx2) {
			destDp[destIdx1][destIdx2] = (destDp[destIdx1][destIdx2] + srcDp[srcIdx1][srcIdx2]) % DIVISOR;
		}

	}

}
