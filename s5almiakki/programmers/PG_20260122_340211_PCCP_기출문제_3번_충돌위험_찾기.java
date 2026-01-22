import java.util.*;

public class PG_20260122_340211_PCCP_기출문제_3번_충돌위험_찾기 {

    class Solution {

        public int solution(int[][] points, int[][] routes) {
            List<Robot> robots = new LinkedList<>();
            for (int[] transitIndices : routes) {
                Queue<Point> transits = new ArrayDeque<>();
                for (int transitIdx : transitIndices) {
                    int[] point = points[transitIdx - 1];
                    // transits.add(grid[point[0]][point[1]]);
                    transits.add(new Point(point[0], point[1]));
                }
                robots.add(new Robot(transits));
            }

            int answer = 0;
            do {
                Map<Point, Integer> pointToVisitCountMap = new HashMap<>();
                Iterator<Robot> it = robots.iterator();
                while (it.hasNext()) {
                    Robot robot = it.next();
                    // System.out.print(robot);
                    Point newPoint = robot.move();
                    // System.out.println(" " + robot);
                    if (newPoint == null) {
                        it.remove();
                        continue;
                    }
                    pointToVisitCountMap.put(newPoint, pointToVisitCountMap.getOrDefault(newPoint, 0) + 1);
                }
                for (Integer visitCount : pointToVisitCountMap.values()) {
                    if (visitCount.compareTo(1) > 0) {
                        answer++;
                    }
                }
                // System.out.print(pointToVisitCountMap);
                // System.out.println(" " + answer);
                // System.out.println();
            } while (!robots.isEmpty());

            return answer;
        }

        private static class Point {

            public final int row;
            public final int col;

            private final int hashCode;
            private final String s;

            public Point(int row, int col) {
                this.row = row;
                this.col = col;

                hashCode = row * 100 + col;
                s = "(" + row + "," + col + ")";
            }

            public Point add(Point point) {
                return add(point.row, point.col);
            }

            public Point add(int row, int col) {
                // return grid[this.row + row][this.col + col];
                return new Point(this.row + row, this.col + col);
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

            @Override
            public String toString() {
                return s;
            }

        }

        private static class Robot {

            private static final Point[] DIRECTIONS = {
                    new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)
            };

            public Point currentPoint;
            public final Queue<Point> transits;

            public Robot(Queue<Point> transits) {
                this.currentPoint = null;
                this.transits = transits;
            }

            public Point move() {
                if (currentPoint == null) {
                    currentPoint = transits.remove();
                    return currentPoint;
                }

                Point destination = transits.peek();
                if (destination.equals(currentPoint)) {
                    transits.remove();
                    if (transits.isEmpty()) {
                        currentPoint = null;
                        return currentPoint;
                    }
                }

                destination = transits.peek();
                int delta = destination.row - currentPoint.row;
                if (delta != 0) {
                    currentPoint = currentPoint.add(DIRECTIONS[delta < 0 ? 0 : 1]);
                    return currentPoint;
                }
                delta = destination.col - currentPoint.col;
                currentPoint = currentPoint.add(DIRECTIONS[delta < 0 ? 2 : 3]);
                return currentPoint;
            }

            @Override
            public String toString() {
                return currentPoint + " " + transits;
            }

        }

    }

}
