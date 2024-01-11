import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int res = Integer.MAX_VALUE, n;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, 0, 0, new boolean[n]);

        System.out.println(res);
    }

    private static void solve(int start, int pos, int depth, int cost, boolean[] visited) {
        if (depth == n && pos == start) {
            res = Math.min(res, cost);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && map[pos][i] != 0) {
                visited[i] = true;
                solve(start, i, depth + 1, cost + map[pos][i], visited);
                visited[i] = false;
            }
        }
    }
}
