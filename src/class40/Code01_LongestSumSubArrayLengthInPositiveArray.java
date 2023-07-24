package src.class40;

// 给定一个正整数组成的无序数组arr，给定一个正整数值K，找到arr的所有子数组里，哪个子数组的累加和等于K并且是长度最大的，返回其长度
public class Code01_LongestSumSubArrayLengthInPositiveArray {
	// solution one
	public static int getMaxLength(int[] arr, int k) {
		if (arr == null 
		    || arr.length == 0 
		    || k <= 0) {
		    return 0;
		}
		
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		
		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		
		return len;
	}
	
	// for test
	public static int right(int[] arr, int k) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (valid(arr, i, j, k)) {
					max = Math.max(max, j - i + 1);
				}
			}
		}
		return max;
	}
	
	// for test
	public static boolean valid(int[] arr, int l, int r, int k) {
		int sum = 0;
		for (int i = l; i <= r; i++) {
			sum += arr[i];
		}
		return sum == k;
	}
	
	// for test
	public static int[] generatePositiveArray(int size, int value) {
		int[] ans = new int[size];
		for (int i = 0; i < size; i++) {
			ans[i] = (int) (Math.random() * value) + 1;
		}
		return ans;
	}
	
	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	// for test
	public static void test() {
		int len = 50;
		int value = 100;
		int testTimes = 100000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generatePositiveArray(len, value);
			int k = (int) (Math.random() * value) + 1;
			int ans1 = getMaxLength(arr, k);
			int ans2 = right(arr, k);
			
			if (ans1 != ans2) {
				System.out.println("Oops");
				printArray(arr);
				System.out.println("k = " + k);
				System.out.println(ans1);
				System.out.println(ans2);
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
