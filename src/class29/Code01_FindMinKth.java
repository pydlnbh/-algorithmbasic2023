package src.class29;

import java.util.Comparator;
import java.util.PriorityQueue;

import src.Solution;

public class Code01_FindMinKth {
	// solution one
	public static class MaxHeapComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
	
	public static int minKth1(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
		
		for (int i = 0; i < k; i++) {
			maxHeap.add(arr[i]);
		}
		
		for (int i = k; i < arr.length; i++) {
			if (arr[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.add(arr[i]);
			}
		}
		
		return maxHeap.peek();
	}
	
	// solution two
	public static int minKth2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		int[] copyArr = copyArray(arr);
		
		return process(copyArr, 0, copyArr.length - 1, k - 1);
	}
	
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	public static int process(int[] arr, int left, int right, int k) {
		if (left == right) {
			return arr[left];
		}
		
		int pivot = arr[left + ((int) (Math.random() * (right - left + 1)))];
		int[] range = partition(arr, left, right, pivot);
		
		if (k >= range[0] && k <= range[1]) {
			return arr[k];
		} else if (k < range[0]) {
			return process(arr, left, range[0] - 1, k);
		} else {
			return process(arr, range[1] + 1, right, k);
		}
	}
	
	public static int[] partition(int[] arr, int left, int right, int pivot) {
		int lessL = left - 1;
		int more = right + 1;
		int index = left;
		
		while (index < more) {
			if (arr[index] == pivot) {
				index++;
			} else if (arr[index] < pivot) {
				swap(arr, index++, ++lessL);
			} else {
				swap(arr, index, --more);
			}
		}
		
		return new int[] {lessL + 1, more - 1};
	}
	
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	// for test
	public static void test() {
		int maxSize = 5;
		int maxValue = 10;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int k = (int) (Math.random() * arr.length) + 1;
			
			int ans1 = minKth1(arr, k);
			int ans2 = minKth2(arr, k);
			
			if (ans1 != ans2) {
				Solution.printArray(arr);
				System.out.println("k = " + k);
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (maxSize)];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
		}
		
		return arr;
	}
	
	// main
	public static void main(String[] args) {
		test();
	}
}
