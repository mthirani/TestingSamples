import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mayankthirani on 1/8/20.
 */
public class Rippling {
    public static Map<Integer, List> hmap = new HashMap<>();

    public static void main(String[] args) {
        hmap.put(1, Arrays.asList(0, 2));
        hmap.put(2, Arrays.asList(1, 3));
        hmap.put(3, Arrays.asList(2, 1));
        hmap.put(4, Arrays.asList(2, 3));
        hmap.put(5, Arrays.asList(4, 6));
        hmap.put(6, Arrays.asList(5, 7));
        hmap.put(7, Arrays.asList(6, 8));
        hmap.put(8, Arrays.asList(7, 9));
        hmap.put(9, Arrays.asList(8, 0));
        hmap.put(0, Arrays.asList(1, 9));

        // "i like to code" --> "code to like i"
        //[i, '' ,l,i, k, '' ,c, o]
        // index = 6
        // [i, l, i, k, c, o]


        /*
        i like to code
        edoc ot ekil i
        code to like i
         */
        //System.out.println(reverseString("i like to code"));
        System.out.println(reverseString("they like to code"));
//        System.out.println(reverseString("i   like to code"));
//        System.out.println(reverseString("i     "));
//        System.out.println(reverseString("    i"));
//        System.out.println(reverseString("    i    "));
//        System.out.println(reverseString("    i    like   "));

        List<Integer> list = new ArrayList<>();
        jumpingNumbers(200, "", list);
        System.out.println(list);
        System.out.println(list.size());

    }

    /*
     * The below function reverses the string without using any space. So space complexity is O(1)
     */
    public static String reverseString(String input) {
        char[] str = input.toCharArray();
        int i = 0, j = str.length - 1;
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        } //edoc ot ekil i
        //System.out.println("String:: " + new String(str));

        i = 0;
        int beg = 0;
        while (i < str.length) {
            if (str[i] == ' ') {
                //System.out.println("space found:: " + i + ", beg found:: " + beg);
                int end = i - 1;
                int start = beg;
                while (start < end) {
                    char temp = str[start];
                    str[start] = str[end];
                    str[end] = temp;
                    start++;
                    end--;
                }
                beg = i + 1;
            }
            i++;
        }

        while (beg < i) {
            char temp = str[beg];
            str[beg] = str[i - 1];
            str[i - 1] = temp;
            beg++;
            i--;
        }

        return new String(str);
    }

    public static void jumpingNumbers(int max, String num, List output) {
        if (compare(num, max)) {
            output.add(Integer.parseInt(num));
        } else {
            if (num.length() != 0) {
                return;
            }
        }
        if (num.length() == 0) {
            for (int i = 1; i <= 9; i++) {
                jumpingNumbers(max, num + i, output);
            }
        } else {
            int key = num.charAt(num.length() - 1) - '0';
            List<Integer> list = hmap.get(key);
            for (int value : list) {
                jumpingNumbers(max, num + value, output);
            }
        }
    }

    public static boolean compare(String num, int max) {
        if (num.equals("")) {
            return false;
        }
        return Integer.parseInt(num) <= max;
    }
}
