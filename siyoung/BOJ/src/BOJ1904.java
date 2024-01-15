package algo;
import java.util.*;
import java.io.*;
 /*
  * 01타일
  */
public class BOJ1904 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+2];
        
        dp[1] = 1;
        dp[2] = 2;
        
        for(int i=3; i<=n; i++) {
        	dp[i] = (dp[i-1]+dp[i-2])%15746;
        }
        System.out.println(dp[n]);
    }
}