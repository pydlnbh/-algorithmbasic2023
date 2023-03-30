package src.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

// 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中字典序最小的结果
public class Code04_LowestLexicography {
	// solution one
	public static String lowestString1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		
		TreeSet<String> ans = process(strs);
		return ans.first();
	}
	
	// recursive method one
	public static TreeSet<String> process(String[] strs) {
		TreeSet<String> ans = new TreeSet<>();
		if (strs.length == 0) {
			ans.add("");
			return ans;
		}
		
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			String[] nextStr = removeIndexString(strs, i);
			TreeSet<String> next = process(nextStr);
			
			for (String cur : next) {
				ans.add(str + cur);
			}
		}
		
		return ans;
	}
	
	// remove method one
	public static String[] removeIndexString(String[] strs, int index) {
		String[] ans = new String[strs.length - 1];
		int ansIndex = 0;
		
		for (int i = 0; i < strs.length; i++) {
			if (i != index) {
				ans[ansIndex++] = strs[i];
			}
		}
		
		return ans;
	}
	
	// help class
	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}
	
	// solution two
	public static String lowestString2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		
		Arrays.sort(strs, new MyComparator());
		
		StringBuilder sb = new StringBuilder("");
		for (String str : strs) {
			sb.append(str);
		}
		
		return sb.toString();
	}
	
	// for test
	public static String[] generateRandomArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		
		return ans;
	}
	
	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 26);
			ans[i] = Math.random() < 0.5 ? (char) (65 + value) : (char) (97 + value); 
		}
		
		return String.valueOf(ans);
	}
	
	// for test
	public static String[] copyArray(String[] arr) {
		String[] ans = new String[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			ans[i] = arr[i];
		}
		
		return ans;
	}
	
	// for test
	public static void test() {
		int arrLen = 2;
		int strLen = 2;
		int testTimes = 100;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			String[] arr = generateRandomArray(arrLen, strLen);
			String[] copy = copyArray(arr);
			
			String ans1 = lowestString1(arr);
			String ans2 = lowestString2(copy);
			
			if (!ans1.equals(ans2)) {
				for (String str : arr) {
					System.out.print(str + ",");
				}
				System.out.println();
				System.out.println("ans1 = " + ans1.toString() + ", ans2 = " + ans2.toString());
				System.out.println("Oops");
				break;
			}
		}
		
		System.out.println("end");
	}
	
	// main
	public static void main(String[] args) {
		test();
	}
}
