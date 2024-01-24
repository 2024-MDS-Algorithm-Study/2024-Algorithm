import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
겹치는 건 싫어
 */

public class BOJ20922 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K, arr[], check[], result;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        check = new int[100001];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;
        check[arr[end]]++;

        while(start <= end) {
            if(check[arr[end]] <= K) {
                result = Math.max(result, end - start + 1);
                end++;
                if(end == N) break;
                check[arr[end]]++;
            } else {
                check[arr[start]]--;
                start++;
            }

        }

        System.out.print(result);
    }
}
