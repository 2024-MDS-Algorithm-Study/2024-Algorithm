import java.io.*;
import java.util.*;

public class BOJ11779 {
    static class Node implements Comparable<Node> {
        int e;
        int c;

        public Node(int e, int c) {
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.c, o.c);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<Node>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s, e, c;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, c));
        }

        st = new StringTokenizer(br.readLine());
        Node dest = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(dest.e, 0));
        int[] dist = new int[n + 1];
        int[] path = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 3);
        dist[dest.e] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.c > dist[cur.e]) continue;
            for (Node next : graph[cur.e]) {
                if (dist[next.e] > dist[cur.e] + next.c) {
                    dist[next.e] = dist[cur.e] + next.c;
                    path[next.e] = cur.e;
                    pq.offer(new Node(next.e, dist[next.e]));
                }
            }
        }

        int next = dest.c;
        Stack<Integer> q = new Stack<>();
        q.push(next);
        while (next != dest.e) {
            next = path[next];
            q.push(next);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dist[dest.c]).append("\n").append(q.size()).append("\n");
        while (!q.isEmpty()) sb.append(q.pop()).append(" ");
        System.out.println(sb);
    }
}
