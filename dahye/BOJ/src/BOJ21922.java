import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
학부 연구생 민상
 */

public class BOJ21922 {
    static class Wind {
        int r, c, vr, vc;

        public Wind(int r, int c, int vr, int vc) {
            this.r = r;
            this.c = c;
            this.vr = vr;
            this.vc = vc;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, map[][], dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Wind> queue = new LinkedList<>();
    static boolean v[][];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 9) {
                    for(int d = 0; d < 4; d++) queue.add(new Wind(r, c, dr[d], dc[d]));
                }
            }
        }

        System.out.println(func());
    }

    private static int func() {
        int result = 0;
        boolean visit[][][] = new boolean[4][N][M];

        while(!queue.isEmpty()) {
            Wind current = queue.poll();

            int vr = current.vr;
            int vc = current.vc;

            if(!v[current.r][current.c]) {
                v[current.r][current.c] = true;
                result++;
            }

            visit[findIdx(vr, vc)][current.r][current.c] = true;

            if(map[current.r][current.c] == 1) vc *= -1;
            else if(map[current.r][current.c] == 2) vr *= -1;
            else if(map[current.r][current.c] == 3) {
                int tmp = vc;
                vc = -vr;
                vr = -tmp;
            } else if(map[current.r][current.c] == 4) {
                int tmp = vr;
                vr = vc;
                vc = tmp;
            }

            int nr = current.r + vr;
            int nc = current.c + vc;

            int idx = findIdx(vr, vc);
            if(!check(nr, nc) || visit[idx][nr][nc]) continue;

            visit[idx][nr][nc] = true;
            queue.add(new Wind(nr, nc, vr, vc));
        }
        return result;
    }

    private static int findIdx(int r, int c) {
        if(r == dr[0] && c == dc[0]) return 0;
        else if(r == dr[1] && c == dc[1]) return 1;
        else if(r == dr[2] && c == dc[2]) return 2;
        else  return 3;
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
