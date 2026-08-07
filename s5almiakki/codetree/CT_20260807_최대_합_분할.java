import java.io.*;
import java.util.*;

public class CT_20260807_최대_합_분할 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int seqLength = Integer.parseInt(br.readLine());
			int[] seq = new int[seqLength];
			int totalSum = 0;
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
				totalSum += seq[i];
			}

			int offset = totalSum;
			int[] dp = new int[(totalSum << 1) + 1];
			Arrays.fill(dp, -1);
			dp[offset + seq[0]] = seq[0];
			dp[offset - seq[0]] = 0;
			dp[offset] = 0;
			// System.out.println(Arrays.toString(dp));
			// for (int idx = 0; idx < dp.length; idx++) {
			//     if (dp[idx] != -1) {
			//         System.out.print((idx - offset) + ":" + dp[idx] + " ");
			//     }
			// }
			// System.out.println();
			for (int i = 1; i < seqLength; i++) {
				int[] newDp = new int[dp.length];
				Arrays.fill(newDp, -1);
				for (int diff = offset << 1; diff >= 0; diff--) {
					if (dp[diff] == -1) {
						continue;
					}
					int idx = diff + seq[i];
					if (0 <= idx && idx <= (offset << 1)) {
						newDp[idx] = Math.max(newDp[idx], dp[diff] + seq[i]);
					}
					idx = diff - seq[i];
					if (0 <= idx && idx <= (offset << 1)) {
						newDp[idx] = Math.max(newDp[idx], dp[diff]);
					}
					idx = diff;
					newDp[idx] = Math.max(newDp[idx], dp[diff]);
				}
				dp = newDp;
				// System.out.println(Arrays.toString(dp));
				// for (int idx = 0; idx < dp.length; idx++) {
				//     if (dp[idx] != -1) {
				//         System.out.print((idx - offset) + ":" + dp[idx] + " ");
				//     }
				// }
				// System.out.println();
			}
			System.out.print(dp[offset]);
		}

	}

}
