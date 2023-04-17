package src.class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 * 
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 */
public class Code02_PrintAllSubsquences {
	// solution one
	public static List<String> subs1(String s) {
		List<String> ans = new ArrayList<>();
		process1(s, ans, 0, "");
		return ans;
	}
	
	// solution two no repeat sub
	public static List<String> subs2(String s) {
		List<String> ans = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		
		process2(s, set, 0, "");
		
		for (String str : set) {
			ans.add(str);
		}
		
		return ans;
	}
	
	// recursive method
	public static void process1(String s, List<String> ans, int index, String path) {
		if (index == s.length()) {
			ans.add(path);
			return;
		}
		
		process1(s, ans, index + 1, path);
		process1(s, ans, index + 1, path + s.charAt(index));
	}
	
	// optimize recursive method
	public static void process2(String s, HashSet<String> ans, int index, String path) {
		if (index == s.length()) {
			ans.add(path.toString());
			return;
		}
		
		process2(s, ans, index + 1, path);
		process2(s, ans, index + 1, path + s.charAt(index));
	}

	// main
	public static void main(String[] args) {
		String s = "abc";
		List<String> ans1 = subs1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		
		System.out.println("---");
		
		List<String> ans2 = subs2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
	}

}
