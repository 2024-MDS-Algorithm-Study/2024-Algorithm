import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c, n;
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[] costs = new int[n];
        int[] customers = new int[n];
        int[] dp = new int[2000];
        Arrays.fill(dp, Integer.MAX_VALUE / 3);
        int max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            customers[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, customers[i]);
        }

        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = customers[i]; j <= c + max; j++) {
                dp[j] = Math.min(dp[j], dp[j - customers[i]] + costs[i]);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = c; i <= c + max; i++) {
            res = Math.min(res, dp[i]);
        }
        System.out.println(res);
    }
}
