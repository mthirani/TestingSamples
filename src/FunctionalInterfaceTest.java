/**
 * Created by mayankthirani on 3/29/18.
 */
@FunctionalInterface
interface FunctionalInterfaceTest {
    static int deletion(int a, int b) {
        return a - b;
    }

    String printString(String a);

    default void add(int a, int b) {
        System.out.println ("Addition result: " + (a + b));
    }
}
