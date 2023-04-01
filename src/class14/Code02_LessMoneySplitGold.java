package src.class14;

import java.util.PriorityQueue;

import src.Solution;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板
 * 比如长度为20的金条，不管怎么切都要花费20个铜板，一群人想整分整块金条，怎么分最省铜板? 
 * 例如，给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60；再把长度50的金条分成20和30，花费50；一共花费110铜板
 * 但如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板
 * 输入一个数组，返回分割的最小代价
 */
public class Code02_LessMoneySplitGold {
	// solution one
	public static int lessMoney1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		
		return process(arr, 0);
	}
	
	// recursive method one
	public static int process(int[] arr, int pre) {
		if (arr.length == 1) {
			return pre;
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int[] next = copyAndMergeTwo(arr, i, j);
				ans = Math.min(ans, process(next, pre + arr[i] + arr[j]));
			}
		}
		
		return ans;
	}
	
	// auxiliary method
	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		
		int index = 0;
		for (int k = 0; k < arr.length; k++) {
			if (k != i && k != j) {
				ans[index++] = arr[k];
			}
		}
		
		ans[index] = arr[i] + arr[j];
		
		return ans;
	}
	
	// solution two
	public static int lessMoney2(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int num : arr) {
			heap.add(num);
		}
		
		int ans = 0;
		int cur = 0;
		while (heap.size() > 1) {
			cur = heap.poll() + heap.poll();
			ans += cur;
			heap.add(cur);
		}
		
		return ans;
	}
	
	// for test
	public static int[] generateRandomArray(int maxLength, int maxValue) {
		int[] ans = new int[(int) (Math.random() * maxLength) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * maxValue) + 1;
		}
		
		return ans;
	}
	
	// for test
	public static void test() {
		int maxValue = 10;
		int maxLength = 6;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLength, maxValue);
			
			int ans1 = lessMoney1(arr);
			int ans2 = lessMoney2(arr);
		
			if (ans1 != ans2) {
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
