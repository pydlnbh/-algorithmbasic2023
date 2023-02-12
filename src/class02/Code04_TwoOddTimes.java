package src.class02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，找到这两种数
 * 
 * @author peiyiding
 *
 */
public class Code04_TwoOddTimes {
	
	/**
	 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，找到这两种数
	 * 
	 * @param arr 数组
	 */
	public static int[] twoOddTimesNum(int[] arr) {
		int ans = 0;
		int eor = 0;
		
		for (int num : arr) {
			eor ^= num;
		}
		
		int rightOne = eor & (~eor + 1);
		
		for (int num : arr) {
			if ((rightOne & num) != 0) {
				ans ^= num;
			}
		}
		
		int[] iArr = new int[2];
		iArr[0] = ans;
		iArr[1] = ans ^ eor;
		
		return iArr; 
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int range = 100;
		int maxOdd = 10;
		int maxEven = 10;
		int testTimes = 1000;
		boolean printFlag = false;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxOdd, maxEven, range);
			
			int[] ans1 = twoOddTimesNum(arr);
			int[] ans2 = testTwoOddTimesNum(arr);
			
			randomPrint(arr, ans1, ans2, printFlag);
			
			if (!isEqual(ans1, ans2)) {
				System.out.println("Oops");
				
				printArray(ans1);
				
				printArray(ans2);

				for (int j = 0; j < arr.length; j++) {
					String comma = j == arr.length - 1 ? "\n" : ", ";
					System.out.print(arr[j] + comma);
				}
				
				break;
			}
		}
		
		System.out.println("end");
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param maxOdd 奇数次最大值
	 * @param maxEven 偶数次最大值
	 * @param range 数值最大值
	 * @return int 出现奇数次的最大值
	 */
	public static int[] generateRandomArray(int maxOdd, int maxEven, int range) {
		int oddTimes = (int) (Math.random() * maxOdd) + 1;
		oddTimes = oddTimes % 2 == 0 ? ++oddTimes : oddTimes; 
		
		int evenTimes = (int) (Math.random() * maxEven) + 1;
		evenTimes = evenTimes % 2 == 0 ? evenTimes : ++evenTimes;
		
		int evenNum = (int) (Math.random() * maxEven) + 1;
		
		int len = oddTimes * 2 + evenTimes *  evenNum;
		
		int[] arr = new int[len];
		
		int index = 0;
		int randomNum = 0;
		
		randomNum = generateRandomNum(range);
		
		for (; index < oddTimes; index++) {				
			arr[index] = randomNum;
		}
		
		do {
			randomNum = generateRandomNum(range);	
		} while (randomNum == arr[oddTimes - 1]);
		
		for (int i = 0; i < oddTimes; i++) {				
			arr[index++] = randomNum;
		}
		
		for (; index < arr.length;) {
			randomNum = generateRandomNum(range);
			
			for (int i = 0; i < evenTimes; i++) {
				arr[index++] = randomNum;
			}
		}
		
		for (int j = 0; j < arr.length; j++) {
			int k = (int) (Math.random() * arr.length);
			int temp = arr[j];
			arr[j] = arr[k];
			arr[k] = temp;
		}
		
		return arr;
	}
	
	/**
	 * 生成随机数值
	 * 
	 * @param range 最大值
	 * @return int 随机数值
	 */
	public static int generateRandomNum(int range) {
		return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
	}
	
	/**
	 * 检查一个数组中有一种数出现了奇数次
	 * 
	 * @param arr 数组
	 * @return int 出现奇数次的数   
	 */
	public static int[] testTwoOddTimesNum(int[] arr) {
		int[] ans = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int iNum : arr) {
			if (map.containsKey(iNum)) {
				map.put(iNum, map.get(iNum) + 1);
			} else {
				map.put(iNum, 1);
			}
		}
		
		int index = 0;
		
		for (int key : map.keySet()) {
			if (map.get(key) % 2 != 0) {
				ans[index] = key;
				index++;
			}
		}
		
		return ans;
	}
	
	/**
	 * 随机打印
	 * 
	 * @param arr 数组
	 * @param ans1 结果1
	 * @param ans2 结果2
	 * @param printFlag 是否打印标识
	 */
	public static void randomPrint(int[] arr, int[] ans1, int[] ans2, boolean printFlag) {
		if (Math.random() < 0.5 && printFlag) {
			
			printArray(ans1);
			
			printArray(ans2);
			
			for (int num : arr) {
				System.out.print(num + " ");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 打印数组
	 * 
	 * @param arr 数组
	 */
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			String comma = i == arr.length - 1 ? "\n" : ", ";
			System.out.print(arr[i] + comma);
		}
	}
	
	/**
	 * 判断两个数组是否相等
	 * 
	 * @param arr1 数组1
	 * @param arr2 数组2
	 * @return boolean 是否相等
	 */
	public static boolean isEqual(int[] arr1, int[] arr2) {	
		Arrays.sort(arr1);
		
		Arrays.sort(arr2);
		
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] == arr2[i]) {
				return true;
			}
		}
		
		return false;
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
