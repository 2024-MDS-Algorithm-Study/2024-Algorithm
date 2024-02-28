import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long vs = Long.MAX_VALUE, a = 0, b = 0, c = 0;
        for (int i = 1; i < n - 1; i++) {
            int s = 0, e = n - 1;
            while (s < i && e > i) {
                long sum = (long) arr[s] + arr[i] + arr[e];
                long g = Math.abs(sum);
                if (g <= vs) {
                    vs = g;
                    a = arr[s];
                    b = arr[i];
                    c = arr[e];
                }
                if (sum < 0) s++;
                else e--;
            }
        }

        System.out.println(a + " " + b + " " + c);
    }
}
