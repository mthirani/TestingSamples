import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

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

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream(),
                "UTF-8"))) {
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream(),
                "UTF-8"))) {
            StringBuffer result = new StringBuffer();
            int c;
            while ((c = rd.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream rd = new BufferedInputStream(url.openStream())) {
            System.out.println("\nAvailable:: " + rd.available());
            byte []bf = new byte[1000];
            StringBuffer result = new StringBuffer();
            int c;
            while ((c = rd.read(bf, 0, bf.length)) != -1) {
                System.out.print(new String(bf));
                Arrays.fill(bf, (byte) 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
