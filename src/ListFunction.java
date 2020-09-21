import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mayankthirani on 7/11/20.
 */
public class ListFunction {
    A head;

    public static void main(String[] args) {
        List<String> mapValues = new ArrayList<>();
        mapValues.add("A");
        mapValues.add("B");
        List<String> mapValues1 = new ArrayList<>();
        mapValues1.add("A1");
        mapValues1.add("B1");
        Map<String, List> map = new HashMap<>();
        map.put("1", mapValues);
        map.put("2", mapValues1);
        Collection<List> out = map.values();
        System.out.println(out);
        A a1 = new A(1);
        A a2 = new A(2);
        A a3 = new A(3);
        A a4 = new A(4);
        A a5 = new A(4);
        A a6 = new A(2);
        A a11 = new A(1);
        a1.next = a11;
        a11.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        A top = a1;
        top = deleteNode(top, 3);
        printValues(top);
    }

    private static void printValues(A top) {
        while (top != null) {
            System.out.println("Value: " + top.v);
            top = top.next;
        }
    }

    /*
     * Delete one or multiple node if value matches with target specified
     */
    private static A deleteNode(A top, int target) {
        A tmp = top;
        while (top != null && top.v == target) {
            top = top.next;
        }
        A first = top;
        while (top != null) {
            if (top.v == target) {
                tmp.next = top.next;
                // for deleting only 1 node
                //break;
                //for deleting multiple node with same value found
                top = top.next;
            } else {
                tmp = top;
                top = top.next;
            }
        }

        return first;
    }

    static class A {
        int v;
        A next;

        A(int v) {
            this.v = v;
            next = null;
        }
    }
}
