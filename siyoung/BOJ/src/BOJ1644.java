package algo;

import java.util.*;
import java.io.*;
/*
 * 소수의 연속합
 */
public class BOJ1644 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] v = new boolean[n+1];
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=2; i<=n; i++) {
        	if(v[i]) continue;
        	v[i] = true;
        	arr.add(i);
        	for(int j=i+i; j<=n; j+=i) {
        		v[j] = true;
        	}
        }
        int start=0, end=0, cnt=0, sum, idx;
        while(start<arr.size() && end<arr.size()) {
        	idx = start;
        	sum = 0;
        	while(idx<=end) {
        		sum += arr.get(idx);
        		idx++;
        	}
        	if(sum<n) end++;
        	else if(sum>n)start++;
        	else {
        		cnt++;
        		start++;
        	}
        }
        System.out.println(cnt);
    }
}