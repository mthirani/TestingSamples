package GuiceInjection;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.junit.Test;

/**
 * Created by mayankthirani on 7/2/18.
 */
public class TestCasesGuice {

    @Test
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        Injector injector = Guice.createInjector(new BindingModule());
        MainClass mainClass = injector.getInstance(MainClass.class);
        mainClass.printTest();
    }
}
