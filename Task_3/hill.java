import java.util.*;

public class HillCipher {

    // Multiply 2x2 matrix with 2x1 vector
    static int[] multiply(int[][] matrix, int[] vector) {
        int[] result = new int[2];

        for (int i = 0; i < 2; i++) {
            result[i] = 0;
            for (int j = 0; j < 2; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] = ((result[i] % 26) + 26) % 26;
        }

        return result;
    }

    // Find modular inverse of determinant
    static int modInverse(int det) {
        det = ((det % 26) + 26) % 26;

        for (int i = 1; i < 26; i++) {
            if ((det * i) % 26 == 1)
                return i;
        }
        return -1; // No inverse exists
    }

    // Find inverse of 2x2 matrix
    static int[][] inverseMatrix(int[][] key) {

        int det = key[0][0] * key[1][1] - key[0][1] * key[1][0];
        det = ((det % 26) + 26) % 26;

        int invDet = modInverse(det);

        if (invDet == -1) {
            System.out.println("Key matrix is not invertible!");
            return null;
        }

        int[][] inverse = new int[2][2];

        inverse[0][0] = key[1][1];
        inverse[1][1] = key[0][0];
        inverse[0][1] = -key[0][1];
        inverse[1][0] = -key[1][0];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverse[i][j] = ((inverse[i][j] * invDet) % 26 + 26) % 26;
            }
        }

        return inverse;
    }

    // Encryption
    static String encrypt(String message, int[][] key) {

        message = message.toUpperCase().replaceAll("[^A-Z]", "");

        if (message.length() % 2 != 0)
            message += "X";

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < message.length(); i += 2) {

            int[] vector = {
                message.charAt(i) - 'A',
                message.charAt(i + 1) - 'A'
            };

            int[] result = multiply(key, vector);

            cipher.append((char)(result[0] + 'A'));
            cipher.append((char)(result[1] + 'A'));
        }

        return cipher.toString();
    }

    // Decryption
    static String decrypt(String cipher, int[][] key) {

        int[][] inverseKey = inverseMatrix(key);
        if (inverseKey == null)
            return "Invalid Key!";

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i += 2) {

            int[] vector = {
                cipher.charAt(i) - 'A',
                cipher.charAt(i + 1) - 'A'
            };

            int[] result = multiply(inverseKey, vector);

            plain.append((char)(result[0] + 'A'));
            plain.append((char)(result[1] + 'A'));
        }

        return plain.toString();
    }

    public static void main(String[] args) {

        int[][] key = {
            {3, 3},
            {2, 5}
        };

        String message = "HELP";

        String encrypted = encrypt(message, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original  : " + message);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
