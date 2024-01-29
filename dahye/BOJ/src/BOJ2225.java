import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
합분해
 */

public class BOJ2225 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int dp[][];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[201][201];


        for(int i = 0; i < N + 1; i++) {
            for(int j = 1; j < K + 1; j++) {
            	if(i == 0 || j == 1) {
            		dp[i][j] = 1;
            		continue;
            	}
            	
                dp[i][j] = dp[i][j - 1];
                
                for(int k = 1; k <= i; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - k][j - 1]) % 1_000_000_000;
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
