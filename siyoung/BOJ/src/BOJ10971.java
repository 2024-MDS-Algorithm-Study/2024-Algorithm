package algo;
import java.util.*;
import java.io.*;

/*
 * 외판원 순회 2
 */
public class BOJ10971 {
	static int map[][], min, n;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        
        map = new int[n][n];
        
        for(int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        min = Integer.MAX_VALUE;
        boolean[] v = new boolean[n];
        v[0] = true;
        solve(v, 0, 0);
        System.out.println(min);
    }
    
    private static void solve(boolean[] v, int cur, int dist) {
    	boolean flag = false;
    	for(int i=0; i<n; i++) {
    		if(!v[i]) {
    			flag = true;
    			break;
    		}
    	}
    	if(!flag) {
    		if(map[cur][0]!=0) {
        		min = Math.min(min, dist+map[cur][0]);
    		}
    		return;
    	}
    	for(int i=0; i<n; i++) {
    		if(!v[i] && map[cur][i]!=0) {
    			v[i] = true;
    			if(dist+map[cur][i]<min) {
        			solve(v, i, dist+map[cur][i]);
    			}
    			v[i] = false;
    		}
    	}
    }
}