import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	static int N, map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] P = new int[N];
		for (int i = 0; i < N / 2; i++) P[N - 1 - i] = 1;
		
		int answer = Integer.MAX_VALUE;
		do {
			int idx1 = 0, idx2 = 0, sum1 = 0, sum2 = 0;
			int[] start = new int[N / 2];
			int[] link = new int[N / 2];
			
			for(int i = 0; i < N; i++) {
				if(P[i] == 1) start[idx1++] = i;
				else link[idx2++] = i;
			}
			
			for(int i = 0; i < N / 2; i++) {
				for(int j = 0; j < N / 2; j++) {
					if(i != j) {
						sum1 += map[start[i]][start[j]];
						sum2 += map[link[i]][link[j]];
					}
				}
			}
			
			answer = Math.min(answer, Math.abs(sum1 - sum2));
		} while(np(P));
		
		System.out.println(answer);
	}

	static boolean np(int[] arr) {
		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;
		if (i == 0)
			return false;
		int j = N - 1;
		while (arr[i - 1] >= arr[j])
			--j;
		swap(arr, i - 1, j);
		int k = N - 1;
		while (i < k)
			swap(arr, i++, k--);
		return true;
	}

	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
