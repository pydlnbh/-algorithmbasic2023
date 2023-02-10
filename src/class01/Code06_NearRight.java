package src.class01;

import java.util.Arrays;

/**
 * 有序数组中找到<=num最右的位置
 * 
 * @author peiyiding
 *
 */
public class Code06_NearRight {

	/**
	 * 有序数组中找到<=num最右的位置
	 * 
	 * @param arr 有序数组
	 * @return int 位置下标
	 */
	public static int nearRightIndex(int[] arr, int num) {
		if (null == arr ||
			0 == arr.length) {
			return -1;
		}
		
		int index = -1;
		int left = 0;
		int right = arr.length - 1;
		int mid;
		
		while (left <= right) {
			mid = left + ((right - left) >> 1);
			
			if (num >= arr[mid]) {
				index = mid;
				left = mid + 1;					
			} else {
				right = mid - 1;
			}
		}
		
		return index;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int maxSize = 100;
		int maxValue = 100;
		int testTimes = 1000;
		boolean printFlag = false;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomSortedArray(maxSize, maxValue);
			
			int	randomNum = (Math.random() < 0.5 && 0 < arr.length) ? 
							arr[(int) (Math.random() * arr.length)] :
						    (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
			
			int ans1 = testNearRight(arr, randomNum);
			int ans2 = nearRightIndex(arr, randomNum);
			
			randomPrint(printFlag, arr, randomNum);
			
			if (ans1 != ans2) {
				System.out.println("Oops");
				System.out.println("num = " + randomNum);
				for (int j = 0; j < arr.length; j++) {
					String comma = j == arr.length - 1 ? "\n" : ", ";
					System.out.print(arr[j] + comma);
				}
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
				break;
			}
		}
		
		System.out.println("end");
	}
	
	/**
	 * 生成随机有序数组
	 * 
	 * @param maxSize 数组最大长度
	 * @param maxValue 数组元素最大值
	 * @return int[] 随机有序数组
	 */
	public static int[] generateRandomSortedArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) (Math.random() * maxSize)];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ((int) (Math.random() * maxValue) + 1) - ((int) (Math.random() * maxValue) + 1);
		}
		
		Arrays.sort(arr);
		
		return arr;
	}
	
	/**
	 * 随机打印
	 * 
	 * @param printFlag 打印标识
	 * @param arr 有序数组
	 * @param num 要查找的数值
	 */
	public static void randomPrint(boolean printFlag, int[] arr, int num) {
		if (printFlag && Math.random() < 0.5) {
			System.out.println("num = " + num);
			System.out.print("arr = ");
			for (int i = 0; i < arr.length; i++) {
				String comma = i == arr.length - 1 ? "\n" : ", ";
				System.out.print(arr[i] + comma);
			}
		}
	}
	
	/**
	 * 测试对数器方法
	 * 
	 * @param arr 有序数组
	 * @param num 需要小于的数
	 * @return int 位置下标
	 */
	public static int testNearRight(int[] arr, int num) {
		int index = -1;

		for (int i = 0; i < arr.length; i++) {
			if (num >= arr[i]) {
				index = i;
			}
		}
		
		return index;
	}
	
	/**
	 * main
	 * 
	 * @parm args 标准入参
	 */
	public static void main(String[] args) {
		test();
	}
	
}
