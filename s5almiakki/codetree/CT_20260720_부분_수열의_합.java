import java.io.*;

public class CT_20260720_부분_수열의_합 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int seqLength = Integer.parseInt(input[0]);
			int[] seq = new int[seqLength];
			int targetSum = Integer.parseInt(input[1]);
			input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
			}
			boolean[] dp = new boolean[targetSum + 1];
			dp[0] = true;
			for (int element : seq) {
				for (int sum = targetSum - element; sum >= 0; sum--) {
					if (!dp[sum]) {
						continue;
					}
					int newSum = sum + element;
					if (targetSum < newSum) {
						continue;
					}
					dp[newSum] = true;
					if (newSum == targetSum) {
						break;
					}
				}
				if (dp[targetSum]) {
					System.out.print("Yes");
					return;
				}
			}
			System.out.print("No");
		}

	}

}
