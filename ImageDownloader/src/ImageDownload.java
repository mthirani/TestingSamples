import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ImageDownload {
    public static final String CACHE = "CACHE";
    public static final String DOWNLOADED = "DOWNLOADED";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Provide correct number of arguments");
            return;
        }
        if (!args[1].endsWith(".txt") || !args[0].endsWith(".txt")) {
            System.out.println("Provide parameters as text file as .txt extension");
            return;
        }
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        List<String> imageURLs = new ArrayList<>();
        BufferedReader bufferedReader = null;
        SimpleLru<String, byte[]> simpleLru = null;
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(outputFilePath);
            bufferedReader = new BufferedReader(new FileReader(inputFilePath));
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            boolean firstLine = true;
            while (bufferedReader != null) {
                if (firstLine) {
                    int cacheSize = Integer.parseInt(bufferedReader.readLine());
                    firstLine = false;
                    simpleLru = new SimpleLru(cacheSize);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    String imageURL = bufferedReader.readLine();
                    if (imageURL == null) {
                        break;
                    }
                    String sha256 = toHexString(getSHA256(imageURL));
                    stringBuilder.append(imageURL).append(" ");
                    imageURLs.add(imageURL);
                    if (simpleLru.get(imageURL) != null) {
                        stringBuilder.append(CACHE).append(" ").append(simpleLru.get(imageURL).length)
                                .append(" ").append(sha256);
                        bw.write(stringBuilder.toString());
                        bw.newLine();
                    } else {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        URL url = new URL(imageURL);
                        URLConnection con = url.openConnection();
                        byte[] byteChunk = new byte[4096];
                        InputStream inputStream = con.getInputStream();
                        int length;
                        while ((length = inputStream.read(byteChunk)) != -1) {
                            baos.write(byteChunk, 0, length);
                        }
                        stringBuilder.append(DOWNLOADED).append(" ").append(baos.size())
                                .append(" ").append(sha256);
                        simpleLru.put(imageURL, baos.toByteArray());
                        inputStream.close();
                        bw.write(stringBuilder.toString());
                        bw.newLine();
                    }
                }
            }
            bw.close();
            fos.close();
            bufferedReader.close();
            System.out.println("!! Work Accomplished !!");

        } catch (IOException e) {
            System.out.println("!! Work Failed !!");
            System.out.println(e.getMessage());
        }
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    private static byte[] getSHA256(final String imageURL) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(imageURL.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * This class is an implementation of LRU Cache
 *
 * @param <K>
 * @param <V>
 */
class SimpleLru<K, V> extends LinkedHashMap<K, V> {

    final int capacity;
    int size;
    int count;

    public SimpleLru(int capacity) {
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (this.size() > this.capacity) {
            this.size = this.size() - ((byte[]) eldest.getValue()).length;
            count--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public V put(final K key, final V value) {
        size += ((byte[]) value).length;
        count++;
        return super.put(key, value);
    }

    @Override
    public int size() {
        return size;
    }

    public int getCount() {
        return count;
    }
}
