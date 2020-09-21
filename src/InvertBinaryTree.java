import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by mayankthirani on 7/15/20.
 */
public class InvertBinaryTree {
    /**
     * 1
     * 2     3
     * 4   5 6   7
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Enum value: " + A.A1);
        Map<A, Integer> map;
        Node root = new Node(1);
        Node rootleft = new Node(2);
        Node rootright = new Node(3);
        Node rootleftleft = new Node(4);
        Node rootleftright = new Node(5);
        Node rootrightleft = new Node(6);
        Node rootrightright = new Node(7);
        root.left = rootleft;
        root.right = rootright;
        rootleft.left = rootleftleft;
        rootleft.right = rootleftright;
        rootright.left = rootrightleft;
        rootright.right = rootrightright;
        printTree(root);
        invertTree(root);
        System.out.println("After inverting the Binary tree");
        printTree(root);
    }

    private static void printTree(final Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node element = q.poll();
            System.out.println(element.value);
            if (element.left != null) {
                q.offer(element.left);
            }
            if (element.right != null) {
                q.offer(element.right);
            }
        }
    }

    public static void invertTree(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        if (root == null) {
            return;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.left != null) {
                queue.offer(curr.left);
            }

            if (curr.right != null) {
                queue.offer(curr.right);
            }

            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
        }
    }

    enum A {
        NONE("None value"),
        A1("A1 value");

        String type;

        A(String type) {
            this.type = type;
        }
    }

    static class Node {
        Node left;
        Node right;
        int value;

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
