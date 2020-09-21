import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Created by mayankthirani on 1/9/20.
 */
public class TrippleDesEncryptionDecryption {
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final String PROVIDER = "BC";
    private static final String UNICODE_FORMAT = "UTF8";
    byte[] arrayBytes;
    SecretKey key;
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    private String myEncryptionKey;
    private String myEncryptionScheme;

    public TrippleDesEncryptionDecryption() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        /*
         * cipher = Cipher.getInstance(ALGORITHM, PROVIDER); //throws Illegal key size error
         */
        key = skf.generateSecret(ks);
    }

    public static void main(String args[]) throws Exception {
        TrippleDesEncryptionDecryption td = new TrippleDesEncryptionDecryption();

        String target = "imparator";
        String encrypted = td.encrypt(target);
        String decrypted = td.decrypt(encrypted);

        System.out.println("String To Encrypt: " + target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);

    }

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}
