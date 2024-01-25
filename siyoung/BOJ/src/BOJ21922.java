package algo;
import java.util.*;
import java.io.*;
/*
 * 학부 연구생 민상
 */
public class BOJ21922 {
	static class node{
		int i, j, dir;
		public node(int i, int j, int dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int n, m, res, map[][];
	static boolean[][][] v;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        v = new boolean[n][m][4];
        
        res = 0;
        Queue<node> q = new LinkedList();
        
        for(int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<m; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if(map[i][j] == 9) {
        			res++;
        			q.add(new node(i, j, 0));
        	        q.add(new node(i, j, 1));
        	        q.add(new node(i, j, 2));
        	        q.add(new node(i, j, 3));
        	        v[i][j][0] = true;
        	        v[i][j][1] = true;
        	        v[i][j][2] = true;
        	        v[i][j][3] = true;
        		}
        	}
        }
        
        while(!q.isEmpty()) {
        	node cn = q.poll();
        	
        	int ni = cn.i+di[cn.dir];
        	int nj = cn.j+dj[cn.dir];
        	if(ni>=0 && ni<n && nj>=0 && nj<m) {
				if(v[ni][nj][cn.dir]) continue;
				if(!visit(ni, nj)) res++;
        		if(map[ni][nj] == 0) {
        			if(cn.dir == 0 || cn.dir == 1) {
        				v[ni][nj][0] = true;
            			v[ni][nj][1] = true;
        			}
        			else {
        				v[ni][nj][2] = true;
            			v[ni][nj][3] = true;
        			}
        			q.add(new node(ni, nj, cn.dir));
        		}
        		if(map[ni][nj]==1) {
    				if(cn.dir == 0 || cn.dir == 1) {
        				v[ni][nj][0] = true;
            			v[ni][nj][1] = true;
            			q.add(new node(ni, nj, cn.dir));
    				}
    				else {
        				v[ni][nj][2] = true;
            			v[ni][nj][3] = true;
    				}
    			}
    			else if(map[ni][nj]==2) {
    				if(cn.dir == 0 || cn.dir == 1) {
        				v[ni][nj][0] = true;
            			v[ni][nj][1] = true;
    				}
    				else {
        				v[ni][nj][2] = true;
            			v[ni][nj][3] = true;
            			q.add(new node(ni, nj, cn.dir));
    				}
    			}
    			else if(map[ni][nj]==3) {
    				if(cn.dir == 0) {
        				v[ni][nj][0] = true;
        				q.add(new node(ni, nj, 3));
        			}
        			if(cn.dir == 3) {
            			v[ni][nj][3] = true;
        				q.add(new node(ni, nj, 0));
        			}
        			if(cn.dir == 1) {
        				v[ni][nj][1] = true;
        				q.add(new node(ni, nj, 2));
        			}
        			if(cn.dir == 2) {
            			v[ni][nj][2] = true;
        				q.add(new node(ni, nj, 1));
        			}
    			}
    			else if(map[ni][nj]==4) {
    				if(cn.dir == 0) {
        				v[ni][nj][0] = true;
        				q.add(new node(ni, nj, 2));
    				}
        			if(cn.dir == 2) {
            			v[ni][nj][2] = true;
        				q.add(new node(ni, nj, 0));
        			}
        			if(cn.dir == 1) {
        				v[ni][nj][1] = true;
        				q.add(new node(ni, nj, 3));
        			}
        			if(cn.dir == 3) {
            			v[ni][nj][3] = true;
        				q.add(new node(ni, nj, 1));
        			}
    			}
    		}
        }
    	System.out.println(res);
    }
    private static boolean visit(int i, int j) {
    	for(int d=0; d<4; d++) {
    		if(v[i][j][d]) return true;
    	}
    	return false;
    }
}