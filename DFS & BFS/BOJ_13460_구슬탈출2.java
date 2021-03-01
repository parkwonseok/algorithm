import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13460_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] board = new char[N][M];
		boolean[][][][] ch = new boolean[N][M][N][M];
		Loc start = new Loc();

		for (int i = 0; i < N; i++) {
			String row = in.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = row.charAt(j);
				if (board[i][j] == 'R') {
					start.rx = j;
					start.ry = i;
				}
				if (board[i][j] == 'B') {
					start.bx = j;
					start.by = i;
				}
			}
		}

		Deque<Loc> q = new ArrayDeque<>();
		q.offer(start);
		ch[start.ry][start.rx][start.by][start.bx] = true;
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };

		while (!q.isEmpty()) {
			Loc cur = q.poll();
			int bx = cur.bx;
			int by = cur.by;
			int rx = cur.rx;
			int ry = cur.ry;
			int dis = cur.dis;

			if (dis >= 10) break;
			
			for (int i = 0; i < 4; i++) {
				int nrx = rx, nry = ry, nbx = bx, nby = by;

				while (board[nry + dy[i]][nrx + dx[i]] != '#' && board[nry][nrx] != 'O') {
					nrx += dx[i];
					nry += dy[i];
				}
				
				while (board[nby + dy[i]][nbx + dx[i]] != '#' && board[nby][nbx] != 'O') {
					nbx += dx[i];
					nby += dy[i];
				}
				
				if(board[nby][nbx] == 'O') continue;
				if(board[nry][nrx] == 'O') {
					System.out.println(dis + 1);
					return;
				}

				if (nbx == nrx && nby == nry) {
					if (i == 0) {
						if (ry > by) nry++;
						else nby++;
					} else if (i == 1) {
						if (rx > bx) nbx--;
						else nrx--;
					} else if (i == 2) {
						if (ry > by) nby--;
						else nry--;
					} else {
						if (rx > bx) nrx++;
						else nbx++;
					}
				}

				if (!ch[nry][nrx][nby][nbx]) {
					ch[nry][nrx][nby][nbx] = true;
					q.offer(new Loc(nrx, nry, nbx, nby, dis + 1));
				}
			}

		}

		System.out.println("-1");
	}

	static class Loc {
		int rx, ry, bx, by, dis;

		Loc() {
			this.dis = 0;
		}

		Loc(int rx, int ry, int bx, int by, int dis) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.dis = dis;
		}
	}
}
