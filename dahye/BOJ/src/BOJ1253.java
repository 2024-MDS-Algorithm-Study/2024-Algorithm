import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
좋다
 */

public class BOJ1253 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, result;
    static long arr[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        if(N < 3) {
            System.out.println(0);
            return;
        }

        Arrays.sort(arr);

        for(int i = 0; i < N; i++) {
            long current = arr[i];

            int start = i == 0 ? 1 : 0;
            int end = N - 1 == i ? N - 2: N - 1;

            while(start < end && start < N  && end < N) {
                if(arr[start] + arr[end] <= current) {
                    if(arr[start] + arr[end] == current) {
                        result += 1;
                        break;
                    }

                    start = start + 1;
                    if(start == i) start++;

                } else {
                    end = end - 1;
                    if(end == i) end--;
                }
            }
        }

        System.out.print(result);
    }
}
