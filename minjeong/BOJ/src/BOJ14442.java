import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14442 {
    private static final int[][] del = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static class Node {
        int x;
        int y;
        int cnt;
        int move;

        private Node(int x, int y, int cnt, int move) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.move = move;
        }
    }

    public static void main(String[] args) throws Exception {
        int res = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m, k;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        int[][][] v = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                Arrays.fill(v[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 0, 1));
        v[0][0][0] = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == n - 1 && cur.y == m - 1) {
                res = Math.min(res, v[cur.x][cur.y][cur.cnt]);
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (map[nx][ny] == '1' && cur.cnt == k) continue;
                int cnt = cur.cnt;
                if (map[nx][ny] == '1') cnt += 1;
                if (v[nx][ny][cnt] > cur.move + 1) {
                    v[nx][ny][cnt] = cur.move + 1;
                    q.offer(new Node(nx, ny, cnt, cur.move + 1));
                }
            }
        }

        if (res == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);
    }
}
