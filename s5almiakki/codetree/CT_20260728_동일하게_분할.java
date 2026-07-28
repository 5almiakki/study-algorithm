import java.io.*;
import java.util.*;

public class CT_20260728_동일하게_분할 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int seqLength = Integer.parseInt(br.readLine());
			int[] seq = new int[seqLength];
			int targetSum = 0;
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
				targetSum += seq[i];
			}
			if (targetSum % 2 != 0) {
				System.out.print("No");
				return;
			}
			targetSum >>= 1;

			String answer = "No";
			boolean[] dp = new boolean[targetSum];
			dp[0] = true;
			for (int num : seq) {
				// System.out.println(num);
				if (dp[targetSum - num]) {
					answer = "Yes";
					break;
				}
				for (int prevSum = targetSum - num - 1; prevSum >= 0; prevSum--) {
					if (dp[prevSum]) {
						dp[prevSum + num] = true;
					}
				}
				// System.out.println(Arrays.toString(dp));
			}
			System.out.print(answer);
		}

	}

}
