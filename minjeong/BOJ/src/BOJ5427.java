import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] del = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        char[][] map;
        int[][] burn;
        StringTokenizer st;
        Queue<Node> q, q2;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int res = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            burn = new int[h][w];
            boolean[][] v = new boolean[h][w];
            q = new ArrayDeque<>();
            q2 = new ArrayDeque<>();
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    burn[i][j] = -1;
                    if (map[i][j] == '@') {
                        map[i][j] = '.';
                        q.offer(new Node(i, j, 0));
                        v[i][j] = true;
                    }
                    else if (map[i][j] == '*') {
                        q2.offer(new Node(i, j, 0));
                        burn[i][j] = 0;
                    }
                }
            }

            fire(burn, w, h, q2, map);

            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (burn[cur.x][cur.y] != -1 && burn[cur.x][cur.y] <= cur.cnt) continue;

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + del[i][0];
                    int ny = cur.y + del[i][1];
                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        res = Math.min(res, cur.cnt + 1);
                        break;
                    }
                    if (map[nx][ny] == '.' && !v[nx][ny]) {
                        q.offer(new Node(nx, ny, cur.cnt + 1));
                        v[nx][ny] = true;
                    }
                }
            }

            if (res != Integer.MAX_VALUE) sb.append(res).append('\n');
            else sb.append("IMPOSSIBLE\n");
        }
        System.out.println(sb);
    }

    private static void fire(int[][] burn, int w, int h, Queue<Node> q, char[][] map) {
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == '#' || burn[nx][ny] != -1) continue;
                burn[nx][ny] = cur.cnt + 1;
                q.offer(new Node(nx, ny, burn[nx][ny]));
            }
        }
    }
}
