package src.class20;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净， 返回从开始等到所有咖啡机变干净的最短时间 三个参数：int[] arr、int N，int a、int b
 */
public class Code03_Coffee {
	// auxiliary class
	public static class Machine {
		public int timePoint;
		public int workTime;
		
		public Machine(int t, int w) {
			timePoint = t;
			workTime = w;
		}
	}
	
	// comparator
	public static class MachineComparator implements Comparator<Machine> {
		@Override
		public int compare(Machine o1, Machine o2) {
			return o1.timePoint + o1.workTime - (o2.timePoint + o2.workTime);
		}
	}
	
	// solution one
	public static int minTime1(int[] arr, int n, int a, int b) {
		PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
		for (int num : arr) {
			heap.add(new Machine(0, num));
		}
		
		int[] drink = new int[n];
		
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drink[i] = cur.timePoint;
			heap.add(cur);
		}
		
		return bestTimes1(drink, a, b, 0, 0);
	}
	
	// solution one
	public static int bestTimes1(int[] drink, int wash, int air, int index, int free) {
		if (index == drink.length) {
			return 0;
		}
		
		int selfClean1 = Math.max(drink[index], free) + wash;
		int restClean1 = bestTimes1(drink, wash, air, index + 1, selfClean1);
		int p1 = Math.max(selfClean1, restClean1);
		
		int selfClean2 = drink[index] + air;
		int restClean2 = bestTimes1(drink, wash, air, index + 1, free);
		int p2 = Math.max(selfClean2, restClean2);
		
		return Math.min(p1, p2);
	}
	
	// solution two
	public static int minTime2(int[] arr, int n, int a, int b) {
		PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
		for (int num : arr) {
			heap.add(new Machine(0, num));
		}
		
		int[] drink = new int[n];
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drink[i] = cur.timePoint;
			heap.add(cur);
		}
		
		return bestTimes2(drink, a, b);
	}
	
	// dynamic program version
	public static int bestTimes2(int[] drink, int wash, int air) {
		int N = drink.length;
		int maxFree = 0;
		for (int i = 0; i < N; i++) {
			maxFree = Math.max(maxFree, drink[i]) + wash;
		}
		
		int[][] dp = new int[N + 1][maxFree + 1];
		for (int index = N - 1; index >= 0; index--) {
			for (int free = 0; free <= maxFree; free++) {
				int selfClean1 = Math.max(drink[index], free) + wash;
				if (selfClean1 > maxFree) {
					continue;
				}
				
				int restClean1 = dp[index + 1][selfClean1];
				int p1 = Math.max(selfClean1, restClean1);
				
				int selfClean2 = drink[index] + air;
				int restClean2 = dp[index + 1][free];
				int p2 = Math.max(selfClean2, restClean2);
				dp[index][free] = Math.min(p1, p2);
			}
		}
		
		return dp[0][0];
	}
	
	// for test
	public static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		
		return arr;
	}
	
	// for test
	public static int randomNumber(int max) {
		return (int) (Math.random() * max) + 1;
	}

	// main
	public static void main(String[] args) {
		int len = 10;
		int max = 10;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = randomArray(len, max);
			int n = randomNumber(max);
			int a = randomNumber(max);
			int b = randomNumber(max);
			
			int ans1 = minTime1(arr, n, a, b);
			int ans2 = minTime2(arr, n, a, b);
			
			if (ans1 != ans2) {
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}

}
