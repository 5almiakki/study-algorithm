import java.util.*;

public class PG_20260917_1844_게임_맵_최단거리 {

	class Solution {

		int[][] deltas = {
				{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
		};

		public int solution(int[][] maps) {
			Queue<int[]> queue = new ArrayDeque<>(); // { row, col, count }
			boolean[][] visited = new boolean[maps.length][maps[0].length];
			queue.add(new int[] { 0, 0, 1 });
			visited[0][0] = true;
			do {
				int[] moment = queue.remove();
				int newCount = moment[2] + 1;
				for (int[] delta : deltas) {
					int newRow = moment[0] + delta[0];
					int newCol = moment[1] + delta[1];
					if (isOutOfBounds(maps, newRow, newCol)
							|| visited[newRow][newCol]
							|| maps[newRow][newCol] == 0) {
						continue;
					}
					if (newRow == maps.length - 1 && newCol == maps[newRow].length - 1) {
						return newCount;
					}
					queue.add(new int[] { newRow, newCol, newCount });
					visited[newRow][newCol] = true;
				}
			} while (!queue.isEmpty());
			return -1;
		}

		boolean isOutOfBounds(int[][] maps, int row, int col) {
			return row < 0 || maps.length <= row
					|| col < 0 || maps[row].length <= col;
		}

	}

}
