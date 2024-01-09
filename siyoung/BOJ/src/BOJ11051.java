package algo;
import java.util.*;
import java.io.*;
 
/*
 * 이항계수 2
 */
public class BOJ11051 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[1001][1001];
        
        map[0][0] = 1;
        map[1][0] = 1;
        map[1][1] = 1;
        for(int i=2; i<=1000; i++) {
        	map[i][0] = 1;
        	map[i][i] = 1;
        	for(int j=1; j<i; j++) {
        		map[i][j] = (map[i-1][j-1] + map[i-1][j]) % 10007;
        	}
        }
        
        System.out.println(map[n][k]);
    }
}