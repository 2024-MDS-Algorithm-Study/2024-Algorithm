import java.io.*;

public class BOJ2133 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (n > 1) dp[2] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 2] * 3;
            for (int j = 4; j <= i; j += 2) dp[i] += dp[i - j] * 2;
        }

        System.out.println(dp[n]);
    }
}