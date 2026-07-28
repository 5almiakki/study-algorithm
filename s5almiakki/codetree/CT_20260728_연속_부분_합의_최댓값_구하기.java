import java.io.*;

public class CT_20260728_연속_부분_합의_최댓값_구하기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int seqLength = Integer.parseInt(br.readLine());
			int[] seq = new int[seqLength];
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
			}

			int answer = seq[0];
			int[] dp = new int[seqLength];
			dp[0] = seq[0];
			for (int i = 1; i < seqLength; i++) {
				dp[i] = Math.max(dp[i - 1] + seq[i], seq[i]);
				answer = Math.max(answer, dp[i]);
			}
			System.out.print(answer);
		}

	}

}
