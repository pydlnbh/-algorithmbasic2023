package src.class25;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的子矩形数量
 *
 * 测试链接：https://leetcode.com/problems/count-submatrices-with-all-ones
 */
public class Code05_CountSubmatricesWithAllOnes {
	// solution one
	public static int numSubmat(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			return 0;
		}
		
		int nums = 0;
		int[] height = new int[mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
			}
			
			nums += countFromBottom(height);
		}
		
		return nums;
	}
	
	public static int countFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		
		int nums = 0;
		int size = height.length;
		int[] stack = new int[size];
		int stackSize = 0;
		
		for (int i = 0; i < size; i++) {
			while (stackSize != 0 && height[stack[stackSize - 1]] >= height[i]) {
				int cur = stack[--stackSize];
				if (height[cur] > height[i]) {
					int left = stackSize == 0 ? -1 : stack[stackSize - 1];
					int n = i - left - 1;
					int down = Math.max(left == -1 ? 0 : height[left], height[i]);
					nums += (height[cur] - down) * num(n);
				}
			}
			
			stack[stackSize++] = i;
		}
		
		while (stackSize != 0) {
			int cur = stack[--stackSize];
			int left = stackSize == 0 ? -1 : stack[stackSize - 1];
			int n = size - left - 1;
			int down = left == -1 ? 0 : height[left];
			nums += (height[cur] - down) * num(n);
		}
		
		return nums;
	}
	
	public static int num(int n) {
		return ((n * (1 + n)) >> 1);
	}
	
	// main
	public static void main(String[] args) {
		int[][] mat = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
		int ans = numSubmat(mat);
		System.out.println(ans);
	}
}
