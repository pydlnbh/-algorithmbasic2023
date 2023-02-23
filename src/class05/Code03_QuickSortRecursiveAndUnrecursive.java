package src.class05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 快排的递归版和费递归版
 * 
 * @author peiyiding
 *
 */
public class Code03_QuickSortRecursiveAndUnrecursive {
	
	/**
	 * 快排递归版
	 * 
	 * @param arr 数组
	 */
	public static void quickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		process(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 */
	public static void process(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		
		swap(arr, left + ((int) (Math.random() * (right - left + 1))), right);
		
		int[] mid = netherLandsFlag(arr, left, right);
		process(arr, left, mid[0] - 1);
		process(arr, mid[1] + 1, right);
	}
	
	/**
	 * 荷兰国旗问题
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return int[] 返回值
	 */
	public static int[] netherLandsFlag(int[] arr, int left, int right) {
		if (left > right) {
			return new int[] {-1, -1};
		}
		
		if (left == right) {
			return new int[] {left, right};
		}
		
		int lessL = left - 1;
		int lessR = right;
		int index = left;
		
		while (index < lessR) {
			if (arr[index] == arr[right]) {
				index++;
			} else if (arr[index] < arr[right]) {
				swap(arr, index++, ++lessL);
			} else {
				swap(arr, index, --lessR);
			}
		}
		
		swap(arr, right, lessR);
		
		return new int[] {lessL + 1, lessR};
	}
	
	public static class Op {
		private int left;
		private int right;
		
		public Op(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	/**
	 * 快排非递归版(使用栈)
	 * 
	 * @param arr 数组
	 */
	public static void quickSort01(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		swap(arr, ((int) (Math.random() * (arr.length - 1))), arr.length - 1);
		int[] mid = netherLandsFlag01(arr, 0, arr.length - 1);
		Stack<Op> stack = new Stack<>();
		stack.push(new Op(0, mid[0] - 1));
		stack.push(new Op(mid[1] + 1, arr.length - 1));

		while (!stack.isEmpty()) {
			Op cur = stack.pop();
			if (cur.left < cur.right) {
				swap(arr, cur.left + ((int) (Math.random() * (cur.right - cur.left + 1))), cur.right);
				mid = netherLandsFlag01(arr, cur.left, cur.right);
				stack.push(new Op(cur.left, mid[0] - 1));
				stack.push(new Op(mid[1] + 1, cur.right));
			}
		}
	}
	
	/**
	 * 荷兰国旗问题
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return int[] 返回值
	 */
	public static int[] netherLandsFlag01(int[] arr, int left, int right) {
		if (left > right) {
			return new int[] {-1, -1};
		}
		
		if (left == right) {
			return new int[] {left, right};
		}
		
		int lessL = left - 1;
		int lessR = right;
		int index = left;
		
		while (index < lessR) {
			if (arr[index] == arr[right]) {
				index++;
			} else if (arr[index] < arr[right]) {
				swap(arr, index++, ++lessL);
			} else {
				swap(arr, index, --lessR);
			}
		}
		
		swap(arr, right, lessR);
		
		return new int[] {lessL + 1, lessR};
	}
	
	/**
	 * 快排非递归版 (使用队列)
	 * 
	 * @param arr 数组
	 */
	public static void quickSort02(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		swap(arr, (int) (Math.random() * arr.length), arr.length - 1);
		int[] mid = netherLandsFlag02(arr, 0, arr.length - 1);
		Queue<Op> op = new LinkedList<>();
		op.offer(new Op(0, mid[0] - 1));
		op.offer(new Op(mid[1] + 1, arr.length - 1));
		
		while (!op.isEmpty()) {
			Op cur = op.poll();
			
			if (cur.left < cur.right) {
				swap(arr, cur.left + (int) (Math.random() * (cur.right - cur.left + 1)), cur.right);
				mid = netherLandsFlag(arr, cur.left, cur.right);
				op.offer(new Op(cur.left, mid[0] - 1));
				op.offer(new Op(mid[1] + 1, cur.right));
			}
		}
	}
	
	/**
	 * 荷兰国旗问题
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return int[] 返回值
	 */
	public static int[] netherLandsFlag02(int[] arr, int left, int right) {
		if (left > right) {
			return new int[] {-1, -1};
		}
		
		if (left == right) {
			return new int[] {left, right};
		}
		
		int lessL = left - 1;
		int lessR = right;
		int index = left;
		
		while (index < lessR) {
			if (arr[index] == arr[right]) {
				index++;
			} else if (arr[index] < arr[right]) {
				swap(arr, index++, ++lessL);
			} else {
				swap(arr, index, --lessR);
			}
		}
		
		swap(arr, right, lessR);
		
		return new int[] {lessL + 1, lessR};
	}
	
	/**
	 * 交换方法
	 * 
	 * @param arr 数组
	 * @param i 下标
	 * @param j 下标
	 */
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxLength 最大长度
	 * @param maxValue 最大值
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArray(int maxLength, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxLength)];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
		}
		
		return arr;
	}
	
	/**
	 * 拷贝数组
	 * 
	 * @param arr 数组
	 * @return int[] 拷贝数组
	 */
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	/**
	 * 判断两个数组是否相等
	 * 
	 * @param arr1 数组
	 * @param arr2 数组
	 * @return boolean 返回值
	 */
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) ||
			(arr1 != null && arr2 == null)) {
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

	/**
	 * 对数器
	 */
	public static void test() {
		int maxLength = 1000;
		int maxValue = 10000;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLength, maxValue);
			int[] copyArray = copyArray(arr);
			int[] copyArray1 = copyArray(arr);
			int[] copyArray2 = copyArray(arr);
			
			Arrays.sort(arr);
			quickSort(copyArray);
			quickSort01(copyArray1);
			quickSort02(copyArray2);
			
			if (!isEqual(arr, copyArray) ||
				!isEqual(arr, copyArray1) ||
				!isEqual(arr, copyArray2)) {
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
