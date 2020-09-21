import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by mayankthirani on 10/16/19.
 */
public class LogDNA {
    public static void main(String[] args) {
        // 2 - 9
        // 2 -> a, b, c
        // 3 -> d, e, f
        // ...

        //getCombo(String nums)

        // input: "23"
        // output: [ad, ae, af, bd, be ,bf, cd, ce, cf]
        String[] mappings = new String[]{"0", "1", "abc", "def", "ghi"};
        System.out.println("Output:: " + getCombo(mappings, "23"));
        HashSet<Integer> hashSet = new HashSet<>();
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("A", 3);
        map.put("B", 2);
        map.put("C", 1);

        Map<String, Integer> map2 = new TreeMap<String, Integer>();
        map2.put("A", 3);
        map2.put("B", 2);
        map2.put("C", 1);

        System.out.println(map);

        System.out.println("map == map2? :: " + map.equals(map2));
        // prints "{A=3, B=2, C=1}"
        SortedSet<Map.Entry<String, Integer>> s = sortedByValues(map);
        System.out.println(s);
        for (Map.Entry<String, Integer> eachEntry : s) {
            System.out.println("Key, Value:: ");
            System.out.println(eachEntry.getKey());
            System.out.println(eachEntry.getValue());
        }
        PriorityQueue<Integer> testPriorityQueue = new PriorityQueue<>((n1, n2) -> map2.get(n1) -
                map2.get(n2));


        // prints "[C=1, B=2, A=3]"
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        System.out.println("Final count:: " + count(list, 2));
        list = Arrays.asList(1, 1, 1, 1);
        System.out.println("Final count:: " + count(list, 2));

        if (list.get(5) != null) {
            System.out.println("Not null for the 5th index of list ");
        } else {
            System.out.println("Found null for the 5th index of list ");
        }

        String str = Arrays.toString(new int[]{1, 2, 3, 4});
        System.out.println("Array to String:: " + str);

        double v1 = -0.2;
        double v2 = 1;
        System.out.println("Long value compare:: " + (v1 > v2));
        System.out.println("Long value compare:: " + (v1 < v2));

    }

    // "2"
    private static List getCombo(String[] mappings, String nums) {
        List<String> output = new LinkedList<>();
        output.add("");
        for (int i = 0; i < nums.length(); i++) {
            char c = nums.charAt(i);
            int indexMapping = Integer.parseInt(String.valueOf(c));     // 2, [abc]
            while (i == output.get(0).length()) {                       // i = 0 and list = 0
                String listStr = output.remove(0);
                for (char t : mappings[indexMapping].toCharArray()) {
                    String newString = listStr + t;
                    output.add(newString);
                }
            }
        }

        return output;
    }

    /**
     * This function returns the SortedSet of Map Collections by values
     *
     * @param map
     * @param <K>
     * @param <V>
     *
     * @return SortedSet
     */
    static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> sortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>(
                (e1, e2) -> {
                    int result = e1.getValue().compareTo(e2.getValue());
                    return result != 0 ? result : 1;
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }


    public static int count(List<Integer> ids, int m) {
        // This map stores all the ids with their respective occurences in the list
        Map<Integer, Integer> itemsCount = new HashMap<>();
        for (int id : ids) {
            itemsCount.put(id, itemsCount.getOrDefault(id, 0) + 1);
        }
        SortedSet<Map.Entry<Integer, Integer>> s = sortedByValues(itemsCount);
        // Looping over the sorted entries to remove the id if 'm' is == or > than respective id
        // count else update the count for the id and 'm' and continue
        for (Map.Entry<Integer, Integer> eachEntry : s) {
            if (m == 0) {
                break;
            }
            int id = eachEntry.getKey();
            int idCount = eachEntry.getValue();
            if (idCount == m) {
                itemsCount.remove(id);
                m = 0;
            } else if (idCount > m) {
                m = 0;
                itemsCount.put(id, idCount - m);
            } else {
                m = m - idCount;
                itemsCount.remove(id);
            }
        }

        return itemsCount.size();
    }

    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i : nums) {
            if (map.containsKey(i)) {
                if (k == 0 && map.get(i) == 1) {
                    result++;
                }
                map.put(i, map.get(i) + 1);
            } else {
                if (map.containsKey(i - k)) {
                    result++;
                }
                if (map.containsKey(i + k)) {
                    result++;
                }
                map.put(i, 1);
            }
        }
        return result;
    }
}
