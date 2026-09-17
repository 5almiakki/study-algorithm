import java.util.*;

public class PG_20260917_42628_이중우선순위큐 {

	class Solution {

		public int[] solution(String[] operations) {
			SortedMap<Integer, Integer> map = new TreeMap<>();
			for (String op : operations) {
				String[] cmd = op.split(" ");
				switch (cmd[0]) {
					case "I":
						insert(map, Integer.valueOf(cmd[1]));
						break;
					case "D":
						delete(map, Integer.parseInt(cmd[1]));
						break;
				}
			}
			return map.isEmpty()
					? new int[] { 0, 0 }
					: new int[] { map.lastKey(), map.firstKey() };
		}

		void insert(Map<Integer, Integer> map, Integer num) {
			map.merge(num, 1, Integer::sum);
		}

		void delete(SortedMap<Integer, Integer> map, int num) {
			if (map.isEmpty()) {
				return;
			}
			Integer key = (num == 1) ? map.lastKey() : map.firstKey();
			Integer count = map.get(key);
			if (count == 1) {
				map.remove(key);
			} else {
				map.put(key, count - 1);
			}
		}

	}

}
