import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// author : bmincof
// date   : 2023-09-20
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        String line = null;
        while (!(line = br.readLine()).equals("#")) {
            // 파싱하기
            Deque<String> corrector = new LinkedList<>();
            char[] lineArr = line.toCharArray();

            int sdx = 0;
            int edx = 0;

            for (int i = 0; i < lineArr.length; i++) {
                if (lineArr[i] == '<') {
                    sdx = lineArr[i + 1] == '/' ? i + 1 : i;
                }
                if (lineArr[i] == '>') {
                    edx = lineArr[i - 1] == '/' ? i - 1 : i;
                    boolean canUseOne = lineArr[i - 1] == '/';

                    // <br/> 같은 혼자 쓰는 태그이면
                    if (canUseOne) {
                        continue;
                    }

                    String tag = line.substring(sdx + 1, edx).split(" ")[0].strip();

                    if (!corrector.isEmpty() && corrector.peek().equals(tag)) {
                        corrector.pop();
                    } else {
                        corrector.push(tag);
                    }
                }
            }

            answer.append(corrector.isEmpty() ? "legal" : "illegal").append("\n");
        }

        System.out.println(answer);
    }
}