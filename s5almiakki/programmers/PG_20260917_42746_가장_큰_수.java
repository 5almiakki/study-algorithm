import java.util.*;

public class PG_20260917_42746_가장_큰_수 {

	class Solution {

		public String solution(int[] numbers) {
			String[] nums = new String[numbers.length];
			for (int i = 0; i < nums.length; i++) {
				nums[i] = String.valueOf(numbers[i]);
			}
			Arrays.sort(nums, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
			StringBuilder sb = new StringBuilder();
			for (String num : nums) {
				sb.append(num);
			}
			return sb.charAt(0) == '0' ? "0" : sb.toString();
		}

	}

}
