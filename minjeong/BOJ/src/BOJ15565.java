import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15565 {

    public static void main(String[] args) throws Exception {
        int res = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, k;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, cnt = 0;
        if (arr[end] == 1) cnt++;
        while (start <= end && end < n) {
            if (cnt < k) {
                if (++end < n && arr[end] == 1) cnt++;
            }
            else {
                if (cnt == k) res = Math.min(res, end - start + 1);
                if (arr[start++] == 1) cnt--;
            }
        }

        if (res == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);
    }
}
