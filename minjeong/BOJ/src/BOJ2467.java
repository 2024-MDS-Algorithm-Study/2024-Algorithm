import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = n - 1, resS = -1, resE = -1;
        int min = Integer.MAX_VALUE;
        while (s < e) {
            int sum = arr[s] + arr[e];
            if (min >= Math.abs(sum)) {
                resS = arr[s];
                resE = arr[e];
                min = Math.abs(sum);
            }

            if (sum < 0) s++;
            else e--;
        }

        System.out.println(resS + " " + resE);
    }
}
