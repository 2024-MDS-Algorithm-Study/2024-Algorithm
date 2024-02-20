import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ28449 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        Map<Integer, Long> map = new HashMap<>();
        Map<Integer, Integer> start = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            map.putIfAbsent(a[i], 0L);
            map.put(a[i], map.get(a[i]) + 1);
        }
        Arrays.sort(a);
        for (int i = 0; i < n; i++) start.putIfAbsent(a[i], i);

        st = new StringTokenizer(br.readLine());
        long win = 0, res = 0;
        for (int i = 0; i < m; i++) {
            int b = Integer.parseInt(st.nextToken());
            int idx = Arrays.binarySearch(a, b);
            if (idx >= 0) {
                idx = start.get(a[idx]);
                res += map.get(a[idx]);
                if (idx - 1 >= 0) win += idx;
            } else win += -(idx + 1);

        }

        System.out.println(((long) m * n - win - res) + " " + win + " " + res);
    }
}
