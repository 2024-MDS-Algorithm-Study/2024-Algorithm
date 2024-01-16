package algo;
import java.util.*;
import java.io.*;
/*
 * 트리
 */
public class BOJ4803 {
	static class node{
		int from, to;
		public node(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n, m, from, to, cnt;
        ArrayList<node>[] arr;
        int testcase = 1;
        while(true) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	if(n==0 && m==0) break;
        	arr = new ArrayList[n+1];
        	for(int i=1; i<=n; i++) {
        		arr[i] = new ArrayList();
        	}
        	for(int i=0; i<m; i++) {
        		st = new StringTokenizer(br.readLine());
        		from = Integer.parseInt(st.nextToken());
        		to = Integer.parseInt(st.nextToken());
        		arr[from].add(new node(from, to));
        		arr[to].add(new node(to, from));
        	}
        	cnt = 0;
        	int[] v = new int[n+1];
//        	Arrays.fill(v, -1);
        	Queue<node> q;
        	for(int i=1; i<=n; i++) {
        		if(v[i]>0) continue;
        		else {
        			cnt++;
        			boolean flag = true;
        			q = new LinkedList<>();
        			q.add(new node(0, i));
        			v[i] = cnt;
        			while(!q.isEmpty()) {
        				node cn = q.poll();
        				for(node num:arr[cn.to]) {
        					if(cn.from == num.to) continue;
        					if(v[num.to]>0) {
//        						System.out.println(i+" ,num :"+num+" "+Arrays.toString(v));
        						flag = false;
        					}
        					else {
        						v[num.to] = cnt;
        						q.add(num);
        					}
        				}
        			}
        			if(!flag) cnt -= 1;
        		}
//    			System.out.println(Arrays.toString(v));
        	}
        	if(cnt==0) System.out.println("Case "+testcase+": No trees.");
        	if(cnt==1) System.out.println("Case "+testcase+": There is one tree.");
        	if(cnt>1) System.out.println("Case "+testcase+": A forest of "+cnt+" trees.");
        	
        	testcase++;
        }
        
        
    }
}