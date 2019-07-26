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
    static String maximumPalinUsingKChanges(String str, int k) {
        char palin[] = str.toCharArray();
        String result = "";
        int l = 0;
        int r = str.length() - 1;

        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) {
                palin[l] = palin[r] = (char) Math.max(str.charAt(l),
                        str.charAt(r));
                k--;
            }
            l++;
            r--;
        }

        if (k < 0) {
            return NOT_POSSIBLE;
        }

        l = 0;
        r = str.length() - 1;

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
        for(int i = 0; i < palin.length; i++) {
            result = result + palin[i];
        }
        return result;
    }

    /*
     * Driver code with various test cases to test above method
     */
    public static void main(String[] args) throws ParseException {
        String str = "0011";
        int k = 1;
        System.out.println(maximumPalinUsingKChanges(str, k));
        str = "0011";
        k = 3;
        System.out.println(maximumPalinUsingKChanges(str, k));
        k = 3;
        str = "092282";
        System.out.println(maximumPalinUsingKChanges(str, k));
        str = "3943";
        k = 1;
        System.out.println(maximumPalinUsingKChanges(str, k));
        str = "0";
        k = 0;
        System.out.println(maximumPalinUsingKChanges(str, k));
    }
}

