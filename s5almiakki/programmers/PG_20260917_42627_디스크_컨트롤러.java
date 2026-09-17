import java.util.*;

public class PG_20260917_42627_디스크_컨트롤러 {

	class Solution {

		public int solution(int[][] jobs) {
			LinkedList<Job> inputJobs = new LinkedList<>();
			for (int i = 0; i < jobs.length; i++) {
				inputJobs.add(new Job(i, jobs[i][0], jobs[i][1]));
			}
			inputJobs.sort((job1, job2) -> Integer.compare(job1.requestTime, job2.requestTime));
			PriorityQueue<Job> pq = new PriorityQueue<>();
			Job currentJob = null;
			int beginTime = -1;
			int turnaroundTimeSum = 0;
			for (int time = 0;; time++) {
				while (!inputJobs.isEmpty()) {
					Job nextJob = inputJobs.peek();
					if (nextJob.requestTime > time) {
						break;
					}
					pq.add(inputJobs.remove());
				}
				if (currentJob != null) {
					int endTime = beginTime + currentJob.duration;
					if (endTime <= time) {
						turnaroundTimeSum += endTime - currentJob.requestTime;
						currentJob = null;
						beginTime = -1;
						if (pq.isEmpty() && inputJobs.isEmpty()) {
							return turnaroundTimeSum / jobs.length;
						}
					}
				}
				if (currentJob == null && !pq.isEmpty()) {
					currentJob = pq.remove();
					beginTime = time;
				}
			}
		}

		static class Job implements Comparable<Job> {

			int id;
			int requestTime;
			int duration;

			Job(int id, int requestTime, int duration) {
				this.id = id;
				this.requestTime = requestTime;
				this.duration = duration;
			}

			@Override
			public int compareTo(Job other) {
				if (duration != other.duration) {
					return Integer.compare(duration, other.duration);
				}
				if (requestTime != other.requestTime) {
					return Integer.compare(requestTime, other.requestTime);
				}
				return Integer.compare(id, other.id);
			}

			@Override
			public String toString() {
				return "[id=" + id + ", requestTime=" + requestTime
						+ ", duration=" + duration + "]";
			}

		}

	}

}
