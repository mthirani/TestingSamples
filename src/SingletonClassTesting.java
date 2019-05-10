import com.google.inject.Singleton;

/**
 * Created by mayankthirani on 6/27/18.
 */
@Singleton
public class SingletonClassTesting {
    int a;

    SingletonClassTesting(int val) {
        a = val;
    }
}
