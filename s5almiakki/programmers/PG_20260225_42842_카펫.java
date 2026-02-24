
public class PG_20260225_42842_카펫 {

    class Solution {

        public int[] solution(int brown, int yellow) {
            int area = brown + yellow;
            int[] answer = new int[2];
            for (int height = 1;; height++) {
                if (area % height != 0) {
                    continue;
                }
                int width = area / height;
                if (height > width) {
                    break;
                }
                int currentBrown = ((height + width) << 1) - 4;
                if (currentBrown == brown) {
                    answer[0] = width;
                    answer[1] = height;
                    break;
                }
            }
            return answer;
        }

    }

}
