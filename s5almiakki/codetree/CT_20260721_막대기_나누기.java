import java.io.*;

public class CT_20260721_막대기_나누기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int stickLength = Integer.parseInt(br.readLine());
			int[] prices = new int[stickLength + 1];
			String[] input = br.readLine().split(" ");
			for (int i = 1; i <= stickLength; i++) {
				prices[i] = Integer.parseInt(input[i - 1]);
			}
			int[] dp = new int[stickLength + 1];
			for (int i = 0; i < stickLength; i++) {
				for (int j = i + 1; j <= stickLength; j++) {
					dp[j] = Math.max(dp[j], dp[i] + prices[j - i]);
				}
			}
			System.out.print(dp[stickLength]);
		}

	}

}
