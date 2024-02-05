package algo;

import java.util.*;
import java.io.*;
/*
 * 치즈
 */
public class BOJ2636 {
	static class node{
		int i, j;
		public node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int n, m, map[][];
	static boolean[][] v;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        
        for(int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<m; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        Queue<node> q;
        int ni, nj, time = 0;
        int csize = cheeseSize();
        while(cheeseSize()>0) {
        	time++;
        	q = new ArrayDeque<>();
        	v = new boolean[n][m];
        	q.add(new node(0, 0));
        	v[0][0] = true;
        	while(!q.isEmpty()) {
        		node cn = q.poll();
        		if(map[cn.i][cn.j]==1) continue;
        		for(int d=0; d<4; d++) {
        			ni = cn.i+di[d];
        			nj = cn.j+dj[d];
        			if(ni>=0 && ni<n && nj>=0 && nj<m && !v[ni][nj]) {
        				v[ni][nj] = true;
        				q.add(new node(ni, nj));
        			}
        		}
        	}
        	csize = cheeseSize();
        	meltCheese();
        }
        System.out.println(time);
        System.out.println(csize);
    }
    private static void meltCheese() {
    	for(int i=0; i<n; i++) {
        	for(int j=0; j<m; j++) {
        		if(v[i][j] && map[i][j]==1) map[i][j] = 0;
        	}
        }
    }
    private static int cheeseSize() {
    	int ret = 0;
    	for(int i=0; i<n; i++) {
        	for(int j=0; j<m; j++) {
        		if(map[i][j]==1) ret++;
        	}
        }
    	return ret;
    }
}