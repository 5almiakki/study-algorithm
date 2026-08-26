import java.io.*;

public class CT_20260826_적절한_옷_고르기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int clothesCount = Integer.parseInt(input[0]);
			int dayCount = Integer.parseInt(input[1]);
			int[][] clotheses = new int[clothesCount][3];
			for (int i = 0; i < clothesCount; i++) {
				input = br.readLine().split(" ");
				clotheses[i][0] = Integer.parseInt(input[0]) - 1;
				clotheses[i][1] = Integer.parseInt(input[1]) - 1;
				clotheses[i][2] = Integer.parseInt(input[2]);
			}

			int[][] dp = new int[dayCount][clothesCount];
			// System.out.println(Arrays.toString(dp[0]));
			for (int day = 1; day < dayCount; day++) {
				for (int clothes = 0; clothes < clothesCount; clothes++) {
					if (day < clotheses[clothes][0] || clotheses[clothes][1] < day) {
						continue;
					}
					for (int prevClothes = 0; prevClothes < clothesCount; prevClothes++) {
						if (day - 1 < clotheses[prevClothes][0] || clotheses[prevClothes][1] < day - 1) {
							continue;
						}
						int score = Math.abs(clotheses[clothes][2] - clotheses[prevClothes][2]);
						dp[day][clothes] = Math.max(dp[day][clothes], dp[day - 1][prevClothes] + score);
					}
				}
				// System.out.println(Arrays.toString(dp[day]));
			}
			int answer = 0;
			for (int i : dp[dayCount - 1]) {
				answer = Math.max(answer, i);
			}
			System.out.print(answer);
		}

	}

}
