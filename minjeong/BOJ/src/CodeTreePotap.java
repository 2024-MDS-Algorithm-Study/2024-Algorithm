import java.io.*;
import java.util.*;

public class CodeTreePotap {
    static int n, m, k, cnt;
    static int[][] map, times, del = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    static class Node {
        int x;
        int y;
        List<Node> path;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public List<Node> laser(Node attactee) {
            path = new ArrayList<>();
            Queue<Node> q = new ArrayDeque<>();
            q.offer(this);
            boolean[][] visited = new boolean[n][m];
            visited[x][y] = true;

            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (cur.x == attactee.x && cur.y == attactee.y) return cur.path;

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + del[i][0];
                    int ny = cur.y + del[i][1];
                    if (nx < 0) nx = n - 1;
                    else if (nx >= n) nx = 0;
                    if (ny < 0) ny = m - 1;
                    else if (ny >= m) ny = 0;
                    if (visited[nx][ny] || map[nx][ny] <= 0) continue;
                    Node next = new Node(nx, ny);
                    next.path = new ArrayList<>(cur.path);
                    next.path.add(next);
                    visited[nx][ny] = true;
                    q.offer(next);
                }
            }
            return new ArrayList<>();
        }

        public List<Node> potan(Node attactee) {
            List<Node> attactees = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                int nx = attactee.x + del[i][0];
                int ny = attactee.y + del[i][1];
                if (nx < 0) nx = n - 1;
                else if (nx >= n) nx = 0;
                if (ny < 0) ny = m - 1;
                else if (ny >= m) ny = 0;
                if ((nx == x && ny == y) || map[nx][ny] <= 0) continue;
                attactees.add(new Node(nx, ny));
            }
            attactees.add(attactee);
            return attactees;
        }

        public int sum() {
            return x + y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        times = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) cnt++;
            }
        }

        int idx = 0;
        while (idx++ < k) {
            Node attactor = findAttactor();
            Node attactee = findAttactee();

            List<Node> attactees = attactor.laser(attactee);
            if (attactees.isEmpty()) attactees = attactor.potan(attactee);
            map[attactor.x][attactor.y] += (n + m);
            times[attactor.x][attactor.y] = idx;
            attact(map[attactor.x][attactor.y], attactees);
            if (cnt <= 1) break;

            attactees.add(attactor);
            rest(attactees);
        }

        printRes();
    }

    private static Node findAttactor() {
        Node attactor = new Node(-1, -1);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] <= 0) continue;
                if (attactorContidions(min, i, j, attactor)) {
                    min = map[i][j];
                    attactor.x = i;
                    attactor.y = j;
                }
            }
        }
        return attactor;
    }

    private static boolean attactorContidions(int min, int x, int y, Node attactor) {
        if (min != map[x][y]) return min > map[x][y];
        if (times[x][y] != times[attactor.x][attactor.y]) return times[x][y] > times[attactor.x][attactor.y];
        if (x + y != attactor.sum()) return x + y > attactor.sum();
        return y > attactor.y;
    }

    private static Node findAttactee() {
        Node attactee = new Node(-1, -1);
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] <= 0) continue;
                if (attacteeContidions(max, i, j, attactee)) {
                    max = map[i][j];
                    attactee.x = i;
                    attactee.y = j;
                }
            }
        }
        return attactee;
    }

    private static boolean attacteeContidions(int max, int x, int y, Node attactee) {
        if (max != map[x][y]) return max < map[x][y];
        if (times[x][y] != times[attactee.x][attactee.y]) return times[x][y] < times[attactee.x][attactee.y];
        if (x + y != attactee.sum()) return x + y < attactee.sum();
        return y < attactee.y;
    }

    private static void attact(int attactValue, List<Node> attactees) {
        int tmpCnt = 0;
        for (int i = 0; i < attactees.size() - 1; i++) {
            Node item = attactees.get(i);
            map[item.x][item.y] -= (attactValue / 2);
            if (map[item.x][item.y] <= 0) tmpCnt++;
        }

        Node attactee = attactees.get(attactees.size() - 1);
        map[attactee.x][attactee.y] -= attactValue;
        if (map[attactee.x][attactee.y] <= 0) tmpCnt++;
        cnt -= tmpCnt;
    }

    private static void rest(List<Node> nodes) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (map[i][j] <= 0) continue;
                boolean flag = true;
                for (Node node : nodes) {
                    if (node.x == i && node.y == j) {
                        flag = false;
                        break;
                    }
                }
                if (flag) map[i][j]++;
            }
    }

    private static void printRes() {
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, map[i][j]);
            }
        }
        System.out.println(res);
    }
}
