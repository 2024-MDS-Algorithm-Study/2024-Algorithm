import java.io.*;
import java.util.*;

public class BOJ19941 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        int res = 0;
        boolean[] v = new boolean[n];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'H') continue;
            for (int j = Math.max(0, i - k); j <= Math.min(i + k, s.length() - 1); j++) {
                if (s.charAt(j) == 'H' && !v[j]) {
                    v[j] = true;
                    res++;
                    break;
                }
            }
        }

        System.out.println(res);
    }
}
