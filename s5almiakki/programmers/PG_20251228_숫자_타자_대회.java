import java.util.*;

public class PG_20251228_숫자_타자_대회 {

    class Solution {

        private static final char[][] KEYS = {
                { '1', '2', '3' },
                { '4', '5', '6' },
                { '7', '8', '9' },
                { '*', '0', '#' }
        };
        private static final int[][] DIRECTIONS = {
                { -1, 0, 2 }, { 1, 0, 2 }, { 0, -1, 2 }, { 0, 1, 2 },
                { -1, -1, 3 }, { -1, 1, 3 }, { 1, -1, 3 }, { 1, 1, 3 }
        };
        private static final int[][] COSTS = new int[10][10];

        static {
            for (int[] d : COSTS) {
                Arrays.fill(d, Integer.MAX_VALUE);
            }

            for (int beginRow = 0; beginRow < KEYS.length; beginRow++) {
                for (int beginCol = 0; beginCol < KEYS[beginRow].length; beginCol++) {
                    char beginLetter = KEYS[beginRow][beginCol];
                    if (!isNum(beginLetter)) {
                        continue;
                    }
                    int beginNum = beginLetter - '0';
                    bfs(beginRow, beginCol, beginNum, COSTS[beginNum]);
                }
            }
        }

        private static void bfs(int beginRow, int beginCol, int beginNum, int[] costs) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[] { beginRow, beginCol, 0 });
            costs[beginNum] = 1;
            do {
                int[] currentPoint = queue.remove();
                for (int[] direction : DIRECTIONS) {
                    int newRow = currentPoint[0] + direction[0];
                    int newCol = currentPoint[1] + direction[1];
                    if (isOutOfBounds(newRow, newCol)) {
                        continue;
                    }
                    char newLetter = KEYS[newRow][newCol];
                    if (!isNum(newLetter)) {
                        continue;
                    }
                    int newNum = newLetter - '0';
                    int newCost = currentPoint[2] + direction[2];
                    if (costs[newNum] <= newCost) {
                        continue;
                    }
                    queue.add(new int[] { newRow, newCol, newCost });
                    costs[newNum] = newCost;
                }
            } while (!queue.isEmpty());
        }

        private static boolean isNum(char letter) {
            return '0' <= letter && letter <= '9';
        }

        private static boolean isOutOfBounds(int row, int col) {
            return row < 0 || KEYS.length <= row
                    || col < 0 || KEYS[row].length <= col;
        }

        public int solution(String numbers) {
            int[] nums = new int[numbers.length()];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = numbers.charAt(i) - '0';
            }

            Map<DepthInfo, Integer> diToCostSumMap;
            Map<DepthInfo, Integer> newDiToCostSumMap = new HashMap<>();
            newDiToCostSumMap.put(new DepthInfo(4, 6), 0);
            for (int newNum : nums) {
                diToCostSumMap = newDiToCostSumMap;
                newDiToCostSumMap = new HashMap<>();
                for (Map.Entry<DepthInfo, Integer> entry : diToCostSumMap.entrySet()) {
                    DepthInfo di = entry.getKey();
                    if (di.leftNum == newNum || di.rightNum == newNum) {
                        DepthInfo newDi = new DepthInfo(di.leftNum, di.rightNum);
                        Integer cost = newDiToCostSumMap.get(newDi);
                        Integer newCost = entry.getValue() + 1;
                        if (cost == null || cost > newCost) {
                            newDiToCostSumMap.put(newDi, newCost);
                        }
                        continue;
                    }

                    DepthInfo newDi = new DepthInfo(newNum, di.rightNum);
                    Integer cost = newDiToCostSumMap.get(newDi);
                    Integer newCost = entry.getValue() + COSTS[di.leftNum][newNum];
                    if (cost == null || cost > newCost) {
                        newDiToCostSumMap.put(newDi, newCost);
                    }
                    newDi = new DepthInfo(di.leftNum, newNum);
                    cost = newDiToCostSumMap.get(newDi);
                    newCost = entry.getValue() + COSTS[newNum][di.rightNum];
                    if (cost == null || cost > newCost) {
                        newDiToCostSumMap.put(newDi, newCost);
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            for (int v : newDiToCostSumMap.values()) {
                answer = Math.min(answer, v);
            }
            return answer;
        }

        private static class DepthInfo {

            public final int leftNum;
            public final int rightNum;

            public DepthInfo(int leftNum, int rightNum) {
                this.leftNum = leftNum;
                this.rightNum = rightNum;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof DepthInfo)) {
                    return false;
                }
                DepthInfo other = (DepthInfo) o;
                return leftNum == other.leftNum && rightNum == other.rightNum;
            }

            @Override
            public int hashCode() {
                return Objects.hash(leftNum, rightNum);
            }

        }

    }

}
