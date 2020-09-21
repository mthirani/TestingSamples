import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.stream.Stream;

//import java.net.URLDecoder;

public class Sample {
    public static void main(String[] args) {

        try {
            testException();
        } catch (NullPointerException e) {
            System.out.println("Exception in main class");
        }

        Gender genderType = Gender.MALE;
        if (genderType == Gender.MALE) {
            System.out.println(genderType + " found");
        }
        genderType = null;
        if (genderType == Gender.MALE) {
            System.out.println("Male found again");
        }

        /*
         * Test the Java 8 features
         */

        FunctionalInterfaceTest t = (s) -> {
            System.out.println ( "Hello " + s );
            return "Hello " + s;
        };
        setName ( "Mayank", t );
        t.add ( 3, 4 );
        int deletionResult = FunctionalInterfaceTest.deletion (6, 5);
        System.out.println ( "Deletion Result: " + deletionResult );

        List <Integer> myList = new ArrayList <> ( );
        myList.add (6);
        myList.add (5);
        for (int i = 0; i < 5; i++) myList.add ( i );
        myList.forEach (System.out::println);        //i -> System.out.println ( i )

        //sequential stream
        Stream <Integer> sequentialStream = myList.stream ( );

        //parallel stream
        Stream <Integer> parallelStream = myList.parallelStream ( );

        sequentialStream.forEach ( p -> System.out.println ( "Nums in sequential=" + p ) );
        parallelStream.forEach ( p -> System.out.println ( "Nums in parallel=" + p ) );

        List <String> replaceList = new ArrayList <> ( );
        replaceList.add ( "Mayank" );
        replaceList.add ( "Nishant" );
        replaceList.add ( "Ganesh" );
        replaceList.add ( "Sarita" );
        replaceList.add ( "Mayank" );

        Collections.replaceAll ( replaceList, "Mayank", "MT" );

        replaceList.forEach ( p -> System.out.println ( "Replacing String function: " + p ) );
        replaceList.removeIf ( p -> p.equals ( "MT" ) );
        replaceList.forEach ( p -> System.out.println ( "Removed String function: " + p ) );
        List <String> addedString = new ArrayList <> ( );
        addedString.add ( "Thirani" );
        addedString.add ( "Maheswari" );
        replaceList.addAll ( addedString );
        Spliterator <String> spliterator = replaceList.spliterator ( );
        spliterator.forEachRemaining (a -> System.out.print ("value: " + a + "\n"));

        Map <String, Integer> mapObject = new HashMap <> ( );
        mapObject.put ( "a", 4 );
        mapObject.put ( "d", 11 );
        mapObject.put ( "b", 0 );
        mapObject.put ( "c", 3 );
        mapObject.putIfAbsent ("e", 5);
        mapObject.forEach ( (k, v) -> System.out.println ( "Keys in Map: " + k ) );

        Stream <Map.Entry <String, Integer>> sortedKey =
                mapObject.entrySet ( ).stream ( )
                        .sorted ( Map.Entry.comparingByKey ( ) );
        sortedKey.forEach ( p -> System.out.println ( "Sorting Map By Key: " + p.getKey ( ) ) );

        Stream <Map.Entry <String, Integer>> sortedValue =
                mapObject.entrySet ( ).stream ( )
                        .sorted ( Collections.reverseOrder ( Map.Entry.comparingByValue ( ) ) );
        sortedValue.forEach ( p -> System.out.println ( "Descending Sorting Map By Value: " + p.getValue ( ) ) );

        Stream <Map.Entry <String, Integer>> sortedValueComparator =
                mapObject.entrySet ( ).stream ( )
                        .sorted ( Map.Entry.comparingByKey ( (o1, o2) -> o2.compareTo ( o1 ) ) );
        sortedValueComparator.forEach (p -> System.out.println ("Descending Sorting Map By Key: " + p.getKey ( )));
        mapObject.compute ( "a", (key, value) -> {
                    System.out.println ( key + ", " + value );
                    return 1;
                }
        );
        mapObject.forEach ((k, v) -> System.out.println ("Keys and Values in Map: " + k + ":" + v));
        mapObject.replaceAll ((k, v) -> 4);
        mapObject.forEach ((k, v) -> System.out.println ("Now Keys and Values in Map: " + k + ":"
                + v));

        double s = myList.stream ( ).mapToDouble ( Number::doubleValue ).sum ( );
        System.out.println ( s );
        CompareClass c1 = new CompareClass(1, "Mayank");
        CompareClass c2 = new CompareClass(2, "Nishant");
        TreeSet<CompareClass> treeSet = new TreeSet<>((ob1, ob2) -> ob2.name.compareTo(ob1.name));
        treeSet.add(c1);
        treeSet.add(c2);
        treeSet.forEach(ob -> System.out.println("Value in TreeSet:: " + ob));
        /*
         * Test the Encoding methods
         *

        String original = new String ("This is my string valúe");
        byte ptext[] = new byte[0];
        try {
            ptext = original.getBytes ("UTF-8");
            String value = new String (ptext, "UTF-8");
            System.out.println ("UTF-8 string : " + value);
            String urlDecodedString = URLDecoder.decode (original, "UTF-8");
            System.out.println ("URL Decoded String : " + urlDecodedString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ( );
        }

        /*
         * Now Test the Annotations in Class
         */

        System.out.println("\n\nTesting Annotations now in class...\n\n");
        TestExample testExample = new TestExample();
        Class <?> testExampleClass = testExample.getClass();

        System.out.println("Valid Class: " + (testExampleClass == TestExample.class));
        // Process @TesterInfo
        if (testExampleClass.isAnnotationPresent(TesterInfo.class)) {

            Annotation annotation = testExampleClass.getAnnotation(TesterInfo.class);
            TesterInfo testerInfo = (TesterInfo) annotation;

            System.out.printf ("%nPriority :%s", testerInfo.priority ( ));
            System.out.printf ("%nCreatedBy :%s", testerInfo.createdBy ( ));
            System.out.printf ("%nTags :");

            int tagLength = testerInfo.tags ( ).length;
            for (String tag : testerInfo.tags ( )) {
                if (tagLength > 1) {
                    System.out.print (tag + ", ");
                } else {
                    System.out.print (tag);
                }
                tagLength--;
            }

            System.out.printf ("%nLastModified :%s%n%n", testerInfo.lastModified ( ));

        }

        /*
         * Now Test the Annotations in each method of that class
         */

        System.out.println("\n\nTesting Annotations now in each method of that class...\n\n");
        int passed = 0, failed = 0, count = 0, ignore = 0;

        // Process @TestingAnnotations
        for (Method method : testExampleClass.getDeclaredMethods()) {

            // if method is annotated with @TestingAnnotations
            if (method.isAnnotationPresent(TestingAnnotations.class)) {

                Annotation annotation = method.getAnnotation(TestingAnnotations.class);
                TestingAnnotations test = (TestingAnnotations) annotation;

                // if enabled = true (default)
                if (test.enabled()) {

                    try {
                        method.invoke(testExampleClass.newInstance());
                        System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }

                } else {
                    System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                    ignore++;
                }

            }

        }
        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);

