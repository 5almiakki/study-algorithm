public class PG_20260917_86491_최소직사각형 {

	class Solution {

		public int solution(int[][] sizes) {
			int maxWidth = 0;
			int maxHeight = 0;
			for (int[] size : sizes) {
				int width;
				int height;
				if (size[0] < size[1]) {
					width = size[0];
					height = size[1];
				} else {
					width = size[1];
					height = size[0];
				}
				maxWidth = Math.max(maxWidth, width);
				maxHeight = Math.max(maxHeight, height);
			}
			return maxWidth * maxHeight;
		}

	}

}
