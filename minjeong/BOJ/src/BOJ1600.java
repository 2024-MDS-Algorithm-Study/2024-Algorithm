import java.io.*;
import java.util.*;

public class BOJ1600 {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int jumpCnt;
        int moveCnt;

        public Node(int x, int y, int jumpCnt, int moveCnt) {
            this.x = x;
            this.y = y;
            this.jumpCnt = jumpCnt;
            this.moveCnt = moveCnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.moveCnt, o.moveCnt);
        }
    }
    static int[][] del = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
            {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    static int k, w, h;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        boolean[][][] v = new boolean[h][w][k + 1];
        int min = Integer.MAX_VALUE;
        Queue<Node> q = new ArrayDeque<>();
        v[0][0][0] = true;
        q.offer(new Node(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == h - 1 && cur.y == w - 1) min = Math.min(min, cur.moveCnt);

            for (int i = 0; i < 12; i++) {
                int nx = cur.x + del[i][0];
                int ny = cur.y + del[i][1];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 1) continue;
                if (cur.jumpCnt == k && i >= 4) continue;
                if (i < 4 && !v[nx][ny][cur.jumpCnt]) {
                    v[nx][ny][cur.jumpCnt] = true;
                    q.offer(new Node(nx, ny, cur.jumpCnt, cur.moveCnt + 1));
                }
                if (i >= 4 && cur.jumpCnt < k && !v[nx][ny][cur.jumpCnt + 1]) {
                    v[nx][ny][cur.jumpCnt + 1] = true;
                    q.offer(new Node(nx, ny, cur.jumpCnt + 1, cur.moveCnt + 1));
                }
            }
        }

        if (min == Integer.MAX_VALUE) min = -1;
        return min;
    }
}