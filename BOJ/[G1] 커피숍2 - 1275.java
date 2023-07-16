import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-17
public class BOJ {
    static class SumTree {
        long[] tree;

        SumTree(int[] array) {
            tree = new long[4*array.length];
            initTree(1, 1, array.length-1, array);
        }

        void initTree(int curr, int left, int right, int[] array) {
            if (left == right) {
                tree[curr] = array[left];
                return;
            }

            int mid = (left + right) / 2;
            initTree(curr*2, left, mid, array);
            initTree(curr*2+1, mid+1, right, array);
            tree[curr] = tree[curr*2] + tree[curr*2+1];
        }

        void update(int curr, int left, int right, int queryIdx, int value) {
            if (queryIdx < left || queryIdx > right) {
                return;
            }

            if (left == right) {
                tree[curr] = value;
                return;
            }

            int mid = (left + right) / 2;
            update(curr*2, left, mid, queryIdx, value);
            update(curr*2+1, mid+1, right, queryIdx, value);
            tree[curr] = tree[curr*2] + tree[curr*2+1];
        }

        long findSum(int curr, int left, int right, int queryLeft, int queryRight) {
            if (queryLeft > right || queryRight < left) {
                return 0;
            }

            if (queryLeft <= left && right <= queryRight) {
                return tree[curr];
            }

            int mid = (left + right) / 2;
            return findSum(curr*2, left, mid, queryLeft, queryRight)
                    + findSum(curr*2+1, mid+1, right, queryLeft, queryRight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] array = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        SumTree sumTree = new SumTree(array);

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }

            sb.append(sumTree.findSum(1, 1, N, x, y)).append("\n");
            sumTree.update(1, 1, N, a, b);
        }

        System.out.println(sb);
    }
}
