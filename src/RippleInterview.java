import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by mayankthirani on 8/14/19.
 */
public class RippleInterview {
    public static void main(String[] args) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] a, int[] b) -> a[0] - b[0]);
        queue.add(new int[]{1, 2});
        queue.add(new int[]{0, 5});
        int[] temp = queue.poll();
        System.out.println(temp[0]);
        String[] split = ".12.4.".split("\\.");
        for (String eachSplit : split) {
            System.out.println(eachSplit);
        }
        TreeNode root = new TreeNode(1);
        TreeNode left2 = new TreeNode(2);
        TreeNode right3 = new TreeNode(3);
        TreeNode left34 = new TreeNode(4);
        TreeNode right35 = new TreeNode(5);
        right3.left = left34;
        right3.right = right35;
        root.left = left2;
        root.right = right3;
        System.out.println("Serialization:: " + serialize(root));
        int result = "Mayank".hashCode() % 40;
        System.out.println(result);

        int[] a = new int[]{2, 4, 5, 7, 10};
        System.out.println("Search Result:: " + Arrays.binarySearch(a, 3));


        Map<String, Object> map = new HashMap<>();
        map.put("Test", "Testing");
        System.out.println("Map Result:: " + map.toString());

        TreeNode test = new TreeNode(123);
        System.out.println("test left result before changeValue :: " + test.left);
        changeValue(test);
        System.out.println("test result after changeValue  :: " + test.val);
        System.out.println("test left result after changeValue :: " + test.left.val);
        String actual = "/a//b////c/d//././/..";
        String expected[] = actual.split("/");
        System.out.println("Size of the array:: " + expected.length);
        for (String e : expected) {
            System.out.println("Split string:: " + e);
        }
        Stack<String> stack = new Stack<>();
        stack.push("Mayank");
        stack.push("Nishant");
        System.out.println("Stack string:: /" + String.join("/", stack));
    }

    private static void changeValue(TreeNode test) {
        TreeNode testing = new TreeNode(100);
        test.val = 120;
        test.left = testing;
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private static void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("NULL").append(",");
        } else {
            sb.append(node.val).append(",");
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
