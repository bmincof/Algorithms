import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author   : bmincof
// date     : 2023-08-31
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String X = st.nextToken();
        String Y = st.nextToken();

        int revX = Integer.parseInt(new StringBuilder(X).reverse().toString());
        int revY = Integer.parseInt(new StringBuilder(Y).reverse().toString());

        System.out.println(Integer.parseInt(new StringBuilder(String.valueOf(revX + revY)).reverse().toString()));
    }
}