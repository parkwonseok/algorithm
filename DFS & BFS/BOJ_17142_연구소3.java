import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17142_¿¬±¸¼Ò3 {
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

		int[] P = new int[vLen];
		for (int i = 0; i < M; i++)
			P[vLen - i - 1] = 1;

		Loop:do {
			dis = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dis[i][j] = -1;
				}
			}

			Deque<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < vLen; i++) {
				if (P[i] == 1) {
					q.offer(new int[] { vList.get(i)[0], vList.get(i)[1] });
					dis[vList.get(i)[1]][vList.get(i)[0]] = 0;
				} else {
					map[vList.get(i)[1]][vList.get(i)[0]] = 3;
				}
			}
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				
				for(int j=0; j<4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					
					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					
					if(map[ny][nx] != 1 && dis[ny][nx] == -1) {
						dis[ny][nx] = dis[y][x] + 1;
						q.offer(new int[] {nx, ny});
					}
				}
			}

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != 1 && dis[i][j] == -1) {
						continue Loop;
					}
					if(map[i][j] != 3)
						max = Math.max(max, dis[i][j]);
				}
			}
			min = Math.min(min, max);
			
			for (int i = 0; i < vLen; i++) {
				if (P[i] == 0) {
					map[vList.get(i)[1]][vList.get(i)[0]] = 2;
				}
			}
		} while (np(P));
		
		if (min != Integer.MAX_VALUE)
			System.out.println(min);
		else
			System.out.println("-1");
	}

	private static boolean np(int[] arr) {
		int i = vLen - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			--i;

		if (i == 0)
			return false;

		int j = vLen - 1;
		while (arr[i - 1] >= arr[j])
			--j;

		swap(arr, i - 1, j);

		int k = vLen - 1;
		while (i < k) {
			swap(arr, i++, k--);
		}
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
