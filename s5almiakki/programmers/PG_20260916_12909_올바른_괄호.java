public class PG_20260916_12909_올바른_괄호 {

	class Solution {

		boolean solution(String s) {
			int length = s.length();
			int openCount = 0;
			for (int i = 0; i < length; i++) {
				switch (s.charAt(i)) {
					case '(':
						openCount++;
						break;
					case ')':
						if (openCount == 0) {
							return false;
						}
						openCount--;
						break;
				}
			}
			return openCount == 0;
		}

	}

}
