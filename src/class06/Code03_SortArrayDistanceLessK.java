package src.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k
 * k相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组进行排序。
 * 
 * @author peiyiding
 *
 */
public class Code03_SortArrayDistanceLessK {
	/**
	 * 解题方法
	 * 
	 * @param arr 数组
	 * @param k 距离
	 */
	public static void solution(int[] arr, int k) {
		// 边界条件
		if (k == 0) {
			return;
		}
		
		// 使用PriorityQueue最小堆对象
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int index = 0;
		
		// 先把k-1个数放入堆中
		for (; index <= Math.min(arr.length - 1, k - 1); index++) {
			heap.add(arr[index]);
		}
		
		// 再加一个弹一个
		int i = 0;
		for (; index < arr.length; i++, index++) {
			heap.add(arr[index]);
			arr[i] = heap.poll();
		}
		
		// 如果剩不到k个, 依次添加
		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxSize 数组长度最大值
	 * @param maxValue 数组元素最大值
	 * @param k 距离
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArrayNoMoveMoreK(int maxSize, int maxValue, int k) {
		int[] arr = new int[(int) (Math.random() * maxSize)];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1);
		}
		
		Arrays.sort(arr);
		
		boolean[] isSwap = new boolean[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			int j = Math.min(i + (int) (Math.random() * (k + 1)), arr.length - 1);
			if (!isSwap[i] && !isSwap[j]) {
				isSwap[i] = true;
				isSwap[j] = true;
				
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
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
			int k = (int) (Math.random() * maxSize) + 1;
			int[] arr1 = generateRandomArrayNoMoveMoreK(maxSize, maxValue, k);
			int[] arr2 = copyArray(arr1);
			
			solution(arr1, k);
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
