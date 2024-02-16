import java.io.*;
import java.util.*;

/*
 * 불켜기
 */

public class BOJ11967 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static boolean light[][], v[][];
    static int N, count;
    static ArrayList<Point> map[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1][N + 1];
        light = new boolean[N + 1][N + 1];
        v = new boolean[N + 1][N + 1];
        light[1][1] = true;
        count++;

        for(int r = 1; r < N + 1; r++)
            for(int c = 1; c < N + 1; c++)
                map[r][c] = new ArrayList<Point>();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[r][c].add(new Point(a, b));
        }

        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(1, 1));
        v[1][1] = true;

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for(Point p: map[current.r][current.c]) {
                if(light[p.r][p.c]) continue;

                // 불 켠다!
                light[p.r][p.c] = true;
                count++;

                for(int d = 0; d < 4; d++) {
                    int checkR = p.r + dr[d];
                    int checkC = p.c + dc[d];

                    if(!check(checkR, checkC)) continue;
                    if(v[checkR][checkC]) {
                        queue.add(new Point(p.r, p.c));
                        v[p.r][p.c] = true;
                        break;
                    }
                }
            }

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nr, nc)) continue;
                if(light[nr][nc] && !v[nr][nc]) {
                    v[nr][nc] = true;
                    queue.add(new Point(nr, nc));
                }
            }
        }

        System.out.println(count);
    }
    private static boolean check(int nr, int nc) {
        return nr >= 1 && nr < N + 1 && nc >= 1 && nc < N + 1;
    }
}