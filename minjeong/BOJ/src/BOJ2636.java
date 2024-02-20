import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2636 {

    private static final int[][] del = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int time;
        boolean isCheese;

        public Node(int x, int y, int time, boolean isCheese) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isCheese = isCheese;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }

    private static PriorityQueue<Node> q = new PriorityQueue<>();
    private static boolean[][] cheese, v;
    private static int totalTime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int h, w;
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        cheese = new boolean[h][w];
        v = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                if (st.nextToken().equals("1")) cheese[i][j] = true;
            }
        }

        int pre = bfs(h, w);
        System.out.println(totalTime + " " + pre);
    }

    private static int bfs(int h, int w) {
        int cnt = 0;
        q.offer(new Node(0, 0, 0, cheese[0][0]));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            totalTime = Math.max(totalTime, cur.time);
            if (cur.isCheese) cnt++;
            if (!q.isEmpty() && q.peek().time != cur.time) cnt = 0;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w || v[nx][ny]) continue;
                v[nx][ny] = true;
                int time = cur.time;
                if (cheese[nx][ny]) time++;
                q.offer(new Node(nx, ny, time, cheese[nx][ny]));
                cheese[nx][ny] = false;
            }
        }

        return cnt;
    }
}
