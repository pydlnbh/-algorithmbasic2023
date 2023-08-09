package src.class45;

import src.class44.DC3;

public class Code01_InsertS2MakeMostAlphabeticalOrder {
	// solution one
	public static String right(String s1, String s2) {
		if (s1 == null || s1.length() == 0) {
			return s2;
		}
		
		if (s2 == null || s2.length() == 0) {
			return s1;
		}
		
		String p1 = s1 + s2;
		String p2 = s2 + s1;
		
		String ans = p1.compareTo(p2) > 0 ? p1 : p2;
		
		for (int end = 1; end < s1.length(); end++) {
			String cur = s1.substring(0, end) 
			    + s2 
			    + s1.substring(end);
			
			if (cur.compareTo(ans) > 0) {
				ans = cur;
			}
		}
		
		return ans;
	}
	
	// solution two
	public static String maxCombine(String s1, String s2) {
		if (s1 == null || s1.length() == 0) {
			return s2;
		}
		
		if (s2 == null || s2.length() == 0) {
			return s1;
		}
		
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		
		int N = str1.length;
		int M = str2.length;
		
		int min = str1[0];
		int max = str1[0];
		
		for (int i = 1; i < N; i++) {
			min = Math.min(min, str1[i]);
			max = Math.max(max, str1[i]);
		}
		
		for (int i = 0; i < M; i++) {
			min = Math.min(min, str2[i]);
			max = Math.max(max, str2[i]);
		}
		
		int[] all = new int[N + M + 1];
		int index = 0;
		
		for (int i = 0; i < N; i++) {
			all[index++] = str1[i] - min + 2;
		}
		
		all[index++] = 1;
		
		for (int i = 0; i < M; i++) {
			all[index++] = str2[i] - min + 2;
		}
		
		DC3 dc3 = new DC3(all, max - min + 2);
		int[] rank = dc3.rank;
		int comp = N + 1;
		
		for (int i = 0; i < N; i++) {
			if (rank[i] < rank[comp]) {
				int best = bestSplit(s1, s2, i);
				return s1.substring(0, best)
				    + s2
				    + s1.substring(best);
			}
		}
		
		return s1 + s2;
	}
	
	public static int bestSplit(String s1, String s2, int first) {
		int N = s1.length();
		int M = s2.length();
		int end = N;
		
		for (int i = first, j = 0; i < N && j < M; i++, j++) {
			if (s1.charAt(i) < s2.charAt(j)) {
				end = i;
				break;
			}
		}
		
		String bestPrefix = s2;
		int bestSplit = first;
		
		for (int i = first + 1, j = M - 1; i <= end; i++, j--) {
			String curPrefix = s1.substring(first, i)
			    + s2.substring(0, j);
			
			if (curPrefix.compareTo(bestPrefix) >= 0) {
				bestPrefix = curPrefix;
				bestSplit = i;
			}
		}
		
		return bestSplit;
	}
	
	// for test
	public static String randomNumberString(int len, int range) {
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ((int) (Math.random() * range) + '0');
		}
		return String.valueOf(str);
	}
	
	// for test
	public static void main(String[] args) {
		int range = 10;
		int len = 50;
		int testTime = 100000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int s1Len = (int) (Math.random() * len);
			int s2Len = (int) (Math.random() * len);
			String s1 = randomNumberString(s1Len, range);
			String s2 = randomNumberString(s2Len, range);
			String ans1 = right(s1, s2);
			String ans2 = maxCombine(s1, s2);
			if (!ans1.equals(ans2)) {
				System.out.println("Oops!");
				System.out.println(s1);
				System.out.println(s2);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("功能测试结束");

		System.out.println("==========");

		System.out.println("性能测试开始");
		int s1Len = 1000000;
		int s2Len = 500;
		String s1 = randomNumberString(s1Len, range);
		String s2 = randomNumberString(s2Len, range);
		long start = System.currentTimeMillis();
		maxCombine(s1, s2);
		long end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " ms");
		System.out.println("性能测试结束");
	}
}
