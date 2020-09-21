/**
 * Created by mayankthirani on 9/3/19.
 */
public class AssignedPorts {
    //0,1,2,3,4,5,6,7,8,9
    int[] unUsedPorts = new int[1024];
    int count = 1024;

    public static void main(String... args) {
        String[] test = "1..3.4".split("\\.");
        System.out.println("Length:: " + test.length);
        for (String t : test) {
            System.out.println("Value:: " + t);
        }
    }

    public int assign() {
        int rand = (int) Math.random() * count;
        int assignedPort = unUsedPorts[rand];
        int temp = unUsedPorts[count - 1];
        unUsedPorts[count - 1] = unUsedPorts[rand];
        unUsedPorts[rand] = temp;
        count--;

        return assignedPort;
    }

    static class Port {
        int index;
        int portValue;
    }
}
