package src.class28;

/**
 * 给定一个字符串str，只能在str的后面添加字符，想让str整体变成回文串，返回至少要添加几个字符
 */
public class Code02_AddShortestEnd {
	// solution
	public static String solution(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		
		char[] str = manacherString(s);
		int[] pArr = new int[str.length];
		int R = -1;
		int C = -1;
		int end = -1;
		
		for (int i = 0; i < str.length; i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
		
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
				
				if (i + pArr[i] > R) {
					R = i + pArr[i];
					C = i;
				}
				
				if (R == str.length) {
					end = pArr[i];
					break;
				}
			}
		}
		
		char[] res = new char[s.length() - end + 1];
		for (int i = 0; i < res.length; i++) {
			res[res.length - 1 - i] = str[i * 2 + 1];
		}
		
		return String.valueOf(res);
	}
	
	public static char[] manacherString(String s) {
		char[] str = s.toCharArray();
		char[] ans = new char[s.length() * 2 + 1];
		int index = 0;
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (i & 1) == 0 ? '#' : str[index++];
		}
		
		return ans;
	}
	
	// main
	public static void main(String[] args) {
		String str = "ab1221";
		System.out.println(solution(str));
	}

}
