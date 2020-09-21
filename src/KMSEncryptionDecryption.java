import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.services.kms.model.EncryptionAlgorithmSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by mayankthirani on 12/20/19.
 */
public class KMSEncryptionDecryption {
    //private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String PROVIDER = "BC";
    private static final int ivBytesLen = 16;
    private static final String NULL_KEY_ID = "00000000-0000-0000-0000-000000000000";
    private static final String KMS_REGION_EXTRACTOR = "arn:aws:kms:(.*?):";
    private static byte[] keyIdBytes = NULL_KEY_ID.getBytes(StandardCharsets.UTF_8);
    private static int keyIdBytesLen = keyIdBytes.length;
    private static byte[] chunkSizeBytes = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte)
            0xFF};
    private static int chunkSizeBytesLen = chunkSizeBytes.length;
    private static int totalHeaderLen = keyIdBytesLen + chunkSizeBytesLen + ivBytesLen;
    private static SecretKeySpec secretKey;

    public static void main(String[] args) {
        //AWSKMS kmsClient = AWSKMSClientBuilder.standard().build();
        String key_arn = "arn:aws:kms:us-east-2:412864912850:key/e8807a69-4ccd-45e2-9146-00092c25d3cc";
        /** Extract region name from key_arn **/

        Pattern pattern = Pattern.compile(KMS_REGION_EXTRACTOR);
        Matcher matcher = pattern.matcher(key_arn);
        if (matcher.find()) {
            System.out.println("Region extracted: " + matcher.group(1));
        }

        AWSKMSClientBuilder awskmsClientBuilder = AWSKMSClientBuilder.standard();
        //awskmsClientBuilder.setCredentials();
        AWSKMS kmsClient = awskmsClientBuilder.withRegion(matcher.group(1)).build();
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
//        System.out.println(s3Client.getBucketLocation("bigdata-log-user"));
//        Region region = Region.getRegion(Regions.US_EAST_1);
//        kmsClient.setRegion(region);
//        CreateKeyRequest createKeyRequest = new CreateKeyRequest();
//        CustomerMasterKeySpec customerMasterKeySpec = CustomerMasterKeySpec.RSA_4096;
//        createKeyRequest.setOrigin(OriginType.AWS_KMS);
//        createKeyRequest.setCustomerMasterKeySpec(customerMasterKeySpec);
//        createKeyRequest.setKeyUsage(KeyUsageType.ENCRYPT_DECRYPT);
//        CreateKeyResult createKeyResult = kmsClient.createKey(createKeyRequest);
//        String key_id = createKeyResult.getKeyMetadata().getKeyId();
//        String key_arn = createKeyResult.getKeyMetadata().getArn();
//        System.out.println("Metadata Info:: " + createKeyResult.getKeyMetadata().toString());
//        System.out.println("ARN:: " + key_arn);
//        System.out.println("Key-id:: " + key_id);
//        CreateAliasRequest createAliasRequest = new CreateAliasRequest();
//        createAliasRequest.withAliasName("alias/mthirani_rsa_4096_key");
//        createAliasRequest.withTargetKeyId(key_id);
//        CreateAliasResult response = kmsClient.createAlias(createAliasRequest);
//        System.out.println("Alias Response:: " + response.getSdkResponseMetadata().toString());
//        DescribeKeyRequest req = new DescribeKeyRequest().withKeyId(key_arn);
//        DescribeKeyResult result = kmsClient.describeKey(req);
//        System.out.println("DescribeResult KeyMetadata:: " + result.getKeyMetadata().toString());


        //key_arn = "arn:aws:kms:us-east-1:412864912850:key/c78741cb-cc6b-4a0c-aa2b-15e4c880fb4a";

        String plain_text = "aesEncryptionKey";
        System.out.println("Plain Text Passed:: " + plain_text);
        ByteBuffer plaintextPassed = ByteBuffer.wrap(plain_text.getBytes());

        EncryptRequest encryptRequest = new EncryptRequest().withKeyId(key_arn).withPlaintext
                (plaintextPassed).withEncryptionAlgorithm(EncryptionAlgorithmSpec.RSAES_OAEP_SHA_256);
        ByteBuffer ciphertext = kmsClient.encrypt(encryptRequest).getCiphertextBlob();
        System.out.println("Plain Text Encrypted:: " + new String(ciphertext.array()));
        byte[] encoded1 = java.util.Base64.getEncoder().encode(ciphertext.array());
        System.out.println("Base64 Encrypted Text:: " + new String(encoded1));
//        byte[] decodedText = java.util.Base64.getDecoder().decode
//                ("HsCEGSCliQI6ZBICqlAwRox8JWDA5aNbt18+np1lX3ounw3U4ROn9SEPOxk3/gTIuW20x95lRYRc7HPgTMXaVUvVaNp9+Ma1pun5MqqPd+FE6d7TLBsQeM4Jw8/31E74mwq4pmMFJFsLhFd3R3u8lvgBjsX1hFazU+xAWF3HoMfM3+8Blj1iKTsSAMTa7VMjgIME4Najo1QdILyk4zfj1w01EwTzPiRelck7hZM/WDm3K+VGBY1OynKCYHV1GPIWHuGZpsZNGiNP/QZnxTYlfYts/+7GjvAv1TrBpcrSc3AKgCTSWKKzuq6ewTlOZojuQ2KK6ZM/VZOknBWQVDyuQAWjYhx8k+YmD9rcPPjnDgmf3EAsDaITZ53zsd6yXABqS3nTFhvglQq8zwfPMY9UZoOEqnGSkKVI4hcp3f/a6DuZLhjjx0s7su+iZNLPJ/HGe1IW3vqA8XqPxw3Af/BuR+oWVGlR1BWNW85WJW1mDqVxMZeJrOT5rplH/k6DA80FfphEfJg461KeA/JEOh+js29w2PpxaIDM6r7RQ4pazzrp2mEyySRqgBQqTfOv48hWEUEKNxF61UthHcpHV7Pqy32IVoerkpZ/VDynoKTlOrsqUqfvRfwJ88YJhD5GXeRKoGeYbRNdnzqKUjvm+wmqfgabW6PN+QcgzGlE/JDWj4I=");
//        ByteBuffer ciphertext = ByteBuffer.wrap(decodedText);

        DecryptRequest decryptRequest = new DecryptRequest().withKeyId(key_arn)
                .withEncryptionAlgorithm(EncryptionAlgorithmSpec.RSAES_OAEP_SHA_256)
                .withCiphertextBlob(ciphertext);
        ByteBuffer plainTextResult = kmsClient.decrypt(decryptRequest).getPlaintext();

        System.out.println("Plain Text Decrypted:: " + new String(plainTextResult.array()));

        byte[] encoded = java.util.Base64.getEncoder().encode(key_arn.getBytes());
        System.out.println("Base64 encoded key:: " + new String(encoded));
        System.out.println("Base64 decoded key:: " + new String(java.util.Base64.getDecoder().decode
                (encoded)));
        System.out.println("Sym_iv length:: " + java.util.Base64.getDecoder().decode(
                "rlzw7ysDugDTPqaYEMy6SA==").length);

        Security.addProvider(new BouncyCastleProvider());
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            int keyBitSize = 256;
            keyGenerator.init(keyBitSize, secureRandom);
            Key key = keyGenerator.generateKey();
            System.out.println("Key generated:: " + key);


            /* First Encoded Key */
            String base64encodedKey = "g/mv5E4Kzhk3DLC1ei8ERA==";
            byte[] base64decodedBytes = decode(base64encodedKey);

            System.out.println("Length of UI key: " + base64encodedKey.length());
            System.out.println("Length of UI key bytes length : " + base64decodedBytes.length);
            System.out.println("Length of Decoded UI key: " + new String(base64decodedBytes).length());
            System.out.println("Print Decoded UI key: " + new String(base64decodedBytes));

            // Why the below length does not matches with original 16 bytes?
            System.out.println("Length of Decoded UI key bytes: " + new String
                    (base64decodedBytes).getBytes().length);

            System.out.println();

            /* Second Encoded Key */
            String base64encodedKey2 = "";
            byte[] base64decodedBytes2 = decode(base64encodedKey2);
            System.out.println("Length of UI key 2: " + base64encodedKey2.length());
            System.out.println("Length of UI key 2 bytes length : " + base64decodedBytes2.length);
            System.out.println("Length of Decoded UI key 2: " + new String(base64decodedBytes2).length());
            System.out.println("Print Decoded UI key 2: " + new String(base64decodedBytes2));

            System.out.println();

            /* Third Sym Key */
            String working_sym_key = "aesEncryptionKey";

            /* Field to Encrypt */
            String field_to_encrypt = "Decrypted Field 4";

            /* Generate the SecretKeySpec */
            //setKey(new String(base64decodedBytes));
            setKey(base64decodedBytes);

            Cipher cipher = Cipher.getInstance(ALGORITHM, PROVIDER);
            String sym_iv = new String("encryptionIntVec");
            byte[] iv = sym_iv.getBytes();
            byte[] base64encodedsymiv = java.util.Base64.getEncoder().encode(iv);
            System.out.println("Encoded sym_iv value:: " + new String(base64encodedsymiv));
            System.out.println("base64 encoded length for iv:: " + base64encodedsymiv.length);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] encryptedBytes = cipher.doFinal(field_to_encrypt.getBytes());

            byte[] base64encodedValue = java.util.Base64.getEncoder().encode(encryptedBytes);
            System.out.println("Encoded value:: " + new String(base64encodedValue));

            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            System.out.println("Decrypted Key :: " + new String(decryptedBytes));

        } catch (Exception e) {
            System.out.println("Exception :: " + e.getMessage());
            System.out.println(e);
        }
    }

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
//            byte[] key = myKey.getBytes("UTF-8");
//            sha = MessageDigest.getInstance("SHA-1");
//            key = sha.digest(key);
//            key = Arrays.copyOf(key, 16);
//            secretKey = new SecretKeySpec(key, "AES");
            System.out.println("Length of key found: " + myKey.getBytes("UTF-8").length);
            secretKey = new SecretKeySpec(myKey.getBytes("UTF-8"), "AES");
        }
