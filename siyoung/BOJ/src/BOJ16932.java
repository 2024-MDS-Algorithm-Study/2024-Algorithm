package algo;

import java.util.*;
import java.io.*;
/*
 * 모양 만들기
 */
public class BOJ16932 {
	static class node{
		int i, j;
		public node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static int[] di = {-1, 1, 0 ,0};
	static int[] dj = {0 ,0, -1, 1};
	static int n, m, map[][];
	static boolean[][] gv;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        gv = new boolean[n][m];
        for(int i=0; i<n; i++) {st = new StringTokenizer(br.readLine());
        	for(int j=0; j<m; j++) {
        		map[i][j] = (Integer.parseInt(st.nextToken())+1)%2;
        	}
        }
        
        for(int i=0; i<n; i++) {
        	for(int j=0; j<m; j++) {
        		if(map[i][j]==0 && !gv[i][j]) {
        			check(i, j);
        		}
        	}
        }
        int max = 0;
        for(int i=0; i<n; i++) {
        	for(int j=0; j<m; j++) {
        		max = Math.max(map[i][j], max);
        	}
        }
        System.out.println(max);
    }
    
    private static void check(int i, int j) {
        LinkedList<node> near = new LinkedList<node>();
        Queue<node> q = new ArrayDeque<>();
        
        int cnt = 1;
        q.add(new node(i, j));
        gv[i][j] = true;
        
        while(!q.isEmpty()) {
        	node cn = q.poll();
        	int ni, nj;
        	for(int d=0; d<4; d++) {
        		ni = cn.i + di[d];
        		nj = cn.j + dj[d];
        		if(ni>=0 && ni<n && nj>=0 && nj<m && !gv[ni][nj]) {
        			if(map[ni][nj]==0) {
        		        gv[ni][nj] = true;
        				cnt++;
        				q.add(new node(ni, nj));
        			}
        			else {
        		        gv[ni][nj] = true;
        				near.add(new node(ni, nj));
        			}
        		}
        	}
        }
        for(node cn:near) {
        	map[cn.i][cn.j] += cnt;
        	gv[cn.i][cn.j] = false;
        }
    }
}