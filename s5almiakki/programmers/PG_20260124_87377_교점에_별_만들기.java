import java.util.*;

public class PG_20260124_87377_교점에_별_만들기 {

    class Solution {

        public String[] solution(int[][] line) {
            Map<Integer, Set<Integer>> yToXMap = new HashMap<>();

            int minX = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (int[] line1 : line) {
                long a = line1[0];
                long b = line1[1];
                long e = line1[2];
                for (int[] line2 : line) {
                    long c = line2[0];
                    long d = line2[1];
                    long divisor = (long) (a * d) - (long) (b * c);
                    if (divisor == 0L) {
                        continue;
                    }
                    long f = line2[2];
                    long xDividend = b * f - e * d;
                    long yDividend = e * c - a * f;
                    if (xDividend % divisor != 0L || yDividend % divisor != 0L) {
                        continue;
                    }
                    int y = (int) (yDividend / divisor);
                    Integer yKey = y;
                    Set<Integer> xs = yToXMap.get(yKey);
                    if (xs == null) {
                        xs = new HashSet<>();
                        yToXMap.put(yKey, xs);
                    }
                    int x = (int) (xDividend / divisor);
                    xs.add(x);
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }

            int height = maxY - minY + 1;
            int width = maxX - minX + 1;
            char[][] grid = new char[height][width];
            for (char[] row : grid) {
                Arrays.fill(row, '.');
            }
            for (Map.Entry<Integer, Set<Integer>> entry : yToXMap.entrySet()) {
                int y = entry.getKey();
                for (int x : entry.getValue()) {
                    grid[maxY - y][x - minX] = '*';
                }
            }

            String[] answer = new String[height];
            for (int row = 0; row < height; row++) {
                answer[row] = new String(grid[row]);
            }
            return answer;
        }

    }

}
