import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1138 {
    private static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, new int[n], new boolean[n], arr);
    }

    private static void solve(int depth, int[] res, boolean[] v, int[] arr) {
        if (flag) return;
        if (depth == res.length) {
            if (finish(res, arr)) {
                for (int x : res) System.out.print((x + 1) + " ");
                System.out.println();
                flag = true;
            }
            return;
        }

        for (int i = 0; i < res.length; i++) {
            if (v[i]) continue;
            res[depth] = i;
            v[i] = true;
            if (!flag) solve(depth + 1, res, v, arr);
            v[i] = false;
        }
    }

    private static boolean finish(int[] res, int[] arr) {
        for (int order = 0; order < res.length; order++) {
            int cnt = 0;
            for (int i = 0; i < order; i++) {
                if (res[i] > res[order]) cnt++;
            }
            if (cnt != arr[res[order]]) return false;
        }
        return true;
    }
}
