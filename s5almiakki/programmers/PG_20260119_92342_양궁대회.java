
public class PG_20260119_92342_양궁대회 {

    class Solution {

        private int[] apeachArrowCounts;
        private int[] ryanArrowCounts = new int[11];
        private int[] answer = new int[11];
        private int maxDelta = 0;

        // private StringBuilder log = new StringBuilder();
        // private String ln = System.lineSeparator();

        public int[] solution(int n, int[] info) {
            apeachArrowCounts = info;
            dfs(n, 0);
            return (maxDelta == 0) ? new int[] { -1 } : answer;
        }

        private void dfs(int remainingArrowCount, int depth) {
            if (depth == 10) {
                ryanArrowCounts[depth] = remainingArrowCount;
                updateAnswer();
                return;
            }
            for (int arrowCount = 0; arrowCount <= remainingArrowCount; arrowCount++) {
                ryanArrowCounts[depth] = arrowCount;
                dfs(remainingArrowCount - arrowCount, depth + 1);
                ryanArrowCounts[depth] = 0;
            }
        }

        private int computeDelta() {
            int apeachScoreSum = 0;
            int ryanScoreSum = 0;
            for (int i = 0; i <= 10; i++) {
                if (apeachArrowCounts[i] == 0 && ryanArrowCounts[i] == 0) {
                    continue;
                }
                if (apeachArrowCounts[i] < ryanArrowCounts[i]) {
                    ryanScoreSum += 10 - i;
                } else {
                    apeachScoreSum += 10 - i;
                }
            }
            return ryanScoreSum - apeachScoreSum;
        }

        private void updateAnswer() {
            int delta = computeDelta();
            if (delta <= 0 || delta < maxDelta) {
                return;
            }
            if (delta == maxDelta) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] > ryanArrowCounts[i]) {
                        return;
                    }
                    if (answer[i] < ryanArrowCounts[i]) {
                        break;
                    }
                }
            }
            maxDelta = delta;
            for (int i = 0; i <= 10; i++) {
                answer[i] = ryanArrowCounts[i];
            }
        }

    }

}
