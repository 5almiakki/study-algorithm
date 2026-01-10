import java.util.*;

public class PG_20251231_PCCP_기출문제_4번_수레_움직이기 {

    class Solution {

        private static final int[][] SAME_PLACE = { { 0, 0 } };
        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        private int[][] maze;
        private Point[][] points;

        private int answer = Integer.MAX_VALUE;

        public int solution(int[][] maze) {
            this.maze = maze;
            points = new Point[maze.length][maze[0].length];
            Point initialRedPoint = null;
            Point initialBluePoint = null;
            Set<Point> initialVisitedRedPoints = new HashSet<>();
            Set<Point> initialVisitedBluePoints = new HashSet<>();
            Point targetRedPoint = null;
            Point targetBluePoint = null;

            for (int row = 0; row < maze.length; row++) {
                for (int col = 0; col < maze[row].length; col++) {
                    points[row][col] = new Point(row, col);
                    switch (maze[row][col]) {
                        case 1:
                            initialRedPoint = points[row][col];
                            initialVisitedRedPoints.add(initialRedPoint);
                            break;
                        case 2:
                            initialBluePoint = points[row][col];
                            initialVisitedBluePoints.add(initialBluePoint);
                            break;
                        case 3:
                            targetRedPoint = points[row][col];
                            break;
                        case 4:
                            targetBluePoint = points[row][col];
                            break;
                    }
                }
            }

            Queue<Moment> queue = new ArrayDeque<>();
            Map<Moment, Integer> momentToCostSumMap = new HashMap<>();
            Moment initialMoment = new Moment(
                    initialRedPoint, initialBluePoint, initialVisitedRedPoints, initialVisitedBluePoints);
            queue.add(initialMoment);
            momentToCostSumMap.put(initialMoment, 0);
            do {
                Moment moment = queue.remove();
                Point redPoint = moment.redPoint;
                Point bluePoint = moment.bluePoint;
                if (redPoint.equals(targetRedPoint) && bluePoint.equals(targetBluePoint)) {
                    answer = Math.min(answer, momentToCostSumMap.get(moment));
                    continue;
                }

                if (redPoint.equals(targetRedPoint)) {
                    saveIfValid(queue, momentToCostSumMap, moment, SAME_PLACE, DIRECTIONS);
                    continue;
                }
                if (bluePoint.equals(targetBluePoint)) {
                    saveIfValid(queue, momentToCostSumMap, moment, DIRECTIONS, SAME_PLACE);
                    continue;
                }
                saveIfValid(queue, momentToCostSumMap, moment, DIRECTIONS, DIRECTIONS);
            } while (!queue.isEmpty());

            return (answer == Integer.MAX_VALUE) ? 0 : answer;
        }

        private boolean isOutOfBounds(int row, int col) {
            return row < 0 || points.length <= row
                    || col < 0 || points[row].length <= col;
        }

        private void saveIfValid(
                Queue<Moment> queue,
                Map<Moment, Integer> momentToCostSumMap,
                Moment moment,
                int[][] redDirections,
                int[][] blueDirections) {
            int newCostSum = momentToCostSumMap.get(moment) + 1;
            if (newCostSum >= answer) {
                return;
            }

            Point redPoint = moment.redPoint;
            Point bluePoint = moment.bluePoint;
            Set<Point> visitedRedPoints = moment.visitedRedPoints;
            Set<Point> visitedBluePoints = moment.visitedBluePoints;
            for (int[] redDirection : redDirections) {
                int newRedRow = redPoint.row + redDirection[0];
                int newRedCol = redPoint.col + redDirection[1];
                if (isOutOfBounds(newRedRow, newRedCol) || maze[newRedRow][newRedCol] == 5) {
                    continue;
                }
                Point newRedPoint = points[newRedRow][newRedCol];
                if (redDirections != SAME_PLACE && visitedRedPoints.contains(newRedPoint)) {
                    continue;
                }
                for (int[] blueDirection : blueDirections) {
                    int newBlueRow = bluePoint.row + blueDirection[0];
                    int newBlueCol = bluePoint.col + blueDirection[1];
                    if (isOutOfBounds(newBlueRow, newBlueCol) || maze[newBlueRow][newBlueCol] == 5) {
                        continue;
                    }
                    Point newBluePoint = points[newBlueRow][newBlueCol];
                    if (newRedPoint.equals(bluePoint) && newBluePoint.equals(redPoint)
                            || newRedPoint.equals(newBluePoint)
                            || (blueDirections != SAME_PLACE && visitedBluePoints.contains(newBluePoint))) {
                        continue;
                    }
                    Set<Point> newVisitedRedPoints = new HashSet<>(visitedRedPoints);
                    Set<Point> newVisitedBluePoints = new HashSet<>(visitedBluePoints);
                    newVisitedRedPoints.add(newRedPoint);
                    newVisitedBluePoints.add(newBluePoint);
                    Moment newMoment = new Moment(
                            newRedPoint, newBluePoint, newVisitedRedPoints, newVisitedBluePoints);
                    Integer costSum = momentToCostSumMap.get(newMoment);
                    if (costSum == null || costSum.compareTo(newCostSum) > 0) {
                        queue.add(newMoment);
                        momentToCostSumMap.put(newMoment, newCostSum);
                    }
                }
            }
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
                if (!(o instanceof Point)) {
                    return false;
                }
                if (this == o) {
                    return true;
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

            public final Point redPoint;
            public final Point bluePoint;
            public final Set<Point> visitedRedPoints;
            public final Set<Point> visitedBluePoints;

            private final int hashCode;

            public Moment(Point redPoint, Point bluePoint, Set<Point> visitedRedPoints, Set<Point> visitedBluePoints) {
                this.redPoint = redPoint;
                this.bluePoint = bluePoint;
                this.visitedRedPoints = visitedRedPoints;
                this.visitedBluePoints = visitedBluePoints;
                hashCode = Objects.hash(redPoint, bluePoint, visitedRedPoints, visitedBluePoints);
            }

            @Override
            public boolean equals(Object o) {
                if (!(o instanceof Moment)) {
                    return false;
                }
                if (this == o) {
                    return true;
                }
                Moment other = (Moment) o;
                return Objects.equals(redPoint, other.redPoint)
                        && Objects.equals(bluePoint, other.bluePoint)
                        && Objects.equals(visitedRedPoints, other.visitedRedPoints)
                        && Objects.equals(visitedBluePoints, other.visitedBluePoints);
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

        }

    }

}
