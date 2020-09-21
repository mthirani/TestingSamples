import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mayankthirani on 12/17/18.
 */
public class FileReading {
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("https://aa.pvcloud.com/planview/Services/ExternalKeyUriMapService" +
                    ".svc?xsd=xsd131");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        try (BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream(),
//                "UTF-8"))) {
//            StringBuffer result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream(),
//                "UTF-8"))) {
//            StringBuffer result = new StringBuffer();
//            int c;
//            while ((c = rd.read()) != -1) {
//                System.out.print((char) c);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedInputStream rd = new BufferedInputStream(url.openStream())) {
//            System.out.println("\nAvailable:: " + rd.available());
//            byte []bf = new byte[1000];
//            StringBuffer result = new StringBuffer();
//            int c;
//            while ((c = rd.read(bf, 0, bf.length)) != -1) {
//                System.out.print(new String(bf));
//                Arrays.fill(bf, (byte) 0);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            url = new URL("http://i.imgur.com/cz0yhtx.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] totalBytes = new byte[2464218];
        try {
            URLConnection con = url.openConnection();
            byte[] byteChunk = new byte[4096];
            InputStream inputStream = con.getInputStream();
            int length;
            int downloaded = 0;
            while ((length = inputStream.read(byteChunk)) != -1) {
                baos.write(byteChunk, 0, length);
            }
            System.out.println("Size:: " + baos.size());
            totalBytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        try {
            baos2.write(totalBytes);
            baos2.write("\n".getBytes());
            System.out.println("Byte array output stream:: " + new String(baos2.toByteArray()));
            baos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
