import java.io.*;
import java.util.*;

/*
 * 수열
 */

public class BOJ2491 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		int dp[][] = new int[2][N];
		
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		dp[0][0] = 1;
		dp[1][0] = 1;
		
		for(int i = 1; i < N; i++) {
			if(arr[i] >= arr[i - 1]) dp[0][i] = dp[0][i - 1] + 1;
			else dp[0][i] = 1;
			
			if(arr[i] <= arr[i - 1]) dp[1][i] = dp[1][i - 1] + 1;
			else dp[1][i] = 1;				
		}
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			result = Math.max(result, Math.max(dp[0][i], dp[1][i]));
		}
		
		System.out.println(result);
	}
}
