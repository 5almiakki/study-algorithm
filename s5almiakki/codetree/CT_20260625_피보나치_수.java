import java.io.*;

public class CT_20260625_피보나치_수 {

    public class Main {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            if (n <= 2) {
                System.out.print(1);
                return;
            }
            int[] sequence = new int[n];
            sequence[0] = 1;
            sequence[1] = 1;
            for (int i = 2; i < n; i++) {
                sequence[i] = sequence[i - 1] + sequence[i - 2];
            }
            System.out.print(sequence[n - 1])
        }

    }

}
