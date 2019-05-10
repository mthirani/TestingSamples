import com.google.common.base.Throwables;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by mayankthirani on 6/27/18.
 */
public class ExceptionTesting {
    int test;

    public static void main (String []args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        changeList(linkedList);
        System.out.println("Size of linked list: " + linkedList.size());
        try {
            testExceptionInParent();
            System.out.println("No Exception caught in main(): ");
            System.out.println("No Exception2 caught in main(): ");
        } catch (SQLException e) {
            Throwable t = Throwables.getRootCause(e);
            System.out.println("Exception caught in main(): " + t);
        }
    }

    private static void changeList(LinkedList<Integer> linkedList) {
        try {
            // LinkedList<Integer> temp = new LinkedList<>(linkedList);
            linkedList.add(2);
            //return;   // Still it will invoke the finally block
            throw new RuntimeException("Test Exception");
        } catch (Exception e) {
            System.out.println("Exception found in changeList module");
            return;    // Still it will invoke the finally block
        } finally {
            System.out.println("Finally invoked in changeList module");
        }
    }

    private static void testExceptionInParent() throws SQLException {
        testExceptionInChild();
    }

    // throws keyword is used when we don't want to handle the Exception in that module and asked
    // parent to handle it. Only used for Compile-time Exceptions. For Run-Time Exception, no
    // need to use throws keyword as JVM will throw error if exception occurred at run time but
    // it should be good if we handle the Run-Time Exception directly.
    private static void testExceptionInChild() throws SQLException{
        throw new SQLException("Exception occurred in Child");
    }
}
