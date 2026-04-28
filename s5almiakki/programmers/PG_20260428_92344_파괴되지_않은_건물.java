
public class PG_20260428_92344_파괴되지_않은_건물 {

    class Solution {

        // {
        //     int[][] sums = new int[10 + 1][10 + 1];
        //     int[][] skill = {
        //         { 2, 0, 0, 9, 9, 1 },
        //         { 2, 2, 2, 5, 5, 1 }
        //     };
        //     for (int[] s : skill) {
        //         int beginRow = s[1];
        //         int beginCol = s[2];
        //         int endRow = s[3];
        //         int endCol = s[4];
        //         int degree = s[5];
        //         sums[s[1]][s[2]] = s[5];
        //         sums[s[1]][s[4] + 1] = -s[5];
        //         sums[s[3] + 1][s[2]] = -s[5];
        //     }
        //     StringBuilder sb = new StringBuilder();
        //     String ln = System.lineSeparator();
        //     for (int[] row : sums) {
        //         for (int cell : row) {
        //             sb.append(cell).append(' ');
        //         }
        //         sb.append(ln);
        //     }
        //     sb.append(ln);
        //     for (int row = 0; row < sums.length; row++) {
        //         for (int col = 1; col < sums[row].length; col++) {
        //             sums[row][col] += sums[row][col - 1];
        //         }
        //     }
        //     for (int row = 1; row < sums.length; row++) {
        //         for (int col = 0; col < sums[row].length; col++) {
        //             sums[row][col] += sums[row - 1][col];
        //         }
        //     }
        //     for (int row = 0; row < sums.length; row++) {
        //         for (int col = 0; col < sums[row].length; col++) {
        //             sb.append(sums[row][col]).append(' ');
        //         }
        //         sb.append(ln);
        //     }
        //     System.out.println(sb);
        // }

        public int solution(int[][] board, int[][] skill) {
            // StringBuilder sb = new StringBuilder();
            // String ln = System.lineSeparator();
            int[][] sums = new int[board.length + 1][board[0].length + 1];
            for (int[] s : skill) {
                int degree = (s[0] == 1) ? -s[5] : s[5];
                sums[s[1]][s[2]] += degree;
                sums[s[1]][s[4] + 1] -= degree;
                sums[s[3] + 1][s[2]] -= degree;
                sums[s[3] + 1][s[4] + 1] += degree;
                // for (int row = 0; row < sums.length; row++) {
                //     for (int col = 0; col < sums[row].length; col++) {
                //         sb.append(sums[row][col]).append(' ');
                //     }
                //     sb.append(ln);
                // }
                // sb.append(ln);
            }
            for (int row = 0; row < sums.length; row++) {
                for (int col = 1; col < sums[row].length; col++) {
                    sums[row][col] += sums[row][col - 1];
                }
            }
            for (int row = 1; row < sums.length; row++) {
                for (int col = 0; col < sums[row].length; col++) {
                    sums[row][col] += sums[row - 1][col];
                }
            }
            int answer = 0;
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] + sums[row][col] > 0) {
                        answer++;
                    }
                }
            }
            // for (int row = 0; row < sums.length; row++) {
            //     for (int col = 0; col < sums[row].length; col++) {
            //         sb.append(sums[row][col]).append(' ');
            //     }
            //     sb.append(ln);
            // }
            // System.out.println(sb);
            return answer;
        }

    }

}
