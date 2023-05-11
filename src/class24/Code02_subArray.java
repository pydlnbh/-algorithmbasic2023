package src.class24;

import java.util.LinkedList;

import src.Solution;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class Code02_subArray {
	// solution one
	public static int right(int[] arr, int num) {
		if (arr == null || arr.length == 0 || num < 0) {
			return 0;
		}
		
		int N = arr.length;
		int count = 0;
		
		for (int left = 0; left < N; left++) {
			for (int right = left; right < N; right++) {
				int max = arr[left];
				int min = arr[left];
				
				for (int i = left + 1; i <= right; i++) {
					max = Math.max(max, arr[i]);
					min = Math.min(min, arr[i]);
				}
				
				if (max - min <= num) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	// solution two
	public static int num(int[] arr, int num) {
		if (arr == null || arr.length == 0 || num < 0) {
			return 0;
		}
		
		LinkedList<Integer> minQueue = new LinkedList<>();
		LinkedList<Integer> maxQueue = new LinkedList<>();
		int right = 0;
		int count = 0;
		
		for (int left = 0; left < arr.length; left++) {
			while (right < arr.length) {
				while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[right]) {
					minQueue.pollLast();
				}
				minQueue.addLast(right);
				
				while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[right]) {
					maxQueue.pollLast();
				}
				maxQueue.addLast(right);
				
				if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
					break;
				} else {
					right++;
				}
			}
			
			count += right - left;
			
			if (minQueue.peekFirst() == left) {
				minQueue.pollFirst();
			}
			
			if (maxQueue.peekFirst() == left) {
				maxQueue.pollFirst();
			}
		}
		
		return count;
	}
	
	// for test
	public static int[] generateRandomArray(int len, int max) {
		int length = (int) (Math.random() * len);
		int[] arr = new int[length];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		
		return arr;
	}
	
	// for test
	public static void test() {
		int len = 5;
		int max = 10;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, max);
			int num = (int) (Math.random() * max);
			
			int ans1 = right(arr, num);
			int ans2 = num(arr, num);
			
			if (ans1 != ans2) {
				System.out.println("num = " + num);
				Solution.printArray(arr);
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
