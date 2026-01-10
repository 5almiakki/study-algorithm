import java.util.*;

public class PG_20260108_혼자서_하는_틱택토 {

    class Solution1 {

        private static final Point[][] points = new Point[3][3];
        private static final Set<Set<Point>> winnerPoints;

        static {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    points[row][col] = new Point(row, col);
                }
            }

            winnerPoints = Set.of(
                    Set.of(points[0][0], points[0][1], points[0][2]),
                    Set.of(points[1][0], points[1][1], points[1][2]),
                    Set.of(points[2][0], points[2][1], points[2][2]),

                    Set.of(points[0][0], points[1][0], points[2][0]),
                    Set.of(points[0][1], points[1][1], points[2][1]),
                    Set.of(points[0][2], points[1][2], points[2][2]),

                    Set.of(points[0][0], points[1][1], points[2][2]),
                    Set.of(points[0][2], points[1][1], points[2][0]));
        }

        public int solution(String[] board) {
            Set<Point> oPoints = new HashSet<>();
            Set<Point> xPoints = new HashSet<>();
            for (int row = 0; row < 3; row++) {
                String s = board[row];
                for (int col = 0; col < 3; col++) {
                    switch (s.charAt(col)) {
                        case 'O':
                            oPoints.add(points[row][col]);
                            break;
                        case 'X':
                            xPoints.add(points[row][col]);
                            break;
                    }
                }
            }

            boolean isOWinner = false;
            boolean isXWinner = false;
            for (Set<Point> points : winnerPoints) {
                if (oPoints.containsAll(points)) {
                    isOWinner = true;
                }
                if (xPoints.containsAll(points)) {
                    isXWinner = true;
                }
            }
            int oCount = oPoints.size();
            int xCount = xPoints.size();
            if (isOWinner && oCount - 1 != xCount
                    || isXWinner && xCount != oCount
                    || oCount < xCount || xCount + 1 < oCount) {
                return 0;
            }
            return 1;
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

    class Solution2 {

        private static final Point[][] points = new Point[3][3];
        private static final Set<Set<Point>> winnerPoints;

        static {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    points[row][col] = new Point(row, col);
                }
            }

            winnerPoints = Set.of(
                    Set.of(points[0][0], points[0][1], points[0][2]),
                    Set.of(points[1][0], points[1][1], points[1][2]),
                    Set.of(points[2][0], points[2][1], points[2][2]),

                    Set.of(points[0][0], points[1][0], points[2][0]),
                    Set.of(points[0][1], points[1][1], points[2][1]),
                    Set.of(points[0][2], points[1][2], points[2][2]),

                    Set.of(points[0][0], points[1][1], points[2][2]),
                    Set.of(points[0][2], points[1][1], points[2][0]));
        }

        public int solution(String[] board) {
            Set<Point> oPoints = new HashSet<>();
            Set<Point> xPoints = new HashSet<>();
            for (int row = 0; row < 3; row++) {
                String s = board[row];
                for (int col = 0; col < 3; col++) {
                    switch (s.charAt(col)) {
                        case 'O':
                            oPoints.add(points[row][col]);
                            break;
                        case 'X':
                            xPoints.add(points[row][col]);
                            break;
                    }
                }
            }

            int oCount = oPoints.size();
            int xCount = xPoints.size();
            boolean isOWinner = false;
            boolean isXWinner = false;
            for (Set<Point> points : winnerPoints) {
                if (oPoints.containsAll(points)) {
                    isOWinner = true;
                }
                if (xPoints.containsAll(points)) {
                    isXWinner = true;
                }
            }

            if (oCount == xCount && !isOWinner
                    || oCount == xCount + 1 && !isXWinner) {
                return 1;
            }
            return 0;
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
