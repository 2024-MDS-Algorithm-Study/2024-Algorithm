import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
이중 우선순위 큐
 */

public class BOJ7662 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, k, n;
    static char ch;
    static TreeMap<Integer, Integer> treeMap;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        treeMap = new TreeMap<>();

        for(int test_case = 0; test_case < T; test_case++) {
            treeMap.clear();

            k = Integer.parseInt(br.readLine());

            for(int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                ch = st.nextToken().charAt(0);
                n = Integer.parseInt(st.nextToken());

                if(ch == 'I') {
                    int nCount = 1;
                    if(treeMap.containsKey(n)) {
                        nCount = treeMap.get(n) + 1;
                    }
                    treeMap.put(n, nCount);
                }

                if(ch == 'D') {
                    if(treeMap.size() == 0) continue;
                    if(n == 1) {
                        // 최대값 삭제
                        int count = treeMap.get(treeMap.lastKey());
                        count -= 1;

                        if(count == 0) treeMap.remove(treeMap.lastKey());
                        else treeMap.put(treeMap.lastKey(), count);
                    }
                    if(n == -1) {
                        // 최솟값 삭제
                        int count = treeMap.get(treeMap.firstKey());
                        count -= 1;
                        if(count == 0) treeMap.remove(treeMap.firstKey());
                        else treeMap.put(treeMap.firstKey(), count);
                    }
                }
            }
            if(treeMap.size() == 0) sb.append("EMPTY" + "\n");
            else {
                int min = treeMap.firstKey();
                int max = treeMap.lastKey();

                sb.append(max + " " + min + "\n");
            }
        }

        System.out.print(sb);
    }
}