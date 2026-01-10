import java.util.*;

public class PG_20260110_1836_리틀_프렌즈_사천성 {

    class Solution {

        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };
        private static Point[][] points = new Point[100][100];

        static {
            for (int row = 0; row < 100; row++) {
                for (int col = 0; col < 100; col++) {
                    points[row][col] = new Point(row, col);
                }
            }
        }

        private int height;
        private int width;
        private char[][] board;

        public String solution(int m, int n, String[] board) {
            height = m;
            width = n;
            this.board = new char[height][];
            for (int row = 0; row < height; row++) {
                this.board[row] = board[row].toCharArray();
            }

            SortedMap<Character, List<Point>> tileToPointsMap = new TreeMap<>();
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    char letter = this.board[row][col];
                    if (letter == '.' || letter == '*') {
                        continue;
                    }
                    Character tile = letter;
                    List<Point> tilePoints = tileToPointsMap.get(tile);
                    if (tilePoints == null) {
                        tilePoints = new ArrayList<>();
                        tileToPointsMap.put(tile, tilePoints);
                    }
                    tilePoints.add(points[row][col]);
                }
            }

            StringBuilder answer = new StringBuilder();
            do {
                Character removedTile = null;
                for (Map.Entry<Character, List<Point>> tileToPoints : tileToPointsMap.entrySet()) {
                    List<Point> tilePoints = tileToPoints.getValue();
                    boolean connectable = isConnectable(
                            tileToPoints.getKey(),
                            tilePoints.get(0),
                            tilePoints.get(1));
                    if (connectable) {
                        for (Point point : tilePoints) {
                            this.board[point.row][point.col] = '.';
                        }
                        removedTile = tileToPoints.getKey();
                        break;
                    }
                }
                if (removedTile == null) {
                    return "IMPOSSIBLE";
                }
                answer.append(removedTile);
                tileToPointsMap.remove(removedTile);
            } while (!tileToPointsMap.isEmpty());
            return answer.toString();
        }

        private boolean isConnectable(
                char tile,
                Point beginPoint,
                Point targetPoint) {
            Queue<Moment> queue = new ArrayDeque<>();
            for (int[] direction : DIRECTIONS) {
                int newRow = beginPoint.row + direction[0];
                int newCol = beginPoint.col + direction[1];
                if (isOutOfBounds(newRow, newCol)) {
                    continue;
                }
                char newTile = board[newRow][newCol];
                if (newTile == tile) {
                    return true;
                }
                if (newTile != '.') {
                    continue;
                }
                queue.add(new Moment(points[newRow][newCol], direction));
            }

            while (!queue.isEmpty()) {
                Moment moment = queue.remove();
                Point point = moment.point;
                // 한 번 꺾었으면 무조건 두 번째 방향으로 진행
                int[] secondDirection = moment.secondDirection;
                if (secondDirection != null) {
                    int newRow = point.row + secondDirection[0];
                    int newCol = point.col + secondDirection[1];
                    if (isOutOfBounds(newRow, newCol)) {
                        continue;
                    }
                    char newTile = board[newRow][newCol];
                    if (newTile == tile) {
                        return true;
                    }
                    if (newTile != '.') {
                        continue;
                    }
                    Point newPoint = points[newRow][newCol];
                    queue.add(new Moment(newPoint, moment.firstDirection, secondDirection));
                    continue;
                }
                // 아직 안 꺾은 경우
                int[] firstDirection = moment.firstDirection;
                for (int[] direction : DIRECTIONS) {
                    // 처음 방향 반대로는 못 꺾음
                    if (firstDirection[0] + direction[0] == 0 && firstDirection[1] + direction[1] == 0) {
                        continue;
                    }
                    int newRow = point.row + direction[0];
                    int newCol = point.col + direction[1];
                    if (isOutOfBounds(newRow, newCol)) {
                        continue;
                    }
                    char newTile = board[newRow][newCol];
                    if (newTile == tile) {
                        return true;
                    }
                    if (newTile != '.') {
                        continue;
                    }
                    if (Arrays.equals(firstDirection, direction)) {
                        queue.add(new Moment(points[newRow][newCol], firstDirection));
                    } else {
                        queue.add(new Moment(points[newRow][newCol], firstDirection, direction));
                    }
                }
            }
            return false;
        }

        private boolean isOutOfBounds(int row, int col) {
            return row < 0 || height <= row
                    || col < 0 || width <= col;
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

        private static class Moment {

            public final Point point;
            public final int[] firstDirection;
            public final int[] secondDirection;

            public Moment(Point point, int[] firstDirection) {
                this(point, firstDirection, null);
            }

            public Moment(Point point, int[] firstDirection, int[] secondDirection) {
                this.point = point;
                this.firstDirection = firstDirection;
                this.secondDirection = secondDirection;
            }

        }

    }

}
