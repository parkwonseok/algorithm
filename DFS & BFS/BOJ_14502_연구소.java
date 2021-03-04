import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14502_¿¬±¸¼Ò {
	static int N, M, max = Integer.MIN_VALUE;
	static int[][] map;
	static int[][] copyMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0);
		
		System.out.println(max);
	}
	
	private static void DFS(int cnt) {
		if(cnt == 3) {
			copyArray();
			BFS();
			max = Math.max(max, countZero());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					DFS(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static int countZero() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void BFS() {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		Deque<int[]> q = new ArrayDeque<int[]>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 2) {
					q.offer(new int[] {j, i});
					
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						int x = cur[0];
						int y = cur[1];
						
						for(int k = 0; k < 4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
							
							if(copyMap[ny][nx] == 0) {
								copyMap[ny][nx] = 2;
								q.offer(new int[] {nx, ny});
							}
						}
					}
				}
			}
		}
	}

	private static void copyArray() {
		copyMap = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
}
