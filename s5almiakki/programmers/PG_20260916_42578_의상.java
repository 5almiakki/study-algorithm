import java.util.*;

public class PG_20260916_42578_의상 {

	class Solution {

		public int solution(String[][] clothes) {
			Map<String, Set<String>> map = new HashMap<>();
			for (String[] cloth : clothes) {
				Set<String> set = map.get(cloth[1]);
				if (set == null) {
					set = new HashSet<>();
					map.put(cloth[1], set);
				}
				set.add(cloth[0]);
			}
			int answer = 1;
			for (Set<String> set : map.values()) {
				answer *= (set.size() + 1);
			}
			answer--;
			return answer;
		}

	}

}
