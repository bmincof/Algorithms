import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        Set<String> usedSet = new HashSet<>();
        String word = null;
        while (!(word = br.readLine()).equals("*")) {
            int N = word.length();
            boolean isSurprising = true;
            
            check:
            for (int len = 0; len <= N-2; len++) {
                usedSet.clear();
            
                for (int i = 0; i < N - len - 1; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(word.charAt(i)).append(word.charAt(i + len + 1));

                    if (!usedSet.add(sb.toString())) {
                        isSurprising = false;
                        break check;
                    }
                }
            }

            answer.append(word)
                    .append(" is")
                    .append(isSurprising ? "" : " NOT")
                    .append(" surprising.")
                    .append("\n");
        }

        System.out.print(answer);
    }
}