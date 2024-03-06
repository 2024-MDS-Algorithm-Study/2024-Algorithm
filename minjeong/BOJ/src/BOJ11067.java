import java.io.*;
import java.util.*;

public class BOJ11067 {
    static TreeMap<Integer, List<Integer>> map;
    static Map<Integer, Node> cafes = new HashMap<>();

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            map = new TreeMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map.computeIfAbsent(x, k -> new ArrayList<>());
                map.get(x).add(y);
            }

            numbering();

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                sb.append(cafes.get(num).x).append(" ").append(cafes.get(num).y).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void numbering() {
        int y = 0, number = 1;
        for (int x : map.keySet()) {
            if (map.get(x).size() == 1) cafes.put(number++, new Node(x, y));
            else {
                if (map.get(x).get(1) < y || map.get(x).get(0) < y) map.get(x).sort((o1, o2) -> Integer.compare(o2, o1));
                else Collections.sort(map.get(x));
                y = map.get(x).get(map.get(x).size() - 1);
                for (int ny : map.get(x)) cafes.put(number++, new Node(x, ny));
            }
        }
    }
}