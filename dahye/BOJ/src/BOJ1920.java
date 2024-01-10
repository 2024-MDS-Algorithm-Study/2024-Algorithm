import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
수 찾기
 */

public class BOJ1920 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M, A[], arr[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        A = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = N - 1;

            boolean flag = false;

            while(start <= end) {
                int middle = (start + end) / 2;
                if(A[middle] < target) {
                    start = middle + 1;
                } else if(A[middle] > target) {
                    end = middle - 1;
                } else {
                    flag = true;
                    break;
                }
            }

            if(flag) sb.append(1 + "\n");
            else sb.append(0 + "\n");
        }

        System.out.print(sb);
    }
}
