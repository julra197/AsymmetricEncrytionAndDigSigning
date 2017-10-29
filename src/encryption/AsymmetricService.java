/*
 * This class provides asymmetric en- and decryption services 
 */
package encryption;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Ralf Ebert
 */
public class AsymmetricService
{

    /**
     * Returns an encrypted message using a specified cipher and a key
     *
     * @param nameOfCipher name of Cipher e.g. "RSA" or "DES"
     * @param message the plaintext message
     * @param key either a PrivateKey or a PublicKey
     * @return byte[] of the encrypted message
     */
    static byte[] encrypt(String nameOfCipher, String message, Key key)
    {
        //when show info is enabled and the cipher is RSA, pirnt whether a Private or Public key is provided
        if (Main.SHOWINFO && "RSA".equals(Main.NAMEOFCIPHER))
        {
            if (key.getClass().toString().equals(
                    "class sun.security.rsa.RSAPrivateCrtKeyImpl"))
            {
                System.out.println("Privatekey is used for encription");
            }
            else if (key.getClass().toString().equals(
                    "class sun.security.rsa.RSAPublicKeyImpl"))
            {
                System.out.println("PublicKey is used for encription");
            }
        }
        byte[] encryptedMessage = null;
        try
        {
            Cipher encryptionCipher = Cipher.getInstance(nameOfCipher);
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedMessage = encryptionCipher.doFinal(message.getBytes());
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex)
        {
            ex.printStackTrace();
        }
        return encryptedMessage;
    }
}
