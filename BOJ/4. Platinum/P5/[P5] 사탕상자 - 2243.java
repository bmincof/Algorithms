import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-21
public class BOJ {
    static final int MAX_CANDIES = 1_000_010;
    // 번호 구간내에 있는 사탕의 수
    static int[] candies;

    // 사탕의 개수 변경
    static void update(int curr, int left, int right, int qIdx, int value) {
        if (qIdx < left || qIdx > right) {
            return;
        }

        if (left == right) {
            candies[curr] += value;
            candies[curr] = candies[curr] < 0 ? 0 : candies[curr];
            return;
        }

        int mid = (left + right) / 2;
        update(curr*2, left, mid, qIdx, value);
        update(curr*2+1, mid+1, right, qIdx, value);
        candies[curr] = candies[curr*2] + candies[curr*2+1];
    }

    static int getCandy(int curr, int left, int right, int qOrder) {
        // 사탕을 찾았다면
        if (left == right) {
            candies[curr]--;
            return left;
        }

        // 현재 사탕을 반으로 나눴을 때 앞쪽 절반에 있는 사탕의 개수
        int amount = candies[curr*2];

        int mid = (left + right) / 2;
        int foundCandy = 0;
        // 찾으려는 순서의 사탕이 앞 쪽 절반에 있을 경우
        if (qOrder <= amount) {
            foundCandy = getCandy(curr*2, left, mid, qOrder);
        } else {
            foundCandy = getCandy(curr*2+1, mid+1, right, qOrder-amount);
        }

        candies[curr] = candies[curr*2] + candies[curr*2+1];
        return foundCandy;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 사탕상자에 손을 댄 횟수
        int n = Integer.parseInt(br.readLine());

        candies = new int[4*MAX_CANDIES];

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());

            int qType = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                int qOrder = Integer.parseInt(st.nextToken());
                sb.append(getCandy(1, 1, MAX_CANDIES, qOrder)).append("\n");
            } else {
                int candyNum = Integer.parseInt(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());
                update(1, 1, MAX_CANDIES, candyNum, amount);
            }
        }

        System.out.println(sb);
    }
}
