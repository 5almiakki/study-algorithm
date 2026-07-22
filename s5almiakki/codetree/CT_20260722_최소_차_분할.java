import java.io.*;
import java.util.*;

public class CT_20260722_최소_차_분할 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int seqLength = Integer.parseInt(br.readLine());
			int[] seq = new int[seqLength];
			int sum = 0;
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
				sum += seq[i];
			}
			int minDiff = Integer.MAX_VALUE;
			boolean[] dp = new boolean[sum];
			dp[0] = true;
			for (int num : seq) {
				for (int oldSum = sum - 1 - num; oldSum >= 0; oldSum--) {
					if (!dp[oldSum]) {
						continue;
					}
					int newSum = oldSum + num;
					dp[newSum] = true;
					int diff = Math.abs((newSum << 1) - sum);
					minDiff = Math.min(minDiff, diff);
				}
			}
			System.out.print(minDiff);
		}

	}

}
