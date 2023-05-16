package src.class25;

import java.util.Stack;

// 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
public class Code03_LargestRectangleInHistogram {
	// solution one 
	public static int largestRectangleArea1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		
		int size = arr.length;
		Stack<Integer> stack = new Stack<>();
		int ans = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
				int j = stack.pop();
				int left = stack.isEmpty() ? -1 : stack.peek();
				ans = Math.max(ans, (i - left - 1) * arr[j]);
			}
			stack.push(i);
		}
		
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int left = stack.isEmpty() ? -1 : stack.peek();
			ans = Math.max(ans, (size - left - 1) * arr[j]);
		}
		
		return ans;
	}
	
	// solution two
	public static int largestRectangleArea2(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		
		int size = arr.length;
		int[] stack = new int[size];
		int stackSize = 0;
		int ans = Integer.MIN_VALUE;
		
		for (int i = 0; i < size; i++) {
			while (stackSize != 0 && arr[i] <= arr[stack[stackSize - 1]]) {
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
	
	public static void main(String[] args) {
		int[] arr = new int[] {2,1,5,6,2,3};
		System.out.println(largestRectangleArea1(arr));
		System.out.println(largestRectangleArea2(arr));

	}

}
