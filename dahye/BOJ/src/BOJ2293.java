
/*
동전 1
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, arr[], dp[][];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n][k + 1];
        for(int j = 1; j < n; j++) dp[j][0] = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < k + 1; j++) {
                if(i == 0) if(j % arr[i] == 0) dp[i][j] = 1;
                if(i > 0) {
                    dp[i][j] = dp[i - 1][j];
                    if(j - arr[i] >= 0) dp[i][j] = dp[i - 1][j] + dp[i][j - arr[i]];
                }
            }
        }

        System.out.println(dp[n - 1][k]);
    }
}
