import java.io.*;
import java.util.*;

public class BOJ15810 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] times = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        long start = 1, end = 1000000L * 1000000L;
        while (start < end) {
            long mid = (start + end) / 2;
            long cnt = 0;
            for (int time : times) cnt += mid / time;
            if (cnt >= m) end = mid;
            else start = mid + 1;
        }

        System.out.println(end);
    }
}
