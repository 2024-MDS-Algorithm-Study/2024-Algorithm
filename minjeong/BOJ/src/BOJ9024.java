import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n, k, arr[];
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            int s = 0, e = n - 1, res = 0, minGap = Integer.MAX_VALUE;
            while (s < e) {
                int sum = arr[s] + arr[e];
                int gap = Math.abs(k - sum);
                if (minGap > gap) {
                    res = 0;
                    minGap = gap;
                }
                if (gap == minGap) res++;
                if (sum - k >= 0) e--;
                else s++;
            }

            sb.append(res).append('\n');
        }

        System.out.println(sb);
    }
}
