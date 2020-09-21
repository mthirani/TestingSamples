import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3EncryptionClientV2Builder;
import com.amazonaws.services.s3.AmazonS3EncryptionV2;
import com.amazonaws.services.s3.model.CryptoConfigurationV2;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.EncryptionMaterials;
import com.amazonaws.services.s3.model.StaticEncryptionMaterialsProvider;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by mayankthirani on 8/11/20.
 */
public class RedshiftBulkLoadMasterKeyFeature {
    public static void main(String[] args) {
//        AWSKMS kmsClient = AWSKMSClientBuilder.standard()
//                .withRegion(Regions.US_EAST_2)
//                .build();

        // create CMK for for testing this example
//        CreateKeyRequest createKeyRequest = new CreateKeyRequest();
//        CreateKeyResult createKeyResult = kmsClient.createKey(createKeyRequest);

// --
        // specify an Amazon KMS customer master key (CMK) ID
//        String keyId = createKeyResult.getKeyMetadata().getKeyId();
//        String keyId = "arn:aws:kms:us-east-2:412864912850:key/14ad648c-fac0-42b7-af28" +
//                "-ad20e4f715a7";
//        GenerateDataKeyRequest dataKeyRequest = new GenerateDataKeyRequest();
//        dataKeyRequest.setKeyId(keyId);
//        dataKeyRequest.setKeySpec(DataKeySpec.AES_256);
//
//        GenerateDataKeyResult dataKeyResult = kmsClient.generateDataKey(dataKeyRequest);
//
//        ByteBuffer plaintextKey = dataKeyResult.getPlaintext();
//
//        ByteBuffer encryptedKey = dataKeyResult.getCiphertextBlob();
//
//        String s3ObjectKey = "EncryptedContent10.txt";
//        String s3ObjectContent = "This is the 1st content to encrypt";
//// --
//
//        AmazonS3Encryption s3Encryption = AmazonS3EncryptionClientBuilder.standard()
//                .withRegion(Regions.US_EAST_2)
//                .withCryptoConfiguration(new CryptoConfiguration().withCryptoMode(CryptoMode.EncryptionOnly))
//                .withEncryptionMaterials(new KMSEncryptionMaterialsProvider(keyId))
//                .withKmsClient(kmsClient)
//                .build();
//
//        s3Encryption.putObject("mthirani", s3ObjectKey, s3ObjectContent);
//        System.out.println(s3Encryption.getObjectAsString("mthirani", s3ObjectKey));

        // schedule deletion of CMK generated for testing
//        ScheduleKeyDeletionRequest scheduleKeyDeletionRequest =
//                new ScheduleKeyDeletionRequest().withKeyId(keyId).withPendingWindowInDays(7);
//        kmsClient.scheduleKeyDeletion(scheduleKeyDeletionRequest);
//
//        s3Encryption.shutdown();
//        kmsClient.shutdown();

        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }
        keyGenerator.init(256);

// --
        // generate a symmetric encryption key for testing
        SecretKey secretKey = keyGenerator.generateKey();

        String s3ObjectKey = "EncryptedContent11.txt";
        String s3ObjectContent = "This is the 1st content to encrypt";
// --

        AmazonS3EncryptionV2 s3Encryption = AmazonS3EncryptionClientV2Builder.standard()
                .withRegion(Regions.US_EAST_2)
                .withClientConfiguration(new ClientConfiguration())
                .withCryptoConfiguration(new CryptoConfigurationV2().withCryptoMode(CryptoMode.AuthenticatedEncryption))
                .withEncryptionMaterialsProvider(new StaticEncryptionMaterialsProvider(new EncryptionMaterials(secretKey)))
                .build();

        s3Encryption.putObject("mthirani", s3ObjectKey, s3ObjectContent);
        System.out.println(s3Encryption.getObjectAsString("mthirani", s3ObjectKey));
        s3Encryption.shutdown();

//        KeyPairGenerator keyPairGenerator = null;
//        try {
//            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        keyPairGenerator.initialize(2048);
//
//        // --
//        // generate an asymmetric key pair for testing
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//        String s3ObjectKey = "EncryptedContent3.txt";
//        String s3ObjectContent = "This is the 3rd content to encrypt";
//        // --
//
//        AmazonS3EncryptionV2 s3Encryption = AmazonS3EncryptionClientV2Builder.standard()
//                .withRegion(Regions.US_EAST_2)
//                .withCryptoConfiguration(new CryptoConfigurationV2().withCryptoMode(CryptoMode.StrictAuthenticatedEncryption))
//                .withEncryptionMaterialsProvider(new StaticEncryptionMaterialsProvider(new EncryptionMaterials(keyPair)))
//                .build();
//
//        s3Encryption.putObject("mthirani", s3ObjectKey, s3ObjectContent);
//        System.out.println(s3Encryption.getObjectAsString("mthirani", s3ObjectKey));
//        s3Encryption.shutdown();
    }
}
