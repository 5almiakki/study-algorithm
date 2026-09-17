import java.util.*;

public class PG_20260918_87694_아이템_줍기 {

	class Solution {

		int[][] deltas = {
				{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
		};

		public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
			Queue<int[]> queue = new ArrayDeque<>(); // { x, y, distance }
			Set<int[]> visitedPoints = new TreeSet<>(
					(o1, o2) -> o1[0] != o2[0]
							? Integer.compare(o1[0], o2[0])
							: Integer.compare(o1[1], o2[1]));
			queue.add(new int[] { characterX, characterY, 0 });
			visitedPoints.add(new int[] { characterX, characterY });
			do {
				int[] moment = queue.remove();
				int newDistance = moment[2] + 1;
				for (int[] delta : deltas) {
					int newX = moment[0] + delta[0];
					int newY = moment[1] + delta[1];
					if (!canMove(visitedPoints, rectangle, moment[0], moment[1], newX, newY)) {
						continue;
					}
					System.out.println();
					if (newX == itemX && newY == itemY) {
						return newDistance;
					}
					queue.add(new int[] { newX, newY, newDistance });
					visitedPoints.add(new int[] { newX, newY });
				}
			} while (!queue.isEmpty());
			return 0;
		}

		boolean canMove(Set<int[]> visitedPoints, int[][] rectangle, int x, int y, int newX, int newY) {
			if (visitedPoints.contains(new int[] { newX, newY })) {
				return false;
			}
			boolean onSameLine = false;
			for (int[] rect : rectangle) {
				if (rect[0] < newX && newX < rect[2] && rect[1] < newY && newY < rect[3]) {
					return false;
				}
				if (crosses(rect, x, y, newX, newY)) {
					return false;
				}
				if (isOnSameLine(rect[0], rect[1], rect[2], rect[1], x, y, newX, newY)
						|| isOnSameLine(rect[0], rect[3], rect[2], rect[3], x, y, newX, newY)
						|| isOnSameLine(rect[0], rect[1], rect[0], rect[3], x, y, newX, newY)
						|| isOnSameLine(rect[2], rect[1], rect[2], rect[3], x, y, newX, newY)) {
					onSameLine = true;
				}
			}
			return onSameLine;
		}

		boolean crosses(int[] rect, int oldX, int oldY, int newX, int newY) {
			int minX = rect[0] << 1;
			int minY = rect[1] << 1;
			int maxX = rect[2] << 1;
			int maxY = rect[3] << 1;
			int x = oldX + newX;
			int y = oldY + newY;
			return minX < x && x < maxX && minY < y && y < maxY;
		}

		boolean isOnSameLine(int fromX, int fromY, int toX, int toY, int oldX, int oldY, int newX, int newY) {
			boolean containsOld = false;
			boolean containsNew = false;
			for (int x = fromX; x <= toX; x++) {
				for (int y = fromY; y <= toY; y++) {
					if (x == oldX && y == oldY) {
						containsOld = true;
						if (containsNew) {
							return true;
						}
					}
					if (x == newX && y == newY) {
						containsNew = true;
						if (containsOld) {
							return true;
						}
					}
				}
			}
			return false;
		}

	}

}
