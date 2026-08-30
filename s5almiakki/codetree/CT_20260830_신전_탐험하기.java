import java.io.*;

public class CT_20260830_신전_탐험하기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int floorCount = Integer.parseInt(br.readLine());
			int[][] temple = new int[floorCount][3];
			for (int i = 0; i < floorCount; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 0; j < 3; j++) {
					temple[i][j] = Integer.parseInt(input[j]);
				}
			}

			for (int floor = 1; floor < floorCount; floor++) {
				temple[floor][0] += Math.max(temple[floor - 1][1], temple[floor - 1][2]);
				temple[floor][1] += Math.max(temple[floor - 1][0], temple[floor - 1][2]);
				temple[floor][2] += Math.max(temple[floor - 1][0], temple[floor - 1][1]);
			}
			int answer = Math.max(Math.max(temple[floorCount - 1][0], temple[floorCount - 1][1]), temple[floorCount - 1][2]);
			System.out.print(answer);
		}

	}

}
