import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Integer>[] graph;
    private static boolean[] v;
    private static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = 1;
        while (true) {
            cnt = 0;
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            if (n == 0 && m == 0) break;

            graph = new List[n + 1];
            v = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < m; i++) {
                s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                graph[a].add(b);
                graph[b].add(a);
            }

            for (int i = 1; i <= n; i++) {
                if (!v[i]) {
                    if (bfs(0, i)) cnt++;
                }
            }

            sb.append("Case ").append(t++).append(": ");
            if (cnt == 0) sb.append("No trees.\n");
            else if (cnt == 1) sb.append("There is one tree.\n");
            else sb.append("A forest of ").append(cnt).append(" trees.\n");
        }
        System.out.println(sb);
    }

    private static boolean bfs(int pre, int cur) {
        boolean flag = true;
        v[cur] = true;
        for (int next : graph[cur]) {
            if (next == pre) continue;
            if (v[next]) return false;
            flag &= bfs(cur, next);
        }
        return flag;
    }
}
