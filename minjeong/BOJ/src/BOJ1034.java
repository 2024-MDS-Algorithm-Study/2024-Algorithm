import java.io.*;
import java.util.*;

public class BOJ1034 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m, k, res = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> cnts = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == '0') cnt++;
            }
            cnts.put(s, cnt);
            map.compute(s, (key, v) -> (v == null) ? 1 : v + 1);
        }

        k = Integer.parseInt(br.readLine());
        for (String s : map.keySet()) {
            if (cnts.get(s) > k) continue;
            if (cnts.get(s) % 2 != k % 2) continue;
            res = Math.max(res, map.get(s));
        }

        System.out.println(res);
    }
}
