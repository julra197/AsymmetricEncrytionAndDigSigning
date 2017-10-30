/*
 * This class provides digital signing services
 */
package encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author Ralf Ebert
 */
public class DigSigning
{

    /**
     * Returns a signedMessage
     * @param message the message to be signed
     * @param privateKey the private key of the message sender
     * @param signatureName the name of the signature algorithm 
     * @return a signed version of the given message
     */
    public static byte[] signMessage(String message, PrivateKey privateKey,
            String signatureName)
    {
        Signature signature;
        byte[] signedMessage = null;
        
        try
        {
            signature = Signature.getInstance(signatureName);
            if(Main.SHOWINFO)
            {
                System.out.println("Signature status: " + signature);
            }
            signature.initSign(privateKey);
            if(Main.SHOWINFO)
            {
                System.out.println("Signature status: " + signature);
            }
            signature.update(message.getBytes());
            signedMessage = signature.sign();
            if(Main.SHOWINFO)
            {
                System.out.println("Signed message: " + new String(signedMessage));
            }
        }
        catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex)
        {
            ex.printStackTrace();
        }
        
        return signedMessage;
    }
    
    public static boolean verifyMessage(byte[] message, PublicKey publicKey, String signatureName)
    {
        boolean verified = false;
        Signature signature = null;
        try
        {
            signature = Signature.getInstance(signatureName);
            signature.initVerify(publicKey);
            verified = signature.verify(message);
        }
        catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex)
        {
            ex.printStackTrace();
        }
        
        return verified;
    }
}
