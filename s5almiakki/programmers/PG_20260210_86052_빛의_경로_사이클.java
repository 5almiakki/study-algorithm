import java.util.*;

public class PG_20260210_86052_빛의_경로_사이클 {

    class Solution {

        static final int[][] DELTAS = {
                { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
        };

        int height;
        int width;
        String[] grid;

        public int[] solution(String[] grid) {
            // System.out.println("grid");
            // for (String row : grid) {
            //     System.out.println(row);
            // }
            // System.out.println();

            height = grid.length;
            width = grid[0].length();
            this.grid = grid;

            // { direction, row, col, costSum }
            List<Integer> answer = new ArrayList<>();
            boolean[][][] visited = new boolean[4][height][width];
            for (int direction = 0; direction < 4; direction++) {
                for (int row = 0; row < height; row++) {
                    for (int col = 0; col < width; col++) {
                        if (!visited[direction][row][col]) {
                            answer.add(travel(visited, direction, row, col));
                        }
                    }
                }
            }
            return answer.stream().mapToInt(i -> i).sorted().toArray();
        }

        int travel(boolean[][][] visited, int beginDirection, int beginRow, int beginCol) {
            int direction = beginDirection;
            int row = beginRow;
            int col = beginCol;
            // System.out.println("direction=" + direction + " row=" + row + " col=" + col);
            visited[direction][row][col] = true;
            int costSum = 0;
            do {
                row = (row + DELTAS[direction][0] + height) % height;
                col = (col + DELTAS[direction][1] + width) % width;
                switch (grid[row].charAt(col)) {
                    case 'L':
                        direction = (direction + 3) % 4;
                        break;
                    case 'R':
                        direction = (direction + 1) % 4;
                        break;
                }
                // System.out.println("direction=" + direction + " row=" + row + " col=" + col);
                visited[direction][row][col] = true;
                costSum++;
            } while (direction != beginDirection || row != beginRow || col != beginCol);
            // System.out.println("finished");
            return costSum;
        }

    }

}
