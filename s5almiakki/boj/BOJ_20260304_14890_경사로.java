import java.io.*;
//import java.util.Arrays;

public class BOJ_20260304_14890_경사로 {

    public static class Main {

        //static final String LN = System.lineSeparator();
        //
        //static StringBuilder log = new StringBuilder();

        public static void main(String[] args) throws IOException {
            try (InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr)) {
                String[] input = br.readLine().split(" ");
                int gridSize = Integer.parseInt(input[0]);
                int slopeLength = Integer.parseInt(input[1]);
                int[][] grid1 = new int[gridSize][gridSize];
                int[][] grid2 = new int[gridSize][gridSize];
                for (int row = 0; row < gridSize; row++) {
                    input = br.readLine().split(" ");
                    for (int col = 0; col < gridSize; col++) {
                        int cell = Integer.parseInt(input[col]);
                        grid1[row][col] = cell;
                        grid2[col][row] = cell;
                    }
                }

                //for (int row = 0; row < gridSize; row++) {
                //    log.append(Arrays.toString(grid1[row]) + ' ' + Arrays.toString(grid2[row])).append(LN);
                //}

                int answer = 0;
                for (int row = 0; row < gridSize; row++) {
                    //log.append(row);
                    if (canGo(slopeLength, grid1[row])) {
                        //log.append(" grid1");
                        answer++;
                    }
                    if (canGo(slopeLength, grid2[row])) {
                        //log.append(" grid2");
                        answer++;
                    }
                    //log.append(LN);
                }

                //System.out.println(log);
                System.out.print(answer);
            }
        }

        static boolean canGo(int slopeLength, int[] way) {
            int pitLength = slopeLength << 1;
            int lastDownIdx = -slopeLength;
            int lastUpIdx = 0;
            for (int i = 1; i < way.length; i++) {
                if (Math.abs(way[i] - way[i - 1]) > 1) {
                    return false;
                }
                if (way[i - 1] > way[i]) {
                    if (i - lastDownIdx < slopeLength) {
                        return false;
                    }
                    lastDownIdx = i;
                } else if (way[i - 1] < way[i]) {
                    if (i - lastUpIdx < slopeLength
                            || i - lastDownIdx < pitLength) {
                        return false;
                    }
                    lastUpIdx = i;
                }
            }

            if (lastDownIdx + slopeLength > way.length) {
                return false;
            }
            return true;
        }

    }

}
