import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ16922 {
    private static int[] nums = {1, 5, 10, 50};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(solve(n, 0, 0, new boolean[n * 50 + 1]));
    }

    private static int solve(int cnt, int num, int pre, boolean[] v) {
        int res = 0;
        if (cnt == 0) {
            if (!v[num]) {
                v[num] = true;
                res = 1;
            }
            return res;
        }

        for (int i = pre; i < 4; i++) {
            res += solve(cnt - 1, num + nums[i], i, v);
        }
        return res;
    }
}
