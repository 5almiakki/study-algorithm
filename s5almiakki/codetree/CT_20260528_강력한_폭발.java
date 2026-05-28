import java.io.*;
import java.util.*;

public class CT_20260528_강력한_폭발 {

    public class Main {

        static final int[][][] DELTAS = {
                { { -2, 0 }, { -1, 0 }, { 0, 0 }, { 1, 0 }, { 2, 0 } },
                { { -1, 0 }, { 0, -1 }, { 0, 0 }, { 0, 1 }, { 1, 0 } },
                { { -1, -1 }, { -1, 1 }, { 0, 0 }, { 1, -1 }, { 1, 1 } }
        };

        static int answer = 0;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int size = Integer.parseInt(br.readLine());
            List<int[]> bombPoints = new ArrayList<>();
            for (int row = 0; row < size; row++) {
                String[] line = br.readLine().split(" ");
                for (int col = 0; col < size; col++) {
                    if ("1".equals(line[col])) {
                        bombPoints.add(new int[] { row, col });
                    }
                }
            }
            dfs(bombPoints, new int[bombPoints.size()], 0, size);
            System.out.print(answer);
        }

        static void dfs(List<int[]> bombPoints, int[] types, int depth, int size) {
            if (types.length == depth) {
                compute(bombPoints, types, size);
                return;
            }
            for (int type = 0; type < 3; type++) {
                types[depth] = type;
                dfs(bombPoints, types, depth + 1, size);
            }
        }

        static void compute(List<int[]> bombPoints, int[] types, int size) {
            int count = 0;
            boolean[][] visited = new boolean[size][size];
            for (int i = 0; i < types.length; i++) {
                int[] bombPoint = bombPoints.get(i);
                for (int[] delta : DELTAS[types[i]]) {
                    int row = bombPoint[0] + delta[0];
                    int col = bombPoint[1] + delta[1];
                    if (!isOutOfBounds(size, row, col) && !visited[row][col]) {
                        visited[row][col] = true;
                        count++;
                    }
                }
            }
            if (answer < count) {
                answer = count;
            }
        }

        static boolean isOutOfBounds(int size, int row, int col) {
            return row < 0 || size <= row
                    || col < 0 || size <= col;
        }
    }

}
