import java.util.*;

public class PG_20260329_42627_디스크_컨트롤러 {

    class Solution {

        public int solution(int[][] jobs) {
            Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
            Queue<Job> queue = new ArrayDeque<>();
            for (int i = 0; i < jobs.length; i++) {
                queue.add(new Job(i, jobs[i][0], jobs[i][1]));
            }

            PriorityQueue<Job> pq = new PriorityQueue<>();
            int now = 0;
            int answer = 0;
            for (;;) {
                while (!queue.isEmpty()) {
                    Job job = queue.peek();
                    if (pq.isEmpty()) {
                        now = Math.max(now, job.requestTime);
                    } else if (now < job.requestTime) {
                        break;
                    }
                    pq.add(queue.remove());
                }
                if (!pq.isEmpty()) {
                    Job job = pq.remove();
                    now += job.duration;
                    answer += now - job.requestTime;
                    continue;
                }
                if (queue.isEmpty()) {
                    break;
                }
            }
            return answer / jobs.length;
        }

        static class Job implements Comparable<Job> {

            final int idx;
            final int requestTime;
            final int duration;

            Job(int idx, int requestTime, int duration) {
                this.idx = idx;
                this.requestTime = requestTime;
                this.duration = duration;
            }

            @Override
            public int compareTo(Job o) {
                if (duration != o.duration) {
                    return Integer.compare(duration, o.duration);
                }
                if (requestTime != o.requestTime) {
                    return Integer.compare(requestTime, o.requestTime);
                }
                return Integer.compare(idx, o.idx);
            }

        }

    }

}