        /**
         * Test References
         */

        List<String> testList = Arrays.asList("A", "B", "C", "D");
        System.out.println("testList:: "+ testList);
        List<String> testTestList = new ArrayList <>(testList);
        testTestList.removeIf( a -> a.equals("A"));
        System.out.println("testList:: "+ testList);
        System.out.println("testTestList:: "+ testTestList);

        /*
         * Testing the java.net.URLDecoder/ URLEncoder
         */
        String urlDecoder = URLDecoder.decode("p%40ssw0rd%279%27%21");
        System.out.println("url decoder: " + urlDecoder);
        String urlEncoder = URLEncoder.encode("p@ssw0rd'9'!");
        System.out.println("url encoder: " + urlEncoder);

        /*
         * Testing the spring framework.URLDecoder/ URLEncoder
         *
        try {
            String urlDecoder = UriUtils.decode("fe%C3%9F233%5E", "UTF-8");
            System.out.println("url decoder: " + urlDecoder);
        } catch (Exception e) {
            //no-op
        }*/

        System.out.println("Minimum value for double: " + Double.MIN_VALUE);
        char[] chars = new char[] {'6' , '4'};      // contains the HEX number; 0x64 = 100
        String sChars = new String(chars);
        System.out.println("Integer to hex string: " + Integer.parseInt(sChars, 16));
        // parseInt with the second parameter will convert any number (number is supplied as
        // String in first argument and radix denotes the type of the number belongs to such as
        // hex or octal or any such) to Integer
        System.out.println("Hex to int: " + Integer.toHexString(100));

        String query = "select * from weather where city = ?";
        String []splitQuery = query.split("\\?");
        for (String queryPart: splitQuery) {
            System.out.println("Splitted Query: " + queryPart);
        }
        System.out.println("Split Query Length: " + splitQuery.length);

        ArrayList<Integer> arrayListTesting = new ArrayList<>();
        arrayListTesting.add(1);
        arrayListTesting.add(2);
        arrayListTesting.add(0, 3);
        System.out.println(arrayListTesting);

        String testString = "SGVsbG8=";             //Mayank£
        byte []b = testString.getBytes();
        String testedString = new String(b);
        System.out.println("Tested Byte array: " + testedString);
//        byte[] bytesDecoded = Base64.decodeBase64(testString.getBytes());
//        System.out.println("Decoded value is " + new String(bytesDecoded));
//        byte[] valueEncoded = Base64.encodeBase64(bytesDecoded);
//        System.out.println("Encoded value is " + new String(valueEncoded));

        String message = "'awsaccessid=1212121212;awssecretid=cddwf123>3w1`3w13'";
        String replacedString = message.replaceAll("'awsaccessid=(.*);awssecretid=(.*)'",
                "'awsaccessid=;awssecretid='");
        System.out.println("Masked String1: "  + replacedString);

        System.out.println("Boolean String1: "  + Boolean.valueOf("true ".trim()));

        String message2 = "'awsaccessid=1212121212;awssecretid=cddwf123>3w1`3w13'<root " +
                "version=\"2.0\"><r>";
        String replacedString2 = message2.replaceAll("<root (.*)><",
                "<root xmlns=w2e2 version=2.3><");

        System.out.println("Masked String2: "  + replacedString2);

        System.out.println("Boolean String2: "  + Boolean.valueOf("true ".trim()));
    }

    ;

    private static void testException() throws NullPointerException {
        String a = null;
        try {
            a.length();
        } catch(NullPointerException e) {
            System.out.println("Exception found in testException");
        }
    }

    public static void setName(String s, FunctionalInterfaceTest t) {
        t.printString ( s );
    }

    enum Gender {
        MALE("male"), FEMALE("female");
        private String name;

        Gender(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        public String toString() {
            return name;
        }
    }
}