package encryption;

import java.security.KeyPair;

/*
 * This is the Main Class to exemplify asymetric encryption and digital 
 * signing in a local environment
 * Note: This is not suitable to be used in network communication, 
 * beause of inappropriate text encoding (e.g. base64 could be used)
 *
 * @author Ralf Ebert
 */
public class Main
{

    //Static variables
    public static boolean SHOWINFO = true;
    public static String NAMEOFCIPHER = "RSA";
    
    //private instance variables
    
    private static String message = "This is the contnent of the test message.\n"
            + "Very important information";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //variables for communication
        
        
        KeyPair keyPair = KeyService.getRandomKeyPair(NAMEOFCIPHER);
        byte[] encryptedMessage = AsymmetricService.encrypt(NAMEOFCIPHER,
                message, keyPair.getPrivate());
        
        // code to simmulate asymmetric communication as well as digital signing
    }

}
