
public class PG_20260125_43105_정수_삼각형 {

    class Solution {

        public int solution(int[][] triangle) {
            for (int row = 1; row < triangle.length; row++) {
                triangle[row][0] += triangle[row - 1][0];
                int end = triangle[row].length - 1;
                for (int col = 1; col < end; col++) {
                    triangle[row][col] += Math.max(triangle[row - 1][col - 1], triangle[row - 1][col]);
                }
                triangle[row][end] += triangle[row - 1][end - 1];
            }

            int answer = 0;
            int floor = triangle.length - 1;
            int end = triangle[floor].length - 1;
            for (int col = 0; col <= end; col++) {
                answer = Math.max(answer, triangle[floor][col]);
            }

            return answer;
        }

    }

}
