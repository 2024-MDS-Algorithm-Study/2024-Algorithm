import java.io.*;
import java.util.*;

public class BOJ1647 {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Node>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b, c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        boolean[] v = new boolean[n + 1];
        int sum = 0;
        int max = 0;
        v[1] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.addAll(graph[1]);

        int cnt = 1;
        while (cnt < n) {
            Node cur = pq.poll();
            if (v[cur.to]) continue;
            cnt++;
            sum += cur.cost;
            max = Math.max(max, cur.cost);
            pq.addAll(graph[cur.to]);
            v[cur.to] = true;
        }

        System.out.println(sum - max);
    }
}
