package src.class14;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲，给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多，返回最多的宣讲场次
 */
public class Code01_BestArrange {
	// auxiliary class
	public static class Program {
		public int start;
		public int end;
		
		public Program(int s, int e) {
			start = s;
			end = e;
		}
	}
	
	// solution one
	public static int bestArrange1(Program[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		return process(arr, 0, 0);
	}
	
	// recursive method one
	public static int process(Program[] arr, int done, int time) {
		if (arr.length == 0) {
			return done;
		}
		
		int max = done;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].start >= time) {
				Program[] next = copyButExcept(arr, i);
				max = Math.max(max, process(next, done + 1, arr[i].end));
			}
		}
		
		return max;
	}
	
	// auxiliary method
	public static Program[] copyButExcept(Program[] arr, int index) {
		Program[] ans = new Program[arr.length - 1];
		
		int i = 0;
		for (int j = 0; j < arr.length; j++) {
			if (j != index) {
				ans[i++] = arr[j];
			}
		}
		
		return ans;
	}
	
	// auxiliary comparator
	public static class MyComparator implements Comparator<Program> {
		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}
	}
	
	// solution two
	public static int bestArrange2(Program[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		Arrays.sort(arr, new MyComparator());
		
		int ans = 0;
		int time = 0;
		for (int i = 0; i < arr.length; i++) {
			if (time <= arr[i].start) {
				ans++;
				time = arr[i].end;
			}
		}
		
		return ans;
	}
	
	// for test
	public static Program[] generateTestArray(int maxLength, int maxValue) {
		Program[] ans = new Program[(int) (Math.random() * maxLength) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			int num1 = (int) (Math.random() * maxValue) + 1;
			int num2 = (int) (Math.random() * maxValue) + 1;
			
			if (num1 == num2) {
				ans[i] = new Program(num1, num2 + 1);
			} else {
				int min = Math.min(num1, num2);
				int max = Math.max(num1, num2);
				
				ans[i] = new Program(min, max);	
			}
		}
		
		return ans;
	}
	
	// for test
	public static Program[] copyArray(Program[] arr) {
		Program[] ans = new Program[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			Program cur = new Program(arr[i].start, arr[i].end);
			ans[i] = cur;
		}
		
		return ans;
	}
	
	// for test
	public static void test() {
		int maxValue = 20;
		int maxLength = 20;
		int testTimes = 100000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			Program[] arr = generateTestArray(maxLength, maxValue);
			Program[] copyArray1 = copyArray(arr);
			Program[] copyArray2 = copyArray(arr);
			
			int ans1 = bestArrange1(copyArray1);
			int ans2 = bestArrange2(copyArray2);
			
			if (ans1 != ans2) {
				for (int j = 0; j < arr.length; j++) {
					System.out.println("start: " + arr[j].start + ", end: " + arr[j].end);
				}
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
