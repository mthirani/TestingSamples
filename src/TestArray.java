import java.util.Arrays;

/**
 * Created by mayankthirani on 7/19/20.
 */
public class TestArray {
    public static void main(String[] args) {
        String inp = "c1B";
        System.out.println(testChars(inp.charAt(0)));
        System.out.println(testChars(inp.charAt(1)));
        System.out.println(testChars(inp.charAt(2)));
        System.out.println("Compare result: " + "1000".compareTo("600"));
        int[] array = new int[]{4, 5, 7, 9};
        System.out.println(Arrays.binarySearch(array, 5));
    }

    public static String testChars(char c) {
        if (c >= 'a' && c <= 'z') {
            System.out.println("Int value for lowercase character: " + (int) c);
            return ("Lowercase found");
        }
        if (c >= 'A' && c <= 'Z') {
            System.out.println("Int value for uppercase character: " + (int) c);
            return ("Uppercase found");
        }
        if (c >= '0' && c <= '9') {
            System.out.println("Int value for number character: " + (int) c);
            return ("Number found");
        }

        return "No such chars found";
    }
}
