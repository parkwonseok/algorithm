import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2468_¹Ú¿ø¼® {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		boolean[][] ch = new boolean[N][N];
		int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minH = Math.min(minH, map[i][j]);
				maxH = Math.max(maxH, map[i][j]);
			}
		}

		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		int max = Integer.MIN_VALUE;
		for (int h = minH; h <= maxH; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > h) {
						ch[i][j] = true;
					}
				}
			}

			int cnt = 0;
			Deque<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (ch[i][j]) {
						cnt++;
						ch[i][j] = false;
						q.offer(new int[] { j, i });

						while (!q.isEmpty()) {
							int[] tmp = q.poll();
							int x = tmp[0];
							int y = tmp[1];

							for (int k = 0; k < 4; k++) {
								int nx = x + dx[k];
								int ny = y + dy[k];

								if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
							
								if(ch[ny][nx]) {
									ch[ny][nx] = false;
									q.offer(new int[] {nx, ny});
								}
							}
						}
					}
				}
			}
			
			max = Math.max(max, cnt);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) { 
					ch[i][j] = false;
				}
			}
		}
		
		System.out.println(max);
	}
}
