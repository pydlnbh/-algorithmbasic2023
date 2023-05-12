package src.class24;

import java.util.LinkedList;

/**
 * 加油站的良好出发点问题
 * 测试链接：https://leetcode.com/problems/gas-station
 */
public class Code03_GasStation {
	// solution one
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		boolean[] good = goodArray(gas, cost);
		
		for (int i = 0; i < gas.length; i++) {
			if (good[i]) {
				return i;
			}
		}
		
		return -1;
	}
	
	// auxiliary class
	public static boolean[] goodArray(int[] gas, int[] cost) {
		int N = gas.length;
		int M = N << 1;
		int[] arr = new int[M];
		
		for (int i = 0; i < N; i++) {
			arr[i] = gas[i] - cost[i];
			arr[i + N] = gas[i] - cost[i];
		}
		
		for (int i = 1; i < M; i++) {
			arr[i] += arr[i - 1];
		}
		
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[i]) {
				queue.pollLast();
			}
			queue.addLast(i);
		}
		
		boolean[] ans = new boolean[N];
		for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
			if (arr[queue.peekFirst()] - offset >= 0) {
				ans[i] = true;
			}
			
			if (queue.peekFirst() == i) {
				queue.pollFirst();
			}
			
			while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[j]) {
				queue.pollLast();
			}
			
			queue.addLast(j);
		}
		
		return ans;
	}
	
	// main
	public static void main(String[] args) {
		
	}

}
