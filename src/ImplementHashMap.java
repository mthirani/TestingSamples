import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by mayankthirani on 7/29/19.
 */
//Getting Random Value from a HashMap
public class ImplementHashMap {
    static HashMap<String, ObjectWithIndex> hmap = new HashMap<>();
    static ObjectWithKey[] random = new ObjectWithKey[100];

    public static void main(String[] args) {
        Arrays.fill(random, null);
        int[][] array = new int[3][4];
        System.out.println("Array Row Length:: " + array.length);
        System.out.println("Array Column Length:: " + array[0].length);
    }

    public void put(String key, String value) {
        if (hmap.containsKey(key)) {
            ObjectWithIndex objectWithIndex = hmap.get(key);
            int indexFound = objectWithIndex.index;
            random[indexFound].objectValue = value;
            hmap.get(key).objectValue = value;
        } else {
            int size = hmap.size();
            if (size >= random.length) {
                ObjectWithKey[] newArray = new ObjectWithKey[random.length];
                System.arraycopy(random, 0, newArray, 0, random.length);
                //List<String> newArray = Arrays.asList(random);
                random = new ObjectWithKey[random.length * 2];
                System.arraycopy(newArray, 0, random, 0, newArray.length);
            }
            random[size] = new ObjectWithKey(key, value);
            ObjectWithIndex objectWithIndex = new ObjectWithIndex(key, size);
            hmap.put(key, objectWithIndex);
        }
    }

    public String get(String key) {
        return hmap.get(key).objectValue;
    }

    public String randomValue() {
        int randIndex = (int) (Math.random() * hmap.size());
        return random[randIndex].objectValue;
    }

    public void remove(String key) {
        ObjectWithIndex objectWithIndexToRemove = hmap.get(key);
        ObjectWithKey valueToReplace = random[hmap.size() - 1];
        random[objectWithIndexToRemove.index] = valueToReplace;
        hmap.get(valueToReplace.key).index = objectWithIndexToRemove.index;
        hmap.remove(key);
    }

    public int size() {
        return hmap.size();
    }

    //round function converts >=0.5 to 1 and <0.5 to 0
    //int rand = (int) (Math.random() * (49-10)) + 10; // Between the range [10 and 49]
    //rand = (int) Math.round(Math.random() * (1-0)) + 0; // Between the range [0 and 1]
    static class ObjectWithIndex {
        public String objectValue;
        public int index;

        ObjectWithIndex(String val, int index) {
            this.objectValue = val;
            this.index = index;
        }
    }

    static class ObjectWithKey {
        public String objectValue;
        public String key;

        ObjectWithKey(String val, String key) {
            this.objectValue = val;
            this.key = key;
        }
    }
}
