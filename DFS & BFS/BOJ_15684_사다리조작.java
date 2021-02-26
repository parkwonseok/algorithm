package week3.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15684_ {
	static int N, M, H, answer = Integer.MAX_VALUE;
	static boolean[][] ladder;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		if (M == 0) {
			System.out.println("0");
			return;
		}

		ladder = new boolean[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = true;
		}

		DFS(0, 1);
		
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		
		System.out.println(answer);
	}

	static void DFS(int cnt, int start) {
		if(cnt > 3) return;
		
		if(check()) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for (int i = start; i <= H; i++) {
			for(int j = 1; j < N; j++) {
				if(!ladder[i][j] && !ladder[i][j - 1] && !ladder[i][j + 1]) {
					ladder[i][j] = true;
					DFS(cnt + 1, i);
					ladder[i][j] = false;
				}
			}
		}
	}
	
	static boolean check() {
		for(int i = 1; i <= N; i++) {
			if(lineCheck(1, i) != i) {
				return false;
			}
		}
		return true;
	}
	
	static int lineCheck(int r, int c) {
		for(int i = r; i <= H; i++) {
			if(ladder[i][c]) return lineCheck(i + 1, c + 1);
			if(ladder[i][c - 1]) return lineCheck(i + 1, c - 1);
		}
		return c;
	}
}
