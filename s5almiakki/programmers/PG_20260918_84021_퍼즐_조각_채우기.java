import java.util.*;

public class PG_20260918_84021_퍼즐_조각_채우기 {

	class Solution {

		int[][] deltas = {
				{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
		};

		public int solution(int[][] game_board, int[][] table) {
			List<Piece> gameBoardPieces = findPieces(game_board, 0);
			List<Piece> tablePieces = findPieces(table, 1);
			int answer = 0;
			for (Piece p : tablePieces) {
				if (gameBoardPieces.remove(p)) {
					answer += p.size;
				}
			}
			return answer;
		}

		List<Piece> findPieces(int[][] grid, int targetCell) {
			List<Piece> pieces = new LinkedList<>();
			Queue<Point> queue = new ArrayDeque<>();
			boolean[][] visited = new boolean[grid.length][grid[0].length];
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid[row].length; col++) {
					if (!visited[row][col] && grid[row][col] == targetCell) {
						pieces.add(bfs(grid, targetCell, queue, visited, row, col));
					}
				}
			}
			return pieces;
		}

		Piece bfs(
				int[][] grid, int targetCell, Queue<Point> queue, boolean[][] visited,
				int beginRow, int beginCol) {
			Set<Point> visitedPoints = new HashSet<>();
			Point beginPoint = new Point(beginRow, beginCol);
			queue.add(beginPoint);
			visited[beginRow][beginCol] = true;
			visitedPoints.add(beginPoint);
			do {
				Point p = queue.remove();
				for (int[] delta : deltas) {
					int newRow = p.row + delta[0];
					int newCol = p.col + delta[1];
					if (isOutOfBounds(grid, newRow, newCol)
							|| visited[newRow][newCol]
							|| grid[newRow][newCol] != targetCell) {
						continue;
					}
					Point newPoint = new Point(newRow, newCol);
					queue.add(newPoint);
					visited[newRow][newCol] = true;
					visitedPoints.add(newPoint);
				}
			} while (!queue.isEmpty());
			return new Piece(visitedPoints);
		}

		boolean isOutOfBounds(int[][] grid, int row, int col) {
			return row < 0 || grid.length <= row
					|| col < 0 || grid[row].length <= col;
		}

		static class Point {

			final int row;
			final int col;
			final int hashCode;

			Point(int row, int col) {
				this.row = row;
				this.col = col;
				hashCode = 31 * row + col;
			}

			@Override
			public boolean equals(Object o) {
				if (this == o) {
					return true;
				}
				if (!(o instanceof Point)) {
					return false;
				}
				Point other = (Point) o;
				return row == other.row && col == other.col;
			}

			@Override
			public int hashCode() {
				return hashCode;
			}

		}

		static class Piece {

			final Set<Set<Point>> pieces = new HashSet<>();
			final int size;
			final int hashCode;

			Piece(Set<Point> points) {
				pieces.add(normalize(points));
				size = points.size();
				for (int i = 0; i < 3; i++) {
					points = rotate(points);
					pieces.add(normalize(points));
				}
				hashCode = pieces.hashCode();
			}

			Set<Point> normalize(Set<Point> points) {
				int minRow = Integer.MAX_VALUE;
				int minCol = Integer.MAX_VALUE;
				for (Point p : points) {
					minRow = Math.min(minRow, p.row);
					minCol = Math.min(minCol, p.col);
				}
				Set<Point> newPoints = new HashSet<>();
				for (Point p : points) {
					newPoints.add(new Point(p.row - minRow, p.col - minCol));
				}
				return newPoints;
			}

			Set<Point> rotate(Set<Point> points) {
				Set<Point> newPoints = new HashSet<>();
				for (Point p : points) {
					newPoints.add(new Point(p.col, -p.row));
				}
				return newPoints;
			}

			@Override
			public boolean equals(Object o) {
				if (this == o) {
					return true;
				}
				if (!(o instanceof Piece)) {
					return false;
				}
				Piece other = (Piece) o;
				return pieces.equals(other.pieces);
			}

			@Override
			public int hashCode() {
				return hashCode;
			}

		}

	}

}
