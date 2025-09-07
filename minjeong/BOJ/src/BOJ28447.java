import java.io.*;
import java.util.*;

public class BOJ28447 {
    static int res = Integer.MIN_VALUE, n, k, map[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, new int[k]);
        System.out.println(res);
    }

    private static void solve(int pos, int depth, int[] com) {
        if (depth == k) {
            res = Math.max(res, calc(com, map));
            return;
        }

        for (int i = pos; i < map.length; i++) {
            com[depth] = i;
            solve(i + 1, depth + 1, com);
        }
    }

    private static int calc(int[] com, int[][] map) {
        int sum = 0;
        for (int i = 0; i < com.length; i++) {
            for (int j = i; j < com.length; j++) {
                sum += map[com[i]][com[j]];
            }
        }
        return sum;
    }
}
