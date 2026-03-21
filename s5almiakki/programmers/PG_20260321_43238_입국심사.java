
public class PG_20260321_43238_입국심사 {

    class Solution {

        public long solution(int n, int[] times) {
            long high = Long.MAX_VALUE;
            long low = 0L;
            long answer = high;
            do {
                long mid = (high + low) >> 1L;
                // System.out.println("high=" + high + " low=" + low + " mid=" + mid);
                long checkedPersonCount = 0L;
                for (int time : times) {
                    checkedPersonCount += mid / (long) time;
                    if (n <= checkedPersonCount) {
                        break;
                    }
                }
                // System.out.println("checkedPersonCount=" + checkedPersonCount);
                // System.out.println();

                if (n <= checkedPersonCount) {
                    answer = mid;
                    high = mid - 1L;
                } else {
                    low = mid + 1L;
                }
            } while (low <= high);

            return answer;
        }

    }

}
