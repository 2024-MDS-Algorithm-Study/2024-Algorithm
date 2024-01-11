import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
마법사 상어와 파이어볼
 */
public class BOJ20056 {
    static class Fireball {
        int r, c, m, d, s;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, K, map[][];
    static ArrayList<Fireball> fireballs;
    static ArrayList<Fireball> removeFireballs;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1]; // 해당 위치에서 fireball의 개수

        fireballs = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c]++;
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        while(K-- > 0) {
            // 모든 파이어볼이 방향 di로 속력 Si칸 만큼 이동
            for(int i = 0; i < fireballs.size(); i++) {
                Fireball fireball = fireballs.get(i);

                int s = fireball.s;
                int d = fireball.d;

                int nr = calNextPoint(fireball.r, dr, s, d);
                int nc = calNextPoint(fireball.c, dc, s, d);

                map[fireball.r][fireball.c]--;

                fireball.r = nr;
                fireball.c = nc;

                map[nr][nc]++;
            }

            // 2개 이상의 파이어볼이 있는 칸
            for(int r = 1; r < N + 1; r++) {
                for(int c = 1; c < N + 1; c++) {
                    // 칸은 칸에 있는 파이어볼은 하나로 합쳐짐
                    // 파이어볼은 4개의 파이어볼로 나누어짐

                    if(map[r][c] >= 2) {
                        map[r][c] = 0;

                        int curR = r;
                        int curC = c;

                        checkFireballs(curR, curC);

                        int removeFireballCnt = removeFireballs.size();
                        boolean checkDir = true;

                        int sumM = 0;
                        int sumS = 0;
                        int checkDirTmp = removeFireballs.get(0).d % 2;

                        for(int i = 0; i < removeFireballs.size(); i++) {
                            sumM += removeFireballs.get(i).m;
                            sumS += removeFireballs.get(i).s;

                            if(i != 0 && (removeFireballs.get(i).d % 2) != checkDirTmp) {
                                checkDir = false;
                            }
                        }

                        for(Fireball fireball: removeFireballs) fireballs.remove(fireball);

                        if(sumM / 5 == 0) continue;
                        int start = 0;
                        if(!checkDir) start = 1;
                        // 파이어볼의 방향이 모두 짝수이거나 홀수 0, 2, 4, 6
                        for(int i = start; i <= 7; i += 2) {
                            fireballs.add(new Fireball(curR, curC,sumM / 5, sumS / removeFireballCnt, i));
                            map[curR][curC]++;
                        }
                    }
                }
            }
        }

        int result = 0;
        for(Fireball fireball: fireballs) {
            result += fireball.m;
        }

        System.out.println(result);
    }

    private static int calNextPoint(int r, int[] dir, int s, int d) {
        int next = (r + dir[d] * s) % N;
        next = next <= 0 ? next + N : next;

        return next;
    }

    private static void checkFireballs(int r, int c) {
        removeFireballs = new ArrayList<>();
        for(int i = 0; i < fireballs.size(); i++) {
            if(fireballs.get(i).r == r && fireballs.get(i).c == c) removeFireballs.add(fireballs.get(i));
        }
    }
}