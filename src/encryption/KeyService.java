/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            //generate a random key pair using the initialised size and default properties of kpg
            keyPair = kpg.generateKeyPair();
            if(Main.SHOWINFO)
            {
                System.out.println("The provider for the specified Cipher is " + kpg.getProvider());
            }
            
        }
        catch (NoSuchAlgorithmException ex)        
        {
            ex.printStackTrace();
        }
        return keyPair;
    }
}
