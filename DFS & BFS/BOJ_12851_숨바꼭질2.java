import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_12851_¼û¹Ù²ÀÁú2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] ch = new boolean[100001];
		
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {N, 0});
		ch[N] = true;
		int min = Integer.MAX_VALUE, cnt = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curPos = cur[0];
			int curDis = cur[1];
			
			ch[curPos] = true;
			
			if(curPos == K) {
				if(curDis < min) {
					min = curDis;
					cnt = 1;
				} else if(curDis == min) {
					cnt++;
				}
			}
			
			if((curPos - 1 >= 0 && !ch[curPos - 1])) 
				q.offer(new int[] {curPos - 1, curDis + 1});
			
			if((curPos + 1 <= 100000 && !ch[curPos + 1])) 
				q.offer(new int[] {curPos + 1, curDis + 1});
			
			if((curPos * 2 <= 100000 && !ch[curPos * 2])) 
				q.offer(new int[] {curPos * 2, curDis + 1});
		}
		
		System.out.println(min);
		System.out.println(cnt);
	}
}
