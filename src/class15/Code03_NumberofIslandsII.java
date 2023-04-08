package src.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 岛问题, 一次给一个坐标
public class Code03_NumberofIslandsII {
	// solution one
	public static List<Integer> numIslands(int m, int n, int[][] positions) {
		Union u = new Union(m, n);
		
		List<Integer> ans = new ArrayList<>();
		for (int[] position : positions) {
			ans.add(u.connect(position[0], position[1]));
		}
		
		return ans;
	}
	
	// auxiliary class one
	public static class Union {
		public int[] parent;
		public int[] size;
		public int[] help;
		public int sets;
		public int row;
		public int col;
		
		public Union(int m, int n) {
			int length = m * n;
			parent = new int[length];
			size = new int[length];
			help = new int[length];
			sets = 0;
			col = m;
			row = n;
		}
		
		public int index(int r, int c) {
			return r * col + c;
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
		
		public void union(int r1, int c1, int r2, int c2) {
			if (r1 < 0 || r1 == row 
			 || r2 < 0 || r2 == row
			 || c1 < 0 || c1 == col
			 || c2 < 0 || c2 == col) {
				return;
			}
			
			int index1 = index(r1, c1);
			int index2 = index(r2, c2);
			int p1 = find(index1);
			int p2 = find(index2);
			
			if (p1 != p2) {
				int s1 = size[p1];
				int s2 = size[p2];
				
				int big = s1 > s2 ? p1 : p2;
				int small = p1 == big ? p2 : p1;
				
				parent[small] = big;
				size[big] = s1 + s2;
				sets--;
			}
		}
		
		public int connect(int r, int c) {
			int index = index(r, c);
			
			if (size[index] == 0) {
				parent[index] = index;
				size[index] = 1;
				sets++;
				union(r - 1, c, r, c);
				union(r + 1, c, r, c);
				union(r, c - 1, r, c);
				union(r, c + 1, r, c);
			}
			
			return sets;
		}
	}
	
	// solution two
	public static List<Integer> numIslands1(int m, int n, int[][] positions) {
		Union1 u = new Union1();
		
		List<Integer> ans = new ArrayList<>();
		for (int[] position : positions) {
			ans.add(u.connect(position[0], position[1]));
		}
		
		return ans;
	}
	
	// auxiliary class two
	public static class Union1 {
		public Map<String, String> parent;
		public Map<String, Integer> size;
		public List<String> help;
		public int sets;
		
		public Union1() {
			parent = new HashMap<>();
			size = new HashMap<>();
			help = new ArrayList<>();
			sets = 0;
		}
		
		public String find(String s) {
			if (!s.equals(parent.get(s))) {
				help.add(s);
				s = parent.get(s);
			}
			
			for (String str : help) {
				parent.put(str, s);
			}
			
			help.clear();
			
			return s;
		}
		
		public void union(String a, String b) {
			if (parent.containsKey(a) && parent.containsKey(b)) {
				String p1 = find(a);
				String p2 = find(b);
				
				if (!p1.equals(b)) {
					int s1 = size.get(p1);
					int s2 = size.get(p2);
					
					String big = s1 > s2 ? p1 : p2;
					String small = big == p1 ? p2 : p1;
					
					parent.put(small, big);
					size.put(big, s1 + s2);
					sets--;
				}
			}
		}
		
		public int connect(int r, int c) {
			String key = String.valueOf(r) + "_" + String.valueOf(c);
			
			if (!parent.containsKey(key)) {
				parent.put(key, key);
				size.put(key, 1);
				sets++;
				String up = String.valueOf(r - 1) + "_" + String.valueOf(c);
				String down = String.valueOf(r + 1) + "_" + String.valueOf(c);
				String left = String.valueOf(r) + "_" + String.valueOf(c - 1);
				String right = String.valueOf(r) + "_" + String.valueOf(c + 1);
				union(up, key);
				union(down, key);
				union(left, key);
				union(right, key);
			}
			
			return sets;
		}
	}
}
