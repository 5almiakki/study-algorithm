import java.util.*;

public class PG_20260512_49994_방문_길이 {

    class Solution {

        public int solution(String dirs) {
            int alphabetCount = 'U' - 'D' + 1;
            int[] dx = new int[alphabetCount];
            int[] dy = new int[alphabetCount];
            dx['L' - 'D'] = -1;
            dx['R' - 'D'] = 1;
            dy['U' - 'D'] = -1;
            dy['D' - 'D'] = 1;
            Set<Edge> visitedEdges = new HashSet<>();
            int x = 0;
            int y = 0;
            int length = dirs.length();
            for (int i = 0; i < length; i++) {
                char direction = dirs.charAt(i);
                int newX = x + dx[direction - 'D'];
                int newY = y + dy[direction - 'D'];
                if (isOutOfBounds(newX, newY)) {
                    continue;
                }
                Edge e = new Edge(new Point(x, y), new Point(newX, newY));
                visitedEdges.add(e);
                x = newX;
                y = newY;
            }
            return visitedEdges.size();
        }

        boolean isOutOfBounds(int x, int y) {
            return x < -5 || 5 < x
                    || y < -5 || 5 < y;
        }

        static class Point {

            final int x;
            final int y;
            private final int hashCode;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
                hashCode = 31 * Integer.hashCode(x) + Integer.hashCode(y);
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
                return x == other.x && y == other.y;
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

        }

        static class Edge {

            private final Set<Point> points;
            private final int hashCode;

            Edge(Point p1, Point p2) {
                points = new HashSet<>();
                points.add(p1);
                points.add(p2);
                hashCode = points.hashCode();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof Edge)) {
                    return false;
                }
                Edge other = (Edge) o;
                return points.equals(other.points);
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

        }

    }

}
