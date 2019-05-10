package GarbageCollection;

/**
 * Created by mayankthirani on 2/21/19.
 */

public class Customer  {
    private String name;

    public String getName() {
        return name;
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(Customer oldCustomer) {
        this.name = oldCustomer.name;
    }


    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void finalize() {
        System.out.println("Garbage collected now");
    }

}
