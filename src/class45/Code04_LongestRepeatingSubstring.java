package src.class45;

import src.class44.DC3;

//一个非常经典的题
//这道题课上没有讲
//后缀数组的模版题
//需要学会DC3算法生成后缀数组
//需要学会课上讲的如何生成高度数组
//时间复杂度O(N)，连官方题解都没有做到的时间复杂度，但这才是最优解
//测试链接 : https://leetcode.cn/problems/longest-repeating-substring/
public class Code04_LongestRepeatingSubstring {
	// solution
	public static int longestRepeatingSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		char[] str = s.toCharArray();
		int n = str.length;
		int min = str[0];
		int max = str[0];

		for (int i = 1; i < n; i++) {
			min = Math.min(min, str[i]);
			max = Math.max(max, str[i]);
		}

		int[] all = new int[n];

		for (int i = 0; i < n; i++) {
			all[i] = str[i] - min + 1;
		}

		DC3 dc3 = new DC3(all, max - min + 1);
		int ans = 0;

		for (int i = 1; i < n; i++) {
			ans = Math.max(ans, dc3.height[i]);
		}

		return ans;
	}

	// 为了测试, 不用提交
	public static String randomString(int n, int r) {
		char[] str = new char[n];
		for (int i = 0; i < n; i++) {
			str[i] = (char) ((int) (Math.random() * r) + 'a');
		}
		return String.valueOf(str);
	}

	// 为了测试, 不用提交
	public static void main(String[] args) {
		int n = 500000;
		int r = 3;
		long start = System.currentTimeMillis();
		longestRepeatingSubstring(randomString(n, r));
		long end = System.currentTimeMillis();
		System.out.println("字符长度为 " + n + ", 字符种类数为 " + r + " 时");
		System.out.println("求最长重复子串的运行时间 : " + (end - start) + " 毫秒");
	}
}
