package src.class02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次， 已知M > 1，K < M，找到出现了K次的数 要求额外空间复杂度O(1)，时间复杂度O(N)
 * 
 * @author peiyiding
 *
 */
public class Code05_KM {
	
	/**
	 * 一个数组中有一种数出现K次，其他数都出现了M次， 已知M > 1，K < M，找到出现了K次的数 要求额外空间复杂度O(1)，时间复杂度O(N)
	 * 
	 * @param arr 数组
	 * @param k 出现k次
	 * @param m 出现m次
	 * @return int 数字
	 */
	public static int onlyKTimes(int[] arr, int k, int m) {
		int[] t = new int[32];
		
		for (int i = 0; i < 32; i++) {
			for (int num : arr) {
				t[i] += (num >> i) & 1;
			}
		}	
		
		int ans = 0;
		
		for (int i = 0; i < 32; i++) {
			if ((t[i] % m) != 0) {
				ans |= (1 << i);
			}
		}
		
		return ans;
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int max = 10;
		int range = 100;
		int numKindsMax = 10;
		int testTimes = 100;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			int t1 = (int) (Math.random() * max) + 1;
			int t2 = (int) (Math.random() * max) + 1;
			
			int k = Math.min(t1, t2);
			int m = Math.max(t1, t2);
			
			if (k == m) {
				m = k + 1;
			}
			
			int[] arr = generateRandomArray(numKindsMax, range, k, m);
			
			int ans1 = onlyKTimes(arr, k, m);
			int ans2 = testOnlyKTimes(arr, k, m);
			
			if (ans1 != ans2) {
				System.out.println("Oops");
			}
		}
		
		System.out.println("end");
	}
	
	/**
	 * 生成随机数组
	 * 
	 * @param numKindsMax 不同数的个数 
	 * @param range 最大值
	 * @param k 出现k次
	 * @param m 出现m次
	 * @return int[] 随机数组
	 */
	public static int[] generateRandomArray(int numKindsMax, int range, int k, int m) {
		int kNum = generateRandomNum(range);
		
		int numKinds = (int) (Math.random() * numKindsMax) + 2;
		
		int[] arr = new int[k + (numKinds - 1) * m];
		
		int index = 0;
		for (; index < k; index++) {
			arr[index] = kNum;
		}
		
		numKinds--;
		
		Set<Integer> set = new HashSet<Integer>();
		set.add(kNum);
		
		while (numKinds > 0) {
			int randomNum = 0;
			do {
				randomNum = generateRandomNum(range);
			} while (set.contains(randomNum));
			
			numKinds--;
			set.add(randomNum);
			
			for (int i = 0; i < m; i++) {
				arr[index++] = randomNum;
			}
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
	 * 对数器方法
	 * 
	 * @param arr 数组
	 * @param k 出现k次
	 * @param m 出现m次
	 * @return int 出现k次的数
	 */
	public static int testOnlyKTimes(int[] arr, int k, int m) {
		int ans = 0;
		Map<Integer, Integer> testMap = new HashMap<>();
		
		for (int num : arr) {
			if (testMap.containsKey(num)) {
				testMap.put(num, testMap.get(num) + 1);
			} else {
				testMap.put(num, 1);
			}
		}
		
		for (int num : arr) {
			if ((testMap.get(num) % m) != 0) {
				ans = num;
				break;
			}
		}
		
		return ans;
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
