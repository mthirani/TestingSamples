/*
 * Java program to get largest palindrome changing
 */

import java.text.ParseException;

class PallindromicStringProblem {

    static final String NOT_POSSIBLE = "-1";
    static final char DIGIT_9 = '9';

    /*
     * Returns maximum possible palindrome using k changes
     */
    static String maximumPalinUsingKChanges(String str, int n, int k) {
        char palin[] = str.toCharArray();
        int l = 0;
        int r = n - 1;

        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) {
                palin[l] = palin[r] = (char) Math.max(str.charAt(l), str.charAt(r));
                k--;
            }
            l++;
            r--;
        }

        if (k < 0) {
            return NOT_POSSIBLE;
        }

        l = 0;
        r = n - 1;

        while (l <= r) {
            if (l == r) {
                if (k > 0) {
                    palin[l] = DIGIT_9;
                }
            }

            if (palin[l] < DIGIT_9) {
                if (k >= 2 && palin[l] == str.charAt(l) && palin[r] == str.charAt(r)) {
                    k -= 2;
                    palin[l] = palin[r] = DIGIT_9;
                } else if (k >= 1 && (palin[l] != str.charAt(l) || palin[r] != str.charAt(r))) {
                    k--;
                    palin[l] = palin[r] = DIGIT_9;
                }
            }
            l++;
            r--;
        }

        return new String(palin);
    }

    /*
     * Driver code with various test cases to test above method
     */
    public static void main(String[] args) throws ParseException {
        String str = "0011";
        int k = 1;
        System.out.println(maximumPalinUsingKChanges(str, str.length(), k));
        str = "0011";
        k = 3;
        System.out.println(maximumPalinUsingKChanges(str, str.length(), k));
        k = 3;
        str = "092282";
        System.out.println(maximumPalinUsingKChanges(str, str.length(), k));
        str = "3943";
        k = 1;
        System.out.println(maximumPalinUsingKChanges(str, str.length(), k));
        str = "0";
        k = 0;
        System.out.println(maximumPalinUsingKChanges(str, str.length(), k));
        str = "0";
        k = 1;
        System.out.println(maximumPalinUsingKChanges(str, str.length(), k));
        str = "";
        k = 1;
        System.out.println(maximumPalinUsingKChanges(str, str.length(), k));
    }
}

