import java.util.*;

public class PG_20260407_1_2_3_떨어트리기 {

    class Solution {

        public int[] solution(int[][] edges, int[] target) {
            int nodeCount = edges.length + 1;
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < nodeCount; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                int node1 = edge[0] - 1;
                int node2 = edge[1] - 1;
                adjList.get(node1).add(node2);
            }
            List<Integer> leaves = new ArrayList<>();
            for (int node = 0; node < nodeCount; node++) {
                List<Integer> children = adjList.get(node);
                if (children.isEmpty()) {
                    leaves.add(node);
                    continue;
                }
            }

            // fromInclusive, toInclusive
            int[][] arrivalCountRanges = new int[target.length][2];
            for (int leaf : leaves) {
                arrivalCountRanges[leaf][0] = (target[leaf] + 2) / 3;
                arrivalCountRanges[leaf][1] = target[leaf] / 1;
            }

            List<Integer> destinations = new ArrayList<>();
            int[] arrivalCounts = new int[nodeCount];
            GameTree gameTree = new GameTree(adjList);
            for (;;) {
                int destination = gameTree.getDestination();
                destinations.add(destination);
                arrivalCounts[destination]++;

                int rangeSatisfactionCount = 0;
                for (int leaf : leaves) {
                    if (arrivalCountRanges[leaf][1] < arrivalCounts[leaf]) {
                        return new int[] { -1 };
                    }
                    if (arrivalCountRanges[leaf][0] <= arrivalCounts[leaf]
                            && arrivalCounts[leaf] <= arrivalCountRanges[leaf][1]) {
                        rangeSatisfactionCount++;
                    }
                }
                if (rangeSatisfactionCount == leaves.size()) {
                    break;
                }
            }
            // System.out.println("destinations: " + destinations);
            // System.out.println("target:        " + Arrays.toString(target));
            // System.out.println("arrivalCounts: " + Arrays.toString(arrivalCounts));

            Map<Integer, PriorityQueue<Integer>> leafToInputsMap = new HashMap<>();
            for (int leaf : leaves) {
                PriorityQueue<Integer> inputs = new PriorityQueue<>();
                Deque<Integer> stack = new ArrayDeque<>();
                for (int i = 0; i < target[leaf]; i++) {
                    stack.push(1);
                }
                while (stack.size() + inputs.size() > arrivalCounts[leaf]) {
                    int newValue = stack.pop() + stack.pop();
                    if (newValue == 3) {
                        inputs.add(newValue);
                    } else {
                        stack.push(newValue);
                    }
                }
                inputs.addAll(stack);
                leafToInputsMap.put(leaf, inputs);
            }
            // System.out.println(leafToInputsMap);

            int[] answer = new int[destinations.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = leafToInputsMap.get(destinations.get(i)).remove();
            }
            return answer;
        }

        static class GameTree {

            final int[] pointedChildIndices;
            final List<List<Integer>> adjList;

            GameTree(List<List<Integer>> adjList) {
                pointedChildIndices = new int[adjList.size()];
                this.adjList = new ArrayList<>();
                for (List<Integer> children : adjList) {
                    List<Integer> newChildren = new ArrayList<>(children);
                    newChildren.sort(null);
                    this.adjList.add(newChildren);
                }
            }

            int getDestination() {
                int currentNode = 0;
                for (;;) {
                    List<Integer> children = adjList.get(currentNode);
                    if (children.isEmpty()) {
                        break;
                    }
                    int childIdx = pointedChildIndices[currentNode];
                    int nextNode = children.get(childIdx);
                    pointedChildIndices[currentNode] = (childIdx + 1) % children.size();
                    currentNode = nextNode;
                }
                return currentNode;
            }

        }

    }

}
