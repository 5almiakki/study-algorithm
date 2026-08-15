import java.io.*;
import java.util.*;

public class CT_20260815_고대_보물_지도의_비밀 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int numCount = Integer.parseInt(input[0]);
			int maxMinusCount = Integer.parseInt(input[1]);
			int[] nums = new int[numCount];
			input = br.readLine().split(" ");
			for (int i = 0; i < numCount; i++) {
				nums[i] = Integer.parseInt(input[i]);
			}

			int answer = nums[0];
			int[] dp = nums[0] >= 0
					? new int[] { nums[0] }
					: new int[] { Integer.MIN_VALUE, nums[0] };
			// System.out.println(Arrays.toString(dp));
			for (int i = 1; i < numCount; i++) {
				int offset = nums[i] >= 0 ? 0 : 1;
				int newLength = dp.length + offset;
				newLength = Math.min(newLength, maxMinusCount + 1);
				int[] newDp = new int[newLength];
				Arrays.fill(newDp, Integer.MIN_VALUE);
				for (int oldMinusCount = 0; oldMinusCount < dp.length; oldMinusCount++) {
					if (dp[oldMinusCount] == Integer.MIN_VALUE) {
						continue;
					}
					if (oldMinusCount + offset >= newLength) {
						break;
					}
					newDp[oldMinusCount + offset] = Math.max(newDp[oldMinusCount + offset], dp[oldMinusCount] + nums[i]);
					answer = Math.max(answer, newDp[oldMinusCount + offset]);
				}
				newDp[offset] = Math.max(newDp[offset], nums[i]);
				answer = Math.max(answer, newDp[offset]);
				dp = newDp;
				// System.out.println(Arrays.toString(dp));
			}
			System.out.print(answer);
		}

	}

}