//        catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void setKey(byte[] myKey) {
        MessageDigest sha = null;
        System.out.println("Length of key found: " + myKey.length);
        secretKey = new SecretKeySpec(myKey, "AES");
    }

    public final static byte[] decode(String str) {
        int[] IA = new int[256];
        char[] CA =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        Arrays.fill(IA, -1);
        for (int i = 0, iS = CA.length; i < iS; i++) {
            IA[CA[i]] = i;
        }
        IA['='] = 0;

        // Check special case
        int sLen = str != null ? str.length() : 0;
        if (sLen == 0) {
            return new byte[0];
        }

        // Count illegal characters (including '\r', '\n') to know what size the returned array
        // will be, so we don't have to reallocate & copy it later.
        int sepCnt = 0; // Number of separator characters. (Actually illegal characters,
        // but that's a bonus...)
        for (int i = 0; i < sLen; i++) { // If input is "pure" (I.e. no line separators or illegal
            // chars) base64 this loop can be commented out.
            if (IA[str.charAt(i)] < 0) {
                sepCnt++;
            }
        }

        // Check so that legal chars (including '=') are evenly divideable by 4 as specified in
        // RFC 2045.
        if ((sLen - sepCnt) % 4 != 0) {
            return null;
        }

        // Count '=' at end
        int pad = 0;
        for (int i = sLen; i > 1 && IA[str.charAt(--i)] <= 0; ) {
            if (str.charAt(i) == '=') {
                pad++;
            }
        }

        int len = ((sLen - sepCnt) * 6 >> 3) - pad;

        byte[] dArr = new byte[len];       // Preallocate byte[] of exact length

        for (int s = 0, d = 0; d < len; ) {
            // Assemble three bytes into an int from four "valid" characters.
            int i = 0;
            for (int j = 0; j < 4; j++) {   // j only increased if a valid char was found.
                int c = IA[str.charAt(s++)];
                if (c >= 0) {
                    i |= c << (18 - j * 6);
                } else {
                    j--;
                }
            }
            // Add the bytes
            dArr[d++] = (byte) (i >> 16);
            if (d < len) {
                dArr[d++] = (byte) (i >> 8);
                if (d < len) {
                    dArr[d++] = (byte) i;
                }
            }
        }

        return dArr;
    }
}
