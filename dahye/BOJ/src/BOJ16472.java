import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
고냥이
 */

public class BOJ16472 {
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();

        String input = br.readLine();
        char charArray[] = new char[input.length()];

        for(int i = 0; i < charArray.length; i++) {
            charArray[i] = input.charAt(i);
        }

        checkAlphaType(hashMap);

        int start = 0;
        int end = 0;

        hashMap.put(charArray[start] , 2);

        while(start <= end) {
            result = Math.max(end - start + 1, result);
            if(end < charArray.length - 1 && hashMap.containsKey(charArray[end + 1])) {
                end++;
                hashMap.put(charArray[end], hashMap.get(charArray[end]) + 1);
            }
            else {
                // hashMap에 가지고 있지 않아!
                if(end == 0) hashMap.put(charArray[end], 1);
                if(end < charArray.length - 1 && checkAlphaType(hashMap) < N) {
                    end++;
                    hashMap.put(charArray[end], 1);
                } else {
                    int value = hashMap.get(charArray[start]);
                    if(value - 1 == 0) hashMap.remove(charArray[start]);
                    else hashMap.put(charArray[start], hashMap.get(charArray[start]) - 1);
                    start++;
                }
            }
            if(end > charArray.length - 1) {
                end = charArray.length -1;
                start++;
            }
        }

        System.out.print(result);
    }

    private static int checkAlphaType(HashMap<Character, Integer> hashMap) {
        cnt = 0;
        hashMap.entrySet().forEach(m -> {
            if(m.getValue() > 0) cnt++;
        });

        return cnt;
    }


}
