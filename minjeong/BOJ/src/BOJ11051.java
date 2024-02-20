import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11051 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;

        System.out.println(dfs(n, k, dp));
    }

    private static int dfs(int n, int k, int[][] dp) {
        if (n < k) return 0;
        if (dp[n][k] != 0) return dp[n][k];
        if (k == 0) return dp[n][k] = 1;
        return dp[n][k] = (dfs(n - 1, k - 1, dp) + dfs(n - 1, k, dp)) % 10007;
    }
}
