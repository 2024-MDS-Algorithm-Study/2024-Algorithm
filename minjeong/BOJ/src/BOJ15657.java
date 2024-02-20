import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ15657 {
    static int n, m, arr[];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        solve(0, 0, new int[m]);
        bw.flush();
        bw.close();
    }

    private static void solve(int pos, int depth, int[] res) throws Exception {
        if (depth == m) {
            for (int x : res) bw.write(x + " ");
            bw.write('\n');
            return;
        }

        for (int i = pos; i < n; i++) {
            res[depth] = arr[i];
            solve(i, depth + 1, res);
        }
    }
}
