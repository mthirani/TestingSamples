package GuiceInjection;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by mayankthirani on 7/2/18.
 */
class GuiceDemo {
    @Qualifier
    @Retention(RUNTIME)
    @interface Message {
    }

    @Qualifier
    @Retention(RUNTIME)
    @interface Name {
    }
}
public class BindingModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    public Integer count() {
        return 1;
    }

    @Provides
    @Named("Message")
    public String message() {
        return "Hello World!";
    }

    @Provides
    @Named("Name")
    public String name() {
        return "Mayank Thirani";
    }
}
