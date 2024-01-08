import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
이동하기
 */
public class BOJ11048 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, arr[][], dp[][];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];

        for(int r = 1; r < arr.length; r++) dp[r][0] = arr[r][0] + dp[r - 1][0];
        for(int c = 1; c < arr[0].length; c++) dp[0][c] = arr[0][c] + dp[0][c - 1];

        for (int r = 1; r < arr.length; r++) {
            for (int c = 1; c < arr[0].length; c++) {
                int max = Math.max(Math.max(dp[r - 1][c - 1], dp[r - 1][c]), dp[r][c - 1]);
                dp[r][c] = arr[r][c] + max;
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}