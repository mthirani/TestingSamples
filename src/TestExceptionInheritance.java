import com.google.common.base.Throwables;

/**
 * Created by mayankthirani on 12/4/18.
 */
public class TestExceptionInheritance {
    static class A {
        static void func() {
            throw new RuntimeException("A");
        }
    }

    static class B {
        static void func() {
            try {
                A.func();
            } catch (Throwable t) {
                throw new RuntimeException("B", t);
            }
        }
    }

    static class C {
        static void func1() {
            System.err.println("func1");
            try {
                B.func();
            } catch (Throwable t) {
                throw new RuntimeException("C", t);
            }
        }
        static void func2() {
            System.err.println("func2");
            try {
                B.func();
            } catch (Throwable t) {
                throw new RuntimeException("C", Throwables.getRootCause(t));
            }
        }
    }

    public static void main(String[] args) {
        try {
            C.func1();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        try {
            C.func2();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
