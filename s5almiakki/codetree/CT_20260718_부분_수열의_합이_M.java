import java.io.*;
import java.util.*;

public class CT_20260718_부분_수열의_합이_M {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int seqLength = Integer.parseInt(input[0]);
			int targetSum = Integer.parseInt(input[1]);
			int[] seq = new int[seqLength];
			input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
			}
			int[] dp = new int[targetSum + 1];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[0] = 0;
			for (int element : seq) {
				for (int sum = targetSum; sum >= element; sum--) {
					if (dp[sum - element] == Integer.MAX_VALUE) {
						continue;
					}
					dp[sum] = Math.min(dp[sum], dp[sum - element] + 1);
				}
			}
			System.out.print(dp[targetSum] == Integer.MAX_VALUE ? -1 : dp[targetSum]);
		}

	}

}
