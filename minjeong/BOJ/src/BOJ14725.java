import java.io.*;
import java.util.*;

public class BOJ14725 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node implements Comparable<Node> {
        String value;
        final TreeSet<Node> childs = new TreeSet<>();

        public Node(String value) {
            this.value = value;
        }

        private Node insert(String value) {
            for (Node node : childs) {
                if (node.value.equals(value)) return node;
            }
            Node next = new Node(value);
            childs.add(next);
            return next;
        }

        @Override
        public int compareTo(Node o) {
            return this.value.compareTo(o.value);
        }
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        Node head = new Node(null);
        StringTokenizer st;
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            Node next = head;
            while (k-- > 0) {
                next = next.insert(st.nextToken());
            }
        }

        print(head, 0);
        bw.close();
    }

    private static void print(Node node, int depth) throws Exception {
        for (Node child : node.childs) {
            for (int i = 0; i < depth; i++) bw.write("--");
            bw.write(child.value + "\n");
            print(child, depth + 1);
        }
    }
}