import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ4803 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static ArrayList<Integer>[] tree;
    static boolean v[];

    public static void main(String[] args) throws Exception {
        int test_case = 1;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int count = 0;
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            tree = new ArrayList[n + 1];
            v = new boolean[n + 1];

            for(int i = 0; i < tree.length; i++) {
                tree[i] = new ArrayList<>();
            }

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[a].add(b);
                tree[b].add(a);
            }

            for(int i = 1; i < n + 1; i++) {
                if(!v[i]) {
                    v[i] = true;
                    if(dfs(-1, i)) count++;
                }
            }
            output(test_case++, count);
        }

        System.out.print(sb);
    }

    private static boolean dfs(int root, int current) {
        for(int next: tree[current]) {
            if(next == root) continue;
            if(v[next]) return false;
            v[next] = true;
            if(!dfs(current, next)) return false;
        }
        return true;
    }

    private static void output(int test_case, int count) {
        if(count == 0) sb.append("Case " + test_case + ": No trees." + "\n");
        else if(count == 1) sb.append("Case " + test_case + ": There is one tree." + "\n");
        else sb.append("Case " + test_case + ": A forest of " + count + " trees." + "\n");
    }
}
