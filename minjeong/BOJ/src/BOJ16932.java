import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16932 {
    static int[][] del = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int n, m;
    static int[][] v, map;
    static Queue<Node> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0, t = 1;
        v = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) res = Math.max(res, bfs(i, j, t++));
            }
        }

        System.out.println(res);
    }

    private static int bfs(int x, int y, int t) {
        int res = 0;
        q.offer(new Node(x, y));
        v[x][y] = t;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            res++;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || v[nx][ny] == t || map[nx][ny] == 0) continue;
                v[nx][ny] = t;
                q.offer(new Node(nx, ny));
            }
        }
        return res;
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
