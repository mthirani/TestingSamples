import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@FunctionalInterface
interface Test {
    static int deletion(int a, int b) {
        return a - b;
    }

    String printString(String a);

    default void add(int a, int b) {
        System.out.println ( "Addition result: " + (a + b) );
    }
}

public class Sample {
    public static void main(String []args) {
        System.out.println("Testing ");
        Test t = s -> {
            System.out.println ( "Hello " + s );
            return "Hello " + s;
        };
        setName ( "Mayank", t );
        t.add ( 3, 4 );
        int deletionResult = Test.deletion ( 6, 5 );
        System.out.println ( "Deletion Result: " + deletionResult );

        List <Integer> myList = new ArrayList <> ( );
        for (int i = 0; i < 5; i++) myList.add ( i );
        myList.forEach ( i -> System.out.println ( i ) );

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

        Map <String, Integer> mapObject = new HashMap <> ( );
        mapObject.put ( "a", 4 );
        mapObject.put ( "d", 11 );
        mapObject.put ( "b", 0 );
        mapObject.put ( "c", 3 );
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
        sortedValueComparator.forEach ( p -> System.out.println ( "Descending Sorting Map By Key: " + p.getKey ( ) ) );
        mapObject.compute ( "a", (key, value) -> {
                    System.out.println ( key + ", " + value );
                    return 1;
                }
        );

        double s = myList.stream ( ).mapToDouble ( Number::doubleValue ).sum ( );
        System.out.println ( s );
    }

    public static void setName(String s, Test t) {
        t.printString ( s );
    }
}