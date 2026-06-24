import java.io.*;

public class CT_20260624_외판원_순회 {

    public class Main {

        static int answer = Integer.MAX_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int nodeCount = Integer.parseInt(br.readLine());
            int[][] adjMat = new int[nodeCount][nodeCount];
            for (int node1 = 0; node1 < nodeCount; node1++) {
                String[] input = br.readLine().split(" ");
                for (int node2 = 0; node2 < nodeCount; node2++) {
                    adjMat[node1][node2] = Integer.parseInt(input[node2]);
                }
            }
            backtrack(adjMat, 0, 1, 0);
            System.out.print(answer);
        }

        static void backtrack(
                int[][] adjMat, int currentNode, int visitedMask, int costSum) {
            // int bitCount = Integer.bitCount(visitedMask);
            // for (int i = 1; i < bitCount; i++) {
            // System.out.print(" ");
            // }
            // System.out.print(currentNode + ", cost: " + costSum);

            int targetVisitedMask = (1 << adjMat.length) - 1;
            if (visitedMask == targetVisitedMask) {
                if (adjMat[currentNode][0] == 0) {
                    return;
                }
                costSum += adjMat[currentNode][0];
                // System.out.println(" final cost: " + costSum);
                if (costSum < answer) {
                    answer = costSum;
                }
                return;
            }

            // System.out.println();

            for (int nextNode = 0; nextNode < adjMat.length; nextNode++) {
                if (adjMat[currentNode][nextNode] == 0) {
                    continue;
                }
                int mask = 1 << nextNode;
                if ((visitedMask & mask) != 0) {
                    continue;
                }
                int newCost = costSum + adjMat[currentNode][nextNode];
                if (answer <= newCost) {
                    continue;
                }
                backtrack(adjMat, nextNode, visitedMask | mask, newCost);
            }
        }

    }

}
