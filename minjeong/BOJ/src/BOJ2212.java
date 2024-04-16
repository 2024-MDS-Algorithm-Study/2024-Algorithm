import java.io.*;
import java.util.*;

public class BOJ2212 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        if (n <= k) {
            System.out.println(0);
            return;
        }

        Set<Integer> set = new TreeSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        int[] arr = new int[set.size() - 1];
        int idx = 0;
        Iterator<Integer> it = set.iterator();
        int next = it.next();
        while (idx < arr.length) {
            int tmp = it.next();
            arr[idx++] = tmp - next;
            next = tmp;
        }

        Arrays.sort(arr);
        int res = 0;
        for (int i = arr.length - k; i >= 0; i--) res += arr[i];
        System.out.println(res);
    }
}
