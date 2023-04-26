package src.class20;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度 比如: str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * 
 * 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class Code01_PalindormeSubsequence {
	// solution one
	public static int longestPalindromeSubseq1(String s) {
		if (s == null || s.length() < 1) {
			return 0;
		}

		return process1(s, 0, s.length() - 1);
	}

	// recursive method one
	public static int process1(String s, int left, int right) {
		if (left == right) {
			return 1;
		}

		if (left == right - 1) {
			return s.charAt(left) == s.charAt(right) ? 2 : 1;
		}

		int p1 = process1(s, left + 1, right - 1);
		int p2 = process1(s, left + 1, right);
		int p3 = process1(s, left, right - 1);
		int p4 = s.charAt(left) != s.charAt(right) ? 0 : (2 + process1(s, left + 1, right - 1));
		
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
 	}
	
	// solution two
	public static int longestPalindromeSubseq2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		String str = reverse(s);
		return longestSubsequence(s, str);
	}
	
	// reverse method
	public static String reverse(String s) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = s.length() - 1; i >= 0; i--) {
			builder.append(s.charAt(i));
		}
		
		return builder.toString();
	}
	
	// longest subsequence
	public static int longestSubsequence(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		int[][] dp = new int[len1][len2];
		dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
		
		for (int i = 1; i < len1; i++) {
			dp[i][0] = s1.charAt(i) == s2.charAt(0) ? 1 : dp[i - 1][0];
		}
		
		for (int i = 1; i < len2; i++) {
			dp[0][i] = s1.charAt(0) == s2.charAt(i) ? 1 : dp[0][i - 1];
		}
		
		for (int i = 1; i < len1; i++) {
			for (int j = 1; j < len2; j++) {
				int p1 = dp[i - 1][j];
				int p2 = dp[i][j - 1];
				int p3 = s1.charAt(i) == s2.charAt(j) ? (1 + dp[i - 1][j - 1]) : 0;
				dp[i][j] = Math.max(Math.max(p1, p2), p3);
			}
		}
		
		return dp[len1 - 1][len2 - 1];
	}
	
	// solution three
	public static int longestPalindromeSubseq3(String s) {
		int len = s.length();
		int[][] dp = new int[len][len];
		
		dp[len - 1][len - 1] = 1;
		for (int i = 0; i < len - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
		}
		
		for (int i = len - 3; i >= 0; i--) {
			for (int j = i + 2; j < len; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
				}
			}
		}
		
		return dp[0][len - 1];
	}

	// main
	public static void main(String[] args) {
		
	}
}
