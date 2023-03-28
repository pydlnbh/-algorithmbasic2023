package src.class13;

import java.util.ArrayList;

import src.Solution;
import src.Solution.TreeNode;

// 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
public class Code01_MaxSubBSTHead {
	// solution one
	public static TreeNode maxSubBSTHead1(TreeNode head) {
		if (head == null) {
			return null;
		}
		if (getBSTSize(head) != 0) {
			return head;
		}
		TreeNode leftAns = maxSubBSTHead1(head.left);
		TreeNode rightAns = maxSubBSTHead1(head.right);
		return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
	}
	
	// solution one
	public static int getBSTSize(TreeNode head) {
		if (head == null) {
			return 0;
		}
		ArrayList<TreeNode> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}

	// solution one
	public static void in(TreeNode head, ArrayList<TreeNode> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}
	
	// solution two
	public static TreeNode maxSubBSTHead2(TreeNode head) {
		if (head == null) {
			return null;
		}
		return process(head).ans;
	}
	
	// extra class
	public static class Info1 {
		public int min;
		public int max;
		public int maxBSTSize;
		public TreeNode ans;
		
		public Info1(int mi, int ma, int maxS, TreeNode ansNode) {
			min = mi;
			max = ma;
			maxBSTSize = maxS;
			ans = ansNode;
		}
	}
	
	// recursive method two
	public static Info1 process(TreeNode head) {
		if (head == null) {
			return null;
		}
		
		Info1 leftInfo = process(head.left);
		Info1 rightInfo = process(head.right);
		
		int maxBSTSize = 0;
		TreeNode ans = null;
		int min = head.value;
		int max = head.value;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			maxBSTSize = leftInfo.maxBSTSize;
			ans = leftInfo.ans;
		}
		
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			if (maxBSTSize < rightInfo.maxBSTSize) {
				maxBSTSize = rightInfo.maxBSTSize;
				ans = rightInfo.ans;
			}
		}
		
		boolean leftBST = leftInfo == null ? true : leftInfo.ans == head.left && leftInfo.max < head.value;
		boolean rightBST = rightInfo == null ? true : rightInfo.ans == head.right && rightInfo.min > head.value;
		
		if (leftBST && rightBST) {
			ans = head;
			int leftSize = leftInfo == null ? 0 : leftInfo.maxBSTSize;
			int rightSize = rightInfo == null ? 0 : rightInfo.maxBSTSize;
			maxBSTSize = leftSize + rightSize + 1;
		}
		
		return new Info1(min, max, maxBSTSize, ans);
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
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000;
		
		System.out.println("start");
		
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomTreeNode(maxLevel, maxValue);
			
			TreeNode ans1 = maxSubBSTHead1(head);
			TreeNode ans2 = maxSubBSTHead2(head);
			
			if (ans1 != ans2) {
				Solution.printTree(head);
				System.out.println("---");
				System.out.println("ans1 = " + ans1.value + ", ans2 = " + ans2.value);
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
