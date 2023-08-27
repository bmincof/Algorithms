import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-08-28
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] lis = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int longest = 0;
        // i번째보다 작은 수들 중 가장 긴 LIS의 길이 + 1
        for (int i = 0; i < N; i++) {
            int maxLen = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    maxLen = Math.max(maxLen, lis[j]);
                }
            }

            lis[i] = maxLen + 1;
            longest = Math.max(longest, lis[i]);
        }
        // 가장 긴 LIS 길이 출력
        System.out.println(longest);

        // LIS 복구하기
        Stack<Integer> restore = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (lis[i] == longest) {
                restore.add(arr[i]);
                longest--;
            }
        }

        StringBuilder answer = new StringBuilder();
        while (!restore.isEmpty()) {
            answer.append(restore.pop()).append(" ");
        }

        System.out.println(answer);
    }
}