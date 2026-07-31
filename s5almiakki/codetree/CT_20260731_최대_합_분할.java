import java.io.*;
import java.util.*;

public class CT_20260731_최대_합_분할 {

	public class Main {

		static int[][] dp;

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int seqLength = Integer.parseInt(br.readLine());
			int[] seq = new int[seqLength];
			String[] input = br.readLine().split(" ");
			int total = 0;
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
				total += seq[i];
			}

			dp = new int[seqLength][(total << 1) + 1];
			for (int[] row : dp) {
				Arrays.fill(row, -1);
			}
			dp[0][total + seq[0]] = seq[0];
			dp[0][total - seq[0]] = 0;
			dp[0][total] = 0;
			for (int seqIdx = 1; seqIdx < seqLength; seqIdx++) {
				for (int diff = -total; diff <= total; diff++) {
					int sumA = dp[seqIdx - 1][total + diff];
					if (sumA == -1) {
						continue;
					}
					int newDiff = total + diff + seq[seqIdx];
					dp[seqIdx][newDiff] = Math.max(dp[seqIdx][newDiff], sumA + seq[seqIdx]);
					newDiff = total + diff - seq[seqIdx];
					dp[seqIdx][newDiff] = Math.max(dp[seqIdx][newDiff], sumA);
					newDiff = total + diff;
					dp[seqIdx][newDiff] = Math.max(dp[seqIdx][newDiff], sumA);
				}
			}
			// for (int[] row : dp) {
			//     System.out.println(Arrays.toString(row));
			// }
			System.out.print(dp[seqLength - 1][total]);
		}

	}

}
