import java.util.*;

public class PG_20260713_389630_격자_뒤집기_미로 {

    class Solution {

        int height;
        int width;
        int[][][] grid;
        int flipCost;
        int answer = Integer.MIN_VALUE;

        void init(int[][] visible, int[][] hidden, int k) {
            height = visible.length;
            width = visible[0].length;
            grid = new int[][][] { visible, hidden };
            // for (int row = 0; row < height; row++) {
            //     for (int flipped = 0; flipped < 2; flipped++) {
            //         System.out.print(Arrays.toString(grid[flipped][row]) + "\t\t");
            //     }
            //     System.out.println();
            // }
            flipCost = k;
        }

        public int solution(int[][] visible, int[][] hidden, int k) {
            init(visible, hidden, k);
            int[] rowFlipped = new int[height];
            backtrack(rowFlipped, 0, 0);
            return answer;
        }

        void backtrack(int[] rowFlipped, int depth, int costSum) {
            if (rowFlipped.length == depth) {
                computeAnswer(rowFlipped, costSum);
                return;
            }
            backtrack(rowFlipped, depth + 1, costSum);
            rowFlipped[depth] = 1 - rowFlipped[depth];
            backtrack(rowFlipped, depth + 1, costSum + flipCost);
            rowFlipped[depth] = 1 - rowFlipped[depth];
        }

        void computeAnswer(int[] rowFlipped, int costSum) {
            // String ln = System.lineSeparator();
            // System.out.println(ln + "rowFlipped: " + Arrays.toString(rowFlipped));
            int result = -costSum;
            // 행 개수, 열 개수 모두 짝수일 때는 셀 하나 빼곤 모두 방문 가능
            // 모든 col을 순회하며
            // col을 뒤집은 경우의 셀의 합과
            // 안 뒤집은 경우의 셀의 합 중 큰 걸(maxSum) result에 누적하는데
            // 위에서 말한 방문 못 하는 셀이 해당 col에 포함될 수도 있음
            // (이때의 셀의 합은 maxSumWithHole)
            // 만약 포함된다면 maxSum 대신 maxSumWithHole을 누적했어야 함
            // 이렇게 maxSum을 maxSumWithHole로 대체하기 위한 값을 replacement에 저장
            // 식으로 나타내면 replacement = -maxSum + maxSumWithHole
            // 이 replacement를 마지막에 result에 더해줘야 함
            // result를 최대로 만들려면 replacement의 최대를 알아야 함
            // col을 순회하며 replacement의 최댓값을 maxReplacement에 갱신
            // 행 개수, 열 개수 모두 짝수일 때만 유효
            int maxReplacement = Integer.MIN_VALUE;
            for (int col = 0; col < width; col++) {
                // System.out.println("col: " + col);
                int[] sums = { 0, -flipCost };
                // 현재 col의 셀 중 최솟값을 저장
                // 행 개수, 열 개수 모두 짝수일 때만 유효
                int[] minCells = { Integer.MAX_VALUE, Integer.MAX_VALUE };
                for (int row = 0; row < height; row++) {
                    for (int colFlipped = 0; colFlipped <= 1; colFlipped++) {
                        int cellFlipped = (rowFlipped[row] + colFlipped) % 2;
                        int cell = grid[cellFlipped][row][col];
                        sums[colFlipped] += cell;
                        // 행 개수, 열 개수 모두 짝수일 때만 유효한 연산
                        if ((row + col) % 2 == 1) {
                            minCells[colFlipped] = Math.min(minCells[colFlipped], cell);
                        }
                    }
                }
                int maxSum = Math.max(sums[0], sums[1]);
                result += maxSum;
                // 행 개수, 열 개수 모두 짝수일 때만 유효한 연산
                // 현재 col이 셀 하나를 제외하는 col인 경우
                // maxSum 대신 maxSumWithHole로 대체해야 함
                int maxSumWithHole = Math.max(sums[0] - minCells[0], sums[1] - minCells[1]);
                int replacement = maxSumWithHole - maxSum;
                maxReplacement = Math.max(maxReplacement, replacement);
                // System.out.println(
                //         "sums:\t\t" + Arrays.toString(sums) + ln
                //         + "minCells:\t" + Arrays.toString(minCells) + ln
                //         + "sumWithHoles:\t[" + (sums[0] - minCells[0]) + ", " + (sums[1] - minCells[1]) + "]" + ln
                //         + "replacement: " + replacement + ln
                //         + "maxReplacement: " + maxReplacement + ln
                //         + "result: " + result);
            }
            // 행 개수, 열 개수 모두 짝수일 때만 유효한 변수, 연산들은
            // 행 개수, 열 개수 모두 짝수가 아닐 때 아래 if 문에서 무효화
            if (height % 2 == 1 || width % 2 == 1) {
                maxReplacement = 0;
            }
            answer = Math.max(answer, result + maxReplacement);
            // System.out.println("answer: " + answer);
        }

    }

}
