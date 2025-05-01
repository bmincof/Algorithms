package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-06-29
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final long MOD = 1_000_007L;
        // 최대 문자열 길이
        final int MAX_SIZE = 2001;
        // 해시함수에 사용할 base (멀티 해싱)
        final long[] UNITS = {5, 13, 23};
        final int UNIT_CNT = UNITS.length;

        // dp처럼 나올 수 있는 unit의 거듭제곱을 모두 구해놓는다
        long[][] powerOfUnit = new long[UNIT_CNT][MAX_SIZE * MAX_SIZE];

        for(int i = 0; i < UNIT_CNT; i++) {
            for(int j = 0; j <= 4_000_000; j++) {
                if(j == 0) {
                    powerOfUnit[i][0] = 1L;
                }
                else {
                    powerOfUnit[i][j] = (powerOfUnit[i][j - 1] * UNITS[i]) % MOD;
                }
            }
        }

        // 큰 2차원 문자열의 해시값을 저장하는 배열
        long[][][] originalHash = new long[UNIT_CNT][MAX_SIZE][MAX_SIZE];

        st = new StringTokenizer(br.readLine());
        // 찾아야하는 패턴의 크기
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        // 문자열의 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 패턴에 대한 해시
        long[] patternHash = new long[UNIT_CNT];

        for(int i = 1; i <= H; i++) {
            String line = br.readLine();
            for(int j = 1; j <= W; j++) {
                // o이면 1, x이면 0
                long value = line.charAt(j - 1) == 'o' ? 1L : 0L;
                // H W 패턴을 해시값 하나로 변환
                for (int k = 0; k < UNIT_CNT; k++) {
                    patternHash[k] = (patternHash[k] + value * powerOfUnit[k][i * M + j] + 5 * MOD) % MOD;
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            String line = br.readLine();
            for(int j = 1; j <= M; j++) {
                long value = line.charAt(j-1) == 'o' ? 1L : 0L;
                // 문자열을 해시값으로 변환 (2차원 prefix sum 방식으로)
                for(int k = 0; k < UNIT_CNT; k++) {
                    originalHash[k][i][j] = (value * powerOfUnit[k][i * M + j]
                            + originalHash[k][i - 1][j]
                            + originalHash[k][i][j - 1]
                            - originalHash[k][i - 1][j - 1]
                            + 5 * MOD) % MOD;
                }
            }
        }

        int count = 0;
        // 패턴 매칭
        for(int i = H; i <= N; i++) {
            for(int j = W; j <= M; j++) {
                boolean isSamePattern = true;
                // 모든 해싱에 대해 값이 일치하면 같은 패턴이라고 간주
                for(int k = 0; k < UNIT_CNT; k++) {
                    // 2차원 prefix-sum으로 부터 값을 계산
                    long hash = (originalHash[k][i][j]
                            - originalHash[k][i - H][j]
                            - originalHash[k][i][j - W]
                            + originalHash[k][i - H][j - W]
                            + 5 * MOD) % MOD;
                    // 나머지 연산의 성질에 의해 패턴의 해시값 * base^(M(i-H)+(j-W)) % MOD와 위에서 계산한 해시값을 비교할 수 있음
                    long shiftedHash = (patternHash[k] * powerOfUnit[k][(i - H) * M + (j - W)]) % MOD;
                    isSamePattern &= hash == shiftedHash;
                }
                if(isSamePattern) count++;
            }
        }

        System.out.println(count);
    }
}
