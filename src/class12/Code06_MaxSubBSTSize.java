package src.class12;

import java.util.ArrayList;
import java.util.List;

import src.Solution;
import src.Solution.TreeNode;

// 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
// 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree
public class Code06_MaxSubBSTSize {
	// solution one
	public static int maxSubBSTSize1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int h = getBSTSize(root);
		
		if (h != 0) {
			return h;
		}
		
		return Math.max(maxSubBSTSize1(root.left), maxSubBSTSize1(root.right));
	}
	
	// method one
	public static int getBSTSize(TreeNode head) {
		if (head == null) {
			return 0;
		}
		
		List<TreeNode> list = new ArrayList<>();
		
		in(head, list);
		
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).value <= list.get(i - 1).value) {
				return 0;
			}
		}
		
		return list.size();
	}
	
	// in-order method
	public static void in(TreeNode head, List<TreeNode> list) {
		if (head == null) {
			return;
		}
		
		in(head.left, list);
		list.add(head);
		in(head.right, list);
	}
	
	// solution two
	public static int maxSubBSTSize2(TreeNode root) {
		if (null == root) {
			return 0;
		}
		
		return process2(root).maxSubBSTSize;
	}
	
	// extra class two
	public static class Info {
		public int maxSubBSTSize;
		public int size;
		public int min;
		public int max;
		
		public Info(int _m, int _s, int _min, int _max) {
			maxSubBSTSize = _m;
			size = _s;
			min =_min;
			max = _max;
		}
	}
	
	// recursive method two
	public static Info process2(TreeNode root) {
		if (null == root) {
			return null;
		}
		
		Info leftInfo = process2(root.left);
		Info rightInfo = process2(root.right);
		
		int size = 1;
		int min = root.value;
		int max = root.value;
		
		if (leftInfo != null) {
			size += leftInfo.size;
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
		}
		
		if (rightInfo != null) {
			size += rightInfo.size;
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
		}
		
		int p1 = -1;
		if (leftInfo != null) {
			p1 = leftInfo.maxSubBSTSize;
		}
		
		int p2 = -1;
		if (rightInfo != null) {
			p2 = rightInfo.maxSubBSTSize;
		}
		
		int p3 = -1;
		boolean leftBST = leftInfo == null ? true : leftInfo.maxSubBSTSize == leftInfo.size;
		boolean rightBST = rightInfo == null ? true : rightInfo.maxSubBSTSize == rightInfo.size;
		if (leftBST && rightBST) {
			boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < root.value;
			boolean rightMinMoreX = rightInfo == null ? true : rightInfo.min > root.value;
			
			if (leftMaxLessX && rightMinMoreX) {
				int leftSize = leftInfo == null ? 0 : leftInfo.size;
				int rightSize = rightInfo == null ? 0 : rightInfo.size;
				p3 = leftSize + rightSize + 1;
			}
		}
		
		int maxSubBSTSize = Math.max(Math.max(p1, p2), p3);
	
		return new Info(maxSubBSTSize, size, min, max);
	}
	
	// for test
	public static TreeNode generateRandomTreeNode(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}
	
	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		
		TreeNode head = new TreeNode((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		
		return head;
	}
	
	// for test
	public static void test() {
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomTreeNode(maxLevel, maxValue);
			
			int ans1 = maxSubBSTSize1(head);
			int ans2 = maxSubBSTSize2(head);
			
			if (ans1 != ans2) {
				Solution.printTree(head);
				System.out.println("---");
				System.out.println("ans1 = " + ans1 + ", ans2 = " + ans2);
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
