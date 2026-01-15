import java.util.*;

public class PG_20260115_1838_몸짱_트레이너_라이언의_고민 {

    class Solution {

        private Point[][] points;

        public int solution(int n, int m, int[][] timetable) {
            // Point 2차원 배열 초기화
            points = new Point[n][n];
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    points[row][col] = new Point(row, col);
                }
            }

            return computeDistance(computeMaxUserCount(timetable));
        }

        // timetable의 시간 압축을 위한 맵 생성
        private Map<Integer, Integer> makeTimeCompressionMap(int[][] timetable) {
            SortedSet<Integer> timeCompressionSet = new TreeSet<>();
            for (int[] times : timetable) {
                for (int time : times) {
                    timeCompressionSet.add(time);
                }
            }
            Map<Integer, Integer> timeCompressionMap = new HashMap<>();
            for (Integer time : timeCompressionSet) {
                timeCompressionMap.put(time, timeCompressionMap.size());
            }
            return timeCompressionMap;
        }

        // 누적합으로 최대 동시 사용자 수를 구하는 메서드
        private int computeMaxUserCount(int[][] timetable) {
            Map<Integer, Integer> timeCompressionMap = makeTimeCompressionMap(timetable);
            int[] accumulations = new int[timeCompressionMap.size() + 1];
            for (int[] usageDuration : timetable) {
                int compressedBeginTime = timeCompressionMap.get(usageDuration[0]);
                int compressedEndTime = timeCompressionMap.get(usageDuration[1]);
                accumulations[compressedBeginTime]++;
                accumulations[compressedEndTime + 1]--;
            }
            int maxUserCount = 0;
            for (int i = 1; i < accumulations.length; i++) {
                accumulations[i] += accumulations[i - 1];
                maxUserCount = Math.max(maxUserCount, accumulations[i]);
            }
            return maxUserCount;
        }

        // 동시 사용자 수가 주어지면 가장 멀리 떨어뜨린 거리를 구하는 메서드
        private int computeDistance(int maxUserCount) {
            if (maxUserCount <= 1) {
                return 0;
            }

            // 거리를 최대 거리(락커룸 크기 * 2)에서 1씩 줄이며 해당 거리가 가능한지 검사
            for (int distance = points.length << 1; distance > 1; distance--) {
                // System.out.println("distance=" + distance);
                Set<Set<Point>> visitedMoments = new HashSet<>(); // 백트래킹용 기록 배열
                SortedSet<Point> userPoints = new TreeSet<>(); // 사용자가 있는 좌표
                userPoints.add(points[0][0]); // 맨 왼쪽 맨 위 좌표에는 무조건 배치
                if (canPutAll(visitedMoments, userPoints, points[0][0], maxUserCount - 1, distance)) {
                    return distance;
                }
            }
            return 1;
        }

        // 주어진 거리 이상으로 주어진 수의 사용자를 배치할 수 있는지를 백트래킹으로 확인 후 반환하는 메서드
        private boolean canPutAll(
                Set<Set<Point>> visitedMoments,
                SortedSet<Point> userPoints,
                Point prevUserPoint,
                int remainingUserCount,
                int targetDistance) {
            // System.out.println(userPoints);

            // 현재 배치를 검사한 적 있는 경우 더 이상 검사 안 함
            if (visitedMoments.contains(userPoints)) {
                return false;
            }
            visitedMoments.add(userPoints);

            // 사용자를 다 배치한 경우 참 반환
            if (remainingUserCount == 0) {
                return true;
            }

            // 모든 좌표를 순회하며 새로 사용자를 배치할 좌표 탐색
            for (Point[] row : points) {
                for (Point newPoint : row) {
                    boolean canPut = true;
                    for (Point point : userPoints) {
                        if (point.computeDistanceTo(newPoint) < targetDistance) {
                            canPut = false;
                            break;
                        }
                    }
                    if (!canPut) {
                        continue;
                    }
                    userPoints.add(newPoint);
                    boolean canPutAll = canPutAll(visitedMoments, userPoints, newPoint, remainingUserCount - 1, targetDistance);
                    userPoints.remove(newPoint);
                    if (canPutAll) {
                        return true;
                    }
                }
            }

            return false;
        }

        private static class Point implements Comparable<Point> {

            public final int row;
            public final int col;

            private final int hashCode;

            public Point(int row, int col) {
                this.row = row;
                this.col = col;

                hashCode = Objects.hash(row, col);
            }

            public int computeDistanceTo(Point other) {
                return Math.abs(row - other.row) + Math.abs(col - other.col);
            }

            @Override
            public int compareTo(Point o) {
                if (row != o.row) {
                    return Integer.compare(row, o.row);
                }
                return Integer.compare(col, o.col);
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
                return "" + '(' + row + ',' + col + ')';
            }

        }

    }

}
