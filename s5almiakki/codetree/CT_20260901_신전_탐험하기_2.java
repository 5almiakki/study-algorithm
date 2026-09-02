import java.io.*;

public class CT_20260901_신전_탐험하기_2 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int floorCount = Integer.parseInt(br.readLine());
			int[][] floors = new int[floorCount][3];
			for (int i = 0; i < floorCount; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 0; j < 3; j++) {
					floors[i][j] = Integer.parseInt(input[j]);
				}
			}

			int answer = 0;
			for (int firstRoom = 0; firstRoom < 3; firstRoom++) {
				int[] dp = new int[3];
				dp[firstRoom] = floors[0][firstRoom];
				for (int floor = 1; floor < floorCount; floor++) {
					int[] newDp = new int[3];
					for (int room = 0; room < 3; room++) {
						for (int prevRoom = 0; prevRoom < 3; prevRoom++) {
							if (room == prevRoom) {
								continue;
							}
							newDp[room] = Math.max(newDp[room], dp[prevRoom]);
						}
						newDp[room] += floors[floor][room];
					}
					dp = newDp;
				}
				for (int lastRoom = 0; lastRoom < 3; lastRoom++) {
					if (firstRoom == lastRoom) {
						continue;
					}
					answer = Math.max(answer, dp[lastRoom]);
				}
			}
			System.out.print(answer);
		}

	}

}
