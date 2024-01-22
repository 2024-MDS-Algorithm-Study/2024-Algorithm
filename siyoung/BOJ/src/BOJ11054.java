package algo;

import java.util.*;
import java.io.*;
/*
 * 가장 긴 바이토닉 부분 수열
 */
public class BOJ11054 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
		
        int[] dp = new int[n];
        dp[0] = 1;
        
        for(int i=1; i<n; i++) {
        	dp[i] = 1;
        	for(int j=0; j<i; j++) {
        		if(arr[j] < arr[i] && dp[i]<=dp[j]) {
        			dp[i] = dp[j] + 1;
        		}
        	}
        }
        int[] rev = new int[n];
        rev[n-1] = 0;
        for(int i=n-2; i>=0; i--) {
        	rev[i] = 0;
        	for(int j=n-1; j>i; j--) {
        		if(arr[j] < arr[i] && rev[i]<=rev[j]) {
        			rev[i] = rev[j] + 1;
        		}
        	}
        }
        int max = 0;
        int[] res = new int[n];
        for(int i=0; i<n; i++) {
        	res[i] = dp[i] + rev[i];
        	max = Math.max(max, res[i]);
        }
        
		System.out.println(max);
    }
}