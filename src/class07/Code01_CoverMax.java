package src.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定很多线段，每个线段都有两个数[start, end]， 表示线段开始位置和结束位置，左右都是闭区间 规定：
 *  1）线段的开始和结束位置一定都是整数值
 * 	2）线段重合区域的长度必须>=1 返回线段最多重合区域中，包含了几条线段
 * 
 * @author peiyiding
 *
 */
public class Code01_CoverMax {
	/**
	 * 解题方法1
	 * 
	 * @param lines 二维数组
	 * @return int 重复的最大值
	 */
	public static int maxCover1(int[][] lines) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < lines.length; i++) {
			min = Math.min(min, lines[i][0]);
			max = Math.max(max, lines[i][1]);
		}
		
		int cover = 0;
		
		for (double p = min + 0.5; p < max; p+=1) {
			int cur = 0;
			
			for (int i = 0; i < lines.length; i++) {
				if (lines[i][0] < p && lines[i][1] > p) {
					cur++;
				}
			}
			
			cover = Math.max(cover, cur);
		}
		
		return cover;
	}
	
	/**
	 * 使用小根堆解题
	 * 
	 * @param lines 二维数组
	 * @return int 重复的最大值
	 */
	public static int maxCover2(int[][] lines) {
		Line[] lineArr = new Line[lines.length];
		
		for (int i = 0; i < lineArr.length; i++) {
			lineArr[i] = new Line(lines[i][0], lines[i][1]);
		}
		
		Arrays.sort(lineArr, new MyComparator());
		
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max = 0;
		
		for (int i = 0; i < lineArr.length; i++) {
			while (!heap.isEmpty() && heap.peek() <= lineArr[i].left) {
				heap.poll();
			}
			heap.add(lineArr[i].right);
			max = Math.max(max, heap.size());
		}
		
		return max;
	}
	
	/**
	 * 比较器
	 */
	public static class MyComparator implements Comparator<Line> {
		@Override
		public int compare(Line o1, Line o2) {
			return o1.left - o2.left;
		}
		
	}
	
	
	/**
	 * 解题辅助类
	 */
	static class Line {
		int left;
		int right;
		
		public Line(int l, int r) {
			left = l;
			right = r;
		}
	}
	
	/**
	 * 不使用辅助类解题
	 */
	public static int maxCover3(int[][] lines) {
		Arrays.sort(lines, (a, b) -> a[0] - b[0]);
		
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		int max = 0;
		for (int[] arr : lines) {
			while (!heap.isEmpty() && heap.peek() <= arr[0]) {
				heap.poll();
			}
			
			heap.add(arr[1]);
			max = Math.max(max, heap.size());
		}
		
		return max;
	}
	
	
	/**
	 * 生成随机二维数组
	 * 
	 * @param maxSize 数组大小
	 * @param L 下标
	 * @param R 下标
	 * @return int[][] 二维数组
	 */
	public static int[][] generateLines(int maxSize, int L, int R) {
		int size = (int) (Math.random() * maxSize) + 1;
		
		int[][] ans = new int[size][2];
		
		for (int i = 0; i < size; i++) {
			int a = L + (int) (Math.random() * (R - L + 1));
			int b = L + (int) (Math.random() * (R - L + 1));
			
			if (a == b) {
				b = a + 1;
			}
			
			ans[i][0] = Math.min(a, b);
			ans[i][1] = Math.max(a, b);
		}
		
		return ans;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int L = 0;
		int R = 200;
		int maxSize = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(maxSize, L, R);
			int ans1 = maxCover1(lines);
			int ans2 = maxCover2(lines);
			int ans3 = maxCover3(lines);
			
			if (ans1 != ans2 || ans1 != ans3) {
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
