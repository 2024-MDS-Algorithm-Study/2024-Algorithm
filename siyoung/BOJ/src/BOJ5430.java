package algo;
import java.util.*;
import java.io.*;
/*
 * AC 
 */
public class BOJ5430 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        
        L: for(int testCase=0; testCase<t; testCase++) {
        	String p = br.readLine();
        	char[] orders = new char[p.length()];
        	for(int i=0; i<p.length(); i++) {
        		orders[i] = p.charAt(i);
        	}
        	int n = Integer.parseInt(br.readLine());
        	String str = br.readLine();
        	String[] sarr = str.substring(1, str.length()-1).split(",");
        	ArrayDeque<Integer> q = new ArrayDeque<>();
        	for(int i=0; i<n; i++) {
        		q.addLast(Integer.parseInt(sarr[i]));
        	}
        	boolean rev = false;
        	for(int i=0; i<orders.length; i++) {
        		
        		if(orders[i]=='R') rev = !rev;
        		if(orders[i]=='D') {
        			if(q.isEmpty()) {
            			System.out.println("error");
            			continue L;
            		}
        			if(rev) q.pollLast();
        			else q.pollFirst();
        		}
        	}
        	int len = q.size();
        	int[] res = new int[len];
        	for(int i=0; i<len; i++) {
        		if(rev) res[i] = q.pollLast();
        		else res[i] = q.pollFirst();
        	}
        	StringBuilder sb = new StringBuilder();
        	sb.append("[");
        	for(int i=0; i<res.length; i++) {
        		if(i!=0)sb.append(",");
        		sb.append(res[i]);
        	}
        	sb.append("]");
        	System.out.println(sb);
        }
        
    }
}