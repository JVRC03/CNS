import java.util.*;

public class PlayfairCipher {

    private static char[][] matrix = new char[5][5];

    private static void generateMatrix(String key) {
        boolean[] used = new boolean[26];
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        StringBuilder sb = new StringBuilder();

        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                sb.append(c);
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!used[c - 'A']) {
                sb.append(c);
            }
        }

        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = sb.charAt(index++);
            }
        }
    }

    private static int[] findPosition(char c) {
        if (c == 'J') c = 'I';

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));

            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                sb.append('X');
            }
        }

        if (sb.length() % 2 != 0) {
            sb.append('X');
        }

        return sb.toString();
    }

    public static String encrypt(String text, String key) {
        generateMatrix(key);
        text = prepareText(text);

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) {
                cipher.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                cipher.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            }
            else if (posA[1] == posB[1]) {
                cipher.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                cipher.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            }
            else {
                cipher.append(matrix[posA[0]][posB[1]]);
                cipher.append(matrix[posB[0]][posA[1]]);
            }
        }

        return cipher.toString();
    }

    public static void main(String[] args) {
        String key = "MONARCHY";
        String text = "INSTRUMENTS";

        String encrypted = encrypt(text, key);

        System.out.println("Encrypted Text: " + encrypted);
    }
}
