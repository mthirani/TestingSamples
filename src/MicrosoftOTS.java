import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by mayankthirani on 2/18/19.
 */
public class MicrosoftOTS {
    public static void main (String []args) {
        List<String> t1 = new ArrayList<>();
        List<String> t2 = new ArrayList<>();
        List<String> sum = new ArrayList<>();
        t1.add("1");
        t1.add("2");
        t2.add("10");
        t2.add("20");
        sum.addAll(t2);
        sum.addAll(t1);
        System.out.println(sum);
        List<TestOTS> testOTSCollection = new ArrayList<TestOTS>();
        TestOTS testOTS = new TestOTS(1, "test");
        testOTSCollection.add(testOTS);
        System.out.println(testOTSCollection.get(0));
        List<TestOTS> cloneTestOTSCollection = new ArrayList<TestOTS>(testOTSCollection);
        cloneTestOTSCollection.get(0).setID(2); // changing the id in cloned object (of original)
        System.out.println("ID returned from original for the object" + testOTSCollection.get(0)
                .getID());
        char testChar = 'c';
        String testStr = "TestCcString";
        int index = testStr.indexOf(testChar);
        System.out.println("Index :: " + index);
        testStr += testChar;
        System.out.println("New String :: " + testStr);

        List<Integer> newList = new ArrayList<>();
        newList.add(1);
        newList.add(-1);
        List<List<Integer>> addList = new ArrayList<>();
        addList.add(newList);
        System.out.println(addList);
        List<List<Integer>> addList2 = new ArrayList<>();
        addList2.addAll(addList);
        addList2.add(newList);
        System.out.println(addList2);

        TestOTS testOTS2 = new TestOTS(2, "test");
        TestOTS testOTS1 = new TestOTS(1, "test");
        TreeSet<TestOTS> testOTSTreeSet = new TreeSet<>();
        TreeSet<TestOTS2> testOTSTreeSet2 = new TreeSet<>(new Comparator<TestOTS2>() {
            @Override
            public int compare(final TestOTS2 o1, final TestOTS2 o2) {
                return o2.getID() - o1.getID();
            }
        });
        testOTSTreeSet.add(testOTS1);
        testOTSTreeSet.add(testOTS2);
        testOTSTreeSet.forEach((testOTSValue) -> {
            System.out.println("ID value in TreeSet :: " + testOTSValue.getID());
        });
        //sorting the list in reverse order of TreeSet's values to test the reverseOrder function
        List<TestOTS> testSort = new ArrayList<>(testOTSTreeSet);
        testSort.sort(Collections.reverseOrder());  //To use sort or Collections.reverseOrder()
        // here for a custom object, you need the class to implement the Comparable
        testSort.forEach( p -> System.out.println("ID value in List :: " + p.getID()));
        String str1 = "abc";
        String str2 = "ad";
        System.out.println("String Comparison:: " + str1.compareTo(str2));


        List<Integer> testIntSort = new ArrayList<>();
        testIntSort.add(2);
        testIntSort.add(3);
        testIntSort.sort(Collections.reverseOrder());
        System.out.println("Integer List :: " + testIntSort);


        TestOTS testOTSNew1 = new TestOTS(1, "test");
        System.out.println("Found in List :: " + testSort.contains(testOTS1));
        HashMap<Integer, TestOTS> hmap = new HashMap<>();
        hmap.put(1, testOTS1);
        hmap.put(2, testOTS2);
        List<Integer> testMap = new LinkedList<>(hmap.keySet());
        System.out.println("Found in Map :: " + hmap.containsValue(testOTSNew1));
        System.out.println("Map Keys :: " + testMap);
        double value = Double.POSITIVE_INFINITY;
        if (!Double.isFinite(value)) {
            System.out.println("double value: " + value);
        }

        String testStr1 = "testStr1";
        String testStr2 = "testStr2";
        testSwapStrings(testStr1, testStr2);
        System.out.println("After swapping, string1: " + testStr1);
        System.out.println("After swapping, string2: " + testStr2);

        //You cannot remove objects from TreeSet by specifying index. Also you cannot get
        // elements from TreeSet specifying index

        // Iterator of HashMap
        // Map.Entry<Integer, TestOTS> mapEntry = hmap.entrySet().iterator().next();

    }

    private static void testSwapStrings(String testStr1, String testStr2) {
        String temp = testStr1;
        testStr1 = testStr2;
        testStr2 = temp;
        System.out.println("In swapping function, string1: " + testStr1);
        System.out.println("In swapping function, string2: " + testStr2);
    }
}
class TestOTS2 {
    private int id;
    private String value;

    TestOTS2 (int id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
class TestOTS implements Comparable<TestOTS>{
    private int id;
    private String value;
    private static int value1;

    TestOTS (int id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String toString() {
        return "ID: " + id + ", Value: " + value;
    }

    /***
     * Think this function as o1.compareTo(o2) => if it returns -ve then o1 is smaller than o2
     * else o1 is larger than o2; -ve is expected by TreeSet for ascending order and it will keep
     * the order o1, o2.. If that returns +ve then TreeSet or other Collections will think that
     * o1 is greater than o2 so they will store o2 first in their list.
     * @param o
     * @return
     */
    @Override
    public int compareTo(final TestOTS o) {
        if (this.id < o.getID()) {
            return 1;   // think if [this.id - o.getID()], if that value is negative and you are
            // returning positive which is 1 then it is in descending sort (which means you
            // are returning positive for second object)
        } else {
            return -1;
        }
    }
}
