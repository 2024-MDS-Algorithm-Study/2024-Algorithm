package algo;

import java.util.*;
import java.io.*;
/*
 * 두 배열의 합
 */
public class BOJ2143 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] arrA = new int[n];
        int size = n*(n+1)/2;
        long[] sumA = new long[size];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
        	arrA[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;
        for(int i=0; i<n; i++) {
        	int sum = 0;
        	for(int j=i; j<n; j++) {
        		sum += arrA[j];
        		sumA[idx] = sum;
        		idx++;
        	}
        }
        int m = Integer.parseInt(br.readLine());
        int[] arrB = new int[m];
        size = m*(m+1)/2;
        long[] sumB = new long[size];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
        	arrB[i] = Integer.parseInt(st.nextToken());
        }
        idx = 0;
        for(int i=0; i<m; i++) {
        	int sum = 0;
        	for(int j=i; j<m; j++) {
        		sum += arrB[j];
        		sumB[idx] = sum;
        		idx++;
        	}
        }
        Arrays.sort(sumA);
        Arrays.sort(sumB);
        int start = 0;
        int end = sumB.length-1;
        long res = 0;
        
        while(start<sumA.length && end>=0) {
//        	System.out.println(sumA[start] +" "+sumB[end]);
        	if(sumA[start]+sumB[end]>t) end--;
        	else if(sumA[start]+sumB[end]<t) start++;
        	else {
        		long curA = sumA[start];
        		long curB = sumB[end];
        		long sameA = 0, sameB = 0;
        		while(start<sumA.length && curA==sumA[start]) {
        			start++;
        			sameA++;
        		}
        		while(end>=0 && curB==sumB[end]) {
        			end--;
        			sameB++;
        		}

        		res += sameA*sameB;
        	}
        }
        System.out.println(res);
    }
}