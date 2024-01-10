import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
보물섬
 */

public class BOJ2589 {
    static class Point {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, result;
    static char map[][];
    static boolean v[][];
    static int dr[] = {-1 ,1 ,0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        result = Integer.MIN_VALUE;

        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = input.charAt(c);
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] == 'L') {
                    result = Math.max(result, bfs(new Point(r, c, 0)));
                }
            }
        }

        System.out.println(result);
    }

    private static int bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        v = new boolean[N][M];

        queue.add(point);
        v[point.r][point.c] = true;

        int result = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            result = Math.max(result, current.cnt);

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == 'W') continue;
                v[nr][nc] = true;
                queue.add(new Point(nr, nc, current.cnt + 1));
            }
        }
        return result;
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
