import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2210_숫자판펌프 {
	static int[][] board = new int[5][5];
	static Set<Integer> set = new HashSet<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				DFS(j, i, 0, board[i][j]);
			}
		}
		
		System.out.println(set.size());
	}

	private static void DFS(int x, int y, int cnt, int res) {
		if (cnt == 5) {
			set.add(res);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

			DFS(nx, ny, cnt + 1, res * 10 + board[ny][nx]);
		}
	}
}
