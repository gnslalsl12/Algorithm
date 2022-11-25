import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(read.readLine());
        int result = 0;
        for(int i = 1; i <= N; i++){
            result += i;
        }
        System.out.println(result);
    }
}
