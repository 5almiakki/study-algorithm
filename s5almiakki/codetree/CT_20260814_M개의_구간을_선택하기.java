import java.io.*;
import java.util.*;

public class CT_20260814_M개의_구간을_선택하기 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] input = br.readLine().split(" ");
			int numCount = Integer.parseInt(input[0]);
			int targetSectionCount = Integer.parseInt(input[1]);
			int[] nums = new int[numCount];
			input = br.readLine().split(" ");
			for (int i = 0; i < numCount; i++) {
				nums[i] = Integer.parseInt(input[i]);
			}

			Map<DpKey, Integer> dp = new HashMap<>();
			dp.put(new DpKey(0, false), 0);
			dp.put(new DpKey(1, true), nums[0]);
			// System.out.println(dp);
			for (int i = 1; i < numCount; i++) {
				Map<DpKey, Integer> newDp = new HashMap<>();
				for (Map.Entry<DpKey, Integer> entry : dp.entrySet()) {
					DpKey key = entry.getKey();
					int value = entry.getValue();

					// nums[i]를 고른 경우
					int newSectionCount = key.sectionCount + (key.prevSelected ? 0 : 1);
					if (newSectionCount <= targetSectionCount) {
						DpKey newKey = new DpKey(newSectionCount, true);
						int storedNewValue = newDp.getOrDefault(newKey, Integer.MIN_VALUE);
						newDp.put(newKey, Math.max(value + nums[i], storedNewValue));
					}

					// nums[i]를 안 고른 경우
					DpKey newKey = new DpKey(key.sectionCount, false);
					int storedNewValue = newDp.getOrDefault(newKey, Integer.MIN_VALUE);
					newDp.put(newKey, Math.max(value, storedNewValue));
				}
				dp = newDp;
				// System.out.println(dp);
			}

			int answer = Integer.MIN_VALUE;
			for (Map.Entry<DpKey, Integer> entry : dp.entrySet()) {
				DpKey key = entry.getKey();
				if (key.sectionCount == targetSectionCount) {
					answer = Math.max(answer, entry.getValue());
				}
			}
			System.out.print(answer);
		}

		static class DpKey {

			final int sectionCount;
			final boolean prevSelected;

			private final int hashCode;

			DpKey(int sectionCount, boolean prevSelected) {
				this.sectionCount = sectionCount;
				this.prevSelected = prevSelected;

				hashCode = 31 * Integer.hashCode(sectionCount) + Boolean.hashCode(prevSelected);
			}

			@Override
			public boolean equals(Object o) {
				if (this == o) {
					return true;
				}
				if (!(o instanceof DpKey)) {
					return false;
				}
				DpKey other = (DpKey) o;
				return sectionCount == other.sectionCount && prevSelected == prevSelected;
			}

			@Override
			public int hashCode() {
				return hashCode;
			}

			@Override
			public String toString() {
				return "[" + sectionCount + " " + prevSelected + "]";
			}

		}

	}

}
