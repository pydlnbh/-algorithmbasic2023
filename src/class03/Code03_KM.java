package src.class03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code03_KM {
	
	/**
	 * 解题方法
	 * 
	 * @param arr 数组
	 * @param k 出现k次
	 * @param m 出现m次
	 * @return int 数字
	 */
	public static int onlyKTimes(int[] arr, int k, int m) {
		int[] t = new int[32];
		
		for (int num : arr) {
			for (int i = 0; i < 32; i++) {
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
	
	public static void main(String[] args) {
		test();
	}
	
	/**
	 * 对数器
	 */
	public static void test() {
		int max = 10;
		int range = 200;
		int numKindsMax = 20;
		int testTimes = 10;
		
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
			
			int ans1 = testOnlyKTimes(arr, k, m);
			int ans2 = onlyKTimes(arr, k, m);
			
			if (ans1 != ans2) {
				for (int num : arr) {
					System.out.print(num + ", ");
				}
				System.out.println();
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
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
		
		Set<Integer> set = new HashSet<>();
		set.add(kNum);
		
		while (numKinds > 0) {
			int curNum;
			do {
				curNum = generateRandomNum(range);
			} while (set.contains(curNum));
			
			set.add(curNum);
			
			numKinds--;
			
			for (int i = 0; i < m; i++) {
				arr[index++] = curNum;
			}
		}
		
		for (int j = 0; j < arr.length; j++) {
			int l = (int) (Math.random() * arr.length);
			int temp = arr[j];
			arr[j] = arr[l];
			arr[l] = temp;
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
		Map<Integer, Integer> testMap = new HashMap<>();
		
		for (int num : arr) {
			if (testMap.containsKey(num)) {
				testMap.put(num, testMap.get(num) + 1);
			} else {
				testMap.put(num, 1);
			}
		}
		
		int ans = 0;
		
		for (int num : arr) {
			if (k == testMap.get(num)) {
				ans = num;
				break;
			}
		}
		
		return ans;
	}
}
