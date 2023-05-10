package src.class24;

import java.util.LinkedList;

import src.Solution;

/**
 * 窗口内最大值或最小值更新结构的实现 假设一个固定大小为W的窗口，依次划过arr， 返回每一次滑出状况的最大值 例如，arr =
 * [4,3,5,4,3,3,6,7], W = 3 返回：[5,5,5,4,6,7]
 */
public class Code01_SlidingWindowMaxArray {
	// solution one
	public static int[] right(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		
		int N = arr.length;
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		int left = 0;
		int right = w - 1;
		
		while (right < N) {
			int max = arr[left];
			
			for (int i = left + 1; i <= right; i++) {
				max = Math.max(max, arr[i]);
			}
			
			res[index++] = max;
			left++;
			right++;
		}
		
		return res;
	}
	
	// solution two
	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		
		LinkedList<Integer> queue = new LinkedList<>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		
		for (int i = 0; i < arr.length; i++) {
			while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
				queue.pollLast();
			}
			
			queue.addLast(i);
			
			if (queue.peekFirst() == i - w) {
				queue.pollFirst();
			}
			
			if (i >= w - 1) {
				res[index++] = arr[queue.peekFirst()];
			}
		}
		
		return res;
	}
	
	// for test
	public static int[] generateRandomArray(int len, int max) {
		int length = (int) (Math.random() * len);
		int[] arr = new int[length];
		
		for (int i = 0; i < length; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		
		return arr;
	}
	
	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
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
	
	// for test
	public static void test() {
		int len = 50;
		int max = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, max);
			int num = (int) (Math.random() * len);
			
			int[] ans1 = right(arr, num);
			int[] ans2 = getMaxWindow(arr, num);
			
			if (!isEqual(ans1, ans2)) {
				Solution.printArray(ans1);
				Solution.printArray(ans2);
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}

	// main
	public static void main(String[] args) {
		test();
	}

}
