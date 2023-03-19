package src.class04;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和 标记 所有数的小和累加起来，叫数组小和 
 * 例子： [1,3,4,2,5] 1左边比1小的数：
 * 没有3左边比3小的数：1 4左边比4小的数：1、3 2左边比2小的数：1 5左边比5小的数：1、3、4、2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16 给定一个数组arr，求数组小和
 *
 * @author peiyiding
 *
 */
public class Code02_SamllNum {
	
	/**
	 * 数组小和, 时间复杂度: O(n * log(n))
	 */
	public static int smallSum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		return recursionMethod(arr, 0, arr.length - 1);
	}
	
	/**
	 * 递归方法
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param right 下标
	 * @return int 返回值
	 */
	public static int recursionMethod(int[] arr, int left, int right) {
		if (left == right) {
			return 0;
		}
		
		int mid = left + ((right - left) >> 1);
		
		return recursionMethod(arr, left, mid) +
			   recursionMethod(arr, mid + 1, right) +
			   merge(arr, left, mid, right);
	}
	
	/**
	 * 归并方法并计算小和
	 * 
	 * @param arr 数组
	 * @param left 下标
	 * @param mid 下标
	 * @param right 下标
	 * @return int 返回值
	 */
	public static int merge(int[] arr, int left, int mid, int right) {
		int[] help = new int[right - left + 1];
		int i = 0;
		int p1 = left;
		int p2 = mid + 1;
		int ans = 0;
		
		while (p1 <= mid && p2 <= right) {
			ans += arr[p1] < arr[p2] ? (right - p2 + 1) * arr[p1] : 0;
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
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
	 * 测试方法, 时间复杂度: O(n^2)
	 * 
	 * @param arr 数组
	 * @return int 数组最小和
	 */
	public static int testMethod(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		
		int ans = 0;
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				ans += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		
		return ans;
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxLength 数组最大长度
	 * @param maxValue 最大值
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArray(int maxLength, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxLength) + 1];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
		}
		
		return arr;
	}
	
	/**
	 * 拷贝数组
	 * 
	 * @param arr 数组
	 * @return int[] 数组
	 */
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}

	/**
	 * 对数器
	 */
	public static void test() {
		int maxLength = 100;
		int maxValue = 1000;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxLength, maxValue);
			int[] arr1 = copyArray(arr);
			
			int ans1 = smallSum(arr);
			int ans2 = testMethod(arr1);
			
			if (ans1 != ans2) {	
				for (int num : arr) {
					System.out.print(num + " ");
				}
				
				System.out.println("\nans1 = " + ans1 + ", ans2 = " + ans2);
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
