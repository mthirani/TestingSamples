package MongoConnection;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by mayankthirani on 3/18/19.
 */
public class MongoTestConnection {
    public static void main (String[] args) {
        String password = "sn@plogic 123!";
        //password = URLEncoder.encode(password);
//        List<MongoCredential> mongoCredentials = Lists.newArrayList(
//                MongoCredential.createScramSha1Credential(
//                        "abanerjee", "admin", "BK_roy111".toCharArray()));
        List<MongoCredential> mongoCredentials = Lists.newArrayList(
                MongoCredential.createScramSha1Credential(
                        "snaplogic", "admin", password.toCharArray()));
        MongoClientOptions clientOptionsBuilder = getMongoClientOptionsBuilder();
        ServerAddress serverAddress1 = new ServerAddress("test1-shard-00-00-0xlau.mongodb.net");
        ServerAddress serverAddress2 = new ServerAddress("test1-shard-00-01-0xlau.mongodb.net");
        ServerAddress serverAddress3 = new ServerAddress("test1-shard-00-02-0xlau.mongodb.net");
        List<ServerAddress> listAddresses = new ArrayList<>();
        listAddresses.add(serverAddress1);listAddresses.add(serverAddress2);listAddresses.add(serverAddress3);

//        List<MongoCredential> mongoCredentials = Lists.newArrayList(
//                MongoCredential.createCredential(
//                        "snapuser", "qatest", "snapuser".toCharArray()));
//        MongoClientOptions clientOptionsBuilder = getMongoClientOptionsBuilderWithNoSSL();
//        ServerAddress serverAddress1 = new ServerAddress("snapqamongo.clouddev.snaplogic.com");
//        List<ServerAddress> listAddresses = new ArrayList<>();
//        listAddresses.add(serverAddress1);

//        MongoClientURI uri = new MongoClientURI(
//                "mongodb://abanerjee:BK_roy111@test1-shard-00-00-0xlau.mongodb.net:27017," +
//                        "test1-shard-00-01-0xlau.mongodb.net:27017,test1-shard-00-02-0xlau." +
//                        "mongodb.net:27017/test2?ssl=true&replicaSet=test1-shard-0");
        MongoClient client = new MongoClient(listAddresses, mongoCredentials,
                clientOptionsBuilder);
//        MongoClient client = new MongoClient(uri);
        client.getDB("admin").command("ping");
        MongoDatabase database = client.getDatabase("test2");
        long count = database.getCollection("testcollection2").count();
        System.out.println("count value: " + count);
        client.close();
    }

    protected static MongoClientOptions getMongoClientOptionsBuilder() {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder = builder.sslEnabled(true).sslInvalidHostNameAllowed(true).socketFactory(getUnsafeSSLSocketFactory());;
        builder.connectTimeout(3000);
        builder.serverSelectionTimeout(3000);
        //builder.requiredReplicaSetName("test1-shard-0");
        return builder.build();
    }

    protected static MongoClientOptions getMongoClientOptionsBuilderWithNoSSL() {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.connectTimeout(1000);
        builder.serverSelectionTimeout(1000);
        return builder.build();
    }

    private static SocketFactory getUnsafeSSLSocketFactory() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            return sc.getSocketFactory();
        } catch (GeneralSecurityException e) {
            System.out.println("SecurityException:: " + e);
            return null;
        }
    }
}
