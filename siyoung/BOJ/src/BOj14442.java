package algo;

import java.util.*;
import java.io.*;
/*
 * 벽 부수고 이동하기 2
 */
public class BOj14442 {
	static class node{
		int i, j, cnt, level;
		public node(int i, int j, int cnt, int level) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.level = level;
		}
	}
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][][] v;
	
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        v = new boolean[n][m][k+1];
        
        for(int i=0; i<n; i++) {
        	String str = br.readLine();
        	for(int j=0; j<m; j++) {
        		map[i][j] = str.charAt(j)-'0';
        	}
        }
        
        int res = -1;
        Queue<node> q = new ArrayDeque<>();
        q.add(new node(0, 0, 1, 0));
        v[0][0][0] = true;
        while(!q.isEmpty()) {
        	node cn = q.poll();
        	if(cn.i == n-1 && cn.j == m-1) {
        		res = cn.cnt;
        		break;
        	}
        	for(int d=0; d<4; d++) {
        		int ni = cn.i+di[d];
        		int nj = cn.j+dj[d];
        		if(ni>=0 && ni<n & nj>=0 && nj<m) {
        			if(map[ni][nj] == 0  && !v[ni][nj][cn.level]) {
        				v[ni][nj][cn.level] = true;
        				q.add(new node(ni, nj, cn.cnt+1, cn.level));
        			}
        			else if(map[ni][nj] == 1 && cn.level<k && !v[ni][nj][cn.level+1]) {
        				v[ni][nj][cn.level+1] = true;
        				q.add(new node(ni, nj, cn.cnt+1, cn.level+1));
        			}
        		}
        	}
        }
        System.out.println(res);
    }
}