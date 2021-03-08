import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17141_¿¬±¸¼Ò2 {
	static int N, M, map[][], dis[][], vLen, idx[], min = Integer.MAX_VALUE;
	static List<int[]> vList = new ArrayList<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					vList.add(new int[] { j, i });
				}
			}
		}

		vLen = vList.size();
		idx = new int[M];

		DFS(0, 0);

		if (min != Integer.MAX_VALUE)
			System.out.println(min);
		else
			System.out.println("-1");
	}

	// Combination
	private static void DFS(int cnt, int start) {
		if (cnt == M) {
			dis = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dis[i][j] = -1;
				}
			}

			// BFS
			Deque<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < M; i++) {
				q.offer(new int[] { vList.get(idx[i])[0], vList.get(idx[i])[1] });
				dis[vList.get(idx[i])[1]][vList.get(idx[i])[0]] = 0;
			}

			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];

				for (int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;

					if (map[ny][nx] != 1 && dis[ny][nx] == -1) {
						dis[ny][nx] = dis[y][x] + 1;
						q.offer(new int[] { nx, ny });
					}
				}
			}

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != 1 && dis[i][j] == -1) {
						return;
					}
					max = Math.max(max, dis[i][j]);
				}
			}
			min = Math.min(min, max);

			return;
		}

		for (int i = start; i < vLen; i++) {
			idx[cnt] = i;
			DFS(cnt + 1, i + 1);
		}
	}
}
