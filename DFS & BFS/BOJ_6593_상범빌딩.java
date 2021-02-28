import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_6593_상범빌딩 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] dx = {0, 1, 0, -1, 0, 0};
		int[] dy = {-1, 0, 1, 0, 0, 0};
		int[] dz = {0, 0, 0 ,0, -1, 1};
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(in.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(L == 0 && R == 0 && C == 0) break;
			
			char[][][] building = new char[L][R][C];
			int[][][] dis = new int[L][R][C];
			Loc start = null;

			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String row = in.readLine();
					
					for (int k = 0; k < C; k++) {
						building[i][j][k] = row.charAt(k);
						if(building[i][j][k] == 'S') start = new Loc(k, j, i);
					}
				}
				in.readLine();
			}
			
			Deque<Loc> q = new ArrayDeque<>();
			q.offer(start);
			int answer = -1;
			
			Loop:while(!q.isEmpty()) {
				Loc tmp = q.poll();
				int x = tmp.x;
				int y = tmp.y;
				int z = tmp.z;
				
				for(int i = 0; i < 6; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					int nz = z + dz[i];
					
					if(nx < 0 || ny < 0 || nz < 0 || nx >= C || ny >= R || nz >= L) continue;
					
					if(building[nz][ny][nx] == '.' && dis[nz][ny][nx] == 0) {
						dis[nz][ny][nx] = dis[z][y][x] + 1;
						q.offer(new Loc(nx, ny, nz));
					} else if(building[nz][ny][nx] == 'E') {
						answer = dis[z][y][x] + 1;
						break Loop;
					}
				}
			}
			
			if(answer != -1)
				sb.append("Escaped in ").append(answer).append(" minute(s).\n");
			else
				sb.append("Trapped!\n");
		}
		
		System.out.println(sb.toString());
	}

	static class Loc {
		int x, y, z;

		Loc(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
