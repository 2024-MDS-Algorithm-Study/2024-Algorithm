/*
외판원 순회 2
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10971 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, W[][], nums[], min;
    static boolean v[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        v = new boolean[N];
        min = Integer.MAX_VALUE;
        W = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 중복 순열
        perm(0);

        System.out.println(min);
    }

    private static void perm(int k) {
        if(k == N) {
            int sum = 0;

            for(int i = 0; i < N; i++) {
                if(W[nums[i]][nums[(i +  1) % N]] == 0) return;
                sum += W[nums[i]][nums[(i +  1) % N]];
            }
            min = Math.min(min, sum);

            return;
        }

        for(int i = 0; i < N; i++) {
            if(v[i]) continue;
            nums[k] = i;
            v[i] = true;
            perm(k + 1);
            v[i] = false;
        }
    }
}