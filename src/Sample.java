import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Stream;

public class Sample {
    public static void main(String []args) {

        /*
         * Test the Java 8 features
         */

        FunctionalInterfaceTest t = s -> {
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

        /*
         * Test the Encoding methods
         */

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
        Class <TestExample> testExampleClass = TestExample.class;

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

    }

    public static void setName(String s, FunctionalInterfaceTest t) {
        t.printString ( s );
    }
}