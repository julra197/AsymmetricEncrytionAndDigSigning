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
    public static String NAMEOFSIGNATURE = "SHA1withRSA";
    
    //private variables
    
    private static String MESSAGE = "This is the contnent of the test message.\n"
            + "Very important information";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //variables and output for exemplification
        String sender = "Bob";
        String eavesdropper = "Eve";
        String receiver = "Alice";
        System.out.println(String.format("Sender: %s, Receiver: %s, Eavesdropper: %s\n", sender, receiver, eavesdropper));
        System.out.println(String.format("INITIAL MESSAGE from %s using %s's public key:\n%s\n", sender, receiver, MESSAGE));
       
        //creating a keyPair (a public and a private key)
        KeyPair keyPair = KeyService.getRandomKeyPair(NAMEOFCIPHER);
        
        if(SHOWINFO)
        {
            System.out.println(String.format("%s's public key is:\n%s", receiver, keyPair.getPublic()));
        }
        //testing digital signing (currently an implementation failture...)
        byte[] signedMessage = DigSigning.signMessage(MESSAGE, keyPair.getPrivate(),
               NAMEOFSIGNATURE);
        System.out.println("signed message:\n" + new String(signedMessage));
        boolean verified = DigSigning.verifyMessage(signedMessage, keyPair.getPublic(),
                NAMEOFSIGNATURE);
        System.out.println("message verified: " + verified);
        System.out.println("message: "+ new String(signedMessage));
        
        //The encrypt method uses a Key argument, so a Private key could be provided for encryption as well
        byte[] encryptedMessage = AsymmetricService.encrypt(NAMEOFCIPHER,
                MESSAGE.getBytes(), keyPair.getPublic());
        System.out.println("\nENCRYPTED MESSAGE to be sent and evaesdropped by " + eavesdropper + ": \n" + new String(encryptedMessage) + "\n");
        String decryptedMessage = new String(AsymmetricService.decrypt(NAMEOFCIPHER,
                encryptedMessage, keyPair.getPrivate()));
        System.out.println("\nDECRYPTED MESSAGE read by "+ receiver +" using "+receiver+"'s public key: \n" + decryptedMessage);
        
    }

}
