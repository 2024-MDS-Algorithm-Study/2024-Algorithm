import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5582 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		
		int dp[][] = new int[A.length() + 1][B.length() + 1];
		int result = 0;
		for(int r = 1; r < dp.length; r++) {
			for(int c = 1; c < dp[0].length; c++) {
				if(A.charAt(r - 1) == B.charAt(c - 1)) {
					dp[r][c] = dp[r - 1][c - 1] + 1;
					result = Math.max(result, dp[r][c]);
				}
			}
		}
		System.out.println(result);
	}
}