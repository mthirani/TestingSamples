import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Created by mayankthirani on 8/14/18.
 */

// https://stackoverflow.com/questions/7224626/how-to-sign-string-with-private-key
public class CertificateGeneration {
    public static void main(String []args) {
        try {
            generateEncodedCert("/Users/mayankthirani/Certificates/cert-request.csr");
            generateStrings("/Users/mayankthirani/Certificates/testPrivateKey.key");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Generating {your_CA_certificate}
    static String generateEncodedCert(String certFile) throws IOException {
        byte[] certBytes;

        try (FileInputStream fileInputStream = new FileInputStream(certFile);
             BufferedInputStream bis = new BufferedInputStream(fileInputStream)) {

            certBytes = new byte[(int) new File(certFile).length()];
            bis.read(certBytes);
        }

        System.out.println("Encoded Certificate: " + Base64.getEncoder().encodeToString(certBytes));

        return Base64.getEncoder().encodeToString(certBytes);
    }

    // Generating {encoded_string} and {encoded_signed_string}
    static void generateStrings(String privateKeyFile) throws Exception {
        byte[] privKeyBytes;

        try (FileInputStream fileInputStream = new FileInputStream(privateKeyFile);
             BufferedInputStream bis = new BufferedInputStream(fileInputStream)) {

            privKeyBytes = new byte[(int) new File(privateKeyFile).length()];
            bis.read(privKeyBytes);
        }

        RSAPrivateKey privKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(
                new PKCS8EncodedKeySpec(privKeyBytes));

        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());

        byte bytes[] = new byte[100];
        random.nextBytes(bytes);

        System.out.println("Encoded Data: " + Base64.getEncoder().encodeToString(bytes));

        byte[] decodedSignedData = sign(privKey, bytes);

        String encodedData = Base64.getEncoder().encodeToString(bytes);
        String encodedSignedData = Base64.getEncoder().encodeToString(decodedSignedData);

        System.out.println("Encoded Data: " + encodedData);
        System.out.println("Encoded Signed Data: " + encodedSignedData);
    }

    static private byte[] sign(PrivateKey privateKey, byte[] dataBytes)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA512withRSA");

        sig.initSign(privateKey);
        sig.update(dataBytes);

        return sig.sign();
    }
}
