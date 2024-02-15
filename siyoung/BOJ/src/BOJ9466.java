package algo;

import java.util.*;
import java.io.*;
/*
 * 텀 프로젝트
 */
public class BOJ9466 {
	static int[] arr;
	static boolean[] v;
	static HashSet<Integer> tmp;
	static ArrayDeque<Integer> cur, ret;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        int n;
        for(int testCase=0; testCase<t; testCase++) {
        	n = Integer.parseInt(br.readLine());
        	arr = new int[n+1];
        	v = new boolean[n+1];
        	st = new StringTokenizer(br.readLine());
        	for(int i=1; i<=n; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        		if(arr[i] == i) v[i] = true;
        	}
        	ret = new ArrayDeque<>();
        	for(int i=1; i<=n; i++) {
        		if(v[i]) continue;
        		tmp = new HashSet<>();;
        		cur = new ArrayDeque<>();
        		solve(i);
        	}
        	System.out.println(ret.size());
        }
    }
    private static void solve(int num) {
    	if(tmp.contains(num)) {
    		while(!cur.isEmpty()) {
    			if(cur.peekFirst()==num) break;
    			ret.add(cur.pollFirst());
    		}
    		return;
    	}
    	if(v[num] && !tmp.contains(num)) {
    		while(!cur.isEmpty()) {
    			ret.add(cur.pollFirst());
    		}
    		return;
    	}
    	tmp.add(num);
    	v[num] = true;
    	cur.add(num);
    	solve(arr[num]);
    }
}