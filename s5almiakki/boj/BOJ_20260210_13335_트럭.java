import java.io.*;
import java.util.*;

public class BOJ_20260210_13335_트럭 {

    public static class Main {

        public static void main(String[] args) throws IOException {
            try (
                    InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr)) {
                String[] input = br.readLine().split(" ");
                int truckCount = Integer.parseInt(input[0]);
                int bridgeLength = Integer.parseInt(input[1]);
                int weightLimit = Integer.parseInt(input[2]);
                Queue<Integer> trucks = new ArrayDeque<>();
                input = br.readLine().split(" ");
                for (int i = 0; i < truckCount; i++) {
                    trucks.add(Integer.parseInt(input[i]));
                }

                int time = 0;
                // { truck, time }
                Queue<int[]> bridge = new ArrayDeque<>();
                int weightSum = 0;
                do {
                    time++;
                    int[] first = bridge.peek();
                    if (first != null && time == first[1] + bridgeLength) {
                        bridge.remove();
                        weightSum -= first[0];
                    }
                    if (trucks.isEmpty()) {
                        continue;
                    }
                    int newWeightSum = weightSum + trucks.peek();
                    if (newWeightSum <= weightLimit) {
                        bridge.add(new int[] { trucks.remove(), time });
                        weightSum = newWeightSum;
                    }
                } while (!bridge.isEmpty());

                System.out.print(time);
            }
        }

    }

}
