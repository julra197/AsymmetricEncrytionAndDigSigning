/*
 * This class provides methods to generate key pairs
 */
package encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ralf Ebert PI:4962733
 */
public class KeyService
{
    static KeyPair getRandomKeyPair(String nameOfCipher)
    {
        KeyPair keyPair = null;
        try
        {
            //Create a KeyPairGenerator
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(nameOfCipher);
            //generate a random key pair using default properties of kpg
            keyPair = kpg.generateKeyPair();
            if(Main.SHOWINFO)
            {
                System.out.println(String.format("The key provider for the specified Cipher %s is %s ", nameOfCipher, kpg.getProvider()));
            }
        }
        catch (NoSuchAlgorithmException ex)        
        {
            ex.printStackTrace();
        }
        return keyPair;
    }
}
