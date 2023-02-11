package src.class02;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，找到这种数
 * 
 * @author peiyiding
 *
 */
public class Code02_OneOddTimes {
	
	/**
	 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，找到这种数
	 * 
	 * @param arr 数组
	 * @return int 出现奇数次的数
	 */
	public static int oddTimesNum(int[] arr) {
		int ans = 0;
		
		for (int num : arr) {
			ans ^= num;
		}
				
		return ans;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int range = 100;
		int maxOdd = 100;
		int maxEven = 100;
		int testTimes = 1000;
		boolean printFlag = false;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxOdd, maxEven, range);
			
			int ans1 = oddTimesNum(arr);
			int ans2 = testOddTimesNum(arr);
			
			randomPrint(arr, ans1, ans2, printFlag);
			
			if (ans1 != ans2) {
				System.out.println("Oops");

				for (int j = 0; j < arr.length; j++) {
					String comma = j == arr.length - 1 ? ", " : "\n";
					System.out.print(j + comma);
				}
				
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
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
		
		int len = oddTimes + evenTimes *  evenNum;
		
		int[] arr = new int[len];
		
		int oddTimesNum = generateRandomNum(range);
		
		int index = 0;
		for (; index < oddTimes; index++) {
			arr[index] = oddTimesNum;
		}
		
		for (; index < arr.length;) {
			oddTimesNum = generateRandomNum(range);
			
			for (int i = 0; i < evenTimes; i++) {
				arr[index++] = oddTimesNum;
			}
		}
		
//		for (int j = 0; j < arr.length; j++) {
//			int k = (int) (Math.random() * arr.length);
//			int temp = arr[j];
//			arr[j] = arr[k];
//			arr[k] = arr[j];
//		}
		
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
	public static int testOddTimesNum(int[] arr) {
		int ans = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int iNum : arr) {
			if (map.containsKey(iNum)) {
				map.put(iNum, map.get(iNum) + 1);
			} else {
				map.put(iNum, 1);
			}
		}
		
		for (int key : map.keySet()) {
			if (map.get(key) % 2 != 0) {
				ans = key;
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
	public static void randomPrint(int[] arr, int ans1, int ans2, boolean printFlag) {
		if (Math.random() < 0.5 && printFlag) {
			
			System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
			
			for (int num : arr) {
				System.out.print(num + " ");
			}
			
			System.out.println();
		}
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
