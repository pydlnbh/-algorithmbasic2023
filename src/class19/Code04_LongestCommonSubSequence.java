package src.class19;

/**
 * 给定两个字符串str1和str2， 返回这两个字符串的最长公共子序列长度 
 * 比如 ： str1 = “a12b3c456d”,str2 =
 * “1ef23ghi4j56k” 最长公共子序列是“123456”，所以返回长度6
 * 测试链接: https://leetcode.com/problems/longest-common-subsequence/
 */
public class Code04_LongestCommonSubSequence {
	// solution one
	public static int longestCommonSubsequence1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		
		return process1(s1, s2, s1.length() - 1, s2.length() - 1);
	}
	
	// recursive method one
	public static int process1(String s1, String s2, int i, int j) {
		if (i == 0 && j == 0) {
			return s1.charAt(0) == s2.charAt(0) ? 1 : 0;
		} else if (i == 0) {
			if (s1.charAt(i) == s2.charAt(j)) {
				return 1;
			} else {
				return process1(s1, s2, i, j - 1);
			}
		} else if (j == 0) {
			if (s1.charAt(i) == s2.charAt(j)) {
				return 1;
			} else {
				return process1(s1, s2, i - 1, j);
			}
		} else {
			int p1 = process1(s1, s2, i - 1, j);
			int p2 = process1(s1, s2, i, j - 1);
			int p3 = s1.charAt(i) == s2.charAt(j) ? (1 + process1(s1, s2, i - 1, j - 1)) : 0;
			
			return Math.max(p1, Math.max(p2, p3));
		}
	}
	
	// solution two
	public static int longestCommonSubsequence2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		
		int[][] dp = new int[s1.length()][s2.length()];
		dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
		
		for (int i = 1; i < s1.length(); i++) {
			dp[i][0] = s1.charAt(i) == s2.charAt(0) ? 1 : dp[i - 1][0];
		}
		
		
		for (int j = 1; j < s2.length(); j++) {
			dp[0][j] = s1.charAt(0) == s2.charAt(j) ? 1 : dp[0][j - 1];
		}
		
		for (int i = 1; i < s1.length(); i++) {
			for (int j = 1; j < s2.length(); j++) {
				int p1 = dp[i - 1][j];
				int p2 = dp[i][j - 1];
				int p3 = s1.charAt(i) == s2.charAt(j) ? (1 + dp[i - 1][j - 1]) : 0;
				dp[i][j] = Math.max(p1, Math.max(p2, p3));
			}
		}
		
		return dp[s1.length() - 1][s2.length() - 1];
	}
}
