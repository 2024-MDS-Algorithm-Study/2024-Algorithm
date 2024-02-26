import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9084 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t, n;
        int[] coins, dp;
        t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            coins = new int[n + 1];
            dp = new int[10001];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());
            for (int i = 1; i <= n; i++) {
                dp[coins[i]]++;
                for (int j = coins[i]; j <= m; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }

            sb.append(dp[m]).append('\n');
        }
        System.out.println(sb);
    }
}
