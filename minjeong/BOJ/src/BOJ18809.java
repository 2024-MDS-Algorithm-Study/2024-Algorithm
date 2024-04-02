import java.io.*;
import java.util.*;

public class BOJ18809 {
    static int n, m, g, r, res;
    static int[][] map, del = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static List<Node> list = new ArrayList<>();
    static class Node {
        int x;
        int y;
        int GR;
        int time;

        public Node(int x, int y, int GR, int time) {
            this.x = x;
            this.y = y;
            this.GR = GR;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) list.add(new Node(i, j, -1, 0));
            }
        }

        solve(0, 0, new HashSet<>(), new HashSet<>(), false);
        System.out.println(res);
    }

    static void solve(int pos, int depth, Set<Integer> green, Set<Integer> red, boolean flag) {
        if (depth == g + r) {
            res = Math.max(res, simul(green, red));
            return;
        }

        if (depth == g) {
            flag = true;
            pos = 0;
        }

        for (int i = pos; i < list.size(); i++) {
            if (!flag) {
                green.add(i);
                solve(i + 1, depth + 1, green, red, flag);
                green.remove(i);
            } else {
                if (green.contains(i)) continue;
                red.add(i);
                solve(i + 1, depth + 1, green, red, flag);
                red.remove(i);
            }
        }
    }

    static int simul(Set<Integer> greens, Set<Integer> reds) {
        int cnt = 0;
        int[][] tmpMap = cloneMap();
        int[][] times = new int[n][m];
        boolean[][] v = new boolean[n][m];
        Queue<Node> q = new ArrayDeque<>();
        fillQueue(q, greens, 3, tmpMap, v);
        fillQueue(q, reds, 4, tmpMap, v);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (tmpMap[cur.x][cur.y] == 0) continue;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || tmpMap[nx][ny] == 0) continue;
                if (!v[nx][ny]) {
                    tmpMap[nx][ny] = cur.GR;
                    times[nx][ny] = cur.time + 1;
                    v[nx][ny] = true;
                    q.offer(new Node(nx, ny, cur.GR, cur.time + 1));
                } else if (v[nx][ny] && tmpMap[nx][ny] != cur.GR && times[nx][ny] == cur.time + 1) {
                    cnt++;
                    tmpMap[nx][ny] = 0;
                }
            }
        }

        return cnt;
    }

    static void fillQueue(Queue<Node> q, Set<Integer> colors, int CODE, int[][] tmpMap, boolean[][] v) {
        for (int color : colors) {
            Node node = list.get(color);
            node.GR = CODE;
            tmpMap[node.x][node.y] = CODE;
            v[node.x][node.y] = true;
            q.offer(node);
        }
    }

    static int[][] cloneMap() {
        int[][] tmpMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            tmpMap[i] = map[i].clone();
        }
        return tmpMap;
    }
}
