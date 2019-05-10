package GarbageCollection;

/**
 * Created by mayankthirani on 2/21/19.
 */

public class Main {

    public static void main(String[] args)
    {
        Runtime runtime = Runtime.getRuntime();

        long availableBytes = runtime.freeMemory();
        System.out.println("Available memory: " + availableBytes / 1024 + "k");

        // let's create a ton of garbage....
        for (int i=0; i<100; i++)
        {
            Customer c;
            c = new Customer("John");
        }

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory: " + availableBytes / 1024 + "k");

        System.gc();

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory: " + availableBytes / 1024 + "k");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}