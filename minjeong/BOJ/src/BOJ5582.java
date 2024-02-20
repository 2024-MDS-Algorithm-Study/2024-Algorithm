import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5582 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int res = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                int c1 = s1.charAt(i);
                int c2 = s2.charAt(j);
                if (c1 == c2) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    res = Math.max(dp[i + 1][j + 1], res);
                }
            }
        }
        
        System.out.println(res);
    }
}
