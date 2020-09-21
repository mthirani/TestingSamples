import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mayankthirani on 2/6/20.
 */
public class CrossAccountAWSAccess {

    private static void displayTextInputStream(InputStream input) throws IOException {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            System.out.println("    " + line);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("111");
        list.add("322");
        String st = String.join(",", list);
        AWSSecurityTokenServiceClientBuilder builder = AWSSecurityTokenServiceClientBuilder
                .standard();
        AWSSecurityTokenService client = builder.build();
        AssumeRoleRequest request = new AssumeRoleRequest().withRoleArn("arn:aws:iam::412864912850:role/CrossAccountMthirani")
                .withRoleSessionName("SLExtreme_IAMRoleARNAccountSession")
                .withExternalId("1234567890")
                .withDurationSeconds(3600);
        AssumeRoleResult response = client.assumeRole(request);
        System.out.println(response.getCredentials().getAccessKeyId());
        System.out.println(response.getCredentials().getSecretAccessKey());
        System.out.println(response.getCredentials().getSessionToken());

        request = new AssumeRoleRequest().withRoleArn("arn:aws:iam::412864912850:role/CrossAccountMthirani_ExternalID")
                .withRoleSessionName("SLExtreme_IAMRoleARNAccountSession")
                .withExternalId("ExternalIDTest1234")
                .withDurationSeconds(3600);
        AssumeRoleResult response2 = client.assumeRole(request);
        System.out.println(response2.getCredentials().getAccessKeyId());
        System.out.println(response2.getCredentials().getSecretAccessKey());
        System.out.println(response2.getCredentials().getSessionToken());

        AWSCredentialsProvider awsCredentialsProvider = new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials
                        (response2.getCredentials().getAccessKeyId(), response2.getCredentials()
                                .getSecretAccessKey(), response2.getCredentials().getSessionToken());
                return basicSessionCredentials;
            }

            @Override
            public void refresh() {

            }
        };

        AmazonS3ClientBuilder s3 = AmazonS3Client.builder();
        s3.setCredentials(awsCredentialsProvider);
        AmazonS3 s3Client = s3.build();

        //Same thing for AWS KMS

        System.out.println("Bucket Location:: " + s3Client.getBucketLocation("mthirani"));

        System.out.println("Current date:: " + new Date());

        request = new AssumeRoleRequest().withRoleArn("arn:aws:iam::412864912850:role/AmazonRedshiftIAMRole_Mthirani")
                .withRoleSessionName("SLExtreme_IAMRoleARNAccountSession")
                .withDurationSeconds(3600);
        AssumeRoleResult response3 = client.assumeRole(request);
        AWSCredentialsProvider awsCredentialsProvider3 = new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials
                        (response3.getCredentials().getAccessKeyId(), response3.getCredentials()
                                .getSecretAccessKey(), response3.getCredentials().getSessionToken
                                ());
                return basicSessionCredentials;
            }

            @Override
            public void refresh() {

            }
        };
        s3 = AmazonS3Client.builder();
        s3.setCredentials(awsCredentialsProvider3);
        s3Client = s3.build();
        System.out.println("Bucket Location Again :: " + s3Client.getBucketLocation("mthirani"));
        ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request().withBucketName
                ("mthirani").withPrefix("hd5_files/jar");
        ListObjectsV2Result result = s3Client.listObjectsV2(listObjectsV2Request);
        for (final S3ObjectSummary s3ObjectSummary : result.getObjectSummaries()) {
            String fileName = s3ObjectSummary.getKey();
            System.out.println("Key:: " + fileName);
            System.out.println("Extracted Filename:: " + fileName.substring(fileName.lastIndexOf
                    ("/") + 1));
            Pattern p = Pattern.compile("(.)*(-BETA)");
            Matcher matcher = p.matcher(fileName.substring(fileName.lastIndexOf("/")));
            if (matcher.find()) {
                System.out.println("File matched with pattern");
            }
        }


//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
//         .build();
//
//        try {
//            System.out.println("Downloading an object");
//            S3Object s3object = s3Client.getObject(
//                    new GetObjectRequest("mthirani", "wordcount.py"));
//            displayTextInputStream(s3object.getObjectContent());
//        }
//        catch(AmazonServiceException ase) {
//            System.err.println("Exception was thrown by the service:: \n" + ase);
//        }
//        catch(AmazonClientException ace) {
//            System.err.println("Exception was thrown by the client:: \n" + ace);
//        }

//        URL url = null;
//        try {
//            url = new URL("https://sts.amazonaws.com?Version=2011-06-15&Action=AssumeRole" +
//                    "&RoleArn=arn:aws:iam::412864912850:role/CrossAccountMthirani" +
//                    "&ExternalId=crossaccountexternalid&AUTHPARAMS");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            URLConnection con = url.openConnection();
//            byte[] byteChunk = new byte[4096];
//            InputStream inputStream = con.getInputStream();
//            int length;
//            int downloaded = 0;
//            while ((length = inputStream.read(byteChunk)) != -1) {
//                baos.write(byteChunk, 0, length);
//            }
//            System.out.println("Size:: " + baos.size());
//            byte[] totalBytes = baos.toByteArray();
//            System.out.println("Output:: " + new String(totalBytes));
//            baos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet request = new HttpGet("https://sts.amazonaws.com?Version=2011-06-15&Action=AssumeRole" +
//                "&RoleSessionName=testAR"+
//                "&RoleArn=arn:aws:iam::412864912850:role/CrossAccountMthirani" +
////                "&Policy=%7B%22Version%22%3A%222012-10-17%22%2C%22Statement%22%3A%5B%7B%22Sid%22" +
////                "%3A%20%22Stmt1%22%2C%22Effect%22%3A%20%22Allow%22%2C%22Action%22%3A%20%22s3%3A" +
////                "*%22%2C%22Resource%22%3A%20%22*%22%7D%5D%7D" +
////                "&PolicyArns.member.1.arn=arn:aws:iam::aws:policy/AmazonEC2FullAccess" +
////                "&PolicyArns.member.2.arn=arn:aws:iam::412864912850:policy/CrossAccountMthirani" +
////                "&PolicyArns.member.3.arn=arn:aws:iam::aws:policy/service-role/AmazonElasticMapReduceRole" +
//                "&DurationSeconds=43200" +
//                "&ExternalId=crossaccountexternalid&AUTHPARAMS");
//        CloseableHttpResponse response = httpClient.execute(request);
//
//        try {
//
//            // Get HttpResponse Status
//            System.out.println(response.getProtocolVersion());              // HTTP/1.1
//            System.out.println(response.getStatusLine().getStatusCode());   // 200
//            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
//            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK
//
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                // return it as a String
//                String result = EntityUtils.toString(entity);
//                System.out.println(result);
//            }
//
//        } finally {
//            response.close();
//            httpClient.close();
//        }
    }
}
