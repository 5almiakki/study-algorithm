import java.io.*;
import java.util.*;

public class CT_20260715_겹치지_않게_선분_고르기_2 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int lineCount = Integer.parseInt(br.readLine());
			int[][] lines = new int[lineCount][2];
			for (int i = 0; i < lineCount; i++) {
				String[] input = br.readLine().split(" ");
				lines[i][0] = Integer.parseInt(input[0]);
				lines[i][1] = Integer.parseInt(input[1]);
			}
			Arrays.sort(lines, (o1, o2) -> Integer.compare(o1[1], o2[1]));
			int answer = 0;
			int lastTail = 0;
			for (int[] line : lines) {
				if (line[0] <= lastTail) {
					continue;
				}
				answer++;
				lastTail = line[1];
			}
			System.out.print(answer);
		}

	}

}
