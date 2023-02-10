package src.class03;

/**
 * 用递归行为得到数组中的最大值，并用master公式来估计时间复杂度
 * 
 * Master公式:
 * 形如 T(N) = a * T(n/b) + O(N^d) 的递归函数, 可以直接用Master公式来确定时间复杂度
 * 如果 Log(b)(a) < d , 时间复杂度就是 O(N^d)
 * 如果 Log(b)(a) > d, 时间复杂度就是O(N^Log(b)(a))
 * 如果 Log(b)(a) = d, 时间复杂度就是O(N^d * Log(2)(N))
 * Tips: Log(b)(a) ==> 以b为底a的对数
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
