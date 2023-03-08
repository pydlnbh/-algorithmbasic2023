package src.class08;

/**
 * 基数排序
 */
public class Code03_RadixSort {
	
	/**
	 * 基数排序
	 * 
	 * @param arr 数组
	 */
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		radixSort(arr, 0, arr.length - 1, maxBits(arr));
	}
	
	public static int maxBits(int[] arr) {
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(arr[i], max);
		}
		
		int ans = 0;
		
		while (max != 0) {
			ans++;
			max /= 10;
		}
		
		return ans;
	}
	
	public static void radixSort(int[] arr, int left, int right, int digit) {
		// 表示10进纸
		final int radix = 10;
		int i = 0, j = 0;
		
		int[] help = new int[right - left  + 1];
		for (int d = 1; d <= digit; d++) {
			int[] count = new int[digit];
			for (i = left; i <= right; i++) {
				j = getDigit(arr[i], d);
				count[j]++;
			}
			
			for (i = 1; i < radix; i++) {
				help[i] = count[i - 1] + count[i];
			}
			
			for (i = right; i >= 0; i--) {
				j = getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			
			for (i = left; i <= right; i++) {
				arr[i] = help[j];
			}
		}
	}
	
	public static int getDigit(int num, int d) {
		
	}
}
