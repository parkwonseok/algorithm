package algotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[M][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int j = y1; j < y2; j++) {
				for (int k = x1; k < x2; k++) {
					map[j][k] = 1;
				}
			}
		}

		int cnt = 0;
		ArrayList<Integer> areaList = new ArrayList<>();
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					cnt++;
					
					Deque<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {j, i});
					map[i][j] = 1;
					int area = 1;
					
					while(!q.isEmpty()) {
						int[] tmp = q.poll();
						
						for(int k = 0; k < 4; k++) {
							int nx = tmp[0] + dx[k];
							int ny = tmp[1] + dy[k];
							
							if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
						
							if(map[ny][nx] == 0) {
								area++;
								map[ny][nx] = 1;
								q.offer(new int[] {nx, ny});
							}
						}
					}
					
					areaList.add(area);
				}
			}
		}
		
		Collections.sort(areaList);
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for(int i = 0; i < cnt; i++) {
			sb.append(areaList.get(i)).append(" ");
		}
		System.out.println(sb.toString());
	}
}
