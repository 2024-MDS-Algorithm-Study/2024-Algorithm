import java.io.*;
import java.util.*;

public class BOJ13900 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] inputs = new long[n + 1];
        long[] sums = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            inputs[i] = sums[i] = Long.parseLong(st.nextToken());
            sums[i] += sums[i - 1];
        }

        long sum = 0;
        for (int i = 1; i < n; i++) {
            sum += inputs[i] * (sums[n] - sums[i]);
        }

        System.out.println(sum);
    }
}
