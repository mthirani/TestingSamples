package GuiceInjection;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by mayankthirani on 7/2/18.
 */
public class TestGuiceInjection {

    public static void main(String []args) {
        Injector injector = Guice.createInjector(new BindingModule());
        MainClass mainClass = injector.getInstance(MainClass.class);
        mainClass.print();
        mainClass.printTest();

        MainClass mainClass2 = new MainClass();
        try {
            mainClass2.printTest();
        } catch (NullPointerException e) {
            System.out.println("Catching Null Pointer Exceptions as it is not injected by guice");
        }

        MainClass mainClass3 = injector.getInstance(MainClass.class);
        mainClass3.printTest();
    }
}
