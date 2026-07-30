import java.io.*;
import java.util.*;

public class CT_20260730_숫자_전쟁 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int cardCount = Integer.parseInt(br.readLine());
			int[] opponentCards = new int[cardCount];
			int[] myCards = new int[cardCount];
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < cardCount; i++) {
				opponentCards[i] = Integer.parseInt(input[i]);
			}
			input = br.readLine().split(" ");
			for (int i = 0; i < cardCount; i++) {
				myCards[i] = Integer.parseInt(input[i]);
			}

			int answer = 0;
			// [opponentCardIdx][myCardIdx]
			int[][] dp = new int[cardCount + 1][cardCount + 1];
			for (int[] row : dp) {
				Arrays.fill(row, -1);
			}
			dp[0][0] = 0;
			for (int opponentCardIdx = 0; opponentCardIdx < cardCount; opponentCardIdx++) {
				for (int myCardIdx = 0; myCardIdx < cardCount; myCardIdx++) {
					if (dp[opponentCardIdx][myCardIdx] == -1) {
						continue;
					}
					dp(opponentCards, myCards, opponentCardIdx, myCardIdx, dp);
				}
				answer = Math.max(answer, dp[opponentCardIdx][cardCount]);
			}
			for (int i : dp[cardCount]) {
				answer = Math.max(answer, i);
			}
			System.out.print(answer);
		}

		static void dp(int[] opponentCards, int[] myCards, int opponentCardIdx, int myCardIdx, int[][] dp) {
			int opponentCard = opponentCards[opponentCardIdx];
			int myCard = myCards[myCardIdx];
			if (opponentCard > myCard) {
				int newIdx = myCardIdx + 1;
				dp[opponentCardIdx][newIdx] = Math.max(
						dp[opponentCardIdx][newIdx],
						dp[opponentCardIdx][myCardIdx] + myCards[myCardIdx]);
			} else if (opponentCard < myCard) {
				int newIdx = opponentCardIdx + 1;
				dp[newIdx][myCardIdx] = Math.max(
						dp[newIdx][myCardIdx],
						dp[opponentCardIdx][myCardIdx]);
			}
			int newOpponentCardIdx = opponentCardIdx + 1;
			int newMyCardIdx = myCardIdx + 1;
			dp[newOpponentCardIdx][newMyCardIdx] = Math.max(
					dp[newOpponentCardIdx][newMyCardIdx],
					dp[opponentCardIdx][myCardIdx]);
			return;
		}

	}

}
