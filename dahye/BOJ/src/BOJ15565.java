import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
귀여운 라이언
 */

public class BOJ15565 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, arr[], result;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int count = 0;
        result = Integer.MAX_VALUE;

        if(arr[start] == 1) count = 1;

        while(start < N && end < N) {
            if(K > count) {
                end++;
                if(end == N) break;
                if(arr[end] == 1) count += 1;
            }
            else {
                if(count == K) result = Math.min(result, (end - start) + 1);
                if(arr[start] == 1) count -= 1;
                start++;
                if(start == N) break;
            }
        }

        result = result == Integer.MAX_VALUE ? -1 : result;

        System.out.println(result);
    }
}
