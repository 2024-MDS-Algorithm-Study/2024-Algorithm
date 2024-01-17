import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
두 수의 합
 */

public class BOJ9024 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int t, n, k, arr[];

    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());

        for(int test_case = 0; test_case < t; test_case++) {
            int sum = Integer.MAX_VALUE;
            int count = 0;

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new int[n];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            Arrays.sort(arr);
            int start = 0;
            int end = n - 1;

            while(start < end) {
                int currentSum = arr[start] + arr[end];

                if(calDiff(sum, k) > calDiff(currentSum, k)) {
                    sum = currentSum;
                    count = 1;
                }else if(calDiff(sum, k) == calDiff(currentSum, k)) {
                    count++;
                }

                if(currentSum >= k) end--;
                else start++;
            }
            sb.append(count + "\n");
        }

        System.out.print(sb);
    }

    private static int calDiff(int n, int k) {
        return Math.abs(n - k);
    }
}