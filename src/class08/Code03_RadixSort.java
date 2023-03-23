package src.class08;

import java.util.Arrays;

import src.Solution;

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
	
	/**
	 * 取出数组最大数的10进制位数
	 * 
	 * @param arr 数组
	 * @return int 最大位数
	 */
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
	
	/**
	 * 基数排序
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @param digit 位数
	 */
	public static void radixSort(int[] arr, int left, int right, int digit) {
		// 表示10进制
		final int radix = 10;
		int i = 0, j = 0;
		
		// 辅助数组
		int[] help = new int[right - left  + 1];
		
		for (int d = 1; d <= digit; d++) {
			
			int[] count = new int[radix];
			
			for (i = left; i <= right; i++) {
				j = getDigit(arr[i], d);
				count[j]++;
			}
			
			for (i = 1; i < radix; i++) {
				count[i] = count[i - 1] + count[i];
			}
			
			for (i = right; i >= left; i--) {
				j = getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			
			for (i = left, j = 0; i <= right; i++, j++) {
				arr[i] = help[j];
			}
		}
	}
	
	/**
	 * 获取当前位数的数
	 * 
	 * @param num 数字
	 * @param d 当前位数
	 * @return int 数值
	 */
	public static int getDigit(int num, int d) {
		return ((num / ((int) (Math.pow(10, d - 1)))) % 10);
	}
	
	/**
	 * 交换函数
	 *
	 * @param arr 数组
	 * @param i 下标i
	 * @param j 下标j
	 */
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxSize 数组长度最大值
	 * @param maxValue 数组元素最大值
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxSize)];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ((int) (Math.random() * maxValue) + 1);
		}
		
		return arr;
	}
	
	/**
	 * 拷贝数组
	 * 
	 * @param arr 源数组
	 * @return int[] 目标数组
	 */
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	/**
	 * 随机打印
	 * 
	 * @param printFalg 打印标识
	 * @param arr1 数组1
	 * @param arr2 数组2
	 */
	public static void randomPrint(boolean printFlag, int[] arr1, int[] arr2) {
		if (printFlag && Math.random() < 0.5) {
			for (int i = 0; i < arr1.length; i++) {
				String comma = i == arr1.length - 1 ? "\n" : ", ";
				System.out.print(arr1[i] + comma);
			}
			
			
			for (int j = 0; j < arr2.length; j++) {
				String comma = j == arr2.length - 1 ? "\n" : ", ";
				System.out.print(arr2[j] + comma);
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 比较两个数组
	 * 
	 * @param arr1 数组1
	 * @param arr2 数组2
	 * @return boolean 是否相等
	 */
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((null == arr1 && null != arr2) || (null != arr1 && null == arr2)) {
			return false;
		}
		
		if (null == arr1 && null == arr2) {
			return true;
		}
		
		if (arr1.length != arr2.length) {
			return false;
		}
		
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int maxSize = 5;
		int maxValue = 100;
		int testTimes = 1000;
		boolean printFlag = false;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr1);
			
			radixSort(arr2);
			Arrays.sort(arr3);
			
			randomPrint(printFlag, arr2, arr3);
			
			if (!isEqual(arr2, arr3)) {
				Solution.printArray(arr1);
				Solution.printArray(arr2);
				Solution.printArray(arr3);
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	/**
	 * main
	 * 
	 * @param args 标准入参
	 */
	public static void main(String[] args) {
		test();
	}
}
