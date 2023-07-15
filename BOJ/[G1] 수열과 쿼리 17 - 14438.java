package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-07-15
public class Main {
    static class MinTree {
        int[] tree;

        // 트리 생성
        MinTree(int[] array) {
            tree = new int[4 * array.length];
            initTree(1, 1, array.length - 1, array);
        }

        // 트리 초기화
        void initTree(int curr, int left, int right, int[] values) {
            // 구간의 크기가 1칸인 노드라면
            if (left == right) {
                tree[curr] = values[left];
                return;
            }

            int mid = (left + right) >> 1;

            initTree(curr * 2, left, mid, values);
            initTree(curr * 2 + 1, mid + 1, right, values);
            tree[curr] = Math.min(tree[curr * 2], tree[curr * 2 + 1]);
        }

        // 트리의 값을 변경하는 메서드
        void update(int curr, int left, int right, int queryIdx, int value) {
            // queryIdx 가 위치할 수 없는 범위이면 제외
            if (queryIdx < left || right < queryIdx) {
                return;
            }

            if (left == right) {
                tree[curr] = value;
                return;
            }

            int mid = (left + right) >> 1;
            update(curr * 2, left, mid, queryIdx, value);
            update(curr * 2 + 1, mid + 1, right, queryIdx, value);
            tree[curr] = Math.min(tree[curr * 2], tree[curr * 2 + 1]);
        }

        // 최솟값을 찾는 메서드
        int findMin(int curr, int left, int right, int queryLeft, int queryRight) {
            // 구하려는 범위가 하나도 포함되지 않으면 제외
            if (queryRight < left || right < queryLeft) {
                return Integer.MAX_VALUE;
            }
            // 구하려는 범위에 완전히 포함되면
            if (queryLeft <= left && right <= queryRight) {
                return tree[curr];
            }

            int mid = (left + right) >> 1;
            return Math.min(findMin(curr * 2, left, mid, queryLeft, queryRight),
            findMin(curr * 2 + 1, mid + 1, right, queryLeft, queryRight));
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        MinTree minTree = new MinTree(arr);

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                minTree.update(1, 1, N, first, second);
            } else {
                sb.append(minTree.findMin(1, 1, N, first, second)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
