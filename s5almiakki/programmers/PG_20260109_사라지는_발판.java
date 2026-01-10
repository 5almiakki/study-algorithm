import java.util.*;

public class PG_20260109_사라지는_발판 {

    class Solution {

        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        private int[][] board;
        private Point[][] points;

        public int solution(int[][] board, int[] aloc, int[] bloc) {
            this.board = board;
            points = new Point[board.length][board[0].length];
            for (int row = 0; row < points.length; row++) {
                for (int col = 0; col < points[row].length; col++) {
                    points[row][col] = new Point(row, col);
                }
            }

            return dfs(
                    new Point[] { points[aloc[0]][aloc[1]], points[bloc[0]][bloc[1]] },
                    new HashSet<>());
        }

        private int dfs(Point[] playerPoints, Set<Point> disappearedPoints) {
            int moveCount = disappearedPoints.size();
            int order = moveCount % 2;
            int nextOrder = (order + 1) % 2;
            List<SortedSet<Integer>> winnerToMoveCountsMap = new ArrayList<>();
            winnerToMoveCountsMap.add(new TreeSet<>());
            winnerToMoveCountsMap.add(new TreeSet<>());

            Point currentPlayerPoint = playerPoints[order];
            Point[] newPlayerPoints = Arrays.copyOf(playerPoints, 2);
            disappearedPoints.add(currentPlayerPoint);
            for (int[] direction : DIRECTIONS) {
                int newRow = currentPlayerPoint.row + direction[0];
                int newCol = currentPlayerPoint.col + direction[1];
                if (isOutOfBounds(newRow, newCol) || board[newRow][newCol] == 0) {
                    continue;
                }
                Point currentPlayerNewPoint = points[newRow][newCol];
                if (disappearedPoints.contains(currentPlayerNewPoint)) {
                    continue;
                }
                newPlayerPoints[order] = currentPlayerNewPoint;
                int result = dfs(newPlayerPoints, disappearedPoints);
                winnerToMoveCountsMap.get((result + 1) % 2).add(result);
            }
            disappearedPoints.remove(currentPlayerPoint);

            SortedSet<Integer> winningMoveCounts = winnerToMoveCountsMap.get(order);
            if (winningMoveCounts.isEmpty()) {
                SortedSet<Integer> losingMoveCounts = winnerToMoveCountsMap.get(nextOrder);
                if (losingMoveCounts.isEmpty()) {
                    return moveCount;
                }
                if (playerPoints[0].equals(playerPoints[1])) {
                    return moveCount + 1;
                }
                return losingMoveCounts.last();
            }
            if (playerPoints[0].equals(playerPoints[1])) {
                return moveCount + 1;
            }
            return winningMoveCounts.first();
        }

        private boolean isOutOfBounds(int row, int col) {
            return row < 0 || points.length <= row
                    || col < 0 || points[row].length <= col;
        }

        private static class Point {

            public final int row;
            public final int col;

            private final int hashCode;

            public Point(int row, int col) {
                this.row = row;
                this.col = col;

                hashCode = Objects.hash(row, col);
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

    }

}
