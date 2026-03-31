
public class PG_20260331_87946_피로도 {

    class Solution {

        int answer = 0;

        public int solution(int k, int[][] dungeons) {
            boolean[] visited = new boolean[dungeons.length];
            for (int i = 0; i < dungeons.length; i++) {
                dfs(dungeons, visited, i, k, 0);
            }
            return answer;
        }

        void dfs(int[][] dungeons, boolean[] visited, int dungeonIdx, int remainingFatigue, int visitedDungeonCount) {
            if (visited[dungeonIdx] || !canVisit(dungeons[dungeonIdx], remainingFatigue)) {
                return;
            }
            visited[dungeonIdx] = true;
            visitedDungeonCount++;
            remainingFatigue -= dungeons[dungeonIdx][1];
            if (answer < visitedDungeonCount) {
                answer = visitedDungeonCount;
            }
            for (int i = 0; i < dungeons.length; i++) {
                dfs(dungeons, visited, i, remainingFatigue, visitedDungeonCount);
            }
            visited[dungeonIdx] = false;
        }

        boolean canVisit(int[] dungeon, int remainingFatigue) {
            return dungeon[0] <= remainingFatigue;
        }

    }

}
