import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_9019_DSLR {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			Deque<Pair> q = new ArrayDeque<>();
			q.offer(new Pair(A, ""));
			boolean[] ch = new boolean[10000];
			ch[A] = true;

			while (!q.isEmpty()) {
				Pair p = q.poll();
				int cur = p.num;
				String cmd = p.cmd;
				
				if(cur == B) {
					sb.append(cmd).append("\n");
					break;
				}

				// D
				int dNum = cur * 2 % 10000;
				if (!ch[dNum]) {
					ch[dNum] = true;
					q.offer(new Pair(dNum, cmd + "D"));
				}

				// S
				int sNum = (cur - 1 == 0) ? 9999 : cur - 1;
				if (!ch[sNum]) {
					ch[sNum] = true;
					q.offer(new Pair(sNum, cmd + "S"));
				}
				
				// L
				int lNum = (cur / 1000) + (cur % 1000) * 10;
				if (!ch[lNum]) {
					ch[lNum] = true;
					q.offer(new Pair(lNum, cmd + "L"));
				}
				
				// R
				int rNum = (cur / 10) + (cur % 10) * 1000;
				if (!ch[rNum]) {
					ch[rNum] = true;
					q.offer(new Pair(rNum, cmd + "R"));
				}
			}
		}
		
		System.out.println(sb.toString());
	}

	static class Pair {
		int num;
		String cmd;

		Pair(int num, String cmd) {
			this.num = num;
			this.cmd = cmd;
		}
	}
}
