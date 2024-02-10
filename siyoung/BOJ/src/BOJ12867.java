package algo;

import java.util.*;
import java.io.*;
import java.sql.Array;
/*
 * N차원 여행
 */
public class BOJ12867 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Object> set = new HashSet<>();

        int[] val = new int[m];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
        	val[i] = Integer.parseInt(st.nextToken());
        	map.put(val[i], 0);
        }
        char[] dir = new char[m];
        String str = br.readLine();
        for(int i=0; i<m; i++) {
        	dir[i] = str.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for(Integer num : map.values()) {
        	sb.append(num);
        }
        set.add(sb.toString());
        
        for(int i=0; i<m; i++) {
        	if(dir[i]=='+') map.put(val[i], map.get(val[i])+1);
        	else map.put(val[i], map.get(val[i])-1);

        	sb = new StringBuilder();
            for(Integer num : map.values()) {
            	sb.append(num);
            }
            set.add(sb.toString());
        }
//    	System.out.println(map);
        if(set.size()==m+1) System.out.println(1);
        else System.out.println(0);
    }
}