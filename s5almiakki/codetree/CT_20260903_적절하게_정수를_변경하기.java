import java.io.*;
import java.util.*;

public class CT_20260903_적절하게_정수를_변경하기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int seqLength = Integer.parseInt(input[0]);
			int maxDiffCount = Integer.parseInt(input[1]);
			input = br.readLine().split(" ");
			int[] seq = new int[seqLength];
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]) - 1;
			}

			// dp[seqIdx][diffCount][element] = maxSimilarity
			int[][][] dp = new int[seqLength][maxDiffCount + 1][4];
			for (int[][] i : dp) {
				for (int[] j : i) {
					Arrays.fill(j, -1);
				}
			}
			for (int element = 0; element <= 3; element++) {
				dp[0][0][element] = seq[0] == element ? 1 : 0;
			}
			for (int seqIdx = 1; seqIdx < seqLength; seqIdx++) {
				for (int prevDiffCount = 0; prevDiffCount <= maxDiffCount; prevDiffCount++) {
					for (int prevElement = 0; prevElement <= 3; prevElement++) {
						int prevSimilarity = dp[seqIdx - 1][prevDiffCount][prevElement];
						if (prevSimilarity == -1) {
							continue;
						}
						for (int currentElement = 0; currentElement <= 3; currentElement++) {
							int currentDiffCount = prevDiffCount + (prevElement == currentElement ? 0 : 1);
							if (currentDiffCount > maxDiffCount) {
								continue;
							}
							dp[seqIdx][currentDiffCount][currentElement] = Math.max(
									dp[seqIdx][currentDiffCount][currentElement],
									prevSimilarity + (seq[seqIdx] == currentElement ? 1 : 0));
						}
					}
				}
			}
			int answer = 0;
			for (int[] i : dp[seqLength - 1]) {
				for (int j : i) {
					answer = Math.max(answer, j);
				}
			}
			System.out.print(answer);
		}

	}

}
