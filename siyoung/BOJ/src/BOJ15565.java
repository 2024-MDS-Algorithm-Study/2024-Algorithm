package algo;
import java.util.*;
import java.io.*;

/*
 * 귀여운 라이언
 */
 
public class BOJ15565 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	if(arr[i] == 1)list.add(i);
        }
        if(list.size()<k) System.out.println(-1);
        else {
        	int idx = k-1;
        	int start = 0;
        	int end = list.get(idx);
        	int min = Integer.MAX_VALUE;
        	while(idx<list.size()) {
        		min = Math.min(min, list.get(idx)-list.get(idx-k+1)+1);
//        		System.out.println(list+" "+min+" "+list.get(idx)+" "+list.get(idx-k+1));
        		idx++;
        	}
        	System.out.println(min);
        }
    }
}