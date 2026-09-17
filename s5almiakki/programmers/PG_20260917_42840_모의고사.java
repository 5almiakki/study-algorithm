import java.util.*;

public class PG_20260917_42840_모의고사 {

	class Solution {

		public int[] solution(int[] answers) {
			int[][] marks = {
					{ 1, 2, 3, 4, 5 },
					{ 2, 1, 2, 3, 2, 4, 2, 5 },
					{ 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 }
			};
			int[] markIndices = new int[3];
			int[] scores = new int[3];
			for (int answer : answers) {
				for (int student = 0; student <= 2; student++) {
					int markIdx = markIndices[student];
					int mark = marks[student][markIdx];
					if (answer == mark) {
						scores[student]++;
					}
					markIndices[student]++;
					markIndices[student] %= marks[student].length;
				}
			}
			int maxScore = 0;
			for (int score : scores) {
				maxScore = Math.max(maxScore, score);
			}
			int studentCount = 0;
			for (int score : scores) {
				if (score == maxScore) {
					studentCount++;
				}
			}
			int idx = 0;
			int[] answer = new int[studentCount];
			for (int i = 0; i <= 2; i++) {
				if (scores[i] == maxScore) {
					answer[idx] = i + 1;
					idx++;
				}
			}
			return answer;
		}

	}

}
