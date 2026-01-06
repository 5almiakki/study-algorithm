import java.util.*;

public class PG_20260106_상담원_인원 {

    class Solution {

        private int typeCount;
        private int[] mentorCounts;
        private int[][] requests;
        private int targetDepth;
        private Set<MentorCounts> visitedMentorCounts = new HashSet<>();

        private int minWaitingTime = Integer.MAX_VALUE;

        public int solution(int k, int n, int[][] reqs) {
            typeCount = k;
            mentorCounts = new int[k];
            requests = reqs;
            Arrays.fill(mentorCounts, 1);
            targetDepth = n - k;
            dfs(0);
            return minWaitingTime;
        }

        private void dfs(int depth) {
            if (depth == targetDepth) {
                minWaitingTime = Math.min(minWaitingTime, computeWaitingTime());
                return;
            }
            for (int type = 0; type < typeCount; type++) {
                mentorCounts[type]++;
                MentorCounts m = new MentorCounts(mentorCounts);
                if (!visitedMentorCounts.contains(m)) {
                    visitedMentorCounts.add(m);
                    dfs(depth + 1);
                }
                mentorCounts[type]--;
            }
        }

        private int computeWaitingTime() {
            int waitingTime = 0;
            List<List<Mentor>> mentors = new ArrayList<>();
            for (int mentorCount : mentorCounts) {
                List<Mentor> ms = new ArrayList<>();
                for (int i = 0; i < mentorCount; i++) {
                    ms.add(new Mentor());
                }
                mentors.add(ms);
            }
            for (int[] request : requests) {
                List<Mentor> ms = mentors.get(request[2] - 1);
                Mentor mostIdleMentor = null;
                int minExpectedEndTime = Integer.MAX_VALUE;
                for (Mentor m : ms) {
                    int expectedEndTime = m.getExpectedEndTime();
                    if (expectedEndTime < minExpectedEndTime) {
                        mostIdleMentor = m;
                        minExpectedEndTime = expectedEndTime;
                    }
                }
                if (minExpectedEndTime <= request[0]) {
                    mostIdleMentor.removeAll();
                    mostIdleMentor.setBeginTime(request[0]);
                } else {
                    waitingTime += minExpectedEndTime - request[0];
                }
                mostIdleMentor.add(request);
            }
            return waitingTime;
        }

        private static class MentorCounts {

            private int[] mentorCounts;
            private int hashCode;

            public MentorCounts(int[] mentorCounts) {
                this.mentorCounts = Arrays.copyOf(mentorCounts, mentorCounts.length);
                hashCode = Arrays.hashCode(this.mentorCounts);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof MentorCounts)) {
                    return false;
                }
                MentorCounts other = (MentorCounts) o;
                return Arrays.equals(mentorCounts, other.mentorCounts);
            }

            @Override
            public int hashCode() {
                return hashCode;
            }

        }

        private static class Mentor {

            private int beginTime = 0;
            private Queue<int[]> queue = new ArrayDeque<>();

            public void setBeginTime(int beginTime) {
                this.beginTime = beginTime;
            }

            public void add(int[] request) {
                queue.add(request);
            }

            public void removeAll() {
                queue = new ArrayDeque<>();
            }

            public int getExpectedEndTime() {
                int expectedEndTime = beginTime;
                for (int request[] : queue) {
                    expectedEndTime += request[1];
                }
                return expectedEndTime;
            }

        }

    }

}
