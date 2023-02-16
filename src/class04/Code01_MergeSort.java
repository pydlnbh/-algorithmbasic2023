package src.class04;

import java.util.Arrays;

/**
 * 归并排序的递归和非递归实现
 * 
 * @author admin
 *
 */
public class Code01_MergeSort {
	
	/**
	 * 归并排序的递归实现
	 * 
	 * @param arr 数组
	 */
	public static void mergeSort01(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		process(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 */
	public static void process(int[] arr, int left, int right) {
		if (left == right) {
			return;
		}
		
		int mid = left + ((right - left) >> 1);
		process(arr, left, mid);
		process(arr, mid + 1, right);
		merge(arr, left, mid, right);
	}
	
	/**
	 * 合并排序
	 * 
	 * @param arr 数组 
	 * @param left 下标
	 * @param mid 下标
	 * @param right 下标
	 */
	public static void merge(int[] arr, int left, int mid, int right) {
		int[] help = new int[right - left + 1];
		int i = 0;
		int p1 = left;
		int p2 = mid + 1;
		
		while (p1 <= mid && p2 <= right) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
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
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxValue 最大值
	 * @param maxLength 最大长度
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArray(int maxValue, int maxLength) {
		int[] arr = new int[(int) (Math.random() * maxLength) + 1];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ((int) (Math.random() * maxValue)) - ((int) (Math.random() * maxValue));
		}
		
		return arr;
	}
	
	/**
	 * 拷贝数组
	 * 
	 * @param arr 数组
	 * @return int[] 拷贝数组
	 */
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	/**
	 * 判断两个是否是否相等
	 * 
	 * @param arr1 数组1
	 * @param arr2 数组2
	 * @return boolean 判断是否相等
	 */
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 != null && arr2 == null) ||
			(arr1 == null && arr2 != null)) {
			return false;
		}
		
		if (arr1 == null && arr2 == null) {
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
		int maxValue = 100;
		int maxLength = 5;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr0 = generateRandomArray(maxValue, maxLength);
			int[] arr1 = copyArray(arr0);
			
			mergeSort01(arr0);
			Arrays.sort(arr1);
			
			if (!isEqual(arr0, arr1)) {
				
				for (int num : arr0) {
					System.out.print(num + " ");
				}
				System.out.println();
				
				for (int num : arr1) {
					System.out.print(num + " ");
				}
				System.out.println();
				
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
