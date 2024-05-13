import java.io.*;
import java.util.*;

public class BOJ2234 {
    static int[][] del = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}, map, labels;
    static int n, m, res;
    static Map<Integer, Integer> values = new HashMap<>();
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
        map = new int[m][n];
        labels = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int label = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (labels[i][j] == 0) bfs(i, j, label++);
            }
        }

        System.out.println(values.size());
        System.out.println(res);
        System.out.println(sum());
    }

    private static void bfs(int x, int y, int label) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, y));
        labels[x][y] = label;
        int cnt = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || labels[nx][ny] > 0 || (map[cur.x][cur.y] & (1 << i)) > 0) continue;
                labels[nx][ny] = label;
                q.offer(new Node(nx, ny));
            }
        }
        res = Math.max(res, cnt);
        values.put(label, cnt);
    }

    private static int sum() {
        int sumRes = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + del[k][0];
                    int ny = j + del[k][1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                    if (labels[i][j] != labels[nx][ny])
                        sumRes = Math.max(sumRes, values.get(labels[i][j]) + values.get(labels[nx][ny]));
                }
            }
        }
        return sumRes;
    }
}
