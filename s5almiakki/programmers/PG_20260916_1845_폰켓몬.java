import java.util.*;

public class PG_20260916_1845_폰켓몬 {

	class Solution {

		public int solution(int[] nums) {
			return Math.min(
					Arrays.stream(nums)
							.distinct()
							.toArray()
							.length,
					nums.length >> 1);
		}

	}

}
