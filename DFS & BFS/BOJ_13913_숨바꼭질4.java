import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13913_숨바꼭질4 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] ch = new boolean[100001];
		int[] parent = new int[100001];
		
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {N, 0});
		ch[N] = true;
		int min = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curPos = cur[0];
			int curDis = cur[1];
			
			if(curPos == K) {
				min = curDis;
				break;
			}
			
			if((curPos - 1 >= 0 && !ch[curPos - 1])) {
				ch[curPos - 1] = true;
				parent[curPos - 1] = curPos;
				q.offer(new int[] {curPos - 1, curDis + 1});
			}
			if((curPos + 1 <= 100000 && !ch[curPos + 1])) {
				ch[curPos + 1] = true;
				parent[curPos + 1] = curPos;
				q.offer(new int[] {curPos + 1, curDis + 1});
			}
			if((curPos * 2 <= 100000 && !ch[curPos * 2])) {
				ch[curPos * 2] = true;
				parent[curPos * 2] = curPos;
				q.offer(new int[] {curPos * 2, curDis + 1});
			}
		}
		
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(K);
		int num = K;
		
		while(num != N) {
			stack.push(parent[num]);
			num = parent[num];
		}
		
		sb.append(min).append("\n");
		while(!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		System.out.println(sb.toString());
	}
}
