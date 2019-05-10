import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mayankthirani on 6/12/18.
 */
public class ThreadSample {
    class TestObject {
        String str;
        public TestObject(String str) {
            this.str = str;
        }

        public String getString() {
            return str;
        }
    }

    private final static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final static Integer integer = new Integer(12);
    private final static StringBuffer str = new StringBuffer("Mayank");
    public static void main(String []args) throws InterruptedException {
        ThreadSample threadSample1 = new ThreadSample();
        ThreadSample threadSample2 = new ThreadSample();
        TestObject testObject1 = threadSample1.new TestObject("https://aa.pvcloud" +
                ".com/planview/Services/ExternalKeyUriMapService.svc?xsd=xsd0");
        TestObject testObject2 = threadSample2.new TestObject("https://aa.pvcloud" +
                ".com/planview/Services/ExternalKeyUriMapService.svc?xsd=xsd0");
        byteArrayOutputStream.write(12);
        byteArrayOutputStream.write(14);
        str.replace(0, str.length(), "T"); // You can change or append or
        // delete the value to this final variable but cannot assign a new object to this reference
        // So final means :: define an entity that can only be assigned once. If it is a Integer
        // with a value then that value cannot be changed but objects like StringBuffer, you can
        // append or delete the value but cannot assign to a new instances
        System.out.println("New String:: " + str.toString());
        AtomicInteger a = new AtomicInteger(10);
//        ThreadTest t = new ThreadTest(a);
//        Thread test = new Thread(t);
//        test.setDaemon(true);
//        test.start();
//        for (int i = 0; i < 1; i++) {
//            a.addAndGet(1);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("In main: " + a.get());
//        }
        Test t1 = new Test(1, "T");
        Test t2 = new Test(1, "T");
        System.out.println("HashCode t1: " + t1.hashCode());
        System.out.println("HashCode t2: " + t2.hashCode());
        boolean checkEquals = (t1 == t2);
        System.out.println("Equals or not based on equals function : " + t1.equals(t2));
        System.out.println("Equals or not based on reference check: " + checkEquals);

        HashMap<Test, Test> h1 = new HashMap<>();
        //While putting the values in hashMap, it uses the equals() and hashCode() to see if the
        // object can be overriden
        h1.put(t1, t1);
        h1.put(t2, t2);
        h1.forEach((k, v) -> {
            System.out.println("Key: " + k + ", Value: " + v);
        });
        System.out.println("Size h1 : " + h1.size());

        HashMap<Integer, Integer> h2 = new HashMap<>();
        h2.put(new Integer(1),1);
        h2.put(new Integer(1),3);
        System.out.println("Size h2: " + h2.size() + ", Value : " + h2.get(1));

        HashMap<String, Integer> h3 = new HashMap<>();
        String a1 = "Testing HashMap";
        String a2 = "Testing HashMap";
        h3.put(a1, 1);
        h3.put(a2, 1);
        System.out.println("Size h3: " + h3.size());

        HashMap<String, Integer> h4 = new HashMap<>();
        String a3 = new String("Testing HashMap");
        String a4 = new String("Testing HashMap");
        h4.put(a3, 1);
        h4.put(a4, 1);
        System.out.println("Size h4: " + h4.size());

        URI uri1 = URI.create(testObject1.getString());
        URI uri2 = URI.create(testObject2.getString());
        ThreadTest2 t = new ThreadTest2();
        Thread test = new Thread(t);
        test.start();
        final HashMap<URI, Integer> h5 = new HashMap<>();
        h5.put(uri1, 1);
        h5.put(uri2, 2);
        System.out.println("Size h5: " + h5.size());
        System.out.println("In mainThread: " + uri1.hashCode());
    }
}
class ThreadTest implements Runnable {
    AtomicInteger atomicInteger;
    ThreadTest(AtomicInteger atomicInteger){
        this.atomicInteger = atomicInteger;
    }

    public void run() {
        while(true){
            System.out.println("In thread: " + atomicInteger.get());
//            if (atomicInteger.get() == 11)
//                return;
            System.out.println("In thread, no more");
        }
    }
}
class ThreadTest2 implements Runnable {
    URI uri;
    ThreadTest2(){
        try {
            String createURI1 = new String("https://aa.pvcloud.com/planview/Services/ExternalKeyUriMapService.svc?xsd=xsd0");
            this.uri = new URI(createURI1);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("In thread2: " + uri.hashCode());
        System.out.println("In thread2, no more");
    }
}
class Test{
    int A;
    String B;
    Test (int A, String B) {
        this.A = A;
        this.B = B;
    }

    public String toString() {
        return "test";
    }

    @Override
    public int hashCode() {
        return 1;
    }   //Used mainly for hashCode() function

    @Override
    public boolean equals(final Object obj) {    //Used mainly for equals()/ hashCode() function
        return true;
    }
}