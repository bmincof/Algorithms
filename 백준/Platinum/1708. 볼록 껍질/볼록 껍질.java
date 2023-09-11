import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-09-11
public class Main {
    static class Point implements Comparable<Point> {
        long x;
        long y;
        long p;
        long q;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.p * o.q != this.q * o.p) {
                return this.q * o.p - this.p * o.q < 0 ? -1 : 1;
            }
            if (this.y != o.y) {
                return this.y - o.y < 0 ? -1 : 1;
            }
            return this.x - o.x < 0 ? -1 : 1;
        }
    }

    static boolean isCCW(Point p, Point q, Point r) {
        Point pq = new Point(q.x - p.x, q.y - p.y);
        Point pr = new Point(r.x - p.x, r.y - p.y);
        return pq.x * pr.y - pq.y * pr.x > 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }
        Arrays.parallelSort(points);

        for (int i = 1; i < N; i++) {
            points[i].p = points[i].x - points[0].x;
            points[i].q = points[i].y - points[0].y;
        }
        Arrays.parallelSort(points, 1, N);

        Deque<Point> stack = new LinkedList<>();
        stack.push(points[0]);
        stack.push(points[1]);

        for (int i = 2; i < N; i++) {
            Point r = points[i];
            while (stack.size() >= 2) {
                Point q = stack.pop();
                Point p = stack.peek();

                if (isCCW(p, q, r)) {
                    stack.push(q);
                    break;
                }
            }
            stack.push(r);
        }

        System.out.println(stack.size());
    }
}