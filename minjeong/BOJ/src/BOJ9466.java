import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466 {
    static int n, res;
    static int[] students = new int[100001];
    static boolean[] v = new boolean[100001], made;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            res = n;
            for (int i = 0; i < n; i++) {
                students[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            made = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!made[i]) solve(i);
            }
            sb.append(res).append('\n');
        }
        System.out.println(sb);
    }

    private static void solve(int pos) {
        if (v[pos]) {
            int next = pos;
            do {
                made[next] = true;
                next = students[next];
                res--;
            } while (next != pos);
            return;
        }

        v[pos] = true;
        if (!made[students[pos]]) solve(students[pos]);
        v[pos] = false;
        made[pos] = true;
    }
}
