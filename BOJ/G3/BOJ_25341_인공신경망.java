package G3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_25341_ÀÎ°ø½Å°æ¸Á {
	static int N, M, Q, B;
	static NeuralNetwork[] WholeNN;
	static NeuralNetwork OutputNN;
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		WholeNN = new NeuralNetwork[M + 1];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int C = Integer.parseInt(tokens.nextToken());
			WholeNN[i] = new NeuralNetwork(new int[C], new int[C]);
			for (int j = 0; j < C; j++) {
				WholeNN[i].P[j] = Integer.parseInt(tokens.nextToken()) - 1;
			}
			for (int j = 0; j < C; j++) {
				WholeNN[i].W[j] = Integer.parseInt(tokens.nextToken());
			}
			WholeNN[i].B = Integer.parseInt(tokens.nextToken());
		}
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < M; i++) {
			int pow = Integer.parseInt(tokens.nextToken());
			int len = WholeNN[i].W.length;
			for (int j = 0; j < len; j++) {
				WholeNN[i].W[j] *= pow;
			}
			WholeNN[i].B *= pow;
		}
		B = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < Q; i++) {
			tokens = new StringTokenizer(read.readLine());
			int[] tempQ = new int[N];
			for (int j = 0; j < N; j++) {
				tempQ[j] = Integer.parseInt(tokens.nextToken());
			}
			write.write(getCalc(tempQ) + "\n");
		}
		write.close();
		read.close();
	}

	private static long getCalc(int[] tempQ) {
		long result = B;
		for (int i = 0; i < M; i++) {
			int len = WholeNN[i].W.length;
			result += WholeNN[i].B;
			for (int j = 0; j < len; j++) {
				result += WholeNN[i].W[j] * tempQ[WholeNN[i].P[j]];
			}
		}
		return result;
	}

	private static class NeuralNetwork {
		int[] P;
		int[] W;
		int B;

		public NeuralNetwork(int[] p, int[] w) {
			P = p;
			W = w;
		}

	}

}
