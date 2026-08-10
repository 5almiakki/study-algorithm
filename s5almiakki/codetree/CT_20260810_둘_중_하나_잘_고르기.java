import java.io.*;
import java.util.*;

public class CT_20260810_둘_중_하나_잘_고르기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int targetCardCount = Integer.parseInt(br.readLine());
			int turnCount = targetCardCount << 1;

			String[] input = br.readLine().split(" ");
			// [blueCardCount]: maxSum
			int[] dp = { Integer.parseInt(input[0]), Integer.parseInt(input[1]) };
			for (int i = 1; i < turnCount; i++) {
				input = br.readLine().split(" ");
				int[] cards = { Integer.parseInt(input[0]), Integer.parseInt(input[1]) };
				int bound = Math.min(dp.length + 1, targetCardCount + 1);
				int[] newDp = new int[bound];
				newDp[0] = dp[0] + cards[0];
				for (int j = 1; j < dp.length; j++) {
					newDp[j] = Math.max(
							Math.max(dp[j] + cards[0], dp[j - 1] + cards[1]),
							newDp[j]);
				}
				if (dp.length < newDp.length) {
					newDp[dp.length] = dp[dp.length - 1] + cards[1];
				}
				dp = newDp;
			}
			System.out.print(dp[targetCardCount]);
		}

	}

}
