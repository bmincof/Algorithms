import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-19
public class BOJ {
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new long[4*(N+1)];

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int qType = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                update(1, 1, N + 1, p1, p2);
            } else {
                if (p1 > p2) {
                    int tmp = p1;
                    p1 = p2;
                    p2 = tmp;
                }

                sb.append(sum(1, 1, N + 1, p1, p2)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void update(int curr, int left, int right, int qdx, int value) {
        if (qdx < left || qdx > right) {
            return;
        }

        if (left == right) {
            tree[curr] = value;
            return;
        }

        int mid = (left + right) / 2;
        update(curr * 2, left, mid, qdx, value);
        update(curr * 2 + 1, mid + 1, right, qdx, value);
        tree[curr] = tree[curr * 2] + tree[curr * 2 + 1];
    }

    static long sum(int curr, int left, int right, int qLeft, int qRight) {
        if (qLeft > right || qRight < left) {
            return 0;
        }

        if (qLeft <= left && right <= qRight) {
            return tree[curr];
        }

        int mid = (left + right) / 2;
        return sum(curr * 2, left, mid, qLeft, qRight)
                + sum(curr * 2 + 1, mid + 1, right, qLeft, qRight);
    }
}
