package src.class15;

// 一群朋友中，有几个不相交的朋友圈
// LeetCode题目：https://leetcode.com/problems/friend-circles/
public class Code01_FriendsCirle {
	// solution one
	public int findCircleNum(int[][] isConnected) {
		int N = isConnected.length;
		
		UnionFind union = new UnionFind(N);
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (isConnected[i][j] == 1) {
					union.union(i, j);	
				}
			}
		}
		
		return union.sets();
	}
	
	// auxiliary class
	public static class UnionFind {
		private int[] parent;
		private int[] size;
		private int[] help;
		private int sets;
		
		public UnionFind(int N) {
			parent = new int[N];
			size = new int[N];
			help = new int[N];
			sets = N;
			
			for (int i = 0; i < N; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}
		
		public int find(int i) {
			int index = 0;
			
			while (i != parent[i]) {
				help[index++] = i;
				i = parent[i];
			}
			
			for (index--; index >= 0; index--) {
				parent[help[index]] = i;
			}
			
			return i;
		}
		
		public void union(int x, int y) {
			int p1 = find(x);
			int p2 = find(y);
			
			if (p1 != p2) {
				int s1 = size[p1];
				int s2 = size[p2];
				
				int b = s1 > s2 ? p1 : p2;
				int s = p1 == b ? p2 : p1;
				
				parent[s] = b;
				size[b] = s1 + s2;
				sets--;
			}
		}
		
		public int sets() {
			return sets;
		}
	}
}
