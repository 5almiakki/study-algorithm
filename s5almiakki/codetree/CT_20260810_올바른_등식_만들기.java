import java.io.*;
import java.util.*;

public class CT_20260810_올바른_등식_만들기 {

	public class Main {

		static final int OFFSET = 20;

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int numCount = Integer.parseInt(input[0]);
			int targetSum = Integer.parseInt(input[1]);
			input = br.readLine().split(" ");
			int[] nums = new int[numCount];
			for (int i = 0; i < numCount; i++) {
				nums[i] = Integer.parseInt(input[i]);
			}

			long[] dp = new long[41];
			dp[20 + nums[0]]++;
			dp[20 - nums[0]]++;
			for (int i = 1; i < numCount; i++) {
				long[] newDp = new long[41];
				for (int oldSum = 0; oldSum <= 40; oldSum++) {
					if (dp[oldSum] == 0) {
						continue;
					}
					int newSum = oldSum - nums[i];
					if (0 <= newSum && newSum <= 40) {
						newDp[newSum] += dp[oldSum];
					}
					newSum = oldSum + nums[i];
					if (0 <= newSum && newSum <= 40) {
						newDp[newSum] += dp[oldSum];
					}
				}
				dp = newDp;
			}
			System.out.print(dp[20 + targetSum]);
		}

	}

}
