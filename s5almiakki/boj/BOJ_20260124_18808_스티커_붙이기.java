import java.io.*;
import java.util.Arrays;
import java.util.regex.*;

public class BOJ_20260124_18808_스티커_붙이기 {

    public static class Main {

        private static final Pattern SPLIT_PATTERN = Pattern.compile(" ");

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] input = split(br.readLine());
            int notebookHeight = Integer.parseInt(input[0]);
            int notebookWidth = Integer.parseInt(input[1]);
            int stickerCount = Integer.parseInt(input[2]);

            int answer = 0;
            char[][] notebook = new char[notebookHeight][notebookWidth];
            for (char[] row : notebook) {
                Arrays.fill(row, '0');
            }
            for (int stickerNum = 0; stickerNum < stickerCount; stickerNum++) {
                input = split(br.readLine());
                int stickerRowCount = Integer.parseInt(input[0]);
                int stickerColCount = Integer.parseInt(input[1]);
                char[][] sticker = new char[stickerRowCount][stickerColCount];
                for (int row = 0; row < stickerRowCount; row++) {
                    input = split(br.readLine());
                    for (int col = 0; col < stickerColCount; col++) {
                        sticker[row][col] = input[col].charAt(0);
                    }
                }

                for (int rotationCount = 0; rotationCount < 4; rotationCount++) {
                    int cellCount = stick(notebook, sticker);
                    if (cellCount != 0) {
                        answer += cellCount;
                        break;
                    }
                    sticker = rotate(sticker);
                }
            }
            br.close();

            System.out.print(answer);
        }

        private static String[] split(CharSequence cs) {
            return SPLIT_PATTERN.split(cs);
        }

        private static int stick(char[][] notebook, char[][] sticker) {
            for (int row = 0; row + sticker.length <= notebook.length; row++) {
                for (int col = 0; col + sticker[0].length <= notebook[row].length; col++) {
                    if (canStick(notebook, sticker, row, col)) {
                        return stick(notebook, sticker, row, col);
                    }
                }
            }
            return 0;
        }

        private static boolean canStick(char[][] notebook, char[][] sticker, int beginRow, int beginCol) {
            if (notebook.length < beginRow + sticker.length || notebook[0].length < beginCol + sticker[0].length) {
                return false;
            }
            for (int rowOffset = 0; rowOffset < sticker.length; rowOffset++) {
                int notebookRow = beginRow + rowOffset;
                for (int colOffset = 0; colOffset < sticker[rowOffset].length; colOffset++) {
                    int notebookCol = beginCol + colOffset;
                    if (notebook[notebookRow][notebookCol] == '1' && sticker[rowOffset][colOffset] == '1') {
                        return false;
                    }
                }
            }
            return true;
        }

        private static int stick(char[][] notebook, char[][] sticker, int beginRow, int beginCol) {
            int cellCount = 0;
            for (int rowOffset = 0; rowOffset < sticker.length; rowOffset++) {
                int notebookRow = beginRow + rowOffset;
                for (int colOffset = 0; colOffset < sticker[rowOffset].length; colOffset++) {
                    if (sticker[rowOffset][colOffset] == '1') {
                        int notebookCol = beginCol + colOffset;
                        notebook[notebookRow][notebookCol] = '1';
                        cellCount++;
                    }
                }
            }
            return cellCount;
        }

        private static char[][] rotate(char[][] sticker) {
            char[][] newSticker = new char[sticker[0].length][sticker.length];
            for (int row = 0; row < sticker.length; row++) {
                for (int col = 0; col < sticker[row].length; col++) {
                    int newRow = col;
                    int newCol = sticker.length - 1 - row;
                    newSticker[newRow][newCol] = sticker[row][col];
                }
            }
            return newSticker;
        }

    }

}
