import java.util.*;
import java.util.stream.*;

public class PG_20260916_42577_전화번호_목록 {

	class Solution1 {

		public boolean solution(String[] phone_book) {
			Set<String> set = new HashSet<>();
			StringBuilder sb = new StringBuilder();
			for (String s : phone_book) {
				sb.append(s).deleteCharAt(s.length() - 1);
				for (int i = s.length() - 2; i >= 0; i--) {
					set.add(sb.toString());
					sb.deleteCharAt(i);
				}
			}
			for (String s : phone_book) {
				if (set.contains(s)) {
					return false;
				}
			}
			return true;
		}

	}

	class Solution2 {

		public boolean solution(String[] phone_book) {
			Set<String> set = Arrays.stream(phone_book)
					.collect(Collectors.toSet());
			for (String s : set) {
				for (int length = s.length() - 1; length >= 1; length--) {
					if (set.contains(s.substring(0, length))) {
						return false;
					}
				}
			}
			return true;
		}

	}

	class Solution3 {

		public boolean solution(String[] phone_book) {
			Arrays.sort(phone_book);
			for (int i = 1; i < phone_book.length; i++) {
				if (phone_book[i].startsWith(phone_book[i - 1])) {
					return false;
				}
			}
			return true;
		}

	}

}
