package src.class27;

/**
 * 判断str1和str2是否互为旋转字符串
 */
public class Code03_IsRotation {
	// solution
	public static boolean isRotation(String str1, String str2) {
		if (str1 == null || str1.length() == 0 || str1.length() != str2.length()) {
			return false;
		}
		
	    String s = str2 + str2;
	    
	    return getIndexOf(s, str1) != -1;
	}
	
	public static int getIndexOf(String str1, String str2) {
		if (str1 == null || str2 == null || str1.length() < str2.length()) {
			return -1;
		}
		
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		
		int[] next = getNextArray(s2);
		int x = 0, y = 0;
		
		while (x < str1.length() && y < str2.length()) {
			if (s1[x] == s2[y]) {
				x++;
				y++;
			} else if (next[y] == -1) {
				x++;
			} else {
				y = next[y];
			}
		}
		
		return y == s2.length ? x - y : -1;
	}
	
	public static int[] getNextArray(char[] str) {
		if (str.length == 1) {
			return new int[] {-1};
		}
		
		int[] next = new int[str.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		
		while (i < next.length) {
			if (str[i - 1] == str[cn]) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		
		return next;
	}
	
	// main
	public static void main(String[] args) {
		// 互旋字符串, 不是回文字符串
		String str1 = "pyd";
		String str2 = "ydp";
		
		System.out.println(isRotation(str1, str2));
//		String str1 = "yunzuocheng";
//		String str2 = "zuochengyun";
//		System.out.println(isRotation(str1, str2));
	}

}
