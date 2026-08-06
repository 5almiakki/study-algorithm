import java.io.*;
import java.util.*;

public class CT_20260806_고대_보물_지도의_비밀 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int seqLength = Integer.parseInt(input[0]);
			int maxMinusCount = Integer.parseInt(input[1]);
			int[] seq = new int[seqLength];
			input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
			}

			int answer = seq[0];
			int[] dp = new int[2];
			if (seq[0] >= 0) {
				dp[0] = seq[0];
				dp[1] = Integer.MIN_VALUE;
			} else {
				dp[0] = Integer.MIN_VALUE;
				dp[1] = seq[0];
			}
			// System.out.println(Arrays.toString(dp));
			for (int i = 1; i < seqLength; i++) {
				int offset = (seq[i] >= 0) ? 0 : 1;
				int newLength = Math.min(dp.length + offset, maxMinusCount + 1);
				int[] newDp = new int[newLength];
				Arrays.fill(newDp, Integer.MIN_VALUE);
				if (dp[0] != Integer.MIN_VALUE) {
					newDp[offset] = dp[0] + seq[i];
				} else {
					newDp[offset] = seq[i];
				}
				answer = Math.max(answer, newDp[offset]);
				for (int minusCount = 1; minusCount + offset < newLength; minusCount++) {
					if (dp[minusCount] == Integer.MIN_VALUE) {
						continue;
					}
					newDp[minusCount + offset] = Math.max(dp[minusCount] + seq[i], seq[i]);
					answer = Math.max(answer, newDp[minusCount + offset]);
				}
				dp = newDp;
				// System.out.println(Arrays.toString(dp));
			}
			System.out.print(answer);
		}

	}


}
