import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Java program to print all permutations of a
// given string.
public class PermutationWithRegex {
    public static void main(String[] args) {
//        String str = "ABC";
//        int n = str.length();
//        Permutation permutation = new Permutation();
//        permutation.permute(str, 0, n-1);

        //m.group(0) is the entire matched pattern

        Pattern p = Pattern.compile("(\\bthis\\b)");
        Matcher m = p.matcher("Print this.r");
        int count = 0;
        while (m.find()) {
            //System.out.println("Matches:: " + m.find());
            System.out.println("Group which matched first for the entire pattern:: " + m.group(0) +
                    ", count: " + count);
            System.out.println("Group which matched first:: " + m.group(1) + ", count: " + count);
            count++;
        }

        count = 0;
//        p = Pattern.compile("([.\\sa-zA-Z0-9*_()]*)(dt1)([.\\sa-zA-Z0-9*_()]*)");
        //p = Pattern.compile("\\bdt1\\b");   //Find all dt1's (even if dt1 contains .)
        //p = Pattern.compile("([.]*)(\\bdt1\\b)([.]*)"); //Find all dt1's
        p = Pattern.compile("(?![\\.])\\bdt1\\b(?![\\.])"); // ELT way of extracting-word with no
        // dots; Find exact dt1 not even with dt1. This is entire treated as expression, not to
        // extract on group basis.
        String sql = "select dt1.a from (select * from table_name) dt1;";
        sql = sql.replaceAll(";$", "");
        m = p.matcher(sql);
        while (m.find()) {
            //System.out.println("Matches:: " + m.find());
            System.out.println("Group which matched second for the entire pattern :: " + m.group
                    (0) + ", count: " + count);
            String result = m.replaceAll("derivedTableName");
            System.out.println("Result after replacing: " + result);
//            System.out.println("Group which matched second :: " + m.group(1) + ", count: " +
// count);
//            System.out.println("Group which matched second :: " + m.group(2) + ", count: " + count);
//            System.out.println("Group which matched second :: " + m.group(3) + ", count: " + count);
            count++;
        }

        count = 0;
        p = Pattern.compile("([.]*)([0-9]+)([.]*)");
//        p = Pattern.compile("([a-zA-Z\\s]*)(\\d+)([!a-zA-Z\\s?]*)");
        m = p.matcher("This order was placed for QT3000! OK?");
        while (m.find()) {
            //System.out.println("Matches:: " + m.find());
            System.out.println("Group which matched third for the entire pattern :: " + m.group
                    (0) + ", count: " + count);
            System.out.println("Group which matched third :: " + m.group(1) + ", count: " + count);
            System.out.println("Group which matched third :: " + m.group(2) + ", count: " + count);
            System.out.println("Group which matched third :: " + m.group(3) + ", count: " + count);
            count++;
        }

        p = Pattern.compile("([^\\d]*[\\d]+[^\\d]+)([\\d]+)");
        m = p.matcher("string1234more567string890");
        count = 0;
        while (m.find()) {
            System.out.println("Group which matched fourth for the entire pattern :: " + m.group
                    (0) + ", count: " + count);
            System.out.println("Group which matched fourth :: " + m.group(1) + ", count: " + count);
            System.out.println("Group which matched fourth :: " + m.group(2) + ", count: " + count);
            count++;
        }

        p = Pattern.compile("(bookid=)(\\d+)");
        m = p.matcher("bookname=cooking&bookid=123456&bookprice=123.45");
        count = 0;
        while (m.find()) {
            System.out.println("Group which matched fifth for the entire pattern :: " + m.group
                    (0) + ", count: " + count);
            System.out.println("Group which matched fifth :: " + m.group(1) + ", count: " + count);
            System.out.println("Group which matched fifth :: " + m.group(2) + ", count: " + count);
            count++;
        }

        p = Pattern.compile("(?<=bookid=)\\d+"); // this is entire treated as expression, not to
        // extract on group basis. So either group() or group(0) will be same and we don't get
        // any result in group(1) etc..
        m = p.matcher("bookname=cooking&bookid=123456&bookprice=123.45");
        count = 0;
        while (m.find()) {
            System.out.println("Group which matched sixth for the entire pattern :: " + m.group
                    (0) + ", count: " + count);
            count++;
        }

    }

    /**
     * permutation function
     *
     * @param str string to calculate permutation for
     * @param l   starting index
     * @param r   end index
     */
    private void permute(String str, int l, int r) {
        if (l == str.length())
            System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                System.out.println("Calling for :: " + str.charAt(l));
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    /**
     * Swap Characters at position
     *
     * @param a string value
     * @param i position 1
     * @param j position 2
     *
     * @return swapped string
     */
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

}

// This code is contributed by Mihir Joshi