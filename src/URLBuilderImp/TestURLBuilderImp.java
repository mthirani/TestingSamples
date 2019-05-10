package URLBuilderImp;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by mayankthirani on 4/12/19.
 */
public class TestURLBuilderImp {
    public static void main(String []args) {
        try {
            String url = "abfs:///filesystem2/dir1/test.txt";
            String userInfo = "abfs.end.point";
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            System.out.println("host Info :: " + host);
            if (uri.getUserInfo() != null) {
                System.out.println("User Info is not null");
            }
            String path = uri.getRawPath();
            System.out.println("get raw path :: " + path);
            String []elements = path.split("/");
            host = userInfo;
            userInfo = elements[1];
            path = path.substring(elements[1].length() + 1);
            switch (scheme) {
                default:
                    if (host == null) {
                        host = StringUtils.EMPTY;
                    }
                    uri = URI.create("abfs:///" + path);
            }
            URIBuilder uriBuilder = new URIBuilder(uri).setHost(host);
            switch (scheme) {
                default:
                    uriBuilder.setUserInfo(userInfo);
            }
            int port = uri.getPort();
            if (port > 0) {
                uriBuilder.setPort(port);
            }
            URI uriTest = uriBuilder.build();
            System.out.println("Test the new formed URI :: " + uriTest.toString());
        } catch (URISyntaxException e) {
            System.out.println("URISyntaxException e :: " + e);
        }
    }
}
