import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16943 {
    static int b, answer = -1;
    static char[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken().toCharArray();
        b = Integer.parseInt(st.nextToken());
        solve(0, new char[a.length], new boolean[a.length]);
        System.out.println(answer);
    }

    private static void solve(int depth, char[] res, boolean[] v) {
        if (depth == a.length) {
            int num = makeNum(res);
            if (num < b) answer = Math.max(answer, num);
            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (v[i]) continue;
            if (depth == a.length - 1 && a[i] == '0') continue;
            v[i] = true;
            res[depth] = a[i];
            solve(depth + 1, res, v);
            v[i] = false;
        }
    }

    private static int makeNum(char[] res) {
        int num = 0;
        int start = 1;
        for (int i = 0; i < res.length; i++) {
            num += (res[i] - '0') * start;
            start *= 10;
        }
        return num;
    }
}
