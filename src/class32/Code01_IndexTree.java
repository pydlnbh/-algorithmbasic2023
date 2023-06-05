package src.class32;

/**
 * IndexTree在一维数组和二维数组上的实现
 */
public class Code01_IndexTree {
	// solution
	public static class IndexTree {
		private int[] tree;
		private int N;
		
		public IndexTree(int size) {
			N = size;
			tree = new int[N + 1];
		}
		
		public int sum(int index) {
			int res = 0;
			while (index > 0) {
				res += tree[index];
				index -= index & -index;
			}
			return res;
		}
		
		public void add(int index, int d) {
			while (index <= N) {
				tree[index] += d;
				index += index & -index;
			}
		}
	}
	
	// for test
	public static class Right {
		private int[] nums;
		private int N;
		
		public Right(int size) {
			N = size + 1;
			nums = new int[N + 1];
		}
		
		public int sum(int index) {
			int res = 0;
			for (int i = 1; i <= index; i++) {
				res += nums[i];
			}
			return res;
		}
		
		public void add(int index, int d) {
			nums[index] += d;
		}
	}
	
	// solution 2D
	public static class IndexTree2D {
		private int[][] tree;
		private int[][] nums;
		private int N;
		private int M;
		
		public IndexTree2D(int[][] martix) {
			if (martix == null || martix.length == 0
			    || martix[0] == null || martix[0].length == 0) {
				return;
			}
			
			N = martix.length;
			M = martix[0].length;
			tree = new int[N + 1][M + 1];
			nums = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					update(i, j, martix[i][j]);
				}
			}
		}
		
		private int sum(int row, int col) {
			int res = 0;
			
			for (int i = row + 1; i > 0; i -= i & -i) {
				for (int j = col + 1; j > 0; j -= j & -j) {
					res += tree[i][j];
				}
			}
			
			return res;
		}
		
		public void update(int row, int col, int val) {
			if (N == 0 || M == 0) {
				return;
			}
			
			int add = val - nums[row][col];
			nums[row][col] = val;
			
			for (int i = row + 1; i <= N; i += i & -i) {
				for (int j = col + 1; j <= N; j += j & -j) {
					tree[i][j] += add;
				}
			}
		}
		
		public int sumRegion(int row1, int col1, int row2, int col2) {
			if (N == 0 || M == 0) {
				return 0;
			}
			
			return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
		}
	}
	
	// for test
	public static void test() {
		int N = 100;
		int V = 100;
		int testTimes = 100000;
		
		IndexTree tree = new IndexTree(N);
		Right test = new Right(N);
		
		System.out.println("start");
		for (int i = 0; i < testTimes; i++) {
			int index = (int) (Math.random() * N) + 1;
			
			if (Math.random() <= 0.5) {
				int add = (int) (Math.random() * V);
				tree.add(index, add);
				test.add(index, add);
			} else {
				if (tree.sum(index) != test.sum(index)) {
					System.out.println("Oops");
				}
			}
		}
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		test();
	}
}
