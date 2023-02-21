package src.class05;

/**
 * 给定一个数组arr，两个整数lower和upper，
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 * Leetcode题目：https://leetcode.com/problems/count-of-range-sum/
 * 
 * @author peiyiding
 *
 */
public class Code01_CountOfRangeSum {
	
	/**
	 * 解题方法
	 * 
	 * @param arr 数组
	 */
	public static int solution(int[] arr, int lower, int upper) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		
		long[] sum = new long[arr.length];
		sum[0] = arr[0];
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}
		
		return process(sum, 0, arr.length, lower, upper);
	}
	
	public static int process(long[] arr, int left, int right, int lower, int upper) {
		if (left == right) {
			return arr[left] >= lower && arr[left] <= upper ? 1 : 0;
		}
		
		int mid = left + ((right - left) >> 1);
		int leftCount = process(arr, left, mid, lower, upper);
		int rightCount = process(arr, mid + 1, right, lower, upper);
		int mergeCount = mergeCount(arr, left, mid, right, lower, upper);
		
		return leftCount + rightCount + mergeCount;
	}
	
	public static int mergeCount(long[] arr, int left, int mid, int right, int lower, int upper) {
		int windowL = left;
		int windowR = left;
		int ans = 0;
		
		for (int i = mid + 1; i <= right; i++) {
			long min = arr[i] - upper;
			long max = arr[i] - lower;
			
			while (windowL <= mid && arr[windowL] < min) {
				windowL++;
			}
			
			while (windowR <= mid && arr[windowR] <= max) {
				windowR++;
			}
			
			ans += windowR - windowL;
		}
		
		long[] help = new long[right - left + 1];
		int i = 0;
		int p1 = left;
		int p2 = mid + 1;
		
		while (p1 <= mid && p2 <= right) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		
		while (p2 <= right) {
			help[i++] = arr[p2++];
		}
		
		for (i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
		
		return ans;
	}
}
