import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16956_´Á´ë¿Í¾ç {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		List<int[]> sList = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			String row = in.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'S') sList.add(new int[] {j, i});
			}
		}
		
		boolean flag = true;
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		for(int i = 0, len = sList.size(); i < len; i++) {
			for(int j = 0; j < 4; j++) {
				int nx = sList.get(i)[0] + dx[j];
				int ny = sList.get(i)[1] + dy[j];
			
				if(nx < 0 || ny < 0 || nx >= C || ny >= R) continue;
			
				if(map[ny][nx] == 'W') {
					flag = false;
					break;
				}
				
				if(map[ny][nx] == '.') {
					map[ny][nx] = 'D';
				}
			}
		}
		
		if(flag) {
			System.out.println("1");
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		} else {
			System.out.println("0");
		}
	}
}
