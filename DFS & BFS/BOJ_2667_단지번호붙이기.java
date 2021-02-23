package algotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class BOJ_2667_단지번호붙이기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String row = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = row.charAt(j);
			}
		}
		
		int cnt = 0;
		ArrayList<Integer> areaList = new ArrayList<>();
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '1') {
					cnt++;
					
					Deque<int[]> q = new ArrayDeque<>();
					q.offer(new int[] {j, i});
					map[i][j] = '0';
					int area = 1;
					
					while(!q.isEmpty()) {
						int[] tmp = q.poll();
						
						for(int k = 0; k < 4; k++) {
							int nx = tmp[0] + dx[k];
							int ny = tmp[1] + dy[k];
							
							if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						
							if(map[ny][nx] == '1') {
								area++;
								map[ny][nx] = '0';
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
			sb.append(areaList.get(i)).append("\n");
		}
		System.out.println(sb.toString());
	}
}
