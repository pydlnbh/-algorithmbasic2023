package src.class19;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为: "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_ConvertToLetterString {
	// solution one
	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		
		return process(str, 0);
	}
	
	// recursive method
	public static int process(String str, int index) {
		if (index == str.length()) {
			return 1;
		}
		
		if (str.charAt(index) == '0') {
			return 0;
		}
		
		int ways = process(str, index + 1);
		if (index + 1 < str.length()
		&& (str.charAt(index + 1) - '0') * 10 + (str.charAt(index) - '0') < 27) {
			ways += process(str, index  + 2);
		}
		
		return ways;
	}
	
	// solution two
	public static int dp(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		
		int n = str.length();
		int[] dp = new int[n + 1];
		dp[n] = 1;
		
		for (int i = n - 1; i >= 0; i--) {
			if (str.charAt(i) != '0') {
				int ways = dp[i + 1];
				
				if (i + 1 < n 
				    && (str.charAt(i + 1) - '0') * 10 + (str.charAt(i) - '0') < 27) {
					ways += dp[i + 2];
				}
				
				dp[i] = ways;
			}
		}
		
		return dp[0];
	}
	
	// for test
	public static String randomString(int len) {
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ((int) (Math.random() * 10) + '0');
		}
		return String.valueOf(str);
	}
	
	// main
	public static void main(String[] args) {
		int len = 30;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			String str = randomString((int) (Math.random() * len));
			
			int ans1 = number(str);
			int ans2 = dp(str);
			
			if (ans1 != ans2) {
				System.out.println(str);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
}
