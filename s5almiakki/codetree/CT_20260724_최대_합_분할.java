import java.io.*;
import java.util.*;

public class CT_20260724_최대_합_분할 {

	public class Main {

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

			int[][] dp = new int[seqLength + 1][(total << 1) + 1];
			for (int[] row : dp) {
				Arrays.fill(row, Integer.MIN_VALUE);
			}
			dp[0][total] = 0;

			for (int i = 1; i <= seqLength; i++) {
				for (int diff = -total; diff <= total; diff++) {
					// A에 i번째 원소 추가해서 (A의 합) - (B의 합) = diff가 된 경우
					update(total, dp, i, diff, diff - seq[i - 1], seq[i - 1]);
					// B에 i번째 원소 추가해서 (A의 합) - (B의 합) = diff가 된 경우
					update(total, dp, i, diff, diff + seq[i - 1], 0);
					// C에 i번째 원소 추가해서 (A의 합) - (B의 합) = diff가 된 경우
					update(total, dp, i, diff, diff, 0);
				}
			}
			System.out.print(dp[seqLength][total]);
		}

		static void update(int total, int[][] dp, int seqIdx, int diff, int prevDiff, int value) {
			if (prevDiff < -total || total < prevDiff
					|| dp[seqIdx - 1][prevDiff + total] == Integer.MIN_VALUE) {
				return;
			}
			dp[seqIdx][diff + total] = Math.max(
					dp[seqIdx][diff + total],
					dp[seqIdx - 1][prevDiff + total] + value);
		}

	}

}
