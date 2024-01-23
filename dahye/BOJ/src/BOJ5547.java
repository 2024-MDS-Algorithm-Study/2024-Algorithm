import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
일루미네이션
 */

public class BOJ5547 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int W, H, result, map[][];
    static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
    static int odd[] = {0, 1, 2, 3, 4, 6};
    static int even[] = {0, 2, 4, 5, 6, 7};
    static boolean v[][];
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 2][W + 2];
        v = new boolean[H + 2][W + 2];

        for(int r = 0; r < H; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < W; c++) {
                map[r + 1][c + 1] = Integer.parseInt(st.nextToken());
            }
        }

        func();

        System.out.println(result);
    }

    private static void func() {
        queue.add(new Point(0, 0));
        queue.add(new Point(0, map[0].length - 1));
        queue.add(new Point(map.length- 1, 0));
        queue.add(new Point(map.length - 1, map[0].length - 1));

        v[0][0] = true;
        v[0][map[0].length - 1] = true;
        v[map.length - 1][0] = true;
        v[map.length - 1][map[0].length - 1] = true;

        int dir[] = null;

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for(int i = 0; i < 6; i++) {
                if(current.r % 2 == 0) dir = even;
                else dir = odd;

                int nr = current.r + dr[dir[i]];
                int nc = current.c + dc[dir[i]];

                if(!check(nr, nc)) continue;
                if(map[nr][nc] == 1) {
                    result++;
                    continue;
                }
                if(!v[nr][nc] && map[nr][nc] == 0) {
                    v[nr][nc] = true;
                    queue.add(new Point(nr, nc));
                }
            }
        }
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length;
    }
}