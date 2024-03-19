import java.io.*;
import java.util.*;

public class BOJ2240 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T, W, res = 0;
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int[] arr = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[W + 1];
        for (int t = 1; t <= T; t++) {
            for (int w = Math.min(W, t); w >= 0; w--) {
                int plus = 0;
                if (w % 2 == 0 && arr[t] == 1 || w % 2 == 1 && arr[t] == 2) plus++;
                dp[w] += plus;
                if (w - 1 >= 0) dp[w] = Math.max(dp[w], dp[w - 1] + plus);
                res = Math.max(res, dp[w]);
            }
        }

        System.out.println(res);
    }
}
