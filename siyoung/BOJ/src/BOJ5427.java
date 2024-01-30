package algo;

import java.util.*;
import java.io.*;
/*
 *  불
 */
public class BOJ5427 {
	static class node{
		int i, j, cnt;
		boolean isfire;
		public node(int i, int j, int cnt, boolean isfire) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.isfire = isfire;
		}
	}
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int y, x;
    	Queue<node> q;
    	ArrayDeque<node> fire;
    	boolean[][] v;
    	L: for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            q = new ArrayDeque<>();
            fire = new ArrayDeque<>();
            v = new boolean[y][x];
        	char[][] map = new char[y][x];
        	for(int j=0; j<y; j++) {
        		String str = br.readLine();
        		for(int k=0; k<x; k++) {
        			map[j][k] = str.charAt(k);
        			if(map[j][k] == '@') {
        				q.add(new node(j, k, 0, false));
        				v[j][k] = true;
        				map[j][k] = '.';
        			}
        			if(map[j][k] == '*') {
        				fire.add(new node(j, k, 0, true));
        			}
        		}
        	}
        	q.addAll(fire);
        	while(!q.isEmpty()) {
        		node cn = q.poll();
        		if(!cn.isfire && map[cn.i][cn.j] == '*'){
        			continue;
        		}
        		int ni, nj;
        		for(int d=0; d<4; d++) {
        			ni = cn.i+di[d];
        			nj = cn.j+dj[d];
        			if(!cn.isfire) {
        				if(ni<0 || ni>=y || nj<0 || nj>=x) {
        					System.out.println(cn.cnt+1);
        					continue L;
        				}
        				else {
        					if(map[ni][nj] == '.' && !v[ni][nj]) {
        						v[ni][nj] = true;
        						q.add(new node(ni, nj, cn.cnt+1, false));
        					}
        				}
        			}
        			else {
        				if(ni>=0 && ni<y && nj>=0 && nj<x && (map[ni][nj] != '#' && map[ni][nj] != '*')) {
        					map[ni][nj] = '*';
        					q.add(new node(ni, nj, n, true));
        				}
        			}
        		}
        	}
        	System.out.println("IMPOSSIBLE");
        }
    }
}