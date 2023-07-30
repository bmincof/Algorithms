package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-30
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 테스트 케이스의 수
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 한 줄에 있는 스티커의 개수
            int n = Integer.parseInt(br.readLine());

            // 스티커의 점수 1-indexed
            int[][] sticker = new int[2][n+1];
            // 스티커 점수 입력
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // dp테이블 채우기
            // 테이블 : [i][j]의 스티커를 포함해서 얻을 수 있는 최대 점수
            // int 범위를 넘어갈 일 없음
            // 테이블 초기화
            int[][] dp = new int[2][n+1];
            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];
            // 대각선으로 선택하거나 2칸 앞에서 젤 큰 스티커를 골라야 함
            for (int i = 2; i <= n; i++) {
                int beforeTwo = Math.max(dp[0][i-2], dp[1][i-2]);
                dp[0][i] = sticker[0][i] + Math.max(dp[1][i-1], beforeTwo);
                dp[1][i] = sticker[1][i] + Math.max(dp[0][i-1], beforeTwo);
            }

            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }

        System.out.print(sb);
    }
}
