package src.class05;

import java.util.Arrays;

import src.Solution;

/**
 * 快速排序从1.0到3.0的实现
 * 
 * @author peiyiding
 *
 */
public class Code02_QuickSort {
	
	/**
	 * 快排1.0, 时间复杂度O(n^2)
	 * 
	 * @param arr 数组
	 */
	public static void quickSort01(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		process01(arr, 0, arr.length - 1);
	}

	/**
	 * 递归方法1.0
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 */
	public static void process01(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		
		// 快排核心代码
		int mid = partition(arr, left, right);
		process01(arr, left, mid - 1);
		process01(arr, mid + 1, right);
	}
	
	/**
	 * 快排核心代码
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return int 返回值
	 */
	public static int partition(int[] arr, int left, int right) {
		if (left > right) {
			return -1;
		}
		
		if (left == right) {
			return left;
		}
		
		int lessEqual = left - 1;
		int index = left;
		
		while (index < right) {
			if (arr[index] <= arr[right]) {
				swap(arr, index, ++lessEqual);
			}
			index++;
		}
		swap(arr, ++lessEqual, right);
		return lessEqual;
	}
	
	/**
	 * 快速排序2.0
	 * 
	 * @param arr 数组
	 */
	public static void quickSort02(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		process02(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 */
	public static void process02(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		
		int[] mid = nearNationFlag02(arr, left, right);
		process02(arr, left, mid[0] - 1);
		process02(arr, mid[1] + 1, right);
	}
	
	/**
	 * 荷兰国旗问题
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return int[] 返回值
	 */
	public static int[] nearNationFlag02(int[] arr, int left, int right) {
		if (left > right) {
			return new int[] {-1, -1};
 		}
		
		if (left == right) {
			return new int[] {left, right};
		}
		
		int lessL = left - 1;
		int more = right;
		int index = left;
		
		while (index < more) {
			if (arr[index] == arr[right]) {
				index++;
			} else if (arr[index] < arr[right]) {
				swap(arr, index++, ++lessL);
			} else {
				swap(arr, index, --more);
			}
		}
		swap(arr, right, more);
		return new int[] {++lessL, more};
	}
	
	/**
	 * 真正的快排, 时间复杂度: O(N * Log(N))
	 * @param arr
	 */
	public static void quickSort(int[] arr) {
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
		if (left >= right) {
			return;
		}
		
		swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
		
		int[] mid = netherLandsFlag(arr, left, right);
		process(arr, left, mid[0] - 1);
		process(arr, mid[1] + 1, right);
	}
	
	/**
	 * 荷兰国旗问题
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return 返回值
	 */
	public static int[] netherLandsFlag(int[] arr, int left, int right) {
		if (left > right) {
			return new int[] {-1, -1};
		}
		
		if (left == right) {
			return new int[] {left, right};
		}
		
		int lessL = left - 1;
		int more = right;
		int index = left;
		
		while (index < more) {
			if (arr[index] == arr[right]) {
				index++;
			} else if (arr[index] < arr[right]) {
				swap(arr, index++, ++lessL);
			} else {
				swap(arr, index, --more);
			}
		}
		
		swap(arr, right, more);
		
		return new int[] {lessL + 1, more};
	}
	
	/**
	 * 交换方法
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
	 * @param maxLength 最大长度
	 * @param maxValue 最大值
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArray(int maxLength, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxLength)];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
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
	 * 判断两个数组是否相等
	 * 
	 * @param arr1 数组
	 * @param arr2 数组
	 * @return boolean 返回值
	 */
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) ||
			(arr1 != null && arr2 == null)) {
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
		int maxLength = 100;
		int maxValue = 10000;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLength, maxValue);
			int[] copyArray1 = copyArray(arr);
			int[] copyArray2 = copyArray(arr);
			
//			quickSort01(arr);
//			quickSort02(arr);
			quickSort(copyArray1);
			Arrays.sort(copyArray2);
			
			if (!isEqual(copyArray1, copyArray2)) {
				Solution.printArray(arr);
				System.out.println("---");
				Solution.printArray(copyArray1);
				System.out.println("---");
				Solution.printArray(copyArray2);
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
