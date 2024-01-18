package algo;
import java.util.*;
import java.io.*;
/*
 * 차이를 최대로
 */
public class BOJ10819 {
	static int max, arr[], n;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        max = 0;
        solve(0, new int[n], new boolean[n]);
        System.out.println(max);
    }
    
    private static void solve(int depth, int[] list, boolean[] v) {
    	if(depth==v.length) {
    		int cur = 0;
    		for(int i=0; i<n-1; i++) {
    			cur += Math.abs(list[i]-list[i+1]);
    		}
    		max = Math.max(max, cur);
    		return;
    	}
    	for(int i=0; i<n; i++) {
    		if(!v[i]) {
    			v[i] = true;
    			int[] tmp = list.clone();
    			tmp[depth] = arr[i];
    			solve(depth+1, tmp, v);
    			v[i] = false;
    		}
    	}
    }
}