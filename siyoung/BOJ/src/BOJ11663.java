package algo;

import java.util.*;
import java.io.*;
import java.sql.Array;
/*
 * 선분 위의 점
 */
public class BOJ11663 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] dot = new long[n];
        st = new StringTokenizer(br.readLine());
        ArrayList<Long> list = new ArrayList();
        for(int i=0; i<n; i++) {
        	list.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        long start, end, ns, ne;
        for(int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	start = Long.parseLong(st.nextToken());
        	end = Long.parseLong(st.nextToken());
        	
        	ne = Collections.binarySearch(list, end);
        	ns = Collections.binarySearch(list, start);
        	
        	if(ne<0) ne = -ne-2;
        	if(ns<0) ns = -ns-1;
//        	System.out.println(ne+" "+ns);
        	
        	if(ne<ns) sb.append("0\n");
        	else sb.append((ne-ns+1)+"\n");
        }
        System.out.println(sb);
    }
}