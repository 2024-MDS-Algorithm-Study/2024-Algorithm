import java.io.*;
import java.util.*;

public class BOJ3079 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n;
        long m;
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        long start = 0, end = arr[0] * m;
        while (start < end) {
            long cnt = 0, mid = (start + end) / 2;
            for (int x : arr) cnt += mid / x;
            if (cnt >= m) end = mid;
            else start = mid + 1;
        }

        System.out.println(end);
    }
}
