package src.class06;

import java.util.Arrays;

/**
 * 堆排序的实现
 * 
 * @author peiyiding
 *
 */
public class Code02_HeapSort {
	/**
	 * 堆排序的实现, 时间复杂度: O(N)*Log(N)
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}

		// 时间复杂度: O(N)*Log(N)
//		for (int i = 0; i < arr.length; i++) {
//			heapInsert(arr, i);
//		}
		
		// 时间复杂度: O(N)
		for (int i = arr.length - 1; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
		
		int heapSize = arr.length;
		
		// 时间复杂度: O(N)*Log(N)
		while (heapSize > 0) {
			swap(arr, 0, --heapSize);
			heapify(arr, 0, heapSize);
		}
	}
	
	/**
	 * 使插进来的数往下比较, 小数放下面
	 * 
	 * @param arr 数组
	 * @param index 下标
	 */
	public static void heapInsert(int[] arr, int index) {
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}
	
	/**
	 * 使大根堆第一个数, 在heapSize下往下沉
	 * 
	 * @param arr 数组
	 * @param index 下标
	 * @param heapSize 堆大小
	 */
	public static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		while (left < heapSize) {
			int max = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
			max = arr[index] > arr[max] ? index : max;
			
			if (max == index) {
				break;
			}
			
			swap(arr, index, max);
			index = max;
			left = index * 2 + 1;
		}
	}
	
	/**
	 * 交换函数
	 * 
	 * @param arr 数组
	 * @param i 下标
	 * @param j 下标
	 */
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
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
			arr[i] = ((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1);
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
		int maxSize = 100;
		int maxValue = 100;
		int testTimes = 1000;
		boolean printFlag = false;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			
			heapSort(arr1);
			Arrays.sort(arr2);
			
			randomPrint(printFlag, arr1, arr2);
			
			if (!isEqual(arr1, arr2)) {
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
