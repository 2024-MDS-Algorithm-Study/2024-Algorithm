import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {
    private static int res;
    private static Queue<Node> q;
    private static int[][] del = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static class Node {
        int x;
        int y;
        int dist;

        private Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') bfs(i, j, n, m, map);
            }
        }

        System.out.println(res);
    }

    private static void bfs(int i, int j, int n, int m, char[][] map) {
        q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];
        v[i][j] = true;
        q.offer(new Node(i, j, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            res = Math.max(res, cur.dist);

            for (int o = 0; o < 4; o++) {
                int nx = cur.x + del[o][0];
                int ny = cur.y + del[o][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m
                        || v[nx][ny] || map[nx][ny] == 'W') continue;
                v[nx][ny] = true;
                q.offer(new Node(nx, ny, cur.dist + 1));
            }
        }
    }
}
