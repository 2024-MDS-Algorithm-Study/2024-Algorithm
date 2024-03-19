import java.util.*;

/*
 * 피보나치 수 5
 */

public class BOJ10870 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long dp[] = new long[21];
		
		dp[1] = 1;
		dp[2] = 1;
		
		for(int i = 3; i < N + 1; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		System.out.println(dp[N]);
	}
}
