import java.io.*;
import java.util.*;

public class CT_20260719_최대_동전_거슬러주기 {

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
			Arrays.fill(dp, -1);
			dp[0] = 0;
			for (int cost = 0; cost < targetCost; cost++) {
				if (dp[cost] == -1) {
					continue;
				}
				for (int coin : coins) {
					int newCost = cost + coin;
					if (targetCost < newCost) {
						continue;
					}
					dp[newCost] = Math.max(dp[newCost], dp[cost] + 1);
				}
			}
			System.out.print(dp[targetCost]);
		}

	}

}
