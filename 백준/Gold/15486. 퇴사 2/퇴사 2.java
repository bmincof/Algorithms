import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-08-06
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N + 1];
        int[] price = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken()) - 1;
            price[i] = Integer.parseInt(st.nextToken());
        }

        // i일 까지 얻을 수 있는 최대 이득
        int[] dp = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            // i + time[i] 로 계산한 값과, i - 1일까지 갱신해온 최댓값 중 더 큰 것 선택
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (i + time[i] <= N) {
                // 기존의 구한 i + time[i]일의 최대 이득과 i - 1일까지 최대 이득 + i일에 시작한 일 비교
                dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i - 1] + price[i]);
            }
        }

        System.out.println(dp[N]);
    }
}