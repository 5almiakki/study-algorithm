import java.io.*;
import java.util.*;

public class CT_20260717_동전_거슬러주기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int coinCount = Integer.parseInt(input[0]);
			int targetCost = Integer.parseInt(input[1]);
			int[] coins = new int[coinCount];
			input = br.readLine().split(" ");
			for (int i = 0; i < coinCount; i++) {
				coins[i] = Integer.parseInt(input[i]);
			}

			int[] dp = new int[targetCost + 1];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[0] = 0;
			for (int coin : coins) {
				for (int prevCost = 0; prevCost <= targetCost - coin; prevCost++) {
					if (dp[prevCost] == Integer.MAX_VALUE) {
						continue;
					}
					dp[prevCost + coin] = Math.min(dp[prevCost + coin], dp[prevCost] + 1);
				}
			}
			// System.out.println(Arrays.toString(dp));
			System.out.print(dp[targetCost] == Integer.MAX_VALUE ? -1 : dp[targetCost]);
		}

	}

}
