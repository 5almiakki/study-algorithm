import java.util.*;

public class PG_20260201_12927_야근_지수 {

    class Solution {

        public long solution(int n, int[] works) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int work : works) {
                pq.add(work);
            }

            while (n > 0 && !pq.isEmpty()) {
                int work = pq.remove();
                int amt = 0;
                if (pq.isEmpty()) {
                    amt = Math.min(n, work);
                } else {
                    int otherWork = pq.peek();
                    if (work == otherWork) {
                        amt = 1;
                    } else {
                        amt = Math.min(n, work - otherWork);
                    }
                }
                work -= amt;
                n -= amt;
                if (work > 0) {
                    pq.add(work);
                }
            }

            long answer = 0;
            while (!pq.isEmpty()) {
                long work = pq.remove();
                answer += work * work;
            }
            return answer;
        }

    }

}
