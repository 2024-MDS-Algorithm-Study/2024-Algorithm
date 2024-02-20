import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2293 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int[] values = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = values[i]; j <= k; j++) {
                if (values[i] == j) dp[j] += 1;
                else dp[j] += dp[j - values[i]];
            }
        }

        System.out.println(dp[k]);
    }
}
