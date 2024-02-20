import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11055 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = dp[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) dp[i] = Math.max(dp[j] + arr[i], dp[i]);
            }
            res = Math.max(dp[i], res);
        }

        System.out.println(res);
    }
}
