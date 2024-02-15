package algo;

import java.util.*;
import java.io.*;
/*
 * 스도쿠
 */
public class BOJ2239 {
	static int[][] map;
	static boolean flag;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        for(int i=0; i<9; i++) {
        	String str = br.readLine();
        	for(int j=0; j<9; j++) {
        		map[i][j] = str.charAt(j)-'0';
        	}
        }
        solve(0, 0);
    }
    
    private static void solve(int i, int j) {
    	if(i==9 && j==0) {
    		StringBuilder sb = new StringBuilder();
    		for(int k=0; k<9; k++) {
    			for(int l=0; l<9; l++) {
    				sb.append(map[k][l]);
    			}
    			if(k+1<9) sb.append("\n");
    		}
    		System.out.println(sb);
    		System.exit(0);
    		return;
    	}
    	if(map[i][j]==0) {
    		boolean[] checked = check(i, j).clone();
    		for(int idx=0; idx<9; idx++) {
    			if(!checked[idx]) {
    				map[i][j] = idx+1;
    				if(j+1==9) solve(i+1, 0);
    				else solve(i, j+1);
    				map[i][j] = 0;
    			}
    		}
    	}
    	else {
    		if(j+1==9) solve(i+1, 0);
			else solve(i, j+1);
    	}
    	
    }
    
    private static boolean[] check(int i, int j) {
		boolean[] ret = new boolean[9];
		
    	for(int idx=0; idx<9; idx++) {
    		if(map[i][idx]!=0) ret[map[i][idx]-1] = true;
    		if(map[idx][j]!=0) ret[map[idx][j]-1] = true;
    	}
    	int boxi = i/3 * 3;
    	int boxj = j/3 * 3;
    	for(int k=boxi; k<boxi+3; k++) {
    		for(int l=boxj; l<boxj+3; l++) {
        		if(map[k][l]!=0) ret[map[k][l]-1] = true;
    		}
    	}
    	
		return ret;
    }
}