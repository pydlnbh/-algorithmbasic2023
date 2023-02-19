package src.class04;

/**
 * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为降序对
 * 给定一个数组arr，求数组的降序对总数量
 * 
 * @author peiyiding
 * 
 */
public class Code03_ReversePair {

	/**
	 * 求数组的降序对总数量
	 * 
	 * @param arr 数组
	 * @return int 降序对数量
	 */
	public static int reversePair(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		
		return process(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return int 返回值
	 */
	public static int process(int[] arr, int left, int right) {
		if (left == right) {
			return 0;
		}
		
		int mid = left + ((right - left) >> 1);
		return process(arr, left, mid) +
				process(arr, mid + 1, right) +
				merge(arr, left, mid, right);
	}
	
	/**
	 * 归并方法并求逆序对数量
	 *  
	 * @param arr 数组
	 * @param left 下标
	 * @param mid 下标
	 * @param right 下标
	 * @return int 返回值
	 */
	public static int merge(int[] arr, int left, int mid, int right) {
		int[] help = new int[right - left + 1];
		int i = help.length - 1;
		int p1 = mid;
		int p2 = right;
		int ans = 0;
		
		while (p1 >= left && p2 > mid) {
			ans += arr[p2] < arr[p1] ? (p2 - mid) : 0;
			help[i--] = arr[p2] < arr[p1] ? arr[p1--] : arr[p2--];
		}
		
		while (p1 >= left) {
			help[i--] = arr[p1--];
		}
		
		while (p2 > mid) {
			help[i--] = arr[p2--];
		}
		
		for (i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
		
		return ans;
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxLength 数组最大长度
	 * @param maxValue 数组最大值
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
	 * @return int[] 随机数组
	 */
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	/**
	 * 对数器测试方法
	 * 
	 * @param arr 数组
	 * @return int 返回值
	 */
	public static int testMethod(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		
		int ans = 0;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans += arr[i] > arr[j] ? 1 : 0;
			}
		}
		
		return ans;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int maxLength = 100;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLength, maxValue);
			int[] testArr = copyArray(arr);
			
			int ans1 = reversePair(arr);
			int ans2 = testMethod(testArr);
			
			if (ans1 != ans2) {
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
