import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
좌표 압축
 */

public class BOJ18870 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static HashSet<Integer> set;
    static Integer N, origin[], X[], copy[];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
//        binarySearch();
        hashMap();

        System.out.println(sb);
    }

    private static void hashMap() {
        int arr[] = new int[N];
        int sortedArr[] = new int[N];

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for(int i = 0; i < N; i++) {
            arr[i] = sortedArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sortedArr);

        int rank = 0;
        for (int s : sortedArr) {
            if(!hashMap.containsKey(s)) {
                hashMap.put(s, rank);
                rank++;
            }
        }

        for(int key: arr) {
            int value = hashMap.get(key);
            sb.append(value + " ");
        }
    }

    private static void binarySearch() {

        set = new HashSet<>();
        origin = new Integer[N];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            origin[i] = n;
            set.add(n);
        }

        X = set.toArray(new Integer[0]);
        copy = new Integer[X.length];
        copy = Arrays.copyOf(X, X.length);
        Arrays.sort(copy);

        for(int i = 0; i < N; i++) {
            int start = 0;
            int end = X.length - 1;
            int target = origin[i];

            while(start <= end) {
                int middle = (start + end) / 2;

                if(copy[middle] < target) start = middle + 1;
                else if(copy[middle] > target) end = middle - 1;
                else {
                    sb.append(middle + " ");
                    break;
                }
            }
        }
    }
}
