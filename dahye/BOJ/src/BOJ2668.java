import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/*
숫자고르기
 */

public class BOJ2668 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static ArrayList<Integer> adj[];
    static boolean v[][];
    static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        v = new boolean[N + 1][2];

        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();

        for(int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(br.readLine());
            adj[num].add(i);
        }

        for(int i = 1; i < N + 1; i++) {
            if(!v[i][0]) {
                v[i][0] = true;
                func(i);
            }
        }

        Collections.sort(list);
        sb.append(list.size() + "\n");
        for(int n: list) sb.append(n + "\n");
        System.out.print(sb);
    }

    private static void func(int k) {
        v[k][1] = true;
        for(int next: adj[k]) {
            if(v[next][1]) {
                for(int i = 1; i <N + 1; i++) {
                    if(v[i][1]) list.add(i);
                }
            }

            if(!v[next][0]) {
                v[next][0] = true;
                v[next][1] = true;
                func(next);
                v[next][1] = false;
            }
        }

        v[k][1] = false;
    }
}
