package src.class03;

/**
 * 用递归行为得到数组中的最大
 * 
 * @author peiyiding
 *
 */
public class Code08_GetMax {
	
	/**
	 * 获得数组中的最大值
	 */
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param arr 数组
	 * @param left 最左侧下标
	 * @param right 最右侧下标
	 * @return int 最大值
	 */
	public static int process(int[] arr, int left, int right) {
		// 递归的base case
		if (left == right) {
			return arr[left];
		}
		
		int mid = left + ((right - left) >> 1);
		int leftMax = process(arr, 0, mid);
		int rightMax = process(arr, mid + 1, right);
		return Math.max(leftMax, rightMax);
	}
	
	
}
