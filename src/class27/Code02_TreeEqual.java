package src.class27;

import java.util.ArrayList;

import src.Solution.TreeNode;

/**
 * 给定两棵二叉树的头节点head1和head2，返回head1中是否有某个子树的结构和head2完全一样
 * 测试链接: https://leetcode.cn/problems/subtree-of-another-tree/
 */
public class Code02_TreeEqual {
	// solution
	public static boolean isSubtree(TreeNode big, TreeNode small) {
		if (small == null) {
			return true;
		}
		
		if (big == null) {
			return false;
		}
		
		ArrayList<String> b = preSerial(big);
		ArrayList<String> s = preSerial(small);
		String[] str = new String[b.size()];
		
		for (int i = 0; i < str.length; i++) {
			str[i] = b.get(i);
		}
		
		String[] match = new String[s.size()];
		for (int i = 0; i < match.length; i++) {
			match[i] = s.get(i);
		}
		
		return getIndexOf(str, match) != -1;
	}
	
	public static ArrayList<String> preSerial(TreeNode big) {
		ArrayList<String> ans = new ArrayList<>();
		pres(big, ans);
		return ans;
	}
	
	public static void pres(TreeNode big, ArrayList<String> ans) {
		if (big == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(big.value));
			pres(big.left, ans);
			pres(big.right, ans);	
		}
	}
	
	public static int getIndexOf(String[] str1, String[] str2) {
		if (str1 == null || str2 == null || str1.length < 1 || str1.length < str2.length) {
			return -1;
		}
		
		int[] next = nextArray(str2);
		int x = 0, y = 0;
		while (x < str1.length && y < str2.length) {
			if (isEqual(str1[x], str2[y])) {
				x++;
				y++;
			} else if (next[y] == -1) {
				x++;
			} else {
				y = next[y];
			}
		}
		
		return y == str2.length ? x - y : -1;
	}
	
	public static int[] nextArray(String[] str) {
		if (str.length == 1) {
			return new int[] {-1};
		}
		
		int[] next = new int[str.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		
		while (i < str.length) {
			if (isEqual(str[i - 1],str[cn])) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0; 
			}
		}
		
		return next;
	}
	
	public static boolean isEqual(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		} else {
			if (s1 == null || s2 == null) {
				return false;
			} else {
				return s1.equals(s2);
			}
		}
	}
}
