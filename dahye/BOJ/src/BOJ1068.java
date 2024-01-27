import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
트리
 */

public class BOJ1068 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, remove, result;
    static ArrayList<Integer> adj[];
    static boolean v[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        remove = Integer.parseInt(br.readLine());

        adj = new ArrayList[N];
        v = new boolean[N];

        int start = 0;
        for(int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int s = Integer.parseInt(st.nextToken());
            if(s == -1) {
                start = i;
                continue;
            }
            adj[s].add(i);
        }

        if(remove != start) {
            v[start] = true;
            dfs(start);
        }

        System.out.println(result);
    }

    private static void dfs(int start) {
        if(adj[start].size() == 0 || (adj[start].size() == 1 && adj[start].contains(remove)))
            result++;
        for(int next: adj[start]) {
            if(next == remove) continue;
            if(!v[next]) {
                v[next] = true;
                dfs(next);
            }
        }
    }
}
