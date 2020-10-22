package GuiceInjection;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by mayankthirani on 7/2/18.
 */
public class MainClass {
    SubClass subClass;
    SubClass2 subClass2;
    String message;

    @Inject
    Test test;

    MainClass() {
        System.out.println("Default Constructor In Main Class");
    }

    @Inject
    MainClass(@Named("Message") String message, SubClass subClass, SubClass2 subClass2) {
        this.subClass = subClass;
        this.subClass2 = subClass2;
        this.message = message;
        System.out.println("Parametrized Constructor In Main Class");
    }

    public void print() {
        System.out.println("SubClass ID:" + subClass.id + "\nSubClass2 ID:" + subClass2.id);
    }

    public void printTest() {
        test.print();
    }
}
class SubClass {
    int id;
    String value;

    SubClass () {
        id = 1;
    }

    SubClass (int a, String b) {
        id = a;
        value = b;
    }
}
class SubClass2 {
    int id;
    String value;

    SubClass2 () {
        id = 2;
    }

    SubClass2 (int a, String b) {
        id = a;
        value = b;
    }
}
class Test {
    public void print() {
        System.out.println("In Test Class");
    }
}
