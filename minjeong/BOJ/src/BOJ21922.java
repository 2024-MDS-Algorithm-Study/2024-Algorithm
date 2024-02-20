import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21922 {
    static int[][] del = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) list.add(new Node(i, j));
            }
        }

        boolean[][] v = new boolean[n][m];
        boolean[][][] v2 = new boolean[n][m][4];
        int res = 0;
        for (Node air : list) {
            for (int i = 0; i < 4; i++) {
                int nx = air.x;
                int ny = air.y;
                int dir = i;
                while (nx >= 0 && nx < n && ny >= 0 && ny < m && !v2[nx][ny][dir]) {
                    if (!v[nx][ny]) res++;
                    v[nx][ny] = true;
                    v2[nx][ny][dir] = true;
                    if (map[nx][ny] == 1) {
                        if (dir == 0 || dir == 2) dir = (dir + 2) % 4;
                    } else if (map[nx][ny] == 2) {
                        if (dir == 1 || dir == 3) dir = (dir + 2) % 4;
                    } else if (map[nx][ny] == 3) {
                        if (dir % 2 == 0) dir = dir - 1 < 0 ? 3 : dir - 1;
                        else dir = (dir + 1) % 4;
                    } else if (map[nx][ny] == 4) {
                        if (dir % 2 == 0) dir = (dir + 1) % 4;
                        else dir = dir - 1;
                    }
                    nx += del[dir][0];
                    ny += del[dir][1];
                }
            }
        }

        System.out.println(res);
    }
}
