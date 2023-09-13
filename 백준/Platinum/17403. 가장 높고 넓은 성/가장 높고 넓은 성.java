import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-09-13
public class Main {
    static class Point implements Comparable<Point> {
        int idx;
        long x, y, p, q;

        public Point(int idx, long x, long y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            // this x o가 양수이면 this가 o보다 오른쪽
            // 오른쪽에 있는 점이 앞에 오도록
            if (this.p * o.q != this.q * o.p) {
                return this.p * o.q - this.q * o.p > 0 ? -1 : 1;
            }
            // 아래의 점이 더 먼저 오도록
            if (this.y != o.y) {
                return this.y < o.y ? -1 : 1;
            }
            // 왼쪽의 점이 더 먼저 오도록
            return this.x < o.x ? -1 : 1;
        }
    }

    static boolean isCCW(Point p, Point q, Point r) {
        return (q.x - p.x) * (r.y - p.y) - (q.y - p.y) * (r.x - p.x) > 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Point> remainPoints = new ArrayList<>();
        int[] floor = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            remainPoints.add(new Point(i, Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }

        int currentFloor = 1;
        // 남은 점의 개수가 2개 이하라면 볼록 껍질을 못 만듦
        while (remainPoints.size() > 2) {
            Point[] points = new Point[remainPoints.size()];
            remainPoints.toArray(points);
            
            for (Point p : points) {
                p.p = p.q = 0L;
            }

            // 위치 별로 정렬
            Arrays.sort(points);

            for (int i = 1; i < points.length; i++) {
                points[i].p = points[i].x - points[0].x;
                points[i].q = points[i].y - points[0].y;
            }

            Arrays.sort(points, 1, points.length);

            Deque<Point> stack = new LinkedList<>();
            stack.push(points[0]);
            stack.push(points[1]);

            for (int i = 2; i < points.length; i++) {
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

            // 볼록 껍질을 만들었다면 층 기록
            if (stack.size() > 2) {
                while (!stack.isEmpty()) {
                    Point p = stack.pop();
                    floor[p.idx] = currentFloor;
                    remainPoints.remove(p);
                }
                currentFloor++;
                // 이번 턴에 볼록 껍질을 못 만들었다면 -> 더 높은 층은 없음
            } else {
                break;
            }
        }

        for (int f : floor) {
            answer.append(f).append(" ");
        }
        System.out.println(answer);
    }
}