#include <iostream>
#include <algorithm>

using namespace std;

struct Point {
    int idx;
    // 점의 좌표
    long long x, y;
    // 기준 점과의 상대 좌표
    long long a, b;
};

bool compare(Point p, Point q) {
    if (p.a * q.b != p.b * q.a) {
        return p.a * q.b > p.b * q.a;
    }

    if (p.y != q.y) return p.y < q.y;

    return p.x < q.x;
}

bool is_parallel(Point p, Point q) {
    return p.a * q.b == p.b * q.a;
}

Point pts[2001];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    int c;

    cin >> c;

    while (c--) {
        int n;

        cin >> n;

        for (int i = 0; i < n; ++i) {
            int x, y;
            cin >> x >> y;

            Point point;
            point.idx = i;
            point.x = x;
            point.y = y;
            point.a = 0;
            point.b = 0;

            pts[i] = point;
        }

        sort(pts, pts + n, compare);

        Point origin = pts[0];

        for (int i = 1; i < n; ++i) {
            pts[i].a = pts[i].x - origin.x;
            pts[i].b = pts[i].y - origin.y;
        }

        sort(pts + 1, pts + n, compare);

        // 마지막 점들이 일직선 상에 있다면 정렬 기준 역순으로
        int rev_idx = n - 1;
        for (int i = n - 1; i >= 1; i--) {
            if (is_parallel(pts[i], pts[i - 1])) rev_idx--;
            else break;
        }

        for (int i = 0; i < rev_idx; ++i) {
            cout << pts[i].idx << ' ';
        }

        for (int i = n - 1; i >= rev_idx; --i) {
            cout << pts[i].idx << ' ';
        }

        cout << '\n';
    }

    return 0;
}