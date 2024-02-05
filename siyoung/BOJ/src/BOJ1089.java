package algo;

import java.util.*;
import java.io.*;
/*
 * 스타트링크 타워
 */
public class BOJ1089 {
	static int[][] pos = {
			{0,0}, {0, 1}, {0, 2},
			{1,0}, {1, 1}, {1, 2},
			{2,0}, {2, 1}, {2, 2},
			{3,0}, {3, 1}, {3, 2},
			{4,0}, {4, 1}, {4, 2}
	};
	static int[][] imposible = {
			{1}, {1, 4}, {10},
			{1, 2, 3, 7}, {-1}, {5, 6},
			{1, 7}, {0, 1, 7}, {10},
			{1, 3, 4, 5, 7, 9}, {-1}, {2},
			{1, 4, 7}, {1, 4, 7}, {10}
	};
	static int n, width;
	static double sum;
	static char[][] map;
	static boolean[][] check;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        width = 4*n-1;
        map = new char[5][width];
        for(int i=0; i<5; i++) {
        	String str = br.readLine();
        	for(int j=0; j<str.length(); j++) {
        		map[i][j] = str.charAt(j);
        	}
        }
        check = new boolean[n][10];
        int curPos;
        for(int i=0; i<n; i++) {
        	curPos = i*4;
        	for(int d=0; d<15; d++) {
        		if(map[pos[d][0]][(curPos+pos[d][1])]=='#') {
        			if(imposible[d][0] == -1) {
        				System.out.println(-1);
        				System.exit(0);
        			}
        			else if(imposible[d][0] == 10) continue;
        			else {
        				for(int k=0; k<imposible[d].length; k++) check[i][imposible[d][k]] = true;
        			}
        		}
        	}
        }
        
        sum = 0;
        for(int i=0; i<n; i++) {
        	double num = 0;
        	int cnt = 0;
        	for(int j=0; j<check[i].length; j++) {
        		if(check[i][j]) continue;
        		num += j;
        		cnt++;
        	}
        	num *= Math.pow(10, n-i-1);
        	num /= cnt;
        	sum += num;
        }
        System.out.printf("%.5f", sum);
    }
}