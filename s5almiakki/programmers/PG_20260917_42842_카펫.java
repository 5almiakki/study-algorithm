public class PG_20260917_42842_카펫 {

	class Solution {

		public int[] solution(int brown, int yellow) {
			int area = brown + yellow;
			int bound = (int) Math.sqrt(area);
			for (int height = 1; height <= bound; height++) {
				if (area % height != 0) {
					continue;
				}
				int width = area / height;
				int computedBrown = (height << 1) + (width << 1) - 4;
				if (computedBrown == brown) {
					return new int[] { width, height };
				}
			}
			return null;
		}

	}

}
