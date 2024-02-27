import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17822 {
    static int n, m, t;
    static int[][] arr, del = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        arr = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x, d, k;
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            rotate(x, d, k);
            erase();
        }

        System.out.println(calc(true));
    }

    static void rotate(int x, int d, int k) {
        while (k-- > 0) {
            for (int i = x; i <= n; i += x) {
                if (d == 0) {
                    for (int j = m; j >= 1; j--) arr[i][j + 1] = arr[i][j];
                    arr[i][1] = arr[i][m + 1];
                } else {
                    for (int j = 1; j <= m; j++) arr[i][j - 1] = arr[i][j];
                    arr[i][m] = arr[i][0];
                }
            }
        }
    }

    private static void erase() {
        for (int i = 1; i <= n; i++) {
            arr[i][0] = arr[i][m];
            arr[i][m + 1] = arr[i][1];
        }

        int cnt = 0;
        boolean[][] v = new boolean[n + 1][m + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (!v[i][j]) cnt += bfs(i, j, v);
            }
        }

        if (cnt == 0) calc(false);
    }

    private static int bfs(int x, int y, boolean[][] v) {
        Queue<Node> q = new ArrayDeque<>();
        int start = arr[x][y];
        v[x][y] = true;
        q.offer(new Node(x, y));
        int cnt = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (arr[cur.x][cur.y] > 0) cnt++;
            arr[cur.x][cur.y] = 0;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 1 || nx > n || ny < 0 || ny > m + 1 || v[nx][ny] || arr[nx][ny] != start) continue;
                v[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }
        if (cnt == 1) {
            arr[x][y] = start;
            return 0;
        }
        return cnt;
    }

    private static int calc(boolean finish) {
        int sum = 0, cnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum += arr[i][j];
                if (arr[i][j] > 0) cnt++;
            }
        }
        if (finish) return sum;

        float avg = (float) sum / cnt;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] == 0) continue;
                if (arr[i][j] > avg) arr[i][j]--;
                else if (arr[i][j] < avg) arr[i][j]++;
            }
        }
        return 0;
    }
}