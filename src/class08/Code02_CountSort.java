package src.class08;

import java.util.Arrays;

import src.Solution;

/**
 * 计数排序
 */
public class Code02_CountSort {
	
	/**
	 * 计数排序, 需要特定的条件, 比如都是正数, 然后在一个区间内, 时间复杂度O(N)
	 * 
	 * @param arr 数组
	 */
	public static void countSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		
		int[] bucket = new int[max + 1];
		
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) {
				arr[i++] = j;
			}
		}
		
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
			arr[i] = (int) (Math.random() * maxValue) + 1;
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
			
			countSort(arr2);
//			Solution.solution(arr2);
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
