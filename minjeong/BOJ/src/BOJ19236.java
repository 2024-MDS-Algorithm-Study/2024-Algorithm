import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int res;
    static int[][] del = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static Node[][] map = new Node[4][4];

    static class Node {
        int num;
        int dir;

        Node(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[i][j] = new Node(a, b);
            }
        }

        dfs(0, 0, 0);
        System.out.println(res);
    }

    private static void dfs(int x, int y, int sum) {
        Node[][] tmpMap = new Node[4][4];
        copyMap(map, tmpMap);

        Node eatFish = map[x][y];
        int sharkDir = eatFish.dir;
        map[x][y] = null;

        moveFish(x, y);

        if (moveShark(x, y, sharkDir, sum, eatFish) == 0) {
            res = Math.max(res, sum + eatFish.num);
        }

        copyMap(tmpMap, map);
    }

    private static void moveFish(int x, int y) {
        l:
        for (int n = 1; n <= 16; n++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] == null || map[i][j].num != n) continue;
                    while (true) {
                        int nx = i + del[map[i][j].dir - 1][0];
                        int ny = j + del[map[i][j].dir - 1][1];
                        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (nx == x && ny == y)) {
                            map[i][j].dir += 1;
                            if (map[i][j].dir == 9) map[i][j].dir = 1;
                            continue;
                        }
                        Node tmpFish = map[i][j];
                        map[i][j] = map[nx][ny];
                        map[nx][ny] = tmpFish;
                        continue l;
                    }
                }
            }
        }
    }

    private static int moveShark(int x, int y, int sharkDir, int sum, Node eatFish) {
        int cnt = 0;
        int nx = x;
        int ny = y;
        Node[][] tmpMap = new Node[4][4];
        copyMap(map, tmpMap);
        while (true) {
            nx += del[sharkDir - 1][0];
            ny += del[sharkDir - 1][1];
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;
            if (map[nx][ny] != null) {
                cnt++;
                dfs(nx, ny, sum + eatFish.num);
                copyMap(tmpMap, map);
            }
        }
        return cnt;
    }

    private static void copyMap(Node[][] src, Node[][] dest) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (src[i][j] == null) dest[i][j] = null;
                else dest[i][j] = new Node(src[i][j].num, src[i][j].dir);
            }
        }
    }
}
