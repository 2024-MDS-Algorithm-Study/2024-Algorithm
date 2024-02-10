package algo;

import java.util.*;
import java.io.*;
import java.sql.Array;

public class BOJ8972 {
	static int[][] dir = {{},
			{1, -1}, {1, 0}, {1, 1},
			{0, -1}, {0, 0}, {0, 1},
			{-1, -1}, {-1, 0}, {-1, 1},
	};
	static int n, m, arr[];
	static char[][] map;
	static boolean fin;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new char[n][m];
        int ii=0, ij=0;
        for(int i=0; i<n; i++) {
        	String str = br.readLine();
        	for(int j=0; j<m; j++) {
        		map[i][j] = str.charAt(j);
        		if(map[i][j]=='I') {
        			ii = i;
        			ij = j;
        		}
        	}
        }
        String num = br.readLine();
        arr = new int[num.length()];
        for(int i=0; i<num.length(); i++) arr[i] = num.charAt(i)-'0';
        fin = false;
        for(int i=0; i<num.length(); i++) {
        	int ni = ii+dir[arr[i]][0];
        	int nj = ij+dir[arr[i]][1];
        	if(map[ni][nj]=='R') {
				fin = true;
				System.out.println("kraj "+(i+1));
				break;
        	}
        	else {
        		map[ii][ij] = '.';
        		ii = ni;
        		ij = nj;
        		map[ii][ij] = 'I';
        	}
        	move(ii, ij, i+1);
        	if(fin) break;
        }
        if(!fin) {
        	StringBuilder sb = new StringBuilder();
        	for(int i=0; i<n; i++) {
        		for(int j=0; j<m; j++) sb.append(map[i][j]);
        		sb.append("\n");
        	}
        	System.out.println(sb);
        }
    }
    
    private static void move(int ii, int ij, int idx) {
    	char[][] tmp = new char[n][m];
    	for(int i=0; i<n; i++) Arrays.fill(tmp[i], '.');
    	tmp[ii][ij] = 'I';
    	L: for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			if(map[i][j]=='R') {
    				int ni, nj, mini=0, minj=0;
    				int min = 10000;
    				for(int d=1; d<10; d++) {
    					ni = i+dir[d][0];
    					nj = j+dir[d][1];
    					if(ni>=n || ni<0 || nj>=m || nj<0) continue;
    					if(Math.abs(ii-ni)+Math.abs(ij-nj)<min) {
    						min = Math.abs(ii-ni)+Math.abs(ij-nj);
    						mini = ni;
    						minj = nj;
    					}
    				}
    				if(tmp[mini][minj]=='R') tmp[mini][minj] = 'x';
    				else if(tmp[mini][minj]=='I') {
    					fin = true;
    					System.out.println("kraj "+idx);
    					break L;
    				}
    				else if(tmp[mini][minj]=='x') tmp[mini][minj] = 'x';
    				else tmp[mini][minj] = 'R';
    			}
    		}
    	}
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			if(tmp[i][j]=='x') tmp[i][j] = '.';
        		map[i][j] = tmp[i][j];
    		}
    	}
    }
}