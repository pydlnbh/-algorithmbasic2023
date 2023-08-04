package src.class44;

//测试链接: https://leetcode.com/problems/last-substring-in-lexicographical-order/
public class Code01_LastSubstringInLexicographicalOrder {
	// solution
	public static String lastSubString(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		
		int N = s.length();
		char[] str = s.toCharArray();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (char cha : str) {
			min = Math.min(min, cha);
			max = Math.max(max, cha);
		}
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = str[i] - min + 1;
		}
		
		DC3 dc3 = new DC3(arr, max - min + 1);
		
		return s.substring(dc3.sa[N - 1]);
	}
}
