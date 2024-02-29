import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1436 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0, res;
        for (res = 666;; res++) {
            if (compare(res)) cnt++;
            if (cnt == n) break;
        }

        System.out.println(res);
    }

    private static boolean compare(int res) {
        String s = String.valueOf(res);
        for (int i = 0; i <= s.length() - 3; i++) {
            if (s.startsWith("666", i)) return true;
        }
        return false;
    }
}
