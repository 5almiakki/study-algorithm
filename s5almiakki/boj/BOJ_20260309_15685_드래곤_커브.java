import java.io.*;
import java.util.*;

public class BOJ_20260309_15685_드래곤_커브 {

    public static class Main {

        static final int[][] DELTAS = {
                { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 }
        };

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean[][] grid = new boolean[101][101];
            int dragonCurveCount = Integer.parseInt(br.readLine());
            for (int i = 0; i < dragonCurveCount; i++) {
                String[] input = br.readLine().split(" ");
                int beginRow = Integer.parseInt(input[1]);
                int beginCol = Integer.parseInt(input[0]);
                int beginDirection = Integer.parseInt(input[2]);
                int generation = Integer.parseInt(input[3]);
                Point beginPoint = new Point(beginRow, beginCol);
                Point lastPoint = new Point(
                        beginRow + DELTAS[beginDirection][0],
                        beginCol + DELTAS[beginDirection][1]);
                Set<Point> dragonCurvePoints = new HashSet<>();
                dragonCurvePoints.add(beginPoint);
                dragonCurvePoints.add(lastPoint);

                //System.out.println(dragonCurvePoints);
                for (int j = 0; j < generation; j++) {
                    Set<Point> newPoints = new HashSet<>();
                    for (Point point : dragonCurvePoints) {
                        newPoints.add(rotate(lastPoint, point));
                    }
                    dragonCurvePoints.addAll(newPoints);
                    lastPoint = rotate(lastPoint, beginPoint);
                    //System.out.println(dragonCurvePoints);
                }

                for (Point point : dragonCurvePoints) {
                    grid[point.row][point.col] = true;
                }
            }

            int answer = 0;
            for (int row = 0; row < 100; row++) {
                for (int col = 0; col < 100; col++) {
                    if (grid[row][col] && grid[row][col + 1]
                            && grid[row + 1][col] && grid[row + 1][col + 1]) {
                        //System.out.println(row + " " + col);
                        answer++;
                    }
                }
            }
            System.out.print(answer);
        }

        static Point rotate(Point axis, Point point) {
            int deltaRow = axis.row - point.row;
            int deltaCol = axis.col - point.col;
            return new Point(axis.row - deltaCol, axis.col + deltaRow);
        }

        static class Point {

            final int row;
            final int col;

            private final int hashCode;
            private final String s;

            Point(int row, int col) {
                this.row = row;
                this.col = col;

                hashCode = 31 * Integer.hashCode(row) + Integer.hashCode(col);
                s = new StringBuilder().append('(').append(row).append(',').append(col).append(')').toString();
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

    }

}
