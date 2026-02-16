import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESProgram {

    // Method to generate DES key from string
    public static SecretKey getKey(String myKey) throws Exception {
        byte[] keyBytes = myKey.getBytes();
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(desKeySpec);
    }

    // Encryption Method
    public static String encrypt(String data, String key) throws Exception {

        SecretKey secretKey = getKey(key);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decryption Method
    public static String decrypt(String encryptedData, String key) throws Exception {

        SecretKey secretKey = getKey(key);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();

            System.out.print("Enter 8-character key: ");
            String key = sc.nextLine();

            if(key.length() != 8){
                System.out.println("❌ Key must be exactly 8 characters for DES");
                return;
            }

            // Encryption
            String encrypted = encrypt(plaintext, key);
            System.out.println("\nEncrypted Text: " + encrypted);

            // Decryption
            String decrypted = decrypt(encrypted, key);
            System.out.println("Decrypted Text: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
