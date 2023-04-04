package src.class14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// 并查集的实现
public class Code05_UnionFind {
	// solution one
	public static class UnionFind1<V> {
		public Map<V, V> parentMap;
		public Map<V, Integer> sizeMap;
		
		public UnionFind1(List<V> values) {
			parentMap = new HashMap<>();
			sizeMap = new HashMap<>();
			
			for (V cur : values) {
				parentMap.put(cur, cur);
				sizeMap.put(cur, 1);
			}
		}
		
		public V findParent(V obj) {
			Stack<V> path = new Stack<>();
			
			while (obj != parentMap.get(obj)) {
				path.push(obj);
				obj = parentMap.get(obj);
			}
			
			while (!path.isEmpty()) {
				parentMap.put(path.pop(), obj);
			}
			
			return obj;
		}
		
		public boolean isSameSet(V obj1, V obj2) {
			return findParent(obj1) == findParent(obj2);
		}
		
		public void union(V obj1, V obj2) {
			V parent1 = parentMap.get(obj1);
			V parent2 = parentMap.get(obj2);
			
			if (parent1 != parent2) {
				int size1 = sizeMap.get(parent1);
				int size2 = sizeMap.get(parent2);
				
				V big = size1 > size2 ? parent1 : parent2;
				V small = big == parent1 ? parent2 : parent1;
				
				parentMap.put(small, big);
				sizeMap.put(big, size1 + size2);
				sizeMap.remove(small);
			}
		}
		
		public int size() {
			return sizeMap.size();
		}
		
		// solution two
		public static class UnionFind2 {
			public static int MAXN = 1000001;
			public static int[] parent = new int[MAXN];
			public static int[] size = new int[MAXN];
			public static int[] help = new int[MAXN];
			
			public static void init(int n) {
				for (int i = 0; i <= n; i++) {
					parent[i] = i;
					size[i] = 1;
				}
			}
			
			public static int find(int i) {
				int hi = 0;
				
				while (i != parent[i]) {
					help[hi++] = i;
					i = parent[i];
				}
				
				for (hi--; hi >= 0; hi--) {
					parent[help[i]] = i; 
				}
				
				return i;
			}
			
			public static boolean isSameSet(int x, int y) {
				return find(x) == find(y);
			}
			
			public static void union(int x, int y) {
				int p1 = parent[x];
				int p2 = parent[y];
				
				while (p1 != p2) {
					int size1 = size[p1];
					int size2 = size[p2];
					
					int big = size1 > size2 ? p1 : p2;
					int small = big == p1 ? p2 : p1;
					
					parent[small] = big;
					size[big] = size1 + size2;
				}
			}
		}
		
		// for test
		public static void test() throws Exception{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StreamTokenizer in = new StreamTokenizer(br);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
			
			while (in.nextToken() != StreamTokenizer.TT_EOF) {
				int n = (int) in.nval;
				UnionFind2.init(n);
				in.nextToken();
				int m = (int) in.nval;
				
				for (int i = 0; i < m; i++) {
					in.nextToken();
					int op = (int) in.nval;
					in.nextToken();
					int x = (int) in.nval;
					in.nextToken();
					int y = (int) in.nval;
					
					if (op == 1) {
						out.print(UnionFind2.isSameSet(x, y) ? "yes" : "no");
						out.flush();
					} else {
						UnionFind2.union(x, y);
					}
				}
			}
		}
		
		// main
		public static void main(String[] args) {
			try {
				test();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
