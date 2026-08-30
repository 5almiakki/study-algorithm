import java.io.*;
import java.util.*;

public class CT_20260830_계단_수 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());

			int[] dp = new int[10];
			int divisor = 1_000_000_007;
			Arrays.fill(dp, 1);
			dp[0] = 0;
			for (int length = 2; length <= n; length++) {
				int[] newDp = new int[10];
				newDp[0] = dp[1];
				newDp[9] = dp[8];
				for (int digit = 1; digit <= 8; digit++) {
					newDp[digit] = (dp[digit - 1] + dp[digit + 1]) % divisor;
				}
				dp = newDp;
			}
			int answer = 0;
			for (int i : dp) {
				answer = (answer + i) % divisor;
			}
			System.out.print(answer);
		}

	}

}
