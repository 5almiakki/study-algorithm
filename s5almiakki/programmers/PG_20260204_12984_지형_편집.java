import java.util.*;

public class PG_20260204_12984_지형_편집 {

    public class Solution {

        public long solution(int[][] land, int P, int Q) {
            long addCost = P;
            long removeCost = Q;

            long removedBlockCount = 0L;
            int length = land.length * land.length;
            long[] linearLand = new long[length];
            for (int row = 0; row < land.length; row++) {
                for (int col = 0; col < land[row].length; col++) {
                    long height = land[row][col];
                    linearLand[row * land.length + col] = height;
                    removedBlockCount += height;
                }
            }
            Arrays.sort(linearLand);

            removedBlockCount -= linearLand[0] * length;
            long addedBlockCount = 0L;
            long answer = addedBlockCount * addCost + removedBlockCount * removeCost;
            long prevHeight = linearLand[0];
            for (int i = 1; i < linearLand.length; i++) {
                if (linearLand[i] == prevHeight) {
                    continue;
                }

                long height = linearLand[i] - prevHeight;
                addedBlockCount += height * (long) i;
                removedBlockCount -= height * (long) (length - i);
                answer = Math.min(answer, addedBlockCount * addCost + removedBlockCount * removeCost);

                prevHeight = linearLand[i];
            }

            return answer;
        }

    }

}
