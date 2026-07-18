import java.io.*;

public class CT_20260718_1_2_5_더하기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int target = Integer.parseInt(br.readLine());
			int[] nums = { 1, 2, 5 };
			int[] dp = new int[target + 1];
			dp[0] = 1;
			for (int sum = 0; sum < target; sum++) {
				if (dp[sum] == 0) {
					continue;
				}
				for (int num : nums) {
					int newSum = sum + num;
					if (newSum > target) {
						break;
					}
					dp[newSum] = (dp[newSum] + dp[sum]) % 10007;
				}
			}
			System.out.print(dp[target]);
		}

	}

}
