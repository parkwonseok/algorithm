package algotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {
	static int R, C, answer;
	static char[][] map;
	static boolean[] visited = new boolean[26];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String row = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = row.charAt(j);
			}
		}

		// DFS로 탐색 (알파벳 중복 체크하면서)
		visited[map[0][0] - 'A'] = true;
		DFS(0, 0, 1);

		// 결과
		System.out.println(answer);
	}

	private static void DFS(int x, int y, int cnt) {
		answer = Math.max(answer, cnt);

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= C || ny >= R)
				continue;

			if (!visited[map[ny][nx] - 'A']) {
				visited[map[ny][nx] - 'A'] = true;
				DFS(nx, ny, cnt + 1);
				visited[map[ny][nx] - 'A'] = false;
			}
		}
	}
}
