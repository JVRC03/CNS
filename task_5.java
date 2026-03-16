/*Blowfish

What is Blowfish?
Blowfish is a symmetric block cipher encryption algorithm designed by Bruce Schneier in 1993. It encrypts data in 64-bit blocks using a variable key length (32–448 bits).

Why is Blowfish used?
It is used to securely encrypt data so that unauthorized users cannot read it. It is fast in software and more secure than older algorithms like Data Encryption Standard.

Simple Example:
Suppose the message is “HELLO” and the secret key is “KEY123”.
Blowfish uses the key to convert the message into unreadable ciphertext (for example: A7F9K2).
When the correct key is used again, the ciphertext is decrypted back to “HELLO”. 🔐

*/

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class BlowfishExample {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter key: ");
        String key = sc.nextLine();

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "Blowfish");

        Cipher cipher = Cipher.getInstance("Blowfish");

        // Encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encrypted);

        System.out.println("Encrypted: " + encryptedText);

        // Decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        System.out.println("Decrypted: " + new String(decrypted));
    }
}

/*Small Example

Suppose the message = 12AB and the key = K1.

Step 1: Split the message
Blowfish divides the message into two parts:

Left (L) = 12
Right (R) = AB

Step 2: Round operation

XOR the left part with the key

L = 12 ⊕ K1

Pass L through the F-function.

XOR the result with R

R = R ⊕ F(L)

Swap L and R

Step 3: Repeat
This process continues for 16 rounds.

Step 4: Final result
After the last round, the two parts are combined to produce the ciphertext (encrypted message).*/
