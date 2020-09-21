import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mayankthirani on 1/10/20.
 */
public class Caching {
    public static void main(String[] args) {
        TreeMap<Integer, String> countStore = new TreeMap<>();
        SimpleLru<String, byte[]> simpleLru = new SimpleLru(60);
        byte[] a = "Mayank Thirani Testing here".getBytes();
        byte[] b = "Nishant Thirani Testing here".getBytes();
        simpleLru.put("test", a);
        //loop to remove the contents if size is > capacity
        computeAndRemove(simpleLru);
        System.out.println(simpleLru.size());
        System.out.println("Count:: " + simpleLru.getCount());
        System.out.println("Adding new entry");
        simpleLru.put("test1", b);
        //loop to remove the contents if size is > capacity
        computeAndRemove(simpleLru);
        System.out.println("B's length::  " + b.length);
        System.out.println("Size:: " + simpleLru.size());
        System.out.println("Count:: " + simpleLru.getCount());
        System.out.println("Get from map:: " + simpleLru.get("test"));

        System.out.println("Adding new entry");
        byte[] c = "Mayank, Nishant and Ganesh Kumar Testing here".getBytes();
        simpleLru.put("test2", c);
        //loop to remove the contents if size is > capacity
        computeAndRemove(simpleLru);
        System.out.println("Count:: " + simpleLru.getCount());
        System.out.println("Size:: " + simpleLru.size());

    }

    public static void computeAndRemove(SimpleLru simpleLru) {
        Iterator it = simpleLru.entrySet().iterator();
        while (it.hasNext() && simpleLru.size() > simpleLru.capacity) {
            Map.Entry item = (Map.Entry) it.next();
            it.remove();
            simpleLru.size = simpleLru.size - ((byte[]) item.getValue()).length;
            simpleLru.count--;
        }
    }
}

class SimpleLru<K, V> extends LinkedHashMap<K, V> {

    final int capacity;
    public int size;
    public int count;

    public SimpleLru(int capacity) {
        this.capacity = capacity;
    }

//    @Override
//    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//        if (this.size() > this.capacity) {
//            this.size = this.size() - ((byte[]) eldest.getValue()).length;
//            count--;
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public V put(final K key, final V value) {
        if (containsKey(key)) {
            //remove this content and add it back to the list
            remove(key);
            return super.put(key, value);
        } else {
            // compute the size and add this to the list removing the eldest entry
            size += ((byte[]) value).length;
            count++;
            return super.put(key, value);
        }
    }

    @Override
    public int size() {
        return size;
    }

    public int getCount() {
        return count;
    }

    @Override
    public V get(final Object key) {
        if (containsKey(key)) {
            put((K) key, super.get(key));
        }
        return super.get(key);
    }
}