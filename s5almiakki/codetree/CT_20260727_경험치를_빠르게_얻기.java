import java.io.*;
import java.util.*;

public class CT_20260727_경험치를_빠르게_얻기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int questCount = Integer.parseInt(input[0]);
			int targetSum = Integer.parseInt(input[1]);
			int maxTimeSum = 0;
			int[][] quests = new int[questCount][2];
			for (int i = 0; i < questCount; i++) {
				input = br.readLine().split(" ");
				quests[i][0] = Integer.parseInt(input[0]);
				quests[i][1] = Integer.parseInt(input[1]);
				maxTimeSum += quests[i][1];
			}

			int[] dp = new int[maxTimeSum + 1];
			Arrays.fill(dp, -1);
			dp[0] = 0;
			int answer = Integer.MAX_VALUE;
			for (int[] quest : quests) {
				for (int prevTime = maxTimeSum - quest[1]; prevTime >= 0; prevTime--) {
					if (dp[prevTime] == -1) {
						continue;
					}
					int newTime = prevTime + quest[1];
					dp[newTime] = Math.max(dp[newTime], dp[prevTime] + quest[0]);
					if (dp[newTime] >= targetSum) {
						answer = Math.min(answer, newTime);
					}
				}
			}
			System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
		}

	}

}
