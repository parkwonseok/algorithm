package algotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
	static int L, C;
	static char[] arr;
	static char[] res;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		res = new char[L];
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < C; i++) arr[i] = st.nextToken().charAt(0);
	
		Arrays.sort(arr);
		
		DFS(0, 0);
		
		System.out.println(sb.toString());
	}

	private static void DFS(int cnt, int start) {
		if(cnt == L) {
			int mo = 0, ja = 0;
			for(int i = 0; i < L; i++) {
				if(res[i] == 'a' || res[i] == 'u' || res[i] == 'e' || res[i] == 'i' || res[i] == 'o') 
					mo++;
				else 
					ja++;
			}
	
			if(mo >= 1 && ja >= 2) {
				for(int i = 0; i < L; i++) {
					sb.append(res[i]);
				}
				sb.append("\n");
			}
	
			return;
		}
		
		for(int i = start; i < C; i++) {
			res[cnt] = arr[i];
			DFS(cnt + 1, i + 1);
		}
	}
}
