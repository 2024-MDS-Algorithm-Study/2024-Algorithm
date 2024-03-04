import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
점프
 */

public class BOJ1890 {
    static int dr[] = {1, 0};
    static int dc[] = {0, 1};
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N][N];
        long dp[][] = new long[N][N];

        dp[0][0] = 1;

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                for(int d = 0; d < 2; d++) {
                    int nr = r + dr[d] * arr[r][c];
                    int nc = c + dc[d] * arr[r][c];

                    if(!check(nr, nc) || (r == N - 1 && c == N - 1)) continue;
                    dp[nr][nc] += dp[r][c];
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}
