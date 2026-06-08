import java.io.*;

public class CT_20260608_2명의_도둑 {

	public class Main {

		static int gridSize;
		static int targetChoiceCount;
		static int capacity;
		static int[][] grid;

		static int weightSum1 = 0;
		static int priceSum1 = 0;
		static int weightSum2 = 0;
		static int priceSum2 = 0;
		static int answer = 0;

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			gridSize = Integer.parseInt(input[0]);
			targetChoiceCount = Integer.parseInt(input[1]);
			capacity = Integer.parseInt(input[2]);
			grid = new int[gridSize][gridSize];
			for (int row = 0; row < gridSize; row++) {
				input = br.readLine().split(" ");
				for (int col = 0; col < gridSize; col++) {
					grid[row][col] = Integer.parseInt(input[col]);
				}
			}

			for (int row1 = 0; row1 < gridSize; row1++) {
				for (int col1 = 0; col1 + targetChoiceCount - 1 < gridSize; col1++) {
					for (int col2 = col1 + targetChoiceCount; col2 + targetChoiceCount - 1 < gridSize; col2++) {
						// System.out.println(row1 + "," + col1 + " 0," + col2);
						backtrack1(row1, col1, row1, col2, 0);
						// System.out.println();
						// System.out.println();
					}
				}
				for (int row2 = row1 + 1; row2 < gridSize; row2++) {
					for (int col1 = 0; col1 + targetChoiceCount - 1 < gridSize; col1++) {
						for (int col2 = 0; col2 + targetChoiceCount - 1 < gridSize; col2++) {
							// System.out.println("base: " + row1 + "," + col1 + " " + row2 + "," + col2);
							backtrack1(row1, col1, row2, col2, 0);
							// System.out.println();
							// System.out.println();
						}
					}
				}
			}
			System.out.print(answer);
		}

		static void backtrack1(int row1, int col1, int row2, int col2, int depth) {
			if (depth == targetChoiceCount) {
				backtrack2(row2, col2, 0);
				return;
			}
			int weight = grid[row1][col1 + depth];
			int sqWeight = weight * weight;
			// System.out.println("weight1: " + weight + ", " + sqWeight + " not chosen");
			backtrack1(row1, col1, row2, col2, depth + 1);
			weightSum1 += weight;
			priceSum1 += sqWeight;
			if (weightSum1 <= capacity) {
				// System.out.println("weight1: " + weight + ", " + sqWeight + " chosen");
				backtrack1(row1, col1, row2, col2, depth + 1);
			}
			weightSum1 -= weight;
			priceSum1 -= sqWeight;
		}

		static void backtrack2(int row2, int col2, int depth) {
			if (depth == targetChoiceCount) {
				// System.out.println(priceSum1 + priceSum2);
				answer = Math.max(answer, priceSum1 + priceSum2);
				return;
			}
			int weight = grid[row2][col2 + depth];
			int sqWeight = weight * weight;
			// System.out.println("weight2: " + weight + ", " + sqWeight + " not chosen");
			backtrack2(row2, col2, depth + 1);
			weightSum2 += weight;
			priceSum2 += sqWeight;
			if (weightSum2 <= capacity) {
				// System.out.println("weight2: " + weight + ", " + sqWeight + " chosen");
				backtrack2(row2, col2, depth + 1);
			}
			weightSum2 -= weight;
			priceSum2 -= sqWeight;
		}

	}

}