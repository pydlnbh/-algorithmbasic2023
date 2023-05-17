package src.class25;

// 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
// 测试链接：https://leetcode.com/problems/maximal-rectangle/
public class Code04_MaximalRectangle {
	// solution
	public static int maximalRectangle(char[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		
		int maxArea = 0;
		int[] height = new int[map[0].length];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
			}
			
			maxArea = Math.max(maxArea, maxRecFromBottom(height));
		}
		
		return maxArea;
	}
	
	public static int maxRecFromBottom(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int size = arr.length;
		int ans = Integer.MIN_VALUE;
		int[] stack = new int[size];
		int stackSize = 0;
		
		for (int i = 0; i < arr.length; i++) {
			while (stackSize != 0 && arr[stack[stackSize - 1]] >= arr[i]) {
				int j = stack[--stackSize];
				int left = stackSize == 0 ? -1 : stack[stackSize - 1];
				ans = Math.max(ans, (i - left - 1) * arr[j]);
			}
			stack[stackSize++] = i;
		}
		
		while (stackSize != 0) {
			int j = stack[--stackSize];
			int left = stackSize == 0 ? -1 : stack[stackSize - 1];
			ans = Math.max(ans, (size - left - 1) * arr[j]);
		}
		
		return ans;
	}
	
	// main
	public static void main(String[] args) {
		char[][] map = {
		    {'1','0','1','0','0'},
		    {'1','0','1','1','1'},
		    {'1','1','1','1','1'},
		    {'1','0','0','1','0'}};
		int ans = maximalRectangle(map);
		System.out.println(ans);
	}
}
