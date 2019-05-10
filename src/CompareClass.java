/**
 * Created by mayankthirani on 3/31/18.
 */
public class CompareClass {
    int ID;
    String name;

    public CompareClass(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String toString() {
        return (ID + ", " + name);
    }
}
