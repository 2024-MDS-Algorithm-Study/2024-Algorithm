import java.io.*;

public class BOJ9082 {
    static char[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new char[2][n];
            arr[0] = br.readLine().toCharArray();
            arr[1] = br.readLine().toCharArray();

            int cnt = 0;
            for (int i = 0; i < n; i += 3) cnt += (arr[0][i] - '0');
            if (n > 2 && n % 3 == 0) cnt += (arr[0][n - 1] - '0');

            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}
