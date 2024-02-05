package algo;

import java.util.*;
import java.io.*;
/*
 * 전화번호 목록
 */
public class BOJ5052 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        L: for(int i=0; i<t; i++) {
        	int n = Integer.parseInt(br.readLine());
    		String[] arr = new String[n];
    		for(int j=0; j<n; j++) {
    			arr[j] = br.readLine();
    		}
    		Arrays.sort(arr);
    		L2: for(int j=1; j<n; j++) {
    			if(arr[j].contains(arr[j-1])) {
    				for(int k=0; k<arr[j-1].length(); k++) {
    					if(arr[j].charAt(k)!=arr[j-1].charAt(k)) continue L2;
    				}
    				sb.append("NO\n");
    				continue L;
    			}
    		}
			sb.append("YES\n");
        }
		System.out.println(sb);
    }
}