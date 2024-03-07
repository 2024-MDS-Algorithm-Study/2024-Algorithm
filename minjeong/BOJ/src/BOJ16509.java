import java.io.*;

public class BOJ16509 {
    static int res = Integer.MAX_VALUE;
    static int[][] map = new int[10][9],
            del1 = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}},
            del2 = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputs = br.readLine().toCharArray();
        int x = inputs[0] - '0';
        int y = inputs[2] - '0';
        inputs = br.readLine().toCharArray();
        map[inputs[0] - '0'][inputs[2] - '0'] = 2;

        solve(x, y, 0);
        System.out.println(res);
    }

    private static void solve(int x, int y, int cnt) {
        if (cnt >= res) return;
        if (map[x][y] == 2) {
            res = cnt;
            return;
        }

        map[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + del1[i][0];
            int ny = y + del1[i][1];
            if (nx < 0 || nx >= 10 || ny < 0 || ny >= 9 || map[nx][ny] == 2) continue;
            l:
            for (int j = i; j < i + 2; j++) {
                int newNx = nx;
                int newNy = ny;
                for (int k = 0; k < 2; k++) {
                    newNx += del2[j][0];
                    newNy += del2[j][1];
                    if ((k == 0 && (newNx < 0 || newNx >= 10 || newNy < 0 || newNy >= 9 || map[newNx][newNy] == 2))
                            || (k == 1 && (newNx < 0 || newNx >= 10 || newNy < 0 || newNy >= 9))) continue l;
                }
                if (map[newNx][newNy] != 1) solve(newNx, newNy, cnt + 1);
            }
        }

        map[x][y] = 0;
    }
}
