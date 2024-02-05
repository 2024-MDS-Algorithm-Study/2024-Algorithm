package algo;

import java.util.*;
import java.io.*;
import java.sql.Array;
/*
 * 공통 부분 문자열
 */
public class BOJ5582 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        
        int[][] map = new int[str1.length()][str2.length()];
        int max = 0;
        for(int i=0; i<str1.length(); i++) {
        	for(int j=0; j<str2.length(); j++) {
        		if(str1.charAt(i)==str2.charAt(j)) {
        			if(i==0||j==0) map[i][j] = 1;
        			else map[i][j] = map[i-1][j-1]+1;
        			max = Math.max(max, map[i][j]);
        		}
        	}
        }
//        for(int i=0; i<str1.length(); i++) {
//        	System.out.println(Arrays.toString(map[i]));
//        }
        System.out.println(max);
    }
}