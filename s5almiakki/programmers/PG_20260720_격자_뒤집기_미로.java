import java.util.*;

public class PG_20260720_격자_뒤집기_미로 {

	class Solution {

		int height;
		int width;
		int[][][] grid = new int[2][][];
		int flipCost;
		int answer = Integer.MIN_VALUE;

		void init(int[][] visible, int[][] hidden, int k) {
			height = visible.length;
			width = visible[0].length;
			grid[0] = visible;
			grid[1] = hidden;
			flipCost = k;
		}

		public int solution(int[][] visible, int[][] hidden, int k) {
			init(visible, hidden, k);
			backtrack(new int[height], 0, 0);
			return answer;
		}

		void backtrack(int[] flipped, int depth, int flipCostSum) {
			if (depth == height) {
				// System.out.println(Arrays.toString(flipped) + ", flipCostSum: " + flipCostSum);
				computeAnswer(flipped, flipCostSum);
				return;
			}
			backtrack(flipped, depth + 1, flipCostSum);
			flipped[depth] = 1 - flipped[depth];
			backtrack(flipped, depth + 1, flipCostSum + flipCost);
			flipped[depth] = 1 - flipped[depth];
		}

		void computeAnswer(int[] rowFlipped, int flipCostSum) {
			boolean gridEven = (height % 2 == 0) && (width % 2 == 0);
			int result = -flipCostSum;
			int maxReplacement = Integer.MIN_VALUE;
			int[] scoreSums = new int[2];
			int[] minHoles = new int[2];
			for (int col = 0; col < width; col++) {
				scoreSums[0] = 0;
				scoreSums[1] = -flipCost;
				minHoles[0] = Integer.MAX_VALUE;
				minHoles[1] = Integer.MAX_VALUE;
				for (int colFlipped = 0; colFlipped <= 1; colFlipped++) {
					for (int row = 0; row < height; row++) {
						int cellFlipped = (rowFlipped[row] + colFlipped) % 2;
						int cell = grid[cellFlipped][row][col];
						scoreSums[colFlipped] += cell;
						if (gridEven && (row + col) % 2 == 1) {
							minHoles[colFlipped] = Math.min(minHoles[colFlipped], cell);
						}
					}
				}
				int maxSum = Math.max(scoreSums[0], scoreSums[1]);
				result += maxSum;
				if (gridEven) {
					int maxSumWithHole = Math.max(scoreSums[0] - minHoles[0], scoreSums[1] - minHoles[1]);
					maxReplacement = Math.max(maxReplacement, maxSumWithHole - maxSum);
				}
			}
			if (gridEven) {
				result += maxReplacement;
			}
			answer = Math.max(answer, result);
		}

	}

}
