import java.util.*;

public class PG_20260323_81304_미로_탈출 {

    class Solution {

        public int solution(int n, int start, int end, int[][] roads, int[] traps) {
            start--;
            end--;
            List<Integer> nodes = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodes.add(i);
            }
            Map<Integer, Map<Integer, Integer>> adjMat = new TreeMap<>();
            for (int[] road : roads) {
                Integer node1 = road[0] - 1;
                Integer node2 = road[1] - 1;
                Map<Integer, Integer> adjNodes = adjMat.get(node1);
                if (adjNodes == null) {
                    adjNodes = new TreeMap<>();
                    adjMat.put(node1, adjNodes);
                }
                Integer cost = adjNodes.get(node2);
                if (cost == null || road[2] < cost) {
                    adjNodes.put(node2, road[2]);
                }
            }
            // System.out.println(adjMat);
            boolean[] isTrap = new boolean[n];
            for (int trap : traps) {
                isTrap[trap - 1] = true;
            }

            PriorityQueue<Moment> pq = new PriorityQueue<>();
            Set<Integer> initialVisitedTraps = new TreeSet<>();
            Moment initialMoment = new Moment(start, initialVisitedTraps, 0);
            pq.add(initialMoment);

            Map<Integer, Map<Set<Integer>, Integer>> costSumHistory = new HashMap<>();
            Map<Set<Integer>, Integer> visitedTrapsToCostSumMap = new HashMap<>();
            visitedTrapsToCostSumMap.put(initialVisitedTraps, 0);
            costSumHistory.put(nodes.get(start), visitedTrapsToCostSumMap);

            int answer = Integer.MAX_VALUE;
            do {
                Moment moment = pq.remove();
                // System.out.println(moment);
                if (moment.node == end) {
                    if (moment.costSum < answer) {
                        answer = moment.costSum;
                    }
                    continue;
                }

                visitedTrapsToCostSumMap = costSumHistory.get(moment.node);
                if (visitedTrapsToCostSumMap != null) {
                    Integer costSumRecord = visitedTrapsToCostSumMap.get(moment.visitedTraps);
                    if (costSumRecord != null && moment.costSum > costSumRecord
                            || moment.costSum >= answer) {
                        continue;
                    }
                }
                boolean trapVisited = moment.visitedTraps.contains(nodes.get(moment.node));
                for (int i = 0; i < n; i++) {
                    Integer adjNode = nodes.get(i);
                    // System.out.println(adjNode);
                    Integer cost = null;
                    try {
                        cost = trapVisited ^ moment.visitedTraps.contains(adjNode)
                                ? adjMat.get(adjNode).get(moment.node)
                                        : adjMat.get(moment.node).get(adjNode);
                    } catch (NullPointerException e) {
                        continue;
                    }
                    if (cost == null) {
                        continue;
                    }

                    Set<Integer> newVisitedTraps = new TreeSet<>(moment.visitedTraps);
                    if (isTrap[i]) {
                        if (newVisitedTraps.contains(adjNode)) {
                            newVisitedTraps.remove(adjNode);
                        } else {
                            newVisitedTraps.add(adjNode);
                        }
                    }
                    Moment newMoment = new Moment(adjNode, newVisitedTraps, moment.costSum + cost);
                    Map<Set<Integer>, Integer> newVisitedTrapsToCostSumMap = costSumHistory.get(adjNode);
                    if (newVisitedTrapsToCostSumMap == null) {
                        newVisitedTrapsToCostSumMap = new HashMap<>();
                        costSumHistory.put(adjNode, newVisitedTrapsToCostSumMap);
                    }
                    Integer costSum = newVisitedTrapsToCostSumMap.get(newVisitedTraps);
                    int newCostSum = moment.costSum + cost;
                    if (costSum == null || newCostSum < costSum) {
                        pq.add(newMoment);
                        newVisitedTrapsToCostSumMap.put(newVisitedTraps, newCostSum);
                    }
                }
            } while (!pq.isEmpty());
            return answer;
        }

        static class Moment implements Comparable<Moment> {

            final Integer node;
            final Set<Integer> visitedTraps;
            final int costSum;

            Moment(Integer node, Set<Integer> visitedTraps, int costSum) {
                this.node = node;
                this.visitedTraps = visitedTraps;
                this.costSum = costSum;
            }

            @Override
            public int compareTo(Moment o) {
                return Integer.compare(costSum, o.costSum);
            }

            @Override
            public String toString() {
                return "[" + node + " " + visitedTraps + " " + costSum + "]";
            }

        }

    }

}
