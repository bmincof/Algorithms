import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-18
public class BOJ {
    static long[] account;

    static void add(int curr, int left, int right, int queryIdx, int value) {
        if (queryIdx < left || queryIdx > right) {
            return;
        }

        if (left == right) {
            account[curr] += value;
            return;
        }

        int mid = (left + right) / 2;
        add(curr*2, left, mid, queryIdx, value);
        add(curr*2+1, mid+1, right, queryIdx, value);
        account[curr] = account[curr*2] + account[curr*2+1];
    }

    static long findSum(int curr, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft > right || queryRight < left) {
            return 0;
        }

        if (queryLeft <= left && right <= queryRight) {
            return account[curr];
        }

        int mid = (left + right) / 2;
        return findSum(curr*2, left, mid, queryLeft, queryRight)
                + findSum(curr*2+1, mid+1, right, queryLeft, queryRight);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        account = new long[4*N];

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int queryType = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (queryType == 1) {
                add(1, 1, N, first, second);
            } else {
                sb.append(findSum(1, 1, N, first, second)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
