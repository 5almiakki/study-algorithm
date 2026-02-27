import java.util.*;

public class PG_20260227_72416_매출_하락_최소화 {

    class Solution {

        //static final String LN = System.lineSeparator();
        //StringBuilder log = new StringBuilder();

        public int solution(int[] sales, int[][] links) {
            int personCount = sales.length;
            List<Set<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < personCount; i++) {
                adjList.add(new HashSet<>());
            }
            for (int[] link : links) {
                int manager = link[0] - 1;
                int member = link[1] - 1;
                adjList.get(manager).add(member);
            }

            int[][] moneySums = new int[personCount][2];
            for (int i = 0; i < personCount; i++) {
                if (adjList.get(i).isEmpty()) {
                    moneySums[i][1] = sales[i];
                }
            }
            dfs(sales, adjList, moneySums, 0);
            //doLog(sales, adjList, moneySums, 0, 0);
            //System.out.print(log);
            return Math.min(moneySums[0][0], moneySums[0][1]);
        }

        void dfs(int[] sales, List<Set<Integer>> adjList, int[][] moneySums, int person) {
            Set<Integer> members = adjList.get(person);
            if (members.isEmpty()) {
                return;
            }

            int minDiff = Integer.MAX_VALUE;
            boolean memberPresent = false;
            for (int member : members) {
                dfs(sales, adjList, moneySums, member);
                int memberAbsentMoney = moneySums[member][0];
                int memberPresentMoney = moneySums[member][1];
                if (memberAbsentMoney < memberPresentMoney) {
                    minDiff = Math.min(minDiff, memberPresentMoney - memberAbsentMoney);
                    moneySums[person][0] += memberAbsentMoney;
                    moneySums[person][1] += memberAbsentMoney;
                } else {
                    memberPresent = true;
                    moneySums[person][0] += memberPresentMoney;
                    moneySums[person][1] += memberPresentMoney;
                }
            }

            if (!memberPresent) {
                moneySums[person][0] += minDiff;
            }
            moneySums[person][1] += sales[person];
        }

        //void doLog(int[] sales, List<Set<Integer>> adjList, int[][] moneySums, int node, int depth) {
        //    StringBuilder indentation = new StringBuilder();
        //    for (int i = 0; i < depth; i++) {
        //        indentation.append("  ");
        //    }
        //    log.append(indentation).append("node={").append(node).append(',').append(sales[node]).append("} ")
        //        .append("moneySums=[").append(moneySums[node][0]).append(',')
        //        .append(moneySums[node][1]).append(']').append(LN);

        //    for (int adjNode : adjList.get(node)) {
        //        doLog(sales, adjList, moneySums, adjNode, depth + 1);
        //    }
        //}

    }

}
