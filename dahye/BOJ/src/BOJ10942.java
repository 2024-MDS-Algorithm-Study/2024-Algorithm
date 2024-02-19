import java.io.*;
import java.util.*;

/*
 * 팰린드롬?
 */
public class BOJ10942 {
	static int arr[], dp[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N + 1];
		
		for(int i = 1; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][N + 1];

		for(int k = 1; k < N + 1; k++) {
			int range = N + 1 - k;
			int i = 1;
			int j = k;
			
			while(range-- > 0) {
				if(k == 1) dp[i][j] = 1;
				else if(j - i == 1) if(arr[i] == arr[j]) dp[i][j] = 1;
				else if(arr[i] == arr[j] && dp[i + 1][j - 1] == 1) dp[i][j] = 1;
				i++;
				j++;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(dp[s][e] + "\n");
		}
		System.out.println(sb);
	}
}
