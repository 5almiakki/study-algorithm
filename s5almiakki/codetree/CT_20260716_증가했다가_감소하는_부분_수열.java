import java.io.*;
import java.util.*;

public class CT_20260716_증가했다가_감소하는_부분_수열 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int seqLength = Integer.parseInt(br.readLine());
			int[] seq = new int[seqLength];
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < seqLength; i++) {
				seq[i] = Integer.parseInt(input[i]);
			}
			int[] dp1 = new int[seqLength];
			dp1[0] = 1;
			for (int tail = 1; tail < seqLength; tail++) {
				int max = 0;
				for (int head = 0; head < tail; head++) {
					if (seq[head] >= seq[tail]) {
						continue;
					}
					max = Math.max(max, dp1[head]);
				}
				dp1[tail] = max + 1;
			}
			int[] dp2 = new int[seqLength];
			dp2[seqLength - 1] = 1;
			for (int tail = seqLength - 2; tail >= 0; tail--) {
				int max = 0;
				for (int head = seqLength - 1; head > tail; head--) {
					if (seq[head] >= seq[tail]) {
						continue;
					}
					max = Math.max(max, dp2[head]);
				}
				dp2[tail] = max + 1;
			}
			int answer = 0;
			for (int i = 0; i < seqLength; i++) {
				answer = Math.max(answer, dp1[i] + dp2[i]);
			}
			System.out.print(answer - 1);
		}

	}

}
