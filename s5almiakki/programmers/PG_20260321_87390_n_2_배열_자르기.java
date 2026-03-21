
public class PG_20260321_87390_n_2_배열_자르기 {

    class Solution {

        public int[] solution(int n, long left, long right) {
            int[] answer = new int[(int) (right - left + 1L)];
            int idx = 0;
            for (long l = left; l <= right; l++) {
                int row = (int) (l / n);
                int col = (int) (l % n);
                // System.out.println("row=" + row + " col=" + col);
                answer[idx] = Math.max(row + 1, col + 1);
                idx++;
            }
            return answer;
        }

    }

}
