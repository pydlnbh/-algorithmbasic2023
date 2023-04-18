package src.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 * 
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class Code03_PrintAllPermutations {
	// solution one
	public static List<String> premutation1(String s) {
		List<String> ans = new ArrayList<>();
		
		if (s == null || s.length() == 0) {
			return ans;
		}
		
		List<Character> charList = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			charList.add(s.charAt(i));
		}
		
		String path = "";
		f1(charList, path, ans);
		
		return ans;
	}
	
	// recursive method one
	public static void f1(List<Character> charList, String path, List<String> ans) {
		if (charList.isEmpty()) {
			ans.add(path);
		} else {
			for (int i = 0; i < charList.size(); i++) {
				char cur = charList.get(i);
				charList.remove(i);
				f1(charList, path + cur, ans);
				charList.add(i, cur);
			}
		}
	}
	
	// solution two
	public static List<String> premutation2(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		
		char[] chars = s.toCharArray();
		f2(chars, 0, ans);
		
		return ans;
	}
	
	// recursive method two
	public static void f2(char[] chars, int index, List<String> ans) {
		if (index == chars.length) {
			ans.add(String.valueOf(chars));
		} else {
			for (int i = index; i < chars.length; i++) {
				swap(chars, i, index);
				f2(chars, index + 1, ans);
				swap(chars, i, index);
			}
		}
	}
	
	// swap method
	public static void swap(char[] chars, int i, int j) {
		char tmp = chars[i];
		chars[i] = chars[j];
		chars[j] = tmp;
	}
	
	// solution three no repeated character
	public static List<String> premutation3(String s) {
		List<String> ans = new ArrayList<>();
		
		if (s == null) {
			return ans;
		}
		
		char[] chars = s.toCharArray();
		f3(chars, 0, ans);
		
		return ans;
	}
	
	// recursive method three
	public static void f3(char[] chars, int index, List<String> ans) {
		if (index == chars.length) {
			ans.add(String.valueOf(chars));
		} else {
			boolean[] visited = new boolean[256];
			
			for (int i = index; i < chars.length; i++) {
				if (!visited[chars[i]]) {
					visited[chars[i]] = true;
					swap(chars, index, i);
					f3(chars, index + 1, ans);
					swap(chars, index, i);
				}
			}
		}
	}
	
	// main
	public static void main(String[] args) {
		String s = "abc";
		List<String> ans1 = premutation1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		
		System.out.println("---");
		
		List<String> ans2 = premutation2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
		
		System.out.println("---");
		
		List<String> ans3 = premutation3(s);
		for (String str : ans3) {
			System.out.println(str);
		}
	}
}
