package algo;

import java.util.*;
import java.io.*;
/*
 * 합분해
 */
public class BOJ2225 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        long[][] map = new long[n+1][k+1];
        
        for(int i=1; i<=n; i++) {
        	map[i][1] = 1;
        }
        for(int i=1; i<=k; i++) {
        	map[1][i] = i;
        }
        
        for(int i=2; i<=n; i++) {
        	for(int j=2; j<=k; j++) {
        		map[i][j] = (map[i-1][j] + map[i][j-1])%1000000000;
        	}
        }
        System.out.println(map[n][k]);
    }
}