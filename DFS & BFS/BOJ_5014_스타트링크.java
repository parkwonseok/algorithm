import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_5014_스타트링크 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int[] UD = new int[2];
		UD[0] = Integer.parseInt(st.nextToken());
		UD[1] = -Integer.parseInt(st.nextToken());
		boolean[] ch = new boolean[F + 1];

		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { S, 0 });
		ch[S] = true;
		int answer = -1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int floor = cur[0];
			int cnt = cur[1];
			
			if(floor == G) {
				answer = cnt;
				break;
			}

			for (int i = 0; i < 2; i++) {
				int nextFloor = floor + UD[i];
				
				if(nextFloor < 1 || nextFloor > F) continue;
				
				if(!ch[nextFloor]) {
					ch[nextFloor] = true;
					q.offer(new int[] {nextFloor, cnt + 1});
				}
			}
		}
		
		if(answer != -1) System.out.println(answer);
		else System.out.println("use the stairs");
	}
}
