/**
 * Created by mayankthirani on 6/22/18.
 */
class ParentClass {

    public ParentClass(){
        name("Nishant");
    }

    String name;
    int id;

    //private method cannot be over-ridden
    // For Method Overriding: Return type must be same with the method signature (function name
    // with parameters and it's type)
    // For Method Overloading: function name should be same, while parameters should be different
    // or their data types. Return type doesn't count here.
    private void name(String s) {
        this.name = s;
        System.out.println("name in ParentClass: " + name);
    }

    public static void print() {
        System.out.println("print() in ParentClass: ");
    }
}
class SubClass extends ParentClass {

    SubClass() {
        name("Mayank");
    }

    private void name(String s) {
        this.name = s;
        System.out.println("name in SubClass: " + name);
    }

    public static void print() {
        System.out.println("print() in ChildClass: ");
    }
}
public class TestInheritancePolymorphism {
    public static void main(String []args) {
        ParentClass p = new SubClass();
        p.print();
    }
}


//Interfaces can do multiple inheritance as the methods are abstract there, implementation of
// the method is not done yet.
interface test1{
    void testing();
}
interface test2{
    void testing();
}

interface test3 extends test1, test2{
    void testingChild();
}