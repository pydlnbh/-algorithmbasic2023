package src.class04;

/**
 * 在一个数组中，对于任何一个数num，求有多少个(后面的数*2)依然<num，返回总个数
 * 比如：[3,1,7,0,2] 
 * 3的后面有：1，0 
 * 1的后面有：0
 * 7的后面有：0，2 
 * 0的后面没有 
 * 2的后面没有 
 * 所以总共有5个
 * 
 * @author peiyiding
 *
 */
public class Code04_BiggerThanRightTwice {
	
	/**
	 * 在一个数组中，对于任何一个数num，求有多少个(后面的数*2)依然<num，返回总个数
	 * 
	 * @param arr 数组
 	 * @return int 返回值
	 */
	public static int solution(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		
		return process(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归求解
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
	 * 归并方法求
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param mid 下标
	 * @param right 下标
	 * @return int 返回值
	 */
	public static int merge(int[] arr, int left, int mid, int right) {
		int r = mid + 1;
		int ans = 0;
		
		for (int i = left; i <= mid; i++) {
			while (r <= right && arr[i] > (arr[r] << 1)) {
				r++;
			}
			ans += r - 1 - mid;
		}
		
		int[] help = new int[right - left + 1];
		int i = 0;
		int p1 = left;
		int p2 = mid + 1;
		
		while (p1 <= mid && p2 <= right) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		
		while (p2 <= right) {
			help[i++] = arr[p2++];
		}
		
		for (i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
		
		return ans;
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxLength 最大长度
	 * @param maxValue 最大值
	 * @return int 返回值
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
	 * @return int 返回值
	 */
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	/**
	 * 测试方法
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
				if (arr[i] > (arr[j] << 1)) {
					ans += 1;
				}
			}
		}
		
		return ans;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int maxLength = 100;
		int maxValue = 1000;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLength, maxValue);
			int[] copyArray = copyArray(arr);
			
			int ans1 = solution(arr);
			int ans2 = testMethod(copyArray);
			
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
	
	public static int merge01(int[] arr, int left, int mid, int right)  {
		// 先处理数值
		int ans = 0;
		int r = mid + 1;
		
		for (int i = left; i <= mid; i++) {
			while (r <= right && arr[i] > (arr[r] << 1)) {
				r++;
			}
			ans += r - 1 - mid;
		}
		
		int[] help = new int[right - left + 1];
		int i = 0;
		int p1 = left;
		int p2 = mid + 1;
		
		while (p1 <= mid && p2 <= right) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		
		while (p2 <= right) {
			help[i++] = arr[p2++];
		}
		
		for (i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
		
		return ans;
	}
}
