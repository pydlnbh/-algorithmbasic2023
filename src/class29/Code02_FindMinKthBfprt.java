package src.class29;

import java.util.Comparator;
import java.util.PriorityQueue;

import src.Solution;

/**
 * 使用 bfprt 算法在无序数组中找到第 K 小的数
 */
public class Code02_FindMinKthBfprt {
	// solution one
	public static int minKth1(int[] arr, int k) {
//		if (arr == null || arr.length == 0) {
//			return -1;
//		}
//		
//		PriorityQueue<Integer> heap = new PriorityQueue<>(new MinKthComparator());
//		
//		for (int i = 0; i < k; i++) {
//			heap.add(arr[i]);
//		}
//		
//		for (int i = k; k < arr.length; i++) {
//			if (heap.peek() > arr[i]) {
//				heap.poll();
//				heap.add(arr[i]);	
//			}
//		}
//		
//		return heap.peek();
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MinKthComparator());
		
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
	
	public static class MinKthComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
	
	// solution two
	public static int minKth2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		int[] ans = copy(arr);
		
		return bfprt(ans, 0, arr.length - 1, k - 1);
	}
	
	public static int[] copy(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	public static int bfprt(int[] arr, int left, int right, int index) {
		if (left == right) {
			return arr[left];
		}
		
		int pivot = medianOfMedians(arr, left, right);
		int[] range = partition(arr, left, right, pivot);
		
		if (index >= range[0] && index <= range[1]) {
			return arr[index];
		} else if (index < range[0]) {
			return bfprt(arr, left, range[0] - 1, index);
		} else {
			return bfprt(arr, range[1] + 1, right, index);
		}
	}
	
	public static int medianOfMedians(int[] arr, int left, int right) {
		int size = right - left + 1;
		int offset = size % 5 == 0 ? 0 : 1;
		int[] mArr = new int[size / 5 + offset];
		
		for (int team = 0; team < mArr.length; team++) {
			int teamFirst = left + team * 5;
			mArr[team] = getMedian(arr, teamFirst, Math.min(right, teamFirst + 4));
		}
		
		return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
	}
	
	public static int getMedian(int[] arr, int left, int right) {
		insertionSort(arr, left, right);
		return arr[(left + right) / 2];
	}
	
	public static void insertionSort(int[] arr, int left, int right) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
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
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxSize) + 1];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
		}
		
		return arr;
	}
	
	// for test
	public static void test() {
		int maxSize = 100;
		int maxValue = 1000;
		int testTimes = 1000;
		
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
	
	// main
	public static void main(String[] args) {
		test();
	}
}
