import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780_종이의개수 {
	static int[][] map;
	static int[] cnt = new int[3];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(N, 0, 0);
		
		for(int i = 0; i < 3; i++) System.out.println(cnt[i]);
	}

	private static void DFS(int size, int r, int c) {
		if(check(size, r, c)) {
			cnt[map[r][c] + 1]++;
			return;
		}
		
		size /= 3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				DFS(size, r + i * size, c + j * size);
			}
		}
	}
	
	private static boolean check(int size, int r, int c) {
		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				if(map[r][c] != map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
