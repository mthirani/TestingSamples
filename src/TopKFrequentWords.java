import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by mayankthirani on 2/21/20.
 */
public class TopKFrequentWords {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Mayank", 2);
        hashMap.put("Nishant", 1);
        hashMap.put("Ganesh", 3);
        PriorityQueue<String> priorityQueue = new PriorityQueue<>((n1, n2) -> hashMap.get(n2) -
                hashMap.get(n1));
        priorityQueue.add("Mayank");
        priorityQueue.add("Ganesh");
        priorityQueue.add("Nishant");

        System.out.println("First string in queue found:: " + priorityQueue.poll());
        System.out.println("Second string in queue found:: " + priorityQueue.poll());
    }
}
