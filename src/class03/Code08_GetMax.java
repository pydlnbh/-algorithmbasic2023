package src.class03;

/**
 * 用递归行为得到数组中的最大值，并用master公式来估计时间复杂度
 * 
 * Master公式:
 * 形如 T(N) = a * T(n/b) + O(N^d) 的递归函数, 可以直接用Master公式来确定时间复杂度
 * 如果 Log(b)(a) < d , 时间复杂度就是 O(N^d)
 * 如果 Log(b)(a) > d, 时间复杂度就是O(N^Log(b)(a))
 * 如果 Log(b)(a) = d, 时间复杂度就是O(N^d * Log(2)(N))
 * Tips: Log(b)(a) ==> 以b为底a的对数
 * 
 * @author peiyiding
 *
 */
public class Code08_GetMax {
	
	/**
	 * 获得数组中的最大值
	 */
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param arr 数组
	 * @param left 最左侧下标
	 * @param right 最右侧下标
	 * @return int 最大值
	 */
	public static int process(int[] arr, int left, int right) {
		// 递归的base case
		if (left == right) {
			return arr[left];
		}
		
		int mid = left + ((right - left) >> 1);
		int leftMax = process(arr, left, mid);
		int rightMax = process(arr, mid + 1, right);
		return Math.max(leftMax, rightMax);
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxValue 最大值
	 * @param maxLength 最大长度
	 * @reutrn int[] 随机数组
	 */
	public static int[] generateRandomArray(int maxValue, int maxLength) {
		int[] ans = new int[(int) (Math.random() * maxLength) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
		}
		
		return ans;
	}
	
	/**
	 * 对数器测试方法
	 * 
	 * @param arr 数组
	 */
	public static int testGetMax(int[] arr) {
		int ans = arr[0];
		for (int num : arr) {
			ans = Math.max(ans, num);
		}
		
		return ans;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int maxValue = 100;
		int maxLength = 100;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxValue, maxLength);
			
			int ans1 = getMax(arr);
			int ans2 = testGetMax(arr);
			
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
