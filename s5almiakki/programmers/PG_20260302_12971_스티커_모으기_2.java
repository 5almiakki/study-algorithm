
public class PG_20260302_12971_스티커_모으기_2 {

    class Solution {

        public int solution(int sticker[]) {
            switch (sticker.length) {
                case 1:
                    return sticker[0];
                case 2:
                    return Math.max(sticker[0], sticker[1]);
                case 3:
                    return Math.max(sticker[0],
                            Math.max(sticker[1], sticker[2]));
            }

            // 첫 칸을 쓴 경우
            int[] dpUsingFirstCell = new int[sticker.length - 1];
            // 첫 칸을 안 쓴 경우
            int[] dpNotUsingFirstCell = new int[sticker.length];

            dpUsingFirstCell[0] = sticker[0];
            dpNotUsingFirstCell[1] = sticker[1];
            dpUsingFirstCell[2] = sticker[2] + dpUsingFirstCell[0];
            dpNotUsingFirstCell[2] = sticker[2] + dpNotUsingFirstCell[0];
            for (int i = 3; i < dpUsingFirstCell.length; i++) {
                dpUsingFirstCell[i] = sticker[i]
                        + Math.max(dpUsingFirstCell[i - 2], dpUsingFirstCell[i - 3]);
                dpNotUsingFirstCell[i] = sticker[i]
                        + Math.max(dpNotUsingFirstCell[i - 2], dpNotUsingFirstCell[i - 3]);
            }
            int lastIdx = sticker.length - 1;
            dpNotUsingFirstCell[dpNotUsingFirstCell.length - 1] = sticker[lastIdx]
                    + Math.max(dpNotUsingFirstCell[lastIdx - 2], dpNotUsingFirstCell[lastIdx - 3]);

            // System.out.println(Arrays.toString(dpUsingFirstCell));
            // System.out.println(Arrays.toString(dpNotUsingFirstCell));

            return Math.max(
                    Math.max(
                            dpUsingFirstCell[dpUsingFirstCell.length - 1],
                            dpNotUsingFirstCell[dpNotUsingFirstCell.length - 1]),
                    Math.max(
                            dpUsingFirstCell[dpUsingFirstCell.length - 2],
                            dpNotUsingFirstCell[dpNotUsingFirstCell.length - 2]));
        }

    }

}
