import java.io.*;
import java.util.*;

public class BOJ21758 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        long[] sum = new long[n];
        int midMax = 0;
        int midIdx = -1;
        for (int i = 0; i < n; i++) {
            sum[i] = arr[i] = Integer.parseInt(st.nextToken());
            if (i > 0) sum[i] += sum[i - 1];
            if (i > 0 && i < n - 1) {
                if (midMax < arr[i]) {
                    midMax = arr[i];
                    midIdx = i;
                }
            }
        }

        long res = sum[n - 1] - arr[n - 1] - arr[0] + arr[midIdx];
        for (int i = 1; i < n - 1; i++) {
            res = Math.max(res,  2 * sum[n - 1] - sum[i] - arr[i] - arr[0]);
        }
        for (int i = n - 2; i > 0; i--) {
            res = Math.max(res, sum[n - 1] + sum[i - 1] - arr[i] - arr[n - 1]);
        }
        System.out.println(res);
    }
}
