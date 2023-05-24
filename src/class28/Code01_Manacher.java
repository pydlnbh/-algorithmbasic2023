package src.class28;

/**
 * Manacher算法实现
 */
public class Code01_Manacher {
	// solution
	public static int manacher(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		char[] str = manacherString(s);
		
		int[] pArr = new int[str.length];
		int C = -1;
		int R = -1;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < str.length; i++) {
			pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
			
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			
			max = Math.max(max, pArr[i]);
		}
		
		return max - 1;
	}
	
	public static char[] manacherString(String s) {
		char[] charArr = s.toCharArray();
		char[] res = new char[s.length() * 2 + 1];
		int index = 0;
		
		for (int i = 1; i < res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		
		return res;
	}
	
	public static int right(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		char[] str = manacherString(s);
		int max = 0;
		
		for (int i = 0; i < str.length; i++) {
			int L = i - 1;
			int R = i + 1;
			
			while (L >= 0 && R < str.length && str[L] == str[R]) {
				L--;
				R++;
			}
			
			max = Math.max(max, R - L - 1);
		}
		
		return max / 2;
	}
	
	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] str = new char[size + 1];
		
		for (int i = 0; i < str.length; i++) {
			str[i] = (char) ((int)(Math.random() * possibilities) + 'a');
		}
		
		return String.valueOf(str);
	}

	// main
	public static void main(String[] args) {
		int possibilities = 5;
		int size = 20;
		int testTimes = 10000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, size);
			
			int ans1 = manacher(str);
			int ans2 = right(str);
			
			if (ans1 != ans2) {
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}

}
