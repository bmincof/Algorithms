#include <iostream>
#include <climits>

using namespace std;

int N, M;
// N 크기의 수열
int arr[100010];

struct TreeNode {
    int value;
    int idx;
};

TreeNode getMinNode(TreeNode n1, TreeNode n2) {
    return n1.value <= n2.value ? n1 : n2;
}

// 트리
TreeNode tree[100010 * 4];

TreeNode build_tree(int left, int right, int tree_idx) {
    if (left == right) {
        return tree[tree_idx] = TreeNode{arr[left], left};
    }

    int mid = (left + right) / 2;

    TreeNode left_min = build_tree(left, mid, tree_idx * 2);
    TreeNode right_min = build_tree(mid + 1, right, tree_idx * 2 + 1);

    return tree[tree_idx] = getMinNode(left_min, right_min);
}

TreeNode update(int left, int right, int tree_idx, int idx, int value) {
    if (idx < left || idx > right) {
        return tree[tree_idx];
    }

    if (left == right) {
        tree[tree_idx].value = value;
        return tree[tree_idx];
    }

    int mid = (left + right) / 2;

    TreeNode result_left = update(left, mid, tree_idx * 2, idx, value);
    TreeNode result_right = update(mid + 1, right, tree_idx * 2 + 1, idx, value);

    return tree[tree_idx] = getMinNode(result_left, result_right);
}

TreeNode query(int left, int right, int tree_idx, int query_left, int query_right) {
    if (query_right < left || query_left > right) {
        return TreeNode{INT_MAX, -1};
    }

    if (query_left <= left && right <= query_right) {
        return tree[tree_idx];
    }

    int mid = (left + right) / 2;

    TreeNode left_min = query(left, mid, tree_idx * 2, query_left, query_right);
    TreeNode right_min = query(mid + 1, right, tree_idx * 2 + 1, query_left, query_right);

    return getMinNode(left_min, right_min);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for (int i = 0; i < N; ++i) {
        cin >> arr[i];
    }
    build_tree(0, N - 1, 1);

    cin >> M;

    for (int i = 0; i < M; ++i) {
        int cmd, first, second;

        cin >> cmd >> first >> second;

        if (cmd == 1) {
            update(0, N - 1, 1, first - 1, second);
        } else if (cmd == 2) {
            cout << query(0, N - 1, 1, first - 1, second - 1).idx + 1 << endl;
        }
    }

    return 0;
}
