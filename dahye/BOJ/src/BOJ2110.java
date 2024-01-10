import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
공유기 설치
 */

public class BOJ2110 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, C, house[], result;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        result = Integer.MIN_VALUE;

        house = new int[N];

        for (int i = 0; i < N; i++) house[i] = Integer.parseInt(br.readLine());

        Arrays.sort(house);

        int start = 1;
        int end = house[N - 1] - house[0];

        while(start <= end) {
            int count = 1; // 첫번쨰 집에 무조건 설치한다고 하자!
            int middle = (start + end) / 2;

            int prevHouse = house[0];

            for(int i = 1; i < N; i++) {
                if(house[i] - prevHouse >= middle) {
                    count++;
                    prevHouse = house[i];
                }
            }

            if(count >= C) {
                result = Math.max(result, middle);
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        System.out.println(result);
    }
}
